package services;

import dao.OrderDao;
import pojo.User;
import pojo.Order;
import pojo.constant.OrderStatusConst;

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
     * Список заказов со статусом(НА РАССМОТРЕНИИ)
     * */
    public  List<Order> findOrdersUnderConsideration(){
        List<Order> ordersUnderConsideration = new ArrayList<>();
            findAllOrders().forEach(order -> {
                if (order.getOrderStatus().equals(OrderStatusConst.UNDER_CONSIDERATION)){
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
            if (order.getOrderStatus().equals(OrderStatusConst.APPROVES)){
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
            if (order.getOrderStatus().equals(OrderStatusConst.REJECT)){
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
        order.setOrderStatus(OrderStatusConst.APPROVES);
        orderDao.update(order);
    }

    /*
     * Установка заказу статуса(ОТКЛОНЕНО)
     * */
    public void setOrderRejectStatus(Order order){
        order.setOrderStatus(OrderStatusConst.REJECT);
        orderDao.update(order);
    }

    /*
     * Установка заказу статуса(ВОЗВРАТ)
     * */
    public void setOrderRefundStatus(Order order){
        order.setOrderStatus(OrderStatusConst.REFUND);
        orderDao.update(order);
    }
}
