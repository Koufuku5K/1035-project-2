package csc1035.project2;
import csc1035.project2.HibernateUtil;
import csc1035.project2.Room;
import csc1035.project2.Module;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.Scanner;

public class DataIO {

    private static Scanner s = new Scanner(System.in);
    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void createRoom(){


        System.out.println("Please enter room number:");
        String room = s.nextLine();
        System.out.println("Please enter room type:");
        String type = s.nextLine();
        System.out.println("Please enter room capacity:");
        int capacity = Integer.parseInt(s.nextLine());
        System.out.println("Please enter room socially distanced capacity:");
        int socialCapacity = Integer.parseInt(s.nextLine());

        try {
            session = HibernateUtil.getSessionFactory().openSession();
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

    public void createModule(){


        System.out.println("Please enter the module ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the module name:");
        String name = s.nextLine();
        System.out.println("Please enter the module credits:");
        s.nextLine();
        int credits = s.nextInt();
        System.out.println("Please enter the weeks the module will last for:");
        s.nextLine();
        int weeks = s.nextInt();
        System.out.println("Please enter the number of lectures per week:");
        s.nextLine();
        int numLectures = s.nextInt();
        System.out.println("Please enter the length of lectures:");
        s.nextLine();
        double lectureLength = s.nextDouble();
        System.out.println("Please enter the number of practicals per week:");
        s.nextLine();
        int numPracticals = s.nextInt();
        System.out.println("Please enter the length of practicals:");
        s.nextLine();
        double practicalLength = s.nextDouble();
        System.out.println("Please enter the number of students enrolled on this module");
        s.nextLine();
        int numEnrolled = s.nextInt();

        Module m = new Module();

    }

    public void createStaff(){

        String temp = "temp";
        System.out.println("Please enter the staff members ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the staff members first name:");
        String firstName = s.nextLine();
        System.out.println("Please enter the staff members second:");
        String lastName = s.nextLine();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
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

    public void createStudent(){

        String temp = "temp";
        System.out.println("Please enter the students ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the students first name:");
        String firstName = s.nextLine();
        System.out.println("Please enter the students second:");
        String lastName = s.nextLine();


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Student S = new Student();
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
        createRoom();
    }
}
