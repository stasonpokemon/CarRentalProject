package menu;

import info.InfoHomeMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeMenu {
    private InfoHomeMenu infoHomeMenu = InfoHomeMenu.getInfoHomeMenu();
    private ClientMenu clientMenu = ClientMenu.getClientMenu();
    private AdminMenu adminMenu = AdminMenu.getAdminMenu();
    private static HomeMenu homeMenu = null;
    private static Scanner scanner = new Scanner(System.in);
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
            boolean operationNumberValid = false;
            do {
                try {
                    infoHomeMenu.input();
                    operationNumber = scanner.nextInt();
                    operationNumberValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter integer value...");
                    scanner.nextLine();
                    System.out.println("Exception: " + e);
                }
            } while (!operationNumberValid);

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
