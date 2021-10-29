package menu;

import pojo.Car;
import pojo.ClientPassport;
import pojo.Order;
import services.CarService;
import services.ClientPassportService;
import services.ClientService;
import info.InfoClientMenu;
import pojo.Client;
import services.OrderService;
import utils.NumberValidUtil;

import java.util.List;
import java.util.Scanner;

public class ClientMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final NumberValidUtil numberValidUtil = NumberValidUtil.getOperationNumberUtil();
    private final InfoClientMenu infoClientMenu = InfoClientMenu.getInfoClientMenu();
    private final ClientService clientService = ClientService.getClientService();
    private final ClientPassportService passportService = ClientPassportService.getPassportService();
    private final CarService carService = CarService.getCarService();
    private final OrderService orderService = OrderService.getOrderService();
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
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientInitMenuInfo());
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
                    break;
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
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientPasswordsDontMatchInfo());
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
        Client authorizedClient = null;
        String clientLogin;
        String clientPassword;
        boolean exitClientLogin = false;
        boolean clientLoginValid = false;
        do {
            System.out.println("Enter login...");
            clientLogin = scanner.next();

            System.out.println("Enter password...");
            clientPassword = scanner.next();

            List<Client> allClients = clientService.findAllClients();
            for (Client client : allClients) {
                if (client.getLogin().equals(clientLogin)) {
                    if (client.getPassword().equals(clientPassword)) {
                        exitClientLogin = true;
                        clientLoginValid = true;
                        authorizedClient = client;
                        break;
                    }
                }
            }
            if (clientLoginValid) {
                clientMenu(authorizedClient);
            }
            if (!clientLoginValid) {
                operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientLoginOrPasswordIsIncorrect());
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
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientMenuInfo(client));
            switch (operationNumber) {
                case 1:
                    /*
                     * Открывается меню выбора атомобиля
                     * */
                    clientAutoInitMenu(client);
                    break;
                case 2:
                    /*
                     * Открывается меню заказов клиента
                     * */
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
        /*
         * Выводит список доступных автомобилей
         * */
        carService.findAllFreeCars().forEach(System.out::println);
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientCarInitMenuInfo());
            switch (operationNumber) {
                case 1:
                    boolean carInitExit = false;
                    Car selectedCar = null;
                    int carId = 0;
                    int rentalPeriod = 0;
                    double orderPrice;
                    boolean selectedCarValid = false;
                    do {
                        carId = numberValidUtil.intNumberValid(carId, "Ввыедите номер(id) автомобиля...");
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
                                rentalPeriod = numberValidUtil.intNumberValid(rentalPeriod, "Введите срок аренды автомобиля...");
                                if (rentalPeriod >= 1) {
                                    rentalPeriodValid = true;
                                } else {
                                    System.out.println("Срок аренды должен быть минимум 1 день...");
                                }
                            } while (!rentalPeriodValid);
                            /*
                             * Расчёт цены заказа
                             * */
                            orderPrice = rentalPeriod * selectedCar.getPricePerDay();
                            /*
                             * Если клиент впервый раз вводит паспортные данные, то выполняется данное условие
                             * */
                            boolean passportValid = false;
                            boolean exitPassportValid = false;
                            do {
                                if (client.getPassport() == null) {
                                    String message = "1. Ввести паспортные данные\n" +
                                            "2. Назад";
                                    operationNumber = numberValidUtil.intNumberValid(operationNumber,message);
                                    switch (operationNumber){
                                        case 1:
                                            ClientPassport passport = clientPassportInitMenu(client);
                                            clientService.addPassportToTheClient(client, passport);
                                            System.out.println("Паспортные данные заполнены");
                                            passportValid = true;
                                            exitPassportValid = true;
                                            break;
                                        case 2:
                                            exitPassportValid = true;
                                            break;
                                        default:
                                            System.out.println("There is no such operation. Try again");
                                            break;
                                    }
                                } else {
                                    passportValid = true;
                                    exitPassportValid = true;
                                }
                            }while (!exitPassportValid);
                            if (passportValid) {
                                /*
                                 * Открывается меню оплаты заказа, в него передаётся выбранный автомобиль,
                                 * цена за выбранный срок аренды и сам клиент
                                 * */
                                clientOrderPaymentMenu(client, selectedCar, orderPrice);
                                autoInitMenuExit = true;
                                carInitExit = true;
                            }else {
                                System.out.println("Вы не указали паспортные данные...");
                                carInitExit = true;
                            }
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
    private ClientPassport clientPassportInitMenu(Client client) {
        String name;
        String surname;
        String patronymic;
        int dayOfBirthday = 0;
        int monthOfBirthday = 0;
        int yearOfBirthday = 0;
        String address;


        System.out.println("Заполните паспортные данные:\n" +
                "Введите имя...");
        name = scanner.next();
        System.out.println("Введите фамилию...");
        surname = scanner.next();

        System.out.println("Введите отчество...");
        patronymic = scanner.next();

        boolean dayOfBirthdayValid = false;
        do {
            dayOfBirthday = numberValidUtil.intNumberValid(dayOfBirthday, "Введите день рождения...");
            if (dayOfBirthday >= 1 && dayOfBirthday <= 31) {
                dayOfBirthdayValid = true;
            } else {
                System.out.println("Не павильно введён день рождения[1-31]...");
            }
        } while (!dayOfBirthdayValid);

        boolean monthOfBirthdayValid = false;
        do {
            monthOfBirthday = numberValidUtil.intNumberValid(monthOfBirthday, "Введите месяц рождения...");
            if (monthOfBirthday >= 1 && monthOfBirthday <= 12) {
                monthOfBirthdayValid = true;
            } else {
                System.out.println("Не павильно введён месяц рождения[1-31]...");
            }

        } while (!monthOfBirthdayValid);

        boolean yearOfBirthdayValid = false;
        do {
            yearOfBirthday = numberValidUtil.intNumberValid(yearOfBirthday, "Введите год рождения...");
            if (yearOfBirthday >= 1000 && yearOfBirthday <= 3000) {
                yearOfBirthdayValid = true;
            } else {
                System.out.println("Не павильно введён год рождения[1000-3000]...");
            }
        } while (!yearOfBirthdayValid);
        System.out.println("Введите свой адрес...");
        address = scanner.next();
        ClientPassport newPassport = new ClientPassport();
        newPassport.setName(name);
        newPassport.setSurname(surname);
        newPassport.setPatronymic(patronymic);
        newPassport.setDayBirthday(dayOfBirthday);
        newPassport.setMonthBirthday(monthOfBirthday);
        newPassport.setYearBirthday(yearOfBirthday);
        newPassport.setAddress(address);
        passportService.addNewPassport(newPassport);

//        client.setPassport(newPassport);
        return newPassport;
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
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientOrderPaymentMenuInfo(selectedCar, orderPrice));
            switch (operationNumber) {
                case 1:
                    orderPaymentMenuExit = true;
                    newOrder.setStatus("НА РАССМОТРЕНИИ");
                    carService.setCarStatusToOccupied(selectedCar);
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
            if (orderService.findAllOrdersByClient(client).size() != 0) {
                System.out.println("Ваши заказы:");
                orderService.findAllOrdersByClient(client).forEach(System.out::println);
            } else {
                System.out.println("У вас нет заказов");
            }
            operationNumber = numberValidUtil.intNumberValid(operationNumber, "1. Назад");
//            Раньше использовался switch
            if (operationNumber == 1) {
                System.out.println("Back to the client menu...");
                exitClientOrdersMenu = true;
            } else {
                System.out.println("There is no such operation. Try again");
            }
        } while (!exitClientOrdersMenu);
    }
}
