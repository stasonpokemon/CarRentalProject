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
    private static ClientMenu menu;
    private int operationNumber;

    public static ClientMenu getMenu() {
        if (menu == null) {
            menu = new ClientMenu();
        }
        return menu;
    }

    public void clientInitializationMenu() {
        boolean exitClientInitializationMenu = false;
        do {
            boolean operationNumberValid = false;
            do {
                try {
                    infoClientMenu.clientInitializationMenuInfo();
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

                clientMenu(newClient);
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
                        clientMenu(client);
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

    private void clientMenu(Client client) {
        boolean exitClientMenu = false;

        do {
            boolean operationNumberValid = false;
            do {
                try {
                    infoClientMenu.clientMenuInfo();
                    operationNumber = scanner.nextInt();
                    operationNumberValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter integer value...");
                    scanner.nextLine();
                    System.out.println("Exception: " + e);
                }
            } while (!operationNumberValid);

            switch (operationNumber){
                case 1:
                    clientService.findAllCars().forEach(System.out::println);
                    break;
                case 2:
                    break;
                case 3:
                    exitClientMenu = true;
                    System.out.println("Exit to the home menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }


        }while (!exitClientMenu);
    }


}
