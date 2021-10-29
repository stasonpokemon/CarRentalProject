package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Admin;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class AdminDao implements DaoInterface<Admin> {

    private static AdminDao adminDao;

    public static AdminDao getAdminDao() {
        if (adminDao == null) {
            adminDao = new AdminDao();
        }
        return adminDao;
    }

    public void save(Admin admin) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(admin);
        transaction.commit();
        session.close();
    }

    public void update(Admin admin) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(admin);
        transaction.commit();
        session.close();
    }

    public void delete(Admin admin) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(admin);
        transaction.commit();
        session.close();
    }

    public List<Admin> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Admin> admins = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + Admin.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return admins;
    }
}
