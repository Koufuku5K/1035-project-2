package csc1035.project2;

import org.hibernate.Session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.hibernate.HibernateException;
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
                               "| 5 - Get Booking Confirmation|\n" +
                               "| 6 - Exit                    |\n" +
                               "-------------------------------");
            System.out.println("Enter option (0-6):");
            String option = s.nextLine();

            switch (option) {
                case "0" -> listRooms(getRooms());
                case "1" -> booking();
                case "2" -> cancel();
                case "3" -> timetable();
                case "4" -> updateRoom();
                case "5" -> confirmation();
                case "6" -> flag = false;
                default -> System.out.println("Please enter a menu option");
            }
        }
    }

    public static void booking(){
        System.out.println("Please enter the User ID of who the booking is for:");
        String userID = s.nextLine();

        System.out.println("Please choose a room type\n" +
                " PC Cluster \n" +
                " Lecture Hall \n" +
                " Seminar Room \n" +
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

        System.out.println("Please enter the time (HH:mm):");
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

        System.out.println("Please enter the booking type:");
        String bookingType = s.nextLine();

        List<Room> availableRooms = new ArrayList<>(getRooms());
        List<Booking> allBookings = new ArrayList<>(getBookings());

        availableRooms = typeFilter(availableRooms, roomType);
        availableRooms = sizeFilter(availableRooms, numPeople, isSociallyDistant);
        allBookings = dateFilter(allBookings, date);
        availableRooms = timeFilter(availableRooms, allBookings, startTime, duration, userID);

        if (availableRooms.isEmpty()) {
            System.out.println("There are no available rooms");
        } else {
            listRooms(availableRooms);
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
                if (session != null) session.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public static List<Room> getRooms() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query roomList = session.createQuery("FROM Room");
        List<Room> rooms = (List<Room>) roomList.list();
        session.getTransaction().commit();
        session.close();

        return rooms;
    }

    public static List<Booking> getBookings() {
        session = HibernateUtil.getSessionFactory().openSession();
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

        System.out.println("Enter the booking ID that you want to cancel: ");
        int userChoice = Integer.parseInt(s.nextLine());

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            Booking booking = session.get(Booking.class, userChoice);
            session.delete(booking);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Room> typeFilter(List<Room> rooms, String type){
        List<Room> toRemove = new ArrayList<>();
        for (Room r: rooms) {
            if (!r.getRoomType().equals(type)) {
                toRemove.add(r);
            }
        }
        rooms.removeAll(toRemove);
        return rooms;
    }

    public static List<Room> sizeFilter(List<Room> rooms, int groupSize, boolean socialDistancing) {
        List<Room> toRemove = new ArrayList<>();
        for (Room r: rooms) {
            if (socialDistancing) {
                if (r.getSocialDistanceCapacity() < groupSize) {
                    toRemove.add(r);
                }
            } else {
                if (r.getMaxCapacity() < groupSize) {
                    toRemove.add(r);
                }
            }
        }
        rooms.removeAll(toRemove);
        return rooms;
    }

    public static List<Booking> dateFilter(List<Booking> bookings, Calendar date) {
        List<Booking> toRemove = new ArrayList<>();
        for (Booking b: bookings) {
            if (!b.getDate().equals(date)) {
                toRemove.add(b);
            }
        }
        bookings.removeAll(toRemove);
        return bookings;
    }

    public static List<Room> timeFilter(List<Room> rooms, List<Booking> bookings, Calendar ourStartTime,
                                  int duration, String id) {
        List<Room> toRemove = new ArrayList<>();
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
                    toRemove.addAll(getRooms());
                    break;
                }
                toRemove.add(b.getRoomNumber());
            }
        }
        rooms.removeAll(toRemove);
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
            case "0" -> field = "roomType";
            case "1" -> field = "maxCapacity";
            case "2" -> field = "socialDistantCapacity";
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
        User user = session.get(User.class, ID);
        session.close();
        return user;
    }

    public static Module getModule(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Module module = session.get(Module.class, ID);
        session.close();
        return module;
    }

    public static Room getRoom(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Room room = session.get(Room.class, ID);
        session.close();
        return room;
    }


    public static void confirmation() {
        System.out.println("Enter the room number to get the booking confirmation: ");
        String roomNumber = s.nextLine();
        Room room = getRoom(roomNumber);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM Booking B WHERE B.roomNumber = :roomNumber";
        Query confirmation = session.createQuery(hql);
        confirmation.setParameter("roomNumber", room);
        List<Booking> booking = (List<Booking>)confirmation.list();
        session.getTransaction().commit();
        session.close();

        for (Booking b : booking) {
            System.out.println("\n");
            System.out.println("Your Booking Confirmation:\n" + "Booking ID: " + b.getBookingID());
            System.out.println("Booking details:\n" + "User ID: " + b.getUserID().getuserID());
            System.out.println("Room Number: " + b.getRoomNumber().getRoomNumber());
            System.out.println("Date: " + b.getDate().get(Calendar.DAY_OF_MONTH) + "/"
                                        + b.getDate().get(Calendar.MONTH) + "/"
                                        + b.getDate().get(Calendar.YEAR));
            System.out.println("\n");
        }
    }

    public static void timetable() {
        System.out.println("Enter room number: ");
        String roomNumber = s.nextLine();
        Room room = getRoom(roomNumber);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM Booking B WHERE B.roomNumber = :roomNumber GROUP BY B.date";
        Query timetable = session.createQuery(hql);
        timetable.setParameter("roomNumber", room);
        List<Booking> bookings = (List<Booking>)timetable.list();
        session.getTransaction().commit();
        session.close();

        for (Booking b : bookings) {
            System.out.println(b);
        }
    }
}
