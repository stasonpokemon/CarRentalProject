package services;

import dao.OrderDao;
import pojo.Client;
import pojo.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = OrderDao.getOrderDao();

    private static OrderService orderService;

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    /*
     * Список всех заказов
     * */
    public List<Order> findAllOrders() {
        List<Order> orders = orderDao.findAll();
        return orders;
    }

    /*
     * Список заказов определённого клиента
     * */
    public List<Order> findAllOrdersByClient(Client client) {
        List<Order> ordersByClient = new ArrayList<>();
        findAllOrders().forEach(order -> {
            if (order.getClient().getId() == client.getId()) {
                ordersByClient.add(order);
            }
        });
        return ordersByClient;
    }


    /*
     * Создание нового заказа
     * */
    public void addOrder(Order order) {
        orderDao.save(order);
    }
}
