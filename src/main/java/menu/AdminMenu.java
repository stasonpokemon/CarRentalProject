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
    private static AdminMenu adminMenu = null;
    private int operationNumber;

    public static AdminMenu getAdminMenu() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;

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

    private void adminLoginMenu(){
        String adminLogin;
        String adminPassword;
        boolean exitAdminLogin = false;
        do {
            System.out.println("Enter login...");
            adminLogin = scanner.nextLine();

            System.out.println("Enter password...");
            adminPassword = scanner.nextLine();

            List<Admin> allAdmins = adminService.findAllAdmins();
            for (Admin admin : allAdmins) {
                if(admin.getLogin().equals(adminLogin)){
                    if (admin.getPassword().equals(adminPassword)){
                        exitAdminLogin = true;
                        /*
                         * Создать метод, в который мы передаём админа
                         * */
                    }else {
                        boolean operationNumberValid = false;
                        do {
                            try {
                                System.out.println("Invalid password...\n" +
                                        "1. Try again enter password \n" +
                                        "2. Exit to the initialization menu");
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
                                break;
                            case 2:
                                exitAdminLogin = true;
                                System.out.println("Exit to the initialization menu...");
                            default:
                                System.out.println("There is no such operation. Try again");
                                break;
                        }
                    }
                }else {
                    boolean operationNumberValid = false;
                    do {
                        try {
                            System.out.println("There is no such login...\n" +
                                    "1. Try again enter login \n" +
                                    "2. Exit to the initialization menu");
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
                            break;
                        case 2:
                            exitAdminLogin = true;
                            System.out.println("Exit to the initialization menu...");
                        default:
                            System.out.println("There is no such operation. Try again");
                            break;
                    }
                }
            }
        }while (exitAdminLogin);

    }


}
