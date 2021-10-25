package info;

public class InfoClientMenu {

    private static InfoClientMenu infoClientMenu = null;

    public static InfoClientMenu getInfoClientMenu() {
        if (infoClientMenu == null) {
            infoClientMenu = new InfoClientMenu();
        }
        return infoClientMenu;
    }


    public void clientInitializationMenuInfo() {
        System.out.println("Client initialization...\n" +
                "1. Login\n" +
                "2. Registration\n" +
                "3. Exit to the home menu");
    }

    public void clientMenuInfo(){
        System.out.println("Client menu:\n" +
                "1. Сделать заказ\n" +
                "2. Мои заказы\n" +
                "3. Выйти");
    }

    /*public static void registrationInfo(){

    }

    public static void loginInfo(){

    }*/

}
