package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Client;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientDao implements DaoInterface<Client> {

    private static ClientDao clientDao;

    public static ClientDao getClientDao() {
        if (clientDao == null) {
            clientDao = new ClientDao();
        }
        return clientDao;
    }

    public void save(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    public void update(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(client);
        transaction.commit();
        session.close();
    }

    public void delete(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(client);
        transaction.commit();
        session.close();
    }

    public List<Client> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clients = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM " + Client.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return clients;
    }

}
