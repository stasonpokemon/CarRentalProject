import dao.mysql.CarDaoImpl;
import menu.HomeMenu;
import pojo.Car;
import pojo.constant.DamageStatusConst;
import pojo.constant.EmploymentStatusConst;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * 10.	Система Прокат автомобилей. Клиент выбирает Автомобиль из списка доступных. Заполняет форму Заказа,
 * указывая паспортные данные, срок аренды. Клиент оплачивает Заказ. Администратор регистрирует возврат автомобиля.
 * В случае повреждения Автомобиля, Администратор вносит информацию и выставляет счет за ремонт.
 * Администратор может отклонить Заявку, указав причины отказа.
 *
 * */


public class Main {
    public static void main(String[] args) throws SQLException {

//        HomeMenu homeMenu = HomeMenu.getHomeMenu();
//        homeMenu.menu();



//        Car read = CarDaoImpl.getCarDaoImpl().read(3);
//        System.out.println(read.getModel());

        List<Car> cars = CarDaoImpl.getCarDaoImpl().readAll();
        cars.forEach(System.out::println);
//
//        Car car = new Car();
//        car.setModel("Volvo C90");
//        car.setPricePerDay(130);
//        car.setEmploymentStatus(EmploymentStatusConst.FREE);
//        car.setDamageStatus(DamageStatusConst.WITHOUT_DAMAGE);
//        CarDaoImpl.getCarDaoImpl().save(car);




    }
}
