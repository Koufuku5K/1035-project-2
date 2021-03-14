package csc1035.project2;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;

public class RoomBooking {

    public static void roomList() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query roomList = session.createQuery("FROM Room");
        List<Room> rooms = (List<Room>) roomList.list();
        session.getTransaction().commit();
        session.close();

        for (Room r : rooms)
            System.out.println(r.getRoomNumber());
    }

    public static void cancel() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query bookingList = session.createQuery("FROM Booking");
        List<Booking> bookings = (List<Booking>) bookingList.list();
        session.getTransaction().commit();
        session.close();

        for (Booking b : bookings) {
            System.out.println("Booking ID: " + b.getBookingID());
            System.out.println("User ID: " + b.getUserID());
            System.out.println("Room Number: " + b.getRoomNumber());
            System.out.println("\n");
        }

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the booking ID that you want to cancel: ");
        int userChoice = s.nextInt();

        try {
            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();

            Booking booking = session1.get(Booking.class, userChoice);
            session1.delete(booking);
            session1.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void confirmation() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "SELECT B.bookingID, B.roomNumber, B.userID, B.date FROM Booking B, Room R where B.roomNumber = R.roomNumber";
        Query confirmation = session.createQuery(hql);
        List<Booking> booking = (List<Booking>)confirmation.list();
        session.getTransaction().commit();
        session.close();

        for (Booking b : booking) {
            System.out.println("Your Booking Confirmation:\n" + "Booking ID: " + b.getBookingID());
            System.out.println("Booking details:\n" + "User ID: " + b.getUserID());
            System.out.println("Room Number: " + b.getRoomNumber());
            System.out.println("Date: " + b.getDate());
        }
    }

    public static void timetable() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter room number: ");
        double roomNumber = s.nextDouble();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM Booking B WHERE B.roomNumber = :roomNumber GROUP BY B.date";
        Query timetable = session.createQuery(hql);
        timetable.setParameter("roomNumber", roomNumber);
        List<Booking> bookings = (List<Booking>)timetable.list();
        session.getTransaction().commit();
        session.close();

        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

}
