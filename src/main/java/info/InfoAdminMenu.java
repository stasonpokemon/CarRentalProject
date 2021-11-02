package info;

import pojo.Car;
import pojo.Order;
import pojo.User;

public class InfoAdminMenu {

    private static InfoAdminMenu infoAdminMenu = null;

    public static InfoAdminMenu getInfoAdminMenu() {
        if (infoAdminMenu == null) {
            infoAdminMenu = new InfoAdminMenu();
        }
        return infoAdminMenu;
    }


    public String adminMenuInfo(User admin) {
        return "Admin menu(" + admin.getLogin() + "):\n" +
                "1. Автомобили\n" +
                "2. Заказы\n" +
                "3. Клиенты\n" +
                "4. Выйти";
    }

    public String adminCarsMenuInfo() {
        return "Admin cars menu:\n" +
                "1. Все автомобили\n" +
                "2. Свободные автомобили\n" +
                "3. Добавить автомобиль\n" +
                "4. Удалить автомобиль\n" +
                "5. Назад";
    }

    public String adminOrdersMenuInfo() {
        return "Admin orders menu:\n" +
                "1. Все заказы\n" +
                "2. Заявки на заказ\n" +
                "3. Регистрация возврата\n" +
                "4. Все возвраты\n" +
                "5. Назад";
    }

    public String adminOrdersUnderConsiderationMenuInfo() {
        return "1. Выбор заявки\n" +
                "2. Назад";
    }

    public String adminOrderStatusMenuInfo(Order selectedOrder) {
        return "Заказ " + selectedOrder + "\n" +
                "1. Одобрить\n" +
                "2. Отклонить\n" +
                "3. Назад";
    }

    public String adminClientsMenuInfo() {
        return "Admin clients menu:\n" +
                "1. Паспорт клиента\n" +
                "2. Удалить клиента\n" +
                "3. Назад";
    }

    public String adminRegisteringRefundMenuInfo() {
        return "Admin registering refund menu:\n" +
                "1. Регистрация возврата\n" +
                "2. Назад";
    }

    /*
     * Метод регистарции заказа
     * */
    public String registeringRefund(Car car) {
        return "Укажите статус состояния автомобиля " + car.getModel() + ":\n" +
                "1. Без повреждений\n" +
                "2. Есть повреждения ";
    }
}
