package dao.mysql;

import dao.Dao;
import pojo.User;

import java.util.List;

public interface UserDaoI extends Dao<User> {

    /*
     * Список всех клиентов
     * */
    List<User> findAllClients();


}
