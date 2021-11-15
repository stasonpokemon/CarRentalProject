package dao.mysql;

import pojo.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl extends BaseDaoImpl implements CarDaoI {

    private static CarDaoImpl carDaoImpl;

    public CarDaoImpl() throws SQLException {
    }

    public static CarDaoImpl getCarDaoImpl()  {
        if (carDaoImpl == null) {
            try {
                carDaoImpl = new CarDaoImpl();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return carDaoImpl;
    }

    /**
     * Работает, но по отдельности
     */
    @Override
    public void save(Car car) {
        String sql = "INSERT INTO cars (id, model, price_per_day, employment_status, damage_status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,car.getId());
            statement.setString(2, car.getModel());
            statement.setDouble(3, car.getPricePerDay());
            statement.setString(4, car.getEmploymentStatus());
            statement.setString(5, car.getDamageStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
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

    /**
     * Работает, но по отдельности
     */
    @Override
    public List<Car> readAll() {
        String sql = "SELECT id, model, price_per_day, employment_status, damage_status FROM cars";
        List<Car> cars = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
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
                if (resultSet != null) {
                    resultSet.close();
                }
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
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
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

    /**
     * Работает, но по отдельности
     */
    @Override
    public void delete(Car car) {
        String sql = "DELETE FROM cars WHERE id = ?";
        PreparedStatement statement = null;
        try {
            connection.prepareStatement(sql);
            statement.setInt(1, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void update(Car car) {
        String sql = "UPDATE cars SET model = ?, price_per_day = ?, employment_status = ?, damage_status = ?  WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, car.getModel());
            statement.setDouble(2, car.getPricePerDay());
            statement.setString(3, car.getEmploymentStatus());
            statement.setString(4, car.getDamageStatus());
            statement.setInt(5, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getMaxCarId() {
        String sql = "SELECT MAX(id) FROM cars";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }






}
