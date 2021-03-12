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
        List<Room> rooms = (List<Room>) roomList.list();
        session.getTransaction().commit();
        session.close();

        for (Room r : rooms)
            System.out.println(r.getRoomNumber());
    }

}
