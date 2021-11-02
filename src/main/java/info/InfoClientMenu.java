package info;

import pojo.Car;
import pojo.User;

public class InfoClientMenu {

    private static InfoClientMenu infoClientMenu = null;

    public static InfoClientMenu getInfoClientMenu() {
        if (infoClientMenu == null) {
            infoClientMenu = new InfoClientMenu();
        }
        return infoClientMenu;
    }

    /*
     * Пароли при регистрации не идентичны
     * */
    public String clientPasswordsDontMatchInfo() {
        return "Passwords don't match...\n" +
                "1. Try again enter password \n" +
                "2. Exit to the initialization menu";
    }

    /*
     * При входе логи или пароль неверны
     * */
    public String clientLoginOrPasswordIsIncorrect() {
        return "Login or password entered incorrectly...\n" +
                "1. Try again \n" +
                "2. Exit to the initialization menu";
    }

    public String clientMenuInfo(User user) {
        return "Client menu(" + user.getLogin() + "):\n" +
                "1. Сделать заказ\n" +
                "2. Мои заказы\n" +
                "3. Выйти";
    }

    public String clientCarInitMenuInfo() {
        return "1. Выбрать авто\n" +
                "2. Назад";
    }

    public String clientOrderPaymentMenuInfo(Car selectedCar, double orderPrice) {
        return "Оплата заказа:\n" +
                "Car - " + selectedCar.getModel() + "\n" +
                "price - " + orderPrice + "\n" +
                "1. Оплатить\n" +
                "2. Отмена заказа";
    }



}
