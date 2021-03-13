package csc1035.project2;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.xml.bind.SchemaOutputResolver;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class RoomBooking {
    public static Scanner s = new Scanner(System.in);

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
/*
            switch (option) {
                case "0" -> ;
                case "1" -> ;
                case "2" -> ;
                case "3" -> ;
                case "4" -> chooseField();
                case "5" -> flag = false;
                default -> System.out.println("Please enter a menu option");
            }

 */
        }
    }

    private static void chooseField() {
        System.out.println("Please choose a field to update");
        System.out.println(" 0 - Room Type");
        System.out.println(" 1 - Max Capacity");
        System.out.println(" 2 - Social Distant Capacity");
        System.out.println("Enter option (0-2):");
        String option = s.nextLine();
        switch (option) {
            /*
            case "0" -> updateRoomType;
            case "1" -> updateMaxCapacity;
            case "2" -> updateDistantCapacity;
            default -> System.out.println("Please enter a menu option");

             */
        }
    }

    private static void updateRoomType() {
        String room = chooseRoom();
        
    }

    private static String chooseRoom() {
        boolean flag = true;
        String room = null;

        System.out.println("Please choose a Room to update");
        /*
        roomList();

         */

        while (flag) {
            System.out.println("Please enter the ID of the Room to update");
            room = s.nextLine();
            flag = !validID(room);
        }
        return room;
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

