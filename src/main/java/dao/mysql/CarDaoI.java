package dao.mysql;

import dao.Dao;
import pojo.Car;

public interface CarDaoI extends Dao<Car> {
    int getMaxCarId();

}
