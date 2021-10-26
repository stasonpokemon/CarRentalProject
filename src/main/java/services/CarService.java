package services;

import dao.CarDao;
import pojo.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private CarDao carDao = CarDao.getCarDao();

    private static CarService carService;

    public static CarService getCarService() {
        if (carService == null) {
            carService = new CarService();
        }
        return carService;
    }

    /*
     * Поиск автомобиля по id
     * */
    public Car findCarById(int carId) {
        Car carById = null;
        for (Car car : findAllCars()) {

            if (carId == car.getId()) {
                carById = car;
            }
        }
        return carById;
    }

    /*
     * Список всех автомобилей
     * */
    public List<Car> findAllCars() {
        List<Car> cars = carDao.findAll();
        return cars;
    }

    /*
     * Список всех свободных автомобилей
     * */
    public List<Car> findAllFreeCars() {
        List<Car> freeCars = new ArrayList<>();
        findAllCars().forEach(car -> {
            if ("FREE".equals(car.getEmploymentStatus())) {
                freeCars.add(car);
            }
        });
        return freeCars;
    }

    /*
    * Смена статуса на (занято)
    * */

    public void changingTheStatusToOccupied(Car car){
        car.setEmploymentStatus("OCCUPIED");
        carDao.update(car);
    }

    /*
     * Смена статуса на (свободно)
     * */
    public void changingTheStatusToFree(Car car){
        car.setEmploymentStatus("FREE");
        carDao.update(car);
    }
}
