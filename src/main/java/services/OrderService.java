package services;

import dao.OrderDao;
import pojo.User;
import pojo.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final OrderDao orderDao = OrderDao.getOrderDao();

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
        List<Order> orders = orderDao.readAll();
        return orders;
    }

    /*
     * Список заказов определённого клиента
     * */
    public List<Order> findAllOrdersByClient(User user) {
        List<Order> ordersByClient = new ArrayList<>();
        findAllOrders().forEach(order -> {
            if (order.getClient().getId() == user.getId()) {
                ordersByClient.add(order);
            }
        });
        return ordersByClient;
    }


    /*
     * Список заказов с определённым статусом
     * */
    public  List<Order> findOrdersByStatus (String status){
        List<Order> ordersRejected = new ArrayList<>();
        findAllOrders().forEach(order -> {
            if (order.getOrderStatus().equals(status)){
                ordersRejected.add(order);
            }
        });
        return ordersRejected;
    }

    /*
     * Создание нового заказа
     * */
    public void addOrder(Order order) {
        orderDao.save(order);
    }

    public void update(Order order) {
        orderDao.update(order);
    }

}
