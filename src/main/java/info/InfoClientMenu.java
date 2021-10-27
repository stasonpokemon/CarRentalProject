package info;

import pojo.Client;

public class InfoClientMenu {

    private static InfoClientMenu infoClientMenu = null;

    public static InfoClientMenu getInfoClientMenu() {
        if (infoClientMenu == null) {
            infoClientMenu = new InfoClientMenu();
        }
        return infoClientMenu;
    }


    public void clientInitMenuInfo() {
        System.out.println("Client initialization...\n" +
                "1. Login\n" +
                "2. Registration\n" +
                "3. Exit to the home menu");
    }

    public void clientMenuInfo(Client client) {
        System.out.println("Client menu(" + client.getLogin() + "):\n" +
                "1. Сделать заказ\n" +
                "2. Мои заказы\n" +
                "3. Выйти");
    }

    public void clientCarInitMenuInfo() {
        System.out.println("1. Выбрать авто\n" +
                "2. Назад");
    }

    /*public static void registrationInfo(){

    }

    public static void loginInfo(){

    }*/

}
