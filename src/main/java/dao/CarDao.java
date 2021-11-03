package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Car;
import utils.HibernateSessionFactoryUtil;

import java.util.List;


public class CarDao implements Dao<Car> {
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

    @Override
    public Car read(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Car car = session.get(Car.class, id);
        transaction.commit();
        session.close();
        return car;
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

    public List<Car> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Car> cars = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + Car.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return cars;
    }
}
