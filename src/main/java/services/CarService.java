package services;

import dao.mysql.CarDaoImpl;
import pojo.Car;
import pojo.constant.EmploymentStatusConst;

import java.util.List;

public class CarService {
    private final CarDaoImpl carDaoImpl = CarDaoImpl.getCarDaoImpl();


    private static CarService carService;

    public static CarService getCarService() {
        if (carService == null) {
            carService = new CarService();
        }
        return carService;
    }

    /*
     * Добавление нового автомобиля
     * */
    public void addNewCar(Car car) {
        int maxCarId = carDaoImpl.getMaxCarId();
        if (maxCarId != 0){
            car.setId(maxCarId + 1);
        }else {
            car.setId(1);
        }
        carDaoImpl.save(car);
    }

    /*
     * Удаление автомобиля
     * */
    public void deleteCar(Car car) {
        carDaoImpl.delete(car);
    }

    /*
     * Поиск автомобиля по id
     * */
    public Car findCarById(int carId) {
        return carDaoImpl.read(carId);
    }

    /*
     * Список всех автомобилей
     * */
    public List<Car> findAllCars() {
        return carDaoImpl.readAll();
    }

    /*
     * Список всех свободных автомобилей
     * */
    public List<Car> findAllFreeCars() {
        return carDaoImpl.readAll(EmploymentStatusConst.FREE);
    }


    public void update(Car car){
        carDaoImpl.update(car);
    }



}
