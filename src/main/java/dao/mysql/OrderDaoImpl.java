package dao.mysql;

import pojo.Car;
import pojo.Order;
import pojo.User;
import utils.JDBCConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDaoI {

    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();
    private static OrderDaoImpl orderDaoImpl;

    public OrderDaoImpl() throws SQLException {
    }

    public static OrderDaoImpl getOrderDao() throws SQLException {
        if (orderDaoImpl == null) {
            orderDaoImpl = new OrderDaoImpl();
        }
        return orderDaoImpl;
    }


    /**
     * Работает, но по отдельности
     */
    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders (user_id, car_id, price, status, order_date) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getClient().getId());
            statement.setInt(2, order.getCar().getId());
            statement.setDouble(3, order.getPrice());
            statement.setString(4, order.getOrderStatus());
            statement.setDate(5, (Date) order.getOrderDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Получить заказ по id
     * */
    @Override
    public Order read(int id) {
        String sql = "SELECT id, user_id, car_id, price, status, order_date FROM orders WHERE id = ?";
        Order order = null;
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setId(id);
                int clientId = resultSet.getInt("user_id");
//                User client = UserDaoImpl.getUserDaoImpl().read(clientId);
//                order.setClient(client);
                if (!resultSet.wasNull()) {
                    User user = new User();
                    user.setId(clientId);
                    order.setClient(user);
                }
                int carId = resultSet.getInt("car_id");
//                Car car = CarDaoImpl.getCarDaoImpl().read(carId);
//                order.setCar(car);
                if (!resultSet.wasNull()) {
                    Car car = new Car();
                    car.setId(carId);
                    order.setCar(car);
                }
                order.setPrice(resultSet.getDouble("price"));
                order.setOrderStatus(resultSet.getString("status"));
                order.setOrderDate(resultSet.getDate("order_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE oreders SET user_id = ?, car_id = ?, price = ?, status = ?, order_date = ? WHERE id = ?";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, order.getClient().getId());
                statement.setInt(2, order.getCar().getId());
                statement.setDouble(3, order.getPrice());
                statement.setString(4,order.getOrderStatus());
                statement.setDate(5, (Date) order.getOrderDate());
                statement.setInt(6,order.getId());
                statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> readAll() {
        String sql = "SELECT id, user_id, car_id, price, status, order_date FROM orders";
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            Order order = null;
            while (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                int clientId = resultSet.getInt("user_id");
//                User client = UserD   aoImpl.getUserDaoImpl().read(clientId);
//                order.setClient(client);
                if (!resultSet.wasNull()) {
                    User user = new User();
                    user.setId(clientId);
                    order.setClient(user);
                }
                int carId = resultSet.getInt("car_id");
//                Car car = CarDaoImpl.getCarDaoImpl().read(carId);
//                order.setCar(car);
                if (!resultSet.wasNull()) {
                    Car car = new Car();
                    car.setId(carId);
                    order.setCar(car);
                }
                order.setPrice(resultSet.getDouble("price"));
                order.setOrderStatus(resultSet.getString("status"));
                order.setOrderDate(resultSet.getDate("order_date"));
                orders.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAllOrdersByClient(User user) {
        String sql = "SELECT id, user_id, car_id, price, status, order_date FROM orders WHERE  = ?";
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            Order order = null;
            while (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setClient(user);
                int carId = resultSet.getInt("car_id");
//                Car car = CarDaoImpl.getCarDaoImpl().read(carId);
//                order.setCar(car);
                if (!resultSet.wasNull()) {
                    Car car = new Car();
                    car.setId(carId);
                    order.setCar(car);
                }
                order.setPrice(resultSet.getDouble("price"));
                order.setOrderStatus(resultSet.getString("status"));
                order.setOrderDate(resultSet.getDate("order_date"));
                orders.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findOrdersByStatus(String status) {
        String sql = "SELECT id, user_id, car_id, price, status, order_date FROM orders WHERE status = ?";
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            resultSet = statement.executeQuery();
            Order order = null;
            while (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                int clientId = resultSet.getInt("user_id");
//                User client = UserDaoImpl.getUserDaoImpl().read(clientId);
//                order.setClient(client);
                if (!resultSet.wasNull()) {
                    User user = new User();
                    user.setId(clientId);
                    order.setClient(user);
                }
                int carId = resultSet.getInt("car_id");
//                Car car = CarDaoImpl.getCarDaoImpl().read(carId);
//                order.setCar(car);
                if (!resultSet.wasNull()) {
                    Car car = new Car();
                    car.setId(carId);
                    order.setCar(car);
                }
                order.setPrice(resultSet.getDouble("price"));
                order.setOrderStatus(resultSet.getString("status"));
                order.setOrderDate(resultSet.getDate("order_date"));
                orders.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

}
