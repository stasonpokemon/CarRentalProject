package menu;

import info.InfoAdminMenu;
import pojo.Admin;
import services.AdminService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private Scanner scanner = new Scanner(System.in);
    private InfoAdminMenu infoAdminMenu = InfoAdminMenu.getInfoAdminMenu();
    private AdminService adminService = AdminService.getAdminService();
    private static AdminMenu menu = null;
    private int operationNumber;

    public static AdminMenu getMenu() {
        if (menu == null) {
            menu = new AdminMenu();
        }
        return menu;

    }

    public void adminInitializationMenu() {
        boolean exitAdminInitializationMenu = false;
        do {
            boolean operationNumberValid = false;
            do {
                try {
                    infoAdminMenu.initializationMenu();
                    operationNumber = scanner.nextInt();
                    scanner.nextLine();
                    operationNumberValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter integer value...");
                    scanner.nextLine();
                    System.out.println("Exception: " + e);
                }
            } while (!operationNumberValid);
            switch (operationNumber) {
                case 1:
                    adminLoginMenu();
                    break;
                case 2:
                    exitAdminInitializationMenu = true;
                    System.out.println("Exit to the home menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitAdminInitializationMenu);
    }

    private void adminLoginMenu() {
        String adminLogin;
        String adminPassword;
        boolean exitAdminLogin = false;
        boolean adminLoginValid = false;
        do {
            System.out.println("Enter login...");
            adminLogin = scanner.next();

            System.out.println("Enter password...");
            adminPassword = scanner.next();

            List<Admin> allAdmins = adminService.findAllAdmins();
            for (Admin admin : allAdmins) {
                if (admin.getLogin().equals(adminLogin)) {
                    if (admin.getPassword().equals(adminPassword)) {
                        exitAdminLogin = true;
                        adminLoginValid = true;
                        adminMenu(admin);
                    }
                }
            }
            if (adminLoginValid == false) {
                boolean operationNumberValid = false;
                do {
                    try {
                        System.out.println("Login or password entered incorrectly...\n" +
                                "1. Try again \n" +
                                "2. Exit to the initialization menu");
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
                        break;
                    case 2:
                        exitAdminLogin = true;
                        System.out.println("Exit to the initialization menu...");
                        break;
                    default:
                        System.out.println("There is no such operation. Try again");
                        break;
                }
            }
        } while (!exitAdminLogin);

    }

    private void adminMenu(Admin admin){

    }


}
