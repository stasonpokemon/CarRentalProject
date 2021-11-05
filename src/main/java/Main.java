import dao.mysql.CarDaoImpl;
import dao.mysql.OrderDaoImpl;
import dao.mysql.UserDaoImpl;
import menu.HomeMenu;
import pojo.Car;
import pojo.Order;
import pojo.User;
import pojo.constant.DamageStatusConst;
import pojo.constant.EmploymentStatusConst;
import pojo.constant.OrderStatusConst;

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

//        System.out.println(UserDaoImpl.getUserDaoImpl().read(1));
//        System.out.println(OrderDaoImpl.getOrderDao().read(1));
    }
}
