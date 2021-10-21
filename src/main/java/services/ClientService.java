package services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Client;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientService {

    private static ClientService clientService = null;

    public static ClientService getClientService() {
        if (clientService == null){
            clientService = new ClientService();
        }
        return clientService;
    }


    public List<Client> findAllClients(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clients = session.createQuery("FROM" +Client.class.getSimpleName()).getResultList();
        transaction.commit();
        session.close();
        return clients;
    }

    public Client findClientById(int clientId){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client client = session.get(Client.class, clientId);
        transaction.commit();
        session.close();
        return client;
    }

    public void clientRegistration(Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }
}
