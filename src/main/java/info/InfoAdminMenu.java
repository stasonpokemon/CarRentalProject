package info;

public class InfoAdminMenu {

    private static InfoAdminMenu infoAdminMenu = null;

    public static InfoAdminMenu getInfoAdminMenu() {
        if (infoAdminMenu == null) {
            infoAdminMenu = new InfoAdminMenu();
        }
        return infoAdminMenu;
    }

    public void initializationMenu(){
        System.out.println("Admin initialization...\n" +
                "1. Login\n" +
                "2. Exit to the home menu");
    }
}
