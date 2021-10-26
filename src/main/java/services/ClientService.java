package services;

import dao.ClientDao;
import pojo.Client;
import pojo.ClientPassport;

import java.util.List;

public class ClientService {

    private ClientDao clientDao = ClientDao.getClientDao();
    private static ClientService clientService = null;

    public static ClientService getClientService() {
        if (clientService == null) {
            clientService = new ClientService();
        }
        return clientService;
    }

    /*
     * Список всех клиентов
     * */
    public List<Client> findAllClients() {
        List<Client> clients = clientDao.findAll();
        return clients;
    }

    /*
     * Поиск клиента по id
     * */
    public Client findClientById(int clientId) {
        Client searchClient = null;
        List<Client> allClients = findAllClients();
        for (Client client : allClients) {
            if (clientId == client.getId()) {
                searchClient = client;
            }
        }
        return searchClient;
    }

    /*
     * Регистрация нового клиента
     * */
    public void clientRegistration(Client client) {
        clientDao.save(client);
    }

    /*
     * Добавление паспорта клиенту
     * */
    public void addPassportToTheClient(Client client, ClientPassport passport) {
        client.setPassport(passport);
        clientDao.update(client);
//        clientDao.addPassportToTheClient(client, passport);
    }

}
