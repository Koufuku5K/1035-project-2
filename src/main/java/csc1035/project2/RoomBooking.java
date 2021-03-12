package csc1035.project2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class RoomBooking {

    public static void main(String[] args) {
        roomList();
    }

    public static void roomList() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query roomList = session.createQuery("from Room");
        List rooms = roomList.list();
        session.getTransaction().commit();
        session.close();

        System.out.println(rooms);
    }

}
