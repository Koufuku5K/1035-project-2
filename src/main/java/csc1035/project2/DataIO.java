package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.Scanner;

public class DataIO {

    private static final Scanner s = new Scanner(System.in);


    public static void createRoom(){

        System.out.println("Please enter room number:");
        String room = s.nextLine();
        System.out.println("Please enter room type:");
        String type = s.nextLine();
        System.out.println("Please enter room capacity:");
        int capacity = Integer.parseInt(s.nextLine());
        System.out.println("Please enter room socially distanced capacity:");
        int socialCapacity = Integer.parseInt(s.nextLine());

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            Room r = new Room();
            r.setRoomNumber(room);
            r.setRoomType(type);
            r.setSocialDistanceCapacity(socialCapacity);
            r.setMaxCapacity(capacity);

            session.save(r);
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

    public static void createModule(){

        System.out.println("Please enter the module ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the module name:");
        String name = s.nextLine();
        System.out.println("Please enter the module credits:");
        int credits = Integer.parseInt(s.nextLine());
        System.out.println("Please enter the weeks the module will last for:");
        int weeks = Integer.parseInt(s.nextLine());
        System.out.println("Please enter the number of lectures per week:");
        int numLectures = Integer.parseInt(s.nextLine());
        System.out.println("Please enter the length of lectures:");
        double lectureLength = Double.parseDouble(s.nextLine());
        System.out.println("Please enter the number of practicals per week:");
        int numPracticals = Integer.parseInt(s.nextLine());
        System.out.println("Please enter the length of practicals:");
        double practicalLength = Double.parseDouble(s.nextLine());
        System.out.println("Please enter the number of students enrolled on this module");
        int numEnrolled = Integer.parseInt(s.nextLine());

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();

            Module m = new Module();
            m.setModuleID(ID);
            m.setModuleName(name);
            m.setWeeks(weeks);
            m.setCredits(credits);
            m.setNumLectures(numLectures);
            m.setLectureLength(lectureLength);
            m.setNumPracticals(numPracticals);
            m.setPracticalLength(practicalLength);
            m.setNumEnrolled(numEnrolled);
            session.save(m);
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

    public static void createStaff(){

        System.out.println("Please enter the staff members ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the staff members first name:");
        String firstName = s.nextLine();
        System.out.println("Please enter the staff members second:");
        String lastName = s.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Staff S = new Staff();
            S.setUserID(ID);
            S.setFirstName(firstName);
            S.setLastName(lastName);

            session.save(S);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void createStudent(){

        String temp = "temp";
        System.out.println("Please enter the students ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the students first name:");
        String firstName = s.nextLine();
        System.out.println("Please enter the students second:");
        String lastName = s.nextLine();


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            Students S = new Students();
            S.setUserID(ID);
            S.setFirstName(firstName);
            S.setLastName(lastName);

            session.save(S);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void main(String[] args) {

        System.out.println("Which table would you like to add data to:");
        System.out.println("1)Room");
        System.out.println("2)Module");
        System.out.println("3)Staff");
        System.out.println("4)Student");
        System.out.println("Please enter a number from 1 to 4:");
        String choice = s.nextLine();
        switch (choice) {
            case "1" -> createRoom();
            case "2" -> createModule();
            case "3" -> createStaff();
            case "4" -> createStudent();
            default -> System.out.println("invalid choice");
        }
    }







}




