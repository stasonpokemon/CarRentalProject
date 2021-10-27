package info;

import pojo.Admin;
import pojo.Order;

public class InfoAdminMenu {

    private static InfoAdminMenu infoAdminMenu = null;

    public static InfoAdminMenu getInfoAdminMenu() {
        if (infoAdminMenu == null) {
            infoAdminMenu = new InfoAdminMenu();
        }
        return infoAdminMenu;
    }

    public void initializationMenuInfo(){
        System.out.println("Admin initialization...\n" +
                "1. Login\n" +
                "2. Exit to the home menu");
    }

    public void adminMenuInfo(Admin admin){
        System.out.println("Admin menu("+admin.getLogin()+"):\n" +
                "1. Автомобили\n" +
                "2. Заказы\n" +
                "3. Клиенты\n" +
                "4. Выйти");
    }

    public String adminCarsMenuInfo(){
        return "Admin cars menu:\n" +
                "1. Все автомобили\n" +
                "2. Свободные автомобили\n" +
                "3. Добавить автомобиль\n" +
                "4. Удалить автомобиль\n" +
                "5. Назад";
    }

    public String adminOrdersMenuInfo(){
        return "Admin orders menu:\n" +
                "1. Все заказы\n" +
                "2. Заявки на заказ\n" +
                "3. Регистрация возврата\n" +
                "4. Назад";
    }

    public String adminOrdersUnderConsiderationMenuInfo(){
        return "1. Выбор заявки\n" +
                "2. Назад";
    }

    public String adminOrderStatusMenuInfo(Order selectedOrder){
        return "Заказ " + selectedOrder + "\n" +
                "1. Одобрить\n" +
                "2. Отклонить\n" +
                "3. Назад";
    }

    public String adminClientsMenuInfo(){
        return "";
    }
}
