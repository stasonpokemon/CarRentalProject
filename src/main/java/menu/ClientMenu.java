package menu;

import pojo.Car;
import pojo.ClientPassport;
import pojo.Order;
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
                    infoClientMenu.clientInitMenuInfo();
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
//        clientService.findAllCars().forEach(car -> {
//            if ("FREE".equals(car.getEmploymentStatus())){
//                System.out.println(car);
//            }
//        });

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

            switch (operationNumber) {
                case 1:
                    /*
                     * Выводит список доступных автомобилей
                     * */
                    clientService.findAllCars().forEach(car -> {
                        if ("FREE".equals(car.getEmploymentStatus())) {
                            System.out.println(car);
                        }
                    });
                    clientAutoInitMenu(client);

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


        } while (!exitClientMenu);
    }

    private void clientAutoInitMenu(Client client) {
        boolean autoInitMenuExit = false;
        do {
            boolean operationNumberValid = false;
            do {
                try {
                    infoClientMenu.clientCarInitMenuInfo();
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
                    boolean carInitExit = false;
                    int carId = 0;
                    int rentalPeriod = 0;
                    Car selectedCar = null;
                    Client readyClient = client;
                    double orderPrice = 0;
                    boolean selectedCarValid = false;
                    do {
                        boolean carIdValid = false;
                        do {
                            try {
                                System.out.println("Ввыедите номер(id) автомобиля...");
                                carId = scanner.nextInt();
                                carIdValid = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Enter integer value...");
                                scanner.nextLine();
                                System.out.println("Exception: " + e);
                            }
                        } while (!carIdValid);

                        for (Car car : clientService.findAllCars()) {
                            if ("FREE".equals(car.getEmploymentStatus())) {
                                if (car.getId() == carId) {
                                    selectedCar = car;
                                    selectedCarValid = true;
                                }
                            }
                        }
                        if (selectedCarValid) {
                            boolean rentalPeriodValid = false;
                            do {
                                /*
                                 * Сделать, чтобы срок аренды не был меньше нуля
                                 * */
                                try {
                                    System.out.println("Введите срок аренды автомобиля...");
                                    rentalPeriod = scanner.nextInt();
                                    scanner.nextLine();
                                    rentalPeriodValid = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter integer value...");
                                    scanner.nextLine();
                                    System.out.println("Exception: " + e);
                                }
                            } while (!rentalPeriodValid);

                            /*
                             * Расчёт цены заказа
                             * */

                            orderPrice = rentalPeriod * selectedCar.getPricePerDay();

                            /*
                             * Если клиент впервый раз вводит паспортные данные, то выполняется данное условие
                             * */
                            if (client.getPassport() == null) {
                                readyClient = clientPassportInitMenu(client);
                            }

                            /*
                             * Создать метод clientOrderPaymentMenu  передать в него readyClient, selectedCar, orderPrice
                             * */
                            clientOrderPaymentMenu(readyClient, selectedCar, orderPrice);
                            autoInitMenuExit = true;
                            carInitExit = true;
                        }
                    } while (!carInitExit);
                    break;
                case 2:
                    autoInitMenuExit = true;
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }

        } while (!autoInitMenuExit);
    }

    private Client clientPassportInitMenu(Client client) {
        String name;
        String surname;
        String patronymic;
        int dayOfBirthday;
        boolean dayOfBirthdayValid = false;
        int monthOfBirthday;
        boolean monthOfBirthdayValid = false;
        int yearOfBirthday;
        boolean yearOfBirthdayValid = false;
        String address;
        ClientPassport newPassport = new ClientPassport();
        System.out.println("Введите имя...");
        name = scanner.nextLine();
        scanner.nextLine();
        newPassport.setName(name);
        System.out.println("Введите фамилию...");
        surname = scanner.nextLine();
        newPassport.setSurname(surname);
        System.out.println("Введите отчество...");
        patronymic = scanner.nextLine();
        scanner.nextLine();
        newPassport.setPatronymic(patronymic);
        do {
            try {
                System.out.println("Введите день рождения...");
                dayOfBirthday = scanner.nextInt();
                newPassport.setDayBirthday(dayOfBirthday);
                dayOfBirthdayValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter integer value...");
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!dayOfBirthdayValid);
        do {
            try {
                System.out.println("Введите месяц рождения...");
                monthOfBirthday = scanner.nextInt();
                newPassport.setMonthBirthday(monthOfBirthday);
                monthOfBirthdayValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter integer value...");
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!monthOfBirthdayValid);
        do {
            try {
                System.out.println("Введите год рождения...");
                yearOfBirthday = scanner.nextInt();
                newPassport.setYearBirthday(yearOfBirthday);
                yearOfBirthdayValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter integer value...");
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!yearOfBirthdayValid);
        System.out.println("Введите свой адрес...");
        address = scanner.nextLine();
        newPassport.setAddress(address);

        int id = client.getId();
        clientService.addPassportToTheClient(id, newPassport);
        client.setPassport(newPassport);

        return client;
    }

    private void clientOrderPaymentMenu(Client readyClient, Car selectedCar, double orderPrice) {
        boolean orderPaymentMenuExit = false;
        Order newOrder = new Order();
        newOrder.setClient(readyClient);
        newOrder.setCar(selectedCar);
        newOrder.setPrice(orderPrice);
        do {
            boolean operationNumberValid = false;
            do {
                try {
                    System.out.println("Оплата заказа:\n" +
                            "Car - " + selectedCar + "\n" +
                            "price - " + orderPrice + "\n" +
                            "1. Оплатить\n" +
                            "2. Отмена заказа");
                    operationNumber = scanner.nextInt();
                    operationNumberValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Enter integer value...");
                    scanner.nextLine();
                    System.out.println("Exception: " + e);
                }
                switch (operationNumber) {
                    case 1:
                        newOrder.setStatus("НА РАССМОТРЕНИИ");
                        clientService.addOrder(newOrder);
                        orderPaymentMenuExit = true;
                        System.out.println("Опдата произошла успешно...");
                        break;
                    case 2:
                        System.out.println("Отмена заказа...\n" +
                                "Exit to the client menu...");
                        orderPaymentMenuExit = true;
                        break;
                    default:
                        System.out.println("There is no such operation. Try again");
                        break;

                }
            } while (!operationNumberValid);


        } while (!orderPaymentMenuExit);

    }


}
