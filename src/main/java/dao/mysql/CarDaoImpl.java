package dao.mysql;

import dao.CarDao;
import dao.Dao;
import pojo.Car;
import pojo.constant.EmploymentStatusConst;
import utils.JDBCConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements Dao<Car> {
    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();

    private static CarDaoImpl carDaoImpl;

    public CarDaoImpl() throws SQLException {
    }

    public static CarDaoImpl getCarDaoImpl() throws SQLException {
        if (carDaoImpl == null) {
            carDaoImpl = new CarDaoImpl();
        }
        return carDaoImpl;
    }

    /**
     * Работает, но по отдельности
     */
    @Override
    public void save(Car car) {
        String sql = "INSERT INTO cars (model, price_per_day, employment_status, damage_status) VALUES (?, ?, ?, ?)";
        //        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getModel());
            statement.setDouble(2, car.getPricePerDay());
            statement.setString(3, car.getEmploymentStatus());
            statement.setString(4, car.getDamageStatus());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Работает, но по отдельности
     */
    @Override
    public Car read(int id) {
        String sql = "SELECT id, model, price_per_day, employment_status, damage_status FROM cars WHERE id = ?";
        Car car = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = new Car();
                car.setId(id);
                car.setModel(resultSet.getString("model"));
                car.setPricePerDay(resultSet.getDouble("price_per_day"));
                car.setEmploymentStatus(resultSet.getString("employment_status"));
                car.setDamageStatus(resultSet.getString("damage_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
        return car;
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void delete(Car car) {

    }

    /**
     * Работает, но по отдельности
     */
    @Override
    public List<Car> readAll() {
        String sql = "SELECT id, model, price_per_day, employment_status, damage_status FROM cars";
        List<Car> cars = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            Car car = null;
            while (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setModel(resultSet.getString("model"));
                car.setPricePerDay(resultSet.getDouble("price_per_day"));
                car.setEmploymentStatus(resultSet.getString("employment_status"));
                car.setDamageStatus(resultSet.getString("damage_status"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
        return cars;
    }

    /*
     * Список автомобилей c указанным статусом свободы
     * */
    /**
     * Работает, но по отдельности
     */
    public List<Car> readAll(String status) {
        String sql = "SELECT id, model, price_per_day, employment_status, damage_status FROM cars WHERE employment_status LIKE ?";
        List<Car> cars = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = jdbcConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            resultSet = statement.executeQuery();
            Car car = null;
            while (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setModel(resultSet.getString("model"));
                car.setPricePerDay(resultSet.getDouble("price_per_day"));
                car.setEmploymentStatus(resultSet.getString("employment_status"));
                car.setDamageStatus(resultSet.getString("damage_status"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
        return cars;
    }
}
