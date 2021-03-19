package csc1035.project2;

import java.text.SimpleDateFormat;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Timetable {
    private static final Scanner input = new Scanner(System.in);

    public static void selectQuery(){
        System.out.println("What would you like to see");
        System.out.println("1)Student Timetable");
        System.out.println("2)Staff Timetable");
        System.out.println("3)Module Timetable");
        System.out.println("4)Room Time table");
        System.out.println("5)List of students on Module");
        System.out.println("6)List of staff on Module");
        String choice = input.nextLine();

        switch (choice) {
            case "1" -> studentTimetable();
            case "2" -> staffTimetable();
            case "3" -> moduleTimetable();
            case "4" -> roomTimetable();
            case "5" -> takesModule();
            case "6" -> teachesModule();
            default -> {
                System.out.println("Invalid Choice");
                selectQuery();
            }
        }
    }

    public static void studentTimetable(){
        List<Booking> classesEnrolledIn = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        System.out.println("Please enter the student ID: ");
        String studentID = input.nextLine();
        Students student = s.get(Students.class, studentID);
        for (Module m: student.getModules()) {
            Query query = s.createQuery("FROM Booking b WHERE b.moduleID = :moduleID");
            query.setParameter("moduleID", m);
            classesEnrolledIn.addAll(query.list());
        }

        for (Booking b: classesEnrolledIn) {
            // Extracts the bit of the date from the Calendar object we want, and format it
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateToParse = b.getDate().getTime();
            String date = dateFormat.format(dateToParse);

            // Extracts the bit of the time from the Calendar object we want, and format it
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date timeToParse = b.getStartTime().getTime();
            String time = timeFormat.format(timeToParse);

            System.out.println("Session");
            System.out.println("Room: " + (b.getRoomNumber()).getRoomNumber());
            System.out.println("Module: " + (b.getModuleID()).getModuleID());
            System.out.println("Social Distancing: " + b.getIsSociallyDistant());
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Duration: " + b.getDuration() + "\n");
        }
    }

    public static void staffTimetable(){
        List<Booking> modulesTaught = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        System.out.println("Please enter the staff ID: ");
        String staffID = input.nextLine();
        Staff staff = s.get(Staff.class, staffID);
        for (Module m: staff.getModules()) {
            Query query = s.createQuery("FROM Booking b WHERE b.moduleID = :moduleID");
            query.setParameter("moduleID", m);
            modulesTaught.addAll(query.list());
        }

        for (Booking b: modulesTaught) {
            // Extracts the bit of the date from the Calendar object we want, and format it
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateToParse = b.getDate().getTime();
            String date = dateFormat.format(dateToParse);

            // Extracts the bit of the time from the Calendar object we want, and format it
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date timeToParse = b.getStartTime().getTime();
            String time = timeFormat.format(timeToParse);

            System.out.println("Session");
            System.out.println("Room: " + (b.getRoomNumber()).getRoomNumber());
            System.out.println("Module: " + (b.getModuleID()).getModuleID());
            System.out.println("Social Distancing: " + b.getIsSociallyDistant());
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Duration: " + b.getDuration() + "\n");
        }
    }

    public static Query moduleTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        System.out.println("Please enter the module ID: ");
        String moduleID = input.nextLine();
        Query query = s.createQuery("SELECT b.date, b.startTime, b.roomNumber, m.moduleID, m.moduleName, b.duration FROM Module m INNER JOIN Booking b ON m.moduleID = b.moduleID WHERE m.moduleID =" + moduleID);
        return query;
    }

    public static Query roomTimetable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        System.out.println("Please enter the room number: ");
        String roomNumber = input.nextLine();
        Query query = s.createQuery("SELECT b.date, b.startTime, b.roomNumber, m.moduleID, m.moduleName, b.duration FROM Module m INNER JOIN Booking b ON m.moduleID=b.moduleID WHERE b.roomNumber = Variable" + roomNumber);
        return query;
    }

    public static void takesModule(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        System.out.println("Please enter the module ID: ");
        String moduleID = input.nextLine();
        Module module = s.get(Module.class, moduleID);
        for (Students stud: module.getStudents()) {
            System.out.println(stud);
        }
    }

    public static void teachesModule(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        System.out.println("Please enter the module ID: ");
        String moduleID = input.nextLine();
        Module module = s.get(Module.class, moduleID);
        for (Staff staff: module.getStaff()) {
            System.out.println(staff);
        }
    }

    public static void main(String[] args) {
        selectQuery();
    }
}
