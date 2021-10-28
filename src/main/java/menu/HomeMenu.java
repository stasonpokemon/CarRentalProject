package menu;

import info.InfoHomeMenu;
import utils.NumberValidUtil;

public class HomeMenu {
    private final NumberValidUtil numberValidUtil = NumberValidUtil.getOperationNumberUtil();
    private final InfoHomeMenu infoHomeMenu = InfoHomeMenu.getInfoHomeMenu();
    private final ClientMenu clientMenu = ClientMenu.getMenu();
    private final AdminMenu adminMenu = AdminMenu.getMenu();
    private static HomeMenu homeMenu = null;
    private static int operationNumber;

    public static HomeMenu getHomeMenu() {
        if (homeMenu == null) {
            homeMenu = new HomeMenu();
        }
        return homeMenu;
    }

    public void menu() {
        boolean exitMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoHomeMenu.input());
            switch (operationNumber) {
                case 1:
                    clientMenu.clientInitializationMenu();
                    break;
                case 2:
                    adminMenu.adminInitializationMenu();
                    break;
                case 3:
                    exitMenu = true;
                    System.out.println("Close...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitMenu);
    }
}
