package dao.mysql;

import dao.Dao;
import pojo.Order;
import pojo.User;

import java.util.List;

public interface OrderDaoI extends Dao<Order> {

    void updateWithRefund(Order order);


    /*
     * Список заказов определённого клиента
     * */
    List<Order> findAllOrdersByClient(User user);

    /*
     * Список заказов с определённым статусом
     * */
    List<Order> findOrdersByStatus(String status);

    int getMaxOrderId();


}
