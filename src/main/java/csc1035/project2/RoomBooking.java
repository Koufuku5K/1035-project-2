package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class RoomBooking {
    public static Scanner s = new Scanner(System.in);
    public static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() throws ParseException {
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
                case "0" -> roomList();
                case "1" -> booking();
                case "2" -> ;
                case "3" -> ;
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

        System.out.println("Please enter the booking type:");
        String bookingType = s.nextLine();

        List<Room> availableRooms = getRooms();
        List<Booking> allBookings = getBookings();

        typeFilter(availableRooms, roomType);
        sizeFilter(availableRooms, numPeople, isSociallyDistant);
        dateFilter(allBookings, date);
        timeFilter(availableRooms, allBookings, startTime, duration, userID);

        listRooms();
    }

    public static void typeFilter(List<Room> rooms , String type){
        for (Room r: rooms) {
            if (!r.getRoomType().equals(type)) {
                rooms.remove(r);
            }
        }
    }

    public static void sizeFilter(List<Room> rooms, int groupSize, boolean socialDistancing) {
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
    }

    public static void dateFilter(List<Booking> bookings, Calendar date) {
        for (Booking b: bookings) {
            if (!b.getDate().equals(date)) {
                bookings.remove(b);
            }
        }
    }

    public static void timeFilter(List<Room> rooms, List<Booking> bookings, Calendar ourStartTime,
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
                if (userFilter(b, id)) {
                    rooms.clear();
                    break;
                }
                rooms.remove(b.getRoomNumber());
            }
        }
    }

    public static boolean userFilter(Booking b, String id) {
        boolean sameUser = false;
        if ((b.getUserID().getuserID()).equals(id)) {
            sameUser = true;
        }
        return sameUser;
    }


    public static void updateRoom() {
        String room = chooseRoom();
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

    public static String chooseRoom() {
        boolean flag = true;
        String room = null;

        System.out.println("Please choose a Room to update");
        roomList();

        while (flag) {
            System.out.println("Please enter the ID of the Room to update:");
            room = s.nextLine();
            flag = !validID(room);
        }
        return room;
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

    public static boolean validID(String roomNumber) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query query = s.createQuery("FROM Room r WHERE r.roomNumber = :id");
        query.setParameter("id", roomNumber);
        s.getTransaction().commit();

        List results = query.getResultList();
        return results.size() != 0;
    }
}

