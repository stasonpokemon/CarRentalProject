package services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Car;
import pojo.Client;
import pojo.ClientPassport;
import pojo.Order;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientService {

    private static ClientService clientService = null;

    public static ClientService getClientService() {
        if (clientService == null) {
            clientService = new ClientService();
        }
        return clientService;
    }


    public List<Client> findAllClients() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clients = session.createQuery("FROM " + Client.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return clients;
    }

    public Client findClientById(int clientId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client client = session.get(Client.class, clientId);
        transaction.commit();
        session.close();
        return client;
    }

    public List<Car> findAllCars() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM " + Car.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return cars;
    }

    public Car findCarById(int carId){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Car car = session.get(Car.class, carId);
        transaction.commit();
        session.close();
        return car;
    }

    public void clientRegistration(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }


    public void addPassportToTheClient(int clientId, ClientPassport passport){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client client = session.get(Client.class, clientId);
        client.setPassport(passport);
        session.update(client);
        transaction.commit();
        session.close();
    }

    public void addOrder(Order order){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }
}
