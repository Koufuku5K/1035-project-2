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

    public void searchDatabase(){
        Scanner s = new Scanner(System.in);
        System.out.println("Who would you like the timetable for:");
        System.out.println("1)A Student");
        System.out.println("2)A member of staff");
        System.out.println("3)A module");
        System.out.println("4)A Room");
        String choice = s.nextLine();
        if(choice =="1"){
            studentTimetable();
        }
        else if(choice == "2"){
            staffTimetable();
        }
        else if(choice == "3"){
            moduleTimetable();
        }
        else if(choice == "4") {
            roomTimetable();
        }
        else{
            System.out.println("Invalid Choice");
            searchDatabase();
            }
    }

    public Query studentTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the student ID: ");
        String studentID = input.nextLine();
        Query query = s.createQuery("select b.date, b.startTime, b.roomNumber, m.moduleName, b.duration from Students s where s.userID = "+studentID+"from Students s");
        return query;
    }

    public Query staffTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the staff ID: ");
        String staffID = input.nextLine();
        Query query = s.createQuery("select b.date, b.startTime, b.roomNumber, m.moduleName, b.duration from Staff s where s.userID = "+staffID+"from Staff s");
        return query;
    }

    public Query moduleTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the module ID: ");
        String moduleID = input.nextLine();
        Query query = s.createQuery("select b.date, b.startTime, b.roomNumber,m.moduleID, m.moduleName, b.duration from Module m where m.moduleID = "+moduleID+"from Module m");
        return query;
    }

    public Query roomTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the room number: ");
        String roomNumber = input.nextLine();
        Query query = s.createQuery("select b.date, b.startTime, b.roomNumber,m.moduleID, m.moduleName, b.duration from Module m where b.roomNumber = "+roomNumber+"from Module m");
        return query;
    }

    public static void main(String[] args) {
    }
}
