package csc1035.project2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class RoomBooking {

    public static void main(String[] args) {
        roomList();
        confirmation();
    }

    public static void roomList() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query roomList = session.createQuery("from Room");
        List<Room> rooms = (List<Room>) roomList.list();
        session.getTransaction().commit();
        session.close();

        for (Room r : rooms)
            System.out.println(r.getRoomNumber());
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

}
