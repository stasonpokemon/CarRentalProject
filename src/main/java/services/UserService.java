package services;

import dao.mysql.UserDaoImpl;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDaoImpl userDaoImpl = UserDaoImpl.getUserDaoImpl();
    private static UserService userService = null;

    public UserService() throws SQLException {
    }

    public static UserService getClientService() {
        if (userService == null) {
            try {
                userService = new UserService();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userService;
    }

    /*
     * Список всех клиентов
     * */
    public List<User> findAllClients() {
        return userDaoImpl.findAllClients();
    }

    /*
     * Поиск клиента по id
     * */
    public User findClientById(int clientId) {
        return userDaoImpl.read(clientId);
    }

    /*
     * Список всех users
     * */
    public List<User> findAllUsers() {
        return userDaoImpl.readAll();
    }


    /*
     * Регистрация нового клиента
     * */
    public void clientRegistration(User user) {
        int maxUserId = userDaoImpl.getMaxUserId();
        if (maxUserId != 0) {
            user.setId(maxUserId + 1);
        } else {
            user.setId(1);
        }
        userDaoImpl.save(user);
    }

    /*
     * Добавление паспорта клиенту
     * */
    public void update(User user) {
        userDaoImpl.update(user);
    }

}
