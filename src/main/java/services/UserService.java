package services;

import dao.UserDao;
import pojo.User;
import pojo.ClientPassport;
import pojo.constant.UserRoleConst;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserDao userDao = UserDao.getClientDao();
    private static UserService userService = null;

    public static UserService getClientService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    /*
     * Список всех клиентов
     * */
    public List<User> findAllClients() {
        List<User> users = userDao.readAll();
        List<User> clients = new ArrayList<>();
        users.forEach(user -> {
            if (UserRoleConst.CLIENT_ROLE.equals(user.getRole())){
                clients.add(user);
            }
        });
        return clients;
    }

    /*
     * Поиск клиента по id
     * */
    public User findClientById(int clientId) {
        User searchClient = null;
        List<User> allUsers = findAllClients();
        for (User user : allUsers) {
            if (clientId == user.getId() && UserRoleConst.CLIENT_ROLE.equals(user.getRole())) {
                searchClient = user;
            }
        }
        return searchClient;
    }

    public List<User> findAllUsers() {
        List<User> users = userDao.readAll();
        return users;
    }



    /*
     * Регистрация нового клиента
     * */
    public void clientRegistration(User user) {
        userDao.save(user);
    }

    /*
     * Добавление паспорта клиенту
     * */
    public void addPassportToTheClient(User user, ClientPassport passport) {
        user.setOrders(new ArrayList<>());
        user.setPassport(passport);
        userDao.update(user);
    }

}
