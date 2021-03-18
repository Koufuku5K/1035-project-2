package csc1035.project2;
import java.util.List;
import javax.persistence.*;
import java.util.Objects;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Timetable {
    /*Todo
    Method to return timetable
    Needs to return timetable for ID of student or staff
    Method to collect data about modules that student or staff on then collect all sessions for those modules

     */
    public void returnTimetable(){
        List timetable;
    }

    public static Query selectQuery(){

        Query query = null;

        Scanner s = new Scanner(System.in);
        System.out.println("What would you like to see");
        System.out.println("1)Student Timetable");
        System.out.println("2)Staff Timetable");
        System.out.println("3)Module Timetable");
        System.out.println("4)Room Time table");
        System.out.println("5)List of students on Module");
        System.out.println("6)List of staff on Module");
        String choice = s.nextLine();

        if(choice.equals("1")){
            query = studentTimetable();
        } else if(choice.equals("2")){
            query = staffTimetable();
        } else if(choice.equals("3")){
            query = moduleTimetable();
        } else if(choice.equals("4")){
            query = roomTimetable();
        } else if(choice.equals("5")){
            query = takesModule();
        } else if(choice.equals("6")){
            query = teachesModule();
        } else{
            System.out.println("Invalid Choice");
            selectQuery();
            }

        return query;

    }

    public static Query studentTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the student ID: ");
        String studentID = input.nextLine();
        Query query = s.createQuery("SELECT b.date, b.startTime, b.roomNumber, m.moduleID, m.moduleName, b.duration FROM Student s JOIN Teach t ON s.userID=t.userID JOIN Module m ON t.moduleID=m.moduleID JOIN Booking b ON m.moduleID=b.moduleID Where s.userID =" + studentID);
        return query;
    }

    public static Query staffTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the staff ID: ");
        String staffID = input.nextLine();
        Query query = s.createQuery("SELECT b.date, b.startTime, b.roomNumber, m.moduleID, m.moduleName, b.duration FROM Staff s JOIN Teach t ON s.userID=t.userID JOIN Module m ON t.moduleID=m.moduleID JOIN Booking b ON m.moduleID=b.moduleID WHERE s.userID =" + staffID);
        return query;
    }

    public static Query moduleTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the module ID: ");
        String moduleID = input.nextLine();
        Query query = s.createQuery("SELECT b.date, b.startTime, b.roomNumber, m.moduleID, m.moduleName, b.duration FROM Module m INNER JOIN Booking b ON m.moduleID = b.moduleID WHERE m.moduleID =" + moduleID);
        return query;
    }

    public static Query roomTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the room number: ");
        String roomNumber = input.nextLine();
        Query query = s.createQuery("SELECT b.date, b.startTime, b.roomNumber, m.moduleID, m.moduleName, b.duration FROM Module m INNER JOIN Booking b ON m.moduleID=b.moduleID WHERE b.roomNumber = Variable" + roomNumber);
        return query;
    }

    public static Query takesModule(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the module ID: ");
        String moduleID = input.nextLine();
        Query query = s.createQuery("SELECT b.date, b.startTime, b.roomNumber, m.moduleID, m.moduleName, b.duration FROM Student s INNER JOIN Teach t ON s.userID=t.userID INNER JOIN Module m ON t.moduleID=m.moduleID INNER JOIN Booking b ON m.moduleID=b.moduleID Where s.userID =" + moduleID);
        return query;
    }

    public static Query teachesModule(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the module ID: ");
        String moduleID = input.nextLine();
        Query query = s.createQuery("SELECT u FROM Take t INNER JOIN Student s ON t.userID=s.userID INNER JOIN User u ON s.userID=u.userID WHERE r.moduleID = " + moduleID);
        return query;
    }


    public static void main(String[] args) {
        Query query = selectQuery();

        outputQuery(query);
    }

    public static void outputQuery(Query query){

        List list = query.list();
        System.out.println(list);



    }


}
