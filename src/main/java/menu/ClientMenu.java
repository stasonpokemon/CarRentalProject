package menu;

import services.ClientService;
import info.InfoClientMenu;
import pojo.Client;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClientMenu {

    private Scanner scanner = new Scanner(System.in);
    private InfoClientMenu infoClientMenu = InfoClientMenu.getInfoClientMenu();
    private ClientService clientService = ClientService.getClientService();
    private static ClientMenu clientMenu;
    private int operationNumber;

    public static ClientMenu getClientMenu() {
        if (clientMenu == null) {
            clientMenu = new ClientMenu();
        }
        return clientMenu;
    }

    public void clientInitializationMenu() {
        boolean exitClientInitializationMenu = false;
        do {
            boolean operationNumberValid = false;
            do {
                try {
                    infoClientMenu.initializationInfo();
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
                    clientLoginMenu();
                    break;
                case 2:
                    clientRegistrationMenu();
                    break;
                case 3:
                    exitClientInitializationMenu = true;
                    System.out.println("Exit to the home menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitClientInitializationMenu);


    }

    private void clientRegistrationMenu() {
        Client newClient;
        String clientLogin;
        String clientPassword;
        String repeatClientPassword;
        boolean exitClientRegistration = false;

        System.out.println("Enter login...");
        clientLogin = scanner.nextLine();
        do {
            System.out.println("Enter password...");
            clientPassword = scanner.nextLine();
            System.out.println("Repeat password...");
            repeatClientPassword = scanner.nextLine();
            if (!repeatClientPassword.equals(clientPassword)) {
                boolean operationNumberValid = false;
                do {
                    try {
                        System.out.println("Passwords don't match...\n" +
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
                        exitClientRegistration = true;
                        System.out.println("Exit to the initialization menu...");
                    default:
                        System.out.println("There is no such operation. Try again");
                        break;
                }
            } else {
                exitClientRegistration = true;
                newClient = new Client();
                newClient.setLogin(clientLogin);
                newClient.setPassword(clientPassword);
                clientService.clientRegistration(newClient);
                /*
                 * Тут должен быть метод, который выполняется, если регистрация прошла успешно
                 * */
            }
        } while (!exitClientRegistration);
    }

    private void clientLoginMenu() {
        String clientLogin;
        String clientPassword;
        boolean exitClientLogin = false;
        do {
            System.out.println("Enter login...");
            clientLogin = scanner.nextLine();

            System.out.println("Enter password...");
            clientPassword = scanner.nextLine();

            List<Client> allClients = clientService.findAllClients();
            for (Client client : allClients) {
                if (client.getLogin().equals(clientLogin)) {
                    if (client.getPassword().equals(clientPassword)) {
                        exitClientLogin = true;
                        /*
                         * Создать метод, в который мы передаём клиента
                         * */
                    } else {
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
                                exitClientLogin = true;
                                System.out.println("Exit to the initialization menu...");
                            default:
                                System.out.println("There is no such operation. Try again");
                                break;
                        }
                    }
                } else {
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
                            exitClientLogin = true;
                            System.out.println("Exit to the initialization menu...");
                        default:
                            System.out.println("There is no such operation. Try again");
                            break;
                    }
                }
            }
        } while (!exitClientLogin);
    }


}
