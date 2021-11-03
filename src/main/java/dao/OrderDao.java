package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Order;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class OrderDao implements Dao<Order> {

    private static OrderDao orderDao;

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    @Override
    public void save(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    @Override
    public Order read(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.get(Order.class, id);
        transaction.commit();
        session.close();
        return order;
    }

    @Override
    public void update(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Order> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Order> orders = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + Order.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return orders;
    }
}
