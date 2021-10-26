package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Car;
import utils.HibernateSessionFactoryUtil;

import java.util.List;


public class CarDao implements DaoInterface<Car> {
    private static CarDao carDao;

    public static CarDao getCarDao() {
        if (carDao == null) {
            carDao = new CarDao();
        }
        return carDao;
    }

    public void save(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }

    public void update(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(car);
        transaction.commit();
        session.close();
    }

    public void delete(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public List<Car> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Car> cars = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + Car.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return cars;
    }
}
