package services;

import dao.OrderDao;
import pojo.Client;
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
     * Список заказов со статусом(НА РАССМОТРЕНИИ)
     * */
    public  List<Order> findOrdersUnderConsideration(){
        List<Order> ordersUnderConsideration = new ArrayList<>();
            findAllOrders().forEach(order -> {
                if (order.getStatus().equals("НА РАССМОТРЕНИИ")){
                    ordersUnderConsideration.add(order);
                }
            });
        return ordersUnderConsideration;
    }

    /*
     * Список заказов со статусом(ОДОБРЕНО)
     * */
    public  List<Order> findOrdersApproved(){
        List<Order> ordersApproved = new ArrayList<>();
        findAllOrders().forEach(order -> {
            if (order.getStatus().equals("ОДОБРЕНО")){
                ordersApproved.add(order);
            }
        });
        return ordersApproved;
    }

    /*
     * Список заказов со статусом(ОТКЛОЕНО)
     * */
    public  List<Order> findOrdersRejected (){
        List<Order> ordersRejected = new ArrayList<>();
        findAllOrders().forEach(order -> {
            if (order.getStatus().equals("ОТКЛОЕНО")){
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

    /*
    * Установка заказу статуса(ОДОБРЕНО)
    * */
    public void setOrderApprovedStatus(Order order){
        order.setStatus("ОДОБРЕНО");
        orderDao.update(order);
    }

    /*
     * Установка заказу статуса(ОТКЛОНЕНО)
     * */
    public void setOrderRejectStatus(Order order){
        order.setStatus("ОТКЛОНЕНО");
        orderDao.update(order);
    }
}
