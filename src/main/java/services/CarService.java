package services;

import dao.CarDao;
import pojo.Car;
import pojo.constant.DamageStatusConst;
import pojo.constant.EmploymentStatusConst;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private final CarDao carDao = CarDao.getCarDao();

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
    public void addNewCar(Car car){
        carDao.save(car);
    }

    /*
     * Удаление автомобиля
     * */
    public void deleteCar(Car car){
        carDao.delete(car);
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
        List<Car> cars = carDao.readAll();
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

    public void setCarStatusToOccupied(Car car){
        car.setEmploymentStatus(EmploymentStatusConst.OCCUPIED);
        carDao.update(car);
    }

    /*
     * Смена статуса на (свободно)
     * */
    public void setCarStatusToFree(Car car){
        car.setEmploymentStatus(EmploymentStatusConst.FREE);
        carDao.update(car);
    }

    /*
     * Смена статуса повреждения на (повреждён)
     * */
    public void setCarDamageStatusToWithDamage(Car car){
        car.setDamageStatus(DamageStatusConst.WITH_DAMAGE);
        carDao.update(car);
    }

    /*
     * Смена статуса повреждения на (не повреждён)
     * */
    public void setCarDamageStatusToWithoutDamage(Car car){
        car.setDamageStatus(DamageStatusConst.WITHOUT_DAMAGE);
        carDao.update(car);
    }


}
