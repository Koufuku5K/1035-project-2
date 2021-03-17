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

/**
 * RoomBooking provides a simple interface to perform operations around rooms and bookings.
 * @author William Moses
 * @author Alfie Fields
 * @author Joseph Burley
 * @author Kyle Anderson
 */
public class RoomBooking {
    public static Scanner s = new Scanner(System.in);
    public static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void main(String[] args) {
        menu();
    }

    /**
     * Provides user with a simple menu.
     */
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

    /**
     * Gets user input to make a booking, puts rooms through filters to find those available.
     * If a room is available then user can book one, the booking is saved to the database.
     */
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
        // Gets users String input and turns it into a Calendar object
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date.setTime(sdf1.parse(tempDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Please enter the time (HH:mm):");
        String tempTime = s.nextLine();
        // Gets users String input and turns it into a Calendar object
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

        // Get a list of all the rooms and all the bookings stored in the database
        List<Room> availableRooms = new ArrayList<>(getRooms());
        List<Booking> allBookings = new ArrayList<>(getBookings());

        // Put lists through filters finding only the ones that fit the criteria given
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

                // Create a new booking and assign all its variables
                Booking b = new Booking();
                // For foreign keys have to fetch the actual object instead of a string ID
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

    /**
     * This is a method that gets all rooms and puts them in a list.
     * @return list of all rooms in the database.
     */
    public static List<Room> getRooms() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Create query to read entire table
        Query roomList = session.createQuery("FROM Room");
        // Create a list with all read records
        List<Room> rooms = (List<Room>) roomList.list();
        session.getTransaction().commit();
        session.close();

        return rooms;
    }

    /**
     * This is a method that gets all bookings and puts them in a list.
     * @return list of all bookings in the database.
     */
    public static List<Booking> getBookings() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Create query to read entire table
        Query bookingList = session.createQuery("FROM Booking");
        // Create a list with all read records
        List<Booking> bookings = (List<Booking>) bookingList.list();
        session.getTransaction().commit();
        session.close();

        return bookings;
    }

    /**
     * This is a method for outputting a given list of rooms.
     * @param rooms list of rooms to be output.
     */
    public static void listRooms(List<Room> rooms) {
        for (Room r : rooms)
            System.out.println(r);
    }

    /**
     * This is a method that allows the user to cancel their booking by entering the
     * booking ID of the booking that they want to cancel.
     */
    public static void cancel() {
        // Get a list of all the bookings
        List<Booking> bookings = getBookings();

        // Loop through list outputting records in a useful way
        for (Booking b : bookings) {
            System.out.println("Booking ID: " + b.getBookingID());
            System.out.println("User ID: " + b.getUserID());
            System.out.println("Room Number: " + b.getRoomNumber());
            System.out.println("\n");
        }

        // User inputs the ID of the booking they wish to cancel
        System.out.println("Enter the booking ID that you want to cancel: ");
        int userChoice = Integer.parseInt(s.nextLine());

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Get relevant booking and delete the record
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

    /**
     * Takes a list of rooms and removes any not of the correct type.
     * @param rooms list of room objects.
     * @param type the type of room the user is looking for.
     * @return list of rooms that have the correct type.
     */
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

    /**
     * Takes a list of rooms and removes all the ones which are not big enough.
     * @param rooms list of room objects.
     * @param groupSize the number of people to use the room.
     * @param socialDistancing is the booking under social distance conditions.
     * @return list of rooms that can fit the number of people.
     */
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

    /**
     * Takes a list of bookings and removes all with a date other than the one we are booking for.
     * @param bookings lit of booking objects.
     * @param date the date the user wants to book for.
     * @return list of bookings all on the desired date.
     */
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

    /**
     * Looks at all the bookings and checks if a room is booked all ready for when the user wants.
     * @param rooms list of room objects.
     * @param bookings list of booking objects.
     * @param ourStartTime the start time of the users booking.
     * @param duration how long the users booking is going to last.
     * @param id the ID of the person the booking is for.
     * @return list of rooms that are available at that time.
     */
    public static List<Room> timeFilter(List<Room> rooms, List<Booking> bookings, Calendar ourStartTime,
                                  int duration, String id) {
        List<Room> toRemove = new ArrayList<>();
        // Work out when the users booking will end
        Calendar ourEndTime = Calendar.getInstance();
        ourEndTime.setTime(ourStartTime.getTime());
        ourEndTime.add(Calendar.HOUR_OF_DAY, duration);

        // Loop through all bookings that were on the correct date
        for (Booking b: bookings) {
            // Get the bookings start and end time
            Calendar startTime = b.getStartTime();
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(startTime.getTime());
            endTime.add(Calendar.HOUR_OF_DAY, duration);
            // Check if users booking will overlap with an existing one
            if ((endTime.after(ourEndTime) || endTime.equals(ourEndTime)) &&
                    (startTime.before(ourEndTime) || startTime.equals(ourEndTime))) {
                // If there is overlap, check if the booking has the same userID
                // If it is the same then the booking can't be made as that person
                // is already booked for another room.
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

    /**
     * Takes a room and a field, and updates it with given data.
     */
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

    /**
     * Choose a room from a given list.
     * @param rooms list o room objects to choose from.
     * @return the primary key of the chosen room.
     */
    public static String chooseRoom(List<Room> rooms) {
        String roomNumber;

        System.out.println("Please choose a Room");
        listRooms(rooms);
        // Loops until user enters the id of a room in the list
        do {
            System.out.println("Please enter the ID of the room you would like:");
            roomNumber = s.nextLine();
        } while (validID(roomNumber, rooms));
        return roomNumber;
    }

    /**
     * The user has to choose the field they wish to update.
     * @return the field the user has chosen.
     */
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

    /**
     * Checks a given ID is in a list of rooms.
     * @param roomNumber ID to check exists.
     * @param rooms list of rooms to check against.
     * @return boolean value of if the ID exists in the given list.
     */
    public static boolean validID(String roomNumber, List<Room> rooms) {
        for (Room r: rooms) {
            if (r.getRoomNumber().equals(roomNumber)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get a user object based on a given ID.
     * @param ID ID of the user you are looking for.
     * @return the user object you were looking for.
     */
    public static User getUser(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, ID);
        session.close();
        return user;
    }

    /**
     * Get a module object based on a given ID.
     * @param ID ID of the module you are looking for.
     * @return the module object you were looking for.
     */
    public static Module getModule(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Module module = session.get(Module.class, ID);
        session.close();
        return module;
    }

    /**
     * Get a room object based on a given ID.
     * @param ID ID of the room you are looking for.
     * @return the room object you were looking for.
     */
    public static Room getRoom(String ID) {
        session = HibernateUtil.getSessionFactory().openSession();
        Room room = session.get(Room.class, ID);
        session.close();
        return room;
    }


    /**
     * This is a method that returns the booking confirmation of the booking that the
     * user made. It prints out some of the important information on the booking
     * confirmation.
     */
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

    /**
     * This method produces the timetable for a room.
     */
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
