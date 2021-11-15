import dao.mysql.*;
import menu.HomeMenu;
import pojo.Car;
import pojo.Order;
import pojo.User;
import pojo.constant.DamageStatusConst;
import pojo.constant.EmploymentStatusConst;
import pojo.constant.OrderStatusConst;
import pojo.constant.UserRoleConst;
import services.UserService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 10.	Система Прокат автомобилей. Клиент выбирает Автомобиль из списка доступных. Заполняет форму Заказа,
 * указывая паспортные данные, срок аренды. Клиент оплачивает Заказ. Администратор регистрирует возврат автомобиля.
 * В случае повреждения Автомобиля, Администратор вносит информацию и выставляет счет за ремонт.
 * Администратор может отклонить Заявку, указав причины отказа.
 */


public class Main {
    public static void main(String[] args) throws SQLException {

        HomeMenu homeMenu = HomeMenu.getHomeMenu();
        homeMenu.menu();



//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        int day = 1;
//        int month = 1;
//        int year = 2000;
//        date.setDate(day);
//        date.setMonth(month - 1);
//        date.setYear(year - 1900);
//        String dateFormat = format.format(date);
//        System.out.println(dateFormat);
//
//        GregorianCalendar calendar = new GregorianCalendar();
//        System.out.println(calendar.getTime());
//
//        GregorianCalendar calendar1 = new GregorianCalendar(2001, 6, 13);
//        System.out.println(new Timestamp(calendar1.getTimeInMillis()));
//
//
//        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
//        System.out.println(format.format(timestamp));

    }
}
