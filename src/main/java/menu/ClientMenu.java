package menu;

import pojo.Car;
import pojo.ClientPassport;
import pojo.Order;
import services.CarService;
import services.ClientService;
import info.InfoClientMenu;
import pojo.Client;
import services.OrderService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClientMenu {

    private Scanner scanner = new Scanner(System.in);
    private InfoClientMenu infoClientMenu = InfoClientMenu.getInfoClientMenu();
    private ClientService clientService = ClientService.getClientService();
    private CarService carService = CarService.getCarService();
    private OrderService orderService = OrderService.getOrderService();
    private static ClientMenu menu;
    private int operationNumber;

    public static ClientMenu getMenu() {
        if (menu == null) {
            menu = new ClientMenu();
        }
        return menu;
    }

    /*
     * Меню инициализации клиента
     * */
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

    /*
     * Меню регистрации клиента
     * */
    private void clientRegistrationMenu() {
        Client newClient;
        String clientLogin;
        String clientPassword;
        String repeatClientPassword;
        boolean exitClientRegistration = false;
        do {
            boolean clientPasswordValid = false;
            boolean clientLoginValid = true;
            System.out.println("Enter login...");
            clientLogin = scanner.nextLine();
            /*
             * Проверка, есть ли данный логин среди всех клиентов
             * */
            for (Client client : clientService.findAllClients()) {
                if (clientLogin.equals(client.getLogin())) {
                    clientLoginValid = false;
                }
            }
            /*
             * Если логин прошёл проверку, то создаём пароль
             * */
            if (clientLoginValid) {
                do {
                    System.out.println("Enter password...");
                    clientPassword = scanner.nextLine();
                    System.out.println("Repeat password...");
                    repeatClientPassword = scanner.nextLine();

                    /*
                     * Проверка на идентичность паролей
                     * */
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
                                clientPasswordValid = true;
                                exitClientRegistration = true;
                                System.out.println("Exit to the initialization menu...");
                            default:
                                System.out.println("There is no such operation. Try again");
                                break;
                        }
                    } else {
                        /*
                         * Если пароль прошёл проверку, то создаём нового клиента
                         * */

                        clientPasswordValid = true;
                        exitClientRegistration = true;
                        newClient = new Client();
                        newClient.setLogin(clientLogin);
                        newClient.setPassword(clientPassword);
                        /*
                         * Добавление нового клиента в бд
                         * */
                        clientService.clientRegistration(newClient);
                        /*
                         * После регистрации вызываем меню аккаунта клиента
                         * */
                        clientMenu(newClient);
                    }
                } while (!clientPasswordValid);
            } else {
                System.out.println("Пользователь с таким логином существует...");
            }
        } while (!exitClientRegistration);
    }

    /*
     * Меню входа в аккаунт клиента
     * */
    private void clientLoginMenu() {
        String clientLogin;
        String clientPassword;
        boolean exitClientLogin = false;
        boolean clientLoginValid = false;
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
                        clientLoginValid = true;
                        clientMenu(client);
                    }
                }
            }
            if (clientLoginValid == false) {
                boolean operationNumberValid = false;
                do {
                    try {
                        System.out.println("Login or password entered incorrectly...\n" +
                                "1. Try again \n" +
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
        } while (!exitClientLogin);
    }

    /*
     * Меню аккаунта клиента
     * */
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
                    carService.findAllFreeCars().forEach(System.out::println);

                    clientAutoInitMenu(client);

                    break;
                case 2:
                    clientOrdersMenu(client);
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

    /*
     * Меню выбора автомобиля и заполнения заказа
     * */
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

                        for (Car car : carService.findAllFreeCars()) {
                            if (car.getId() == carId) {
                                selectedCar = car;
                                selectedCarValid = true;
                            }
                        }
                        if (!selectedCarValid) {
                            System.out.println("Нет автомобиля с таким номером(id)...");
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

    /*
     * Меню для указания паспотрных данных клиента
     * */
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
        name = scanner.next();
        newPassport.setName(name);
        System.out.println("Введите фамилию...");
        surname = scanner.next();
        newPassport.setSurname(surname);
        System.out.println("Введите отчество...");
        patronymic = scanner.next();
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
        address = scanner.next();
        newPassport.setAddress(address);

        clientService.addPassportToTheClient(client, newPassport);
//        client.setPassport(newPassport);

        return client;
    }

    /*
     * Меню опаты заказа
     * */
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
                            "Car - " + selectedCar.getModel() + "\n" +
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
            } while (!operationNumberValid);
            switch (operationNumber) {
                    case 1:
                        orderPaymentMenuExit = true;
                        newOrder.setStatus("НА РАССМОТРЕНИИ");
                        carService.changingTheStatusToOccupied(selectedCar);
                        orderService.addOrder(newOrder);
                        System.out.println("Опдата произошла успешно...");
                        break;
                    case 2:
                        orderPaymentMenuExit = true;
                        System.out.println("Отмена заказа...\n" +
                                "Exit to the client menu...");
                        break;
                    default:
                        System.out.println("There is no such operation. Try again");
                        break;

                }
        } while (!orderPaymentMenuExit);
    }


    /*
    * Меню заказов клиента
    * */
    private void clientOrdersMenu(Client client) {
        boolean exitClientOrdersMenu = false;
        do {
            if (orderService.findAllOrdersByClient(client).size() != 0){
                System.out.println("Ваши заказы:");
//                for (Order order: orderService.findAllOrdersByClient(client)){
//                    System.out.println(order);
//                }
                orderService.findAllOrdersByClient(client).forEach(System.out::println);
            }else {
                System.out.println("У вас нет заказов");
            }
            boolean operationNumberValid = false;
            do {
                try {
                    System.out.println(
                            "1. Назад");
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
                    System.out.println("Back to the client menu...");
                    exitClientOrdersMenu = true;
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }

        } while (!exitClientOrdersMenu);
    }


}
