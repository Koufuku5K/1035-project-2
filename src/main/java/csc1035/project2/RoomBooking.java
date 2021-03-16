package csc1035.project2;

import org.hibernate.Session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;

public class RoomBooking {
    public static Scanner s = new Scanner(System.in);
    public static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean flag = true;

        while (flag) {
            System.out.println("-------------------------------\n" +
                               "| MENU:                       |\n" +
                               "| 0 - View All Rooms          |\n" +
                               "| 1 - Book A Room             |\n" +
                               "| 2 - Cancel Booking          |\n" +
                               "| 3 - View Room Timetable     |\n" +
                               "| 4 - Update A Room           |\n" +
                               "| 5 - Exit                    |\n" +
                               "-------------------------------");
            System.out.println("Enter option (0-5):");
            String option = s.nextLine();

            switch (option) {
                case "0" -> listRooms(getRooms());
                case "1" -> booking();
                case "2" -> cancel();
                case "3" -> timetable();
                case "4" -> updateRoom();
                case "5" -> flag = false;
                default -> System.out.println("Please enter a menu option");
            }
        }
    }

    public static void booking() throws ParseException {
        System.out.println("Please enter the User ID of who the booking is for:");
        String userID = s.nextLine();

        System.out.println("Please choose a room type\n" +
                " PC Cluster \n" +
                " Lecture Hall \n" +
                " Seminar Room" +
                " Meeting Room");
        System.out.println("Please enter the room type:");
        String roomType = s.nextLine();

        System.out.println("Please enter the date (yyyy-MM-dd):");
        String tempDate = s.nextLine();
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date.setTime(sdf1.parse(tempDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Please enter the time (HH:mm");
        String tempTime = s.nextLine();
        Calendar startTime = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        try {
            startTime.setTime(sdf2.parse(tempTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Please enter the duration (number of hours e.g. 2):");
        int duration = Integer.parseInt(s.nextLine());

        System.out.println("Please enter the number of people:");
        int numPeople = Integer.parseInt(s.nextLine());

        System.out.println("Is the booking socially distanced? (y/n):");
        String tempBool = s.nextLine();
        boolean isSociallyDistant;
        isSociallyDistant = tempBool.equals("y");

        System.out.println("Please enter the module ID, or leave blank");
        String moduleID = s.nextLine();
        Module module = getModule(moduleID);

        System.out.println("Please enter the booking type:");
        String bookingType = s.nextLine();

        List<Room> availableRooms = getRooms();
        List<Booking> allBookings = getBookings();

        availableRooms = typeFilter(availableRooms, roomType);
        availableRooms = sizeFilter(availableRooms, numPeople, isSociallyDistant);
        allBookings = dateFilter(allBookings, date);
        availableRooms = timeFilter(availableRooms, allBookings, startTime, duration, userID);

        if (availableRooms.isEmpty()) {
            System.out.println("There are no available rooms");
        } else {
            listRooms(availableRooms);
        }
        String room = chooseRoom(availableRooms);

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Booking b = new Booking();
            b.setUserID(getUser(userID));
            b.setRoomNumber(getRoom(room));
            b.setModuleID(getModule(moduleID));
            b.setStartTime(startTime);
            b.setDuration(duration);
            b.setDate(date);
            b.setIsSociallyDistant(isSociallyDistant);
            b.setBookingType(bookingType);
            b.setNumPeople(numPeople);

            session.save(b);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Room> getRooms() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query roomList = session.createQuery("FROM Room");
        List<Room> rooms = (List<Room>) roomList.list();
        session.getTransaction().commit();
        session.close();

        return rooms;
    }

    public static List<Booking> getBookings() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query bookingList = session.createQuery("FROM Booking");
        List<Booking> bookings = (List<Booking>) bookingList.list();
        session.getTransaction().commit();
        session.close();

        return bookings;
    }


    public static void listRooms(List<Room> rooms) {
        for (Room r : rooms)
            System.out.println(r);
    }

    public static void cancel() {
        List<Booking> bookings = getBookings();

        for (Booking b : bookings) {
            System.out.println("Booking ID: " + b.getBookingID());
            System.out.println("User ID: " + b.getUserID());
            System.out.println("Room Number: " + b.getRoomNumber());
            System.out.println("\n");
        }

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the booking ID that you want to cancel: ");
        int userChoice = s.nextInt();

        try {
            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();

            Booking booking = session1.get(Booking.class, userChoice);
            session1.delete(booking);
            session1.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Room> typeFilter(List<Room> rooms, String type){
        for (Room r: rooms) {
            if (!r.getRoomType().equals(type)) {
                rooms.remove(r);
            }
        }
        return rooms;
    }

    public static List<Room> sizeFilter(List<Room> rooms, int groupSize, boolean socialDistancing) {
        for (Room r: rooms) {
            if (socialDistancing) {
                if (r.getSocialDistanceCapacity() < groupSize) {
                    rooms.remove(r);
                }
            } else {
                if (r.getMaxCapacity() < groupSize) {
                    rooms.remove(r);
                }
            }
        }
        return rooms;
    }

    public static List<Booking> dateFilter(List<Booking> bookings, Calendar date) {
        for (Booking b: bookings) {
            if (!b.getDate().equals(date)) {
                bookings.remove(b);
            }
        }
        return bookings;
    }

    public static List<Room> timeFilter(List<Room> rooms, List<Booking> bookings, Calendar ourStartTime,
                                  int duration, String id) {
        boolean sameUser = false;
        Calendar ourEndTime = Calendar.getInstance();
        ourEndTime.setTime(ourStartTime.getTime());
        ourEndTime.add(Calendar.HOUR_OF_DAY, duration);

        for (Booking b: bookings) {
            Calendar startTime = b.getStartTime();
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(startTime.getTime());
            endTime.add(Calendar.HOUR_OF_DAY, duration);
            if ((endTime.after(ourEndTime) || endTime.equals(ourEndTime)) &&
                    (startTime.before(ourEndTime) || startTime.equals(ourEndTime))) {
                if ((b.getUserID().getuserID()).equals(id)) {
                    rooms.clear();
                    break;
                }
                rooms.remove(b.getRoomNumber());
            }
        }
        return rooms;
    }

    public static void updateRoom() {
        String room = chooseRoom(getRooms());
        String field = chooseField();
        System.out.println("Please enter the new data:");
        String newData = s.nextLine();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Room r = (session.get(Room.class, room));
            switch (field) {
                case "roomType" -> r.setRoomType(newData);
                case "maxCapacity" -> r.setMaxCapacity(Integer.parseInt(newData));
                case "socialDistantCapacity" -> r.setSocialDistanceCapacity(Integer.parseInt(newData));
            }
            session.update(r);
            session.getTransaction().commit();
        } catch (HibernateException e){
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static String chooseRoom(List<Room> rooms) {
        String roomNumber;

        System.out.println("Please choose a Room");
        listRooms(rooms);
        do {
            System.out.println("Please enter the ID of the room you would like:");
            roomNumber = s.nextLine();
        } while (validID(roomNumber, rooms));
        return roomNumber;
    }

    public static String chooseField() {
        String field = null;
        System.out.println("Please choose a field to update");
        System.out.println(" 0 - Room Type");
        System.out.println(" 1 - Max Capacity");
        System.out.println(" 2 - Social Distant Capacity");
        System.out.println("Enter option (0-2):");
        String option = s.nextLine();
        switch (option) {
            case "0" -> {
                field = "roomType";
            }
            case "1" -> {
                field = "maxCapacity";
            }
            case "2" -> {
                field = "socialDistantCapacity";
            }
            default -> System.out.println("Please enter a menu option");
        }
        return field;
    }

    public static boolean validID(String roomNumber, List<Room> rooms) {
        for (Room r: rooms) {
            if (r.getRoomNumber().equals(roomNumber)) {
                return false;
            }
        }
        return true;
    }

    public static User getUser(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.get(User.class, ID);
        session.close();
        return user;
    }

    public static Module getModule(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Module module = (Module) session.get(Module.class, ID);
        session.close();
        return module;
    }

    public static Room getRoom(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Room room = (Room) session.get(Room.class, ID);
        session.close();
        return room;
    }


    public static void confirmation() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the room number to get the booking confirmation: ");
        double roomNumber = s.nextDouble();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "SELECT B.bookingID, B.roomNumber, B.userID, B.date FROM Booking B, Room R WHERE B.roomNumber = :roomNumber";
        Query confirmation = session.createQuery(hql);
        confirmation.setParameter("roomNumber", roomNumber);
        List<Booking> booking = (List<Booking>)confirmation.list();
        session.getTransaction().commit();
        session.close();

        for (Booking b : booking) {
            System.out.println("Your Booking Confirmation:\n" + "Booking ID: " + b.getBookingID());
            System.out.println("Booking details:\n" + "User ID: " + b.getUserID());
            System.out.println("Room Number: " + b.getRoomNumber());
            System.out.println("Date: " + b.getDate());
        }
    }

    public static void timetable() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter room number: ");
        double roomNumber = s.nextDouble();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM Booking B WHERE B.roomNumber = :roomNumber GROUP BY B.date";
        Query timetable = session.createQuery(hql);
        timetable.setParameter("roomNumber", roomNumber);
        List<Booking> bookings = (List<Booking>)timetable.list();
        session.getTransaction().commit();
        session.close();

        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

}
