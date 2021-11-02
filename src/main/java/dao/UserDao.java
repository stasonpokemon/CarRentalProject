package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.User;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDao implements DaoInterface<User> {

    private static UserDao userDao;

    public static UserDao getClientDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public List<User> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + User.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return users;
    }

}
