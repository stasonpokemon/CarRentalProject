import dao.mysql.CarDaoImpl;
import dao.mysql.UserDaoImpl;
import menu.HomeMenu;
import pojo.Car;
import pojo.User;
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

        HomeMenu homeMenu = HomeMenu.getHomeMenu();
        homeMenu.menu();



//        Car read = CarDaoImpl.getCarDaoImpl().read(7);
//        System.out.println(read.getModel());


//        List<Car> cars = CarDaoImpl.getCarDaoImpl().readAll();
//        cars.forEach(System.out::println);
//
//        Car car = new Car();
//        car.setId(6);
//        car.setModel("Volvo C90");
//        car.setPricePerDay(130);
//        car.setEmploymentStatus(EmploymentStatusConst.FREE);
//        car.setDamageStatus(DamageStatusConst.WITHOUT_DAMAGE);
//        CarDaoImpl.getCarDaoImpl().save(car);

//        CarDaoImpl.getCarDaoImpl().setCarDamageStatusToWithoutDamage(car);

//        CarDaoImpl.getCarDaoImpl().setCarStatusToFree(car);

//        CarDaoImpl.getCarDaoImpl().delete(car);

//        User user = new User();
//        user.setLogin("testAdd");
//        user.setPassword("1111");
//        UserDaoImpl.getUserDaoImpl().save(user);

//        System.out.println(UserDaoImpl.getUserDaoImpl().read(4));

//        UserDaoImpl.getUserDaoImpl().findAllClients().forEach(System.out::println);


//        UserDaoImpl.getUserDaoImpl().delete(user);
    }
}
