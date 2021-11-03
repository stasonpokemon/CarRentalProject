package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.ClientPassport;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientPassportDao implements Dao<ClientPassport> {

    private static ClientPassportDao passportDao;

    public static ClientPassportDao getPassportDao() {
        if (passportDao == null) {
            passportDao = new ClientPassportDao();
        }
        return passportDao;
    }

    public void save(ClientPassport passport) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passport);
        transaction.commit();
        session.close();
    }

    @Override
    public ClientPassport read(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ClientPassport passport = session.get(ClientPassport.class, id);
        transaction.commit();
        session.close();
        return passport;
    }

    public void update(ClientPassport passport) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(passport);
        transaction.commit();
        session.close();
    }

    public void delete(ClientPassport passport) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(passport);
        transaction.commit();
        session.close();
    }

    public List<ClientPassport> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ClientPassport> passports = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + ClientPassport.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return passports;
    }
}
