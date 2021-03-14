package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.xml.bind.SchemaOutputResolver;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class RoomBooking {
    public static Scanner s = new Scanner(System.in);
    public static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
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
                case "0" -> ;
                case "1" -> ;
                case "2" -> ;
                case "3" -> ;
                case "4" -> updateRoom();
                case "5" -> flag = false;
                default -> System.out.println("Please enter a menu option");
            }
        }
    }



    private static void updateRoom() {
        String room = chooseRoom();
        String field = chooseField();
        System.out.println("Please enter the new data");
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

    private static String chooseRoom() {
        boolean flag = true;
        String room = null;

        System.out.println("Please choose a Room to update");
        roomList();

        while (flag) {
            System.out.println("Please enter the ID of the Room to update");
            room = s.nextLine();
            flag = !validID(room);
        }
        return room;
    }

    private static String chooseField() {
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

    private static boolean validID(String roomNumber) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query query = s.createQuery("FROM Room r WHERE r.roomNumber = :id");
        query.setParameter("id", roomNumber);
        s.getTransaction().commit();

        List results = query.getResultList();
        return results.size() != 0;
    }
}

