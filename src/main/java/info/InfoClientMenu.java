package info;

public class InfoClientMenu {

    private static InfoClientMenu infoClientMenu = null;

    public static InfoClientMenu getInfoClientMenu() {
        if (infoClientMenu == null) {
            infoClientMenu = new InfoClientMenu();
        }
        return infoClientMenu;
    }


    public void initializationInfo() {
        System.out.println("Client initialization...\n" +
                "1. Login\n" +
                "2. Registration\n" +
                "3. Exit to the home menu");
    }

    /*public static void registrationInfo(){

    }

    public static void loginInfo(){

    }*/

}
