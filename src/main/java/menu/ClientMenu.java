package menu;

import pojo.*;
import pojo.constant.EmploymentStatusConst;
import pojo.constant.OrderStatusConst;
import services.CarService;
import services.ClientPassportService;
import services.UserService;
import info.InfoClientMenu;
import services.OrderService;
import utils.NumberValidUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ClientMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final NumberValidUtil numberValidUtil = NumberValidUtil.getOperationNumberUtil();
    private final InfoClientMenu infoClientMenu = InfoClientMenu.getInfoClientMenu();
    private final UserService userService = UserService.getClientService();
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
     * Меню аккаунта клиента
     * */
    public void clientMenu(User user) {

        boolean exitClientMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientMenuInfo(user));
            switch (operationNumber) {
                case 1:
                    /*
                     * Открывается меню выбора атомобиля
                     * */
                    clientAutoInitMenu(user);
                    break;
                case 2:
                    /*
                     * Открывается меню заказов клиента
                     * */
                    clientOrdersMenu(user);
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
    private void clientAutoInitMenu(User user) {
        boolean autoInitMenuExit = false;
        /*
         * Выводит список доступных автомобилей
         * */
        System.out.printf("Список свободных автомобилей:\n" +
                "---------------------------------------------------------------------------------------------------\n" +
                "%-15s%-30s%-15s%-25s%-15s\n" +
                "---------------------------------------------------------------------------------------------------\n" +
                "", "id", "model", "pricePerDay", "employmentStatus", "damageStatus");
        carService.findAllFreeCars().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------------------");
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
                                if (user.getPassport() == null) {
                                    String message = "1. Ввести паспортные данные\n" +
                                            "2. Назад";
                                    operationNumber = numberValidUtil.intNumberValid(operationNumber,message);
                                    switch (operationNumber){
                                        case 1:
                                            clientPassportInitMenu(user);
                                            System.out.println("Паспортные данные заполнены...");
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
                                 * цена за выбранный срок аренды, срок аренды и сам клиент
                                 * */
                                clientOrderPaymentMenu(user, selectedCar, orderPrice, rentalPeriod);
                                autoInitMenuExit = true;
                            }else {
                                System.out.println("Вы не указали паспортные данные...");
                            }
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
    private void clientPassportInitMenu(User user) {
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
        newPassport.setBirthday(new Timestamp(new GregorianCalendar(yearOfBirthday, monthOfBirthday - 1, dayOfBirthday).getTimeInMillis()));
        newPassport.setAddress(address);

//        Если мы работаем с собственным пулом соединений, то эта строчка с добавлением паспорта в бд нужна
        passportService.addNewPassport(newPassport);

//        Добавляем клиенту паспорт
        user.setPassport(newPassport);
//        Без обьявления листа заказов выдаёт ошибку
        user.setOrders(new ArrayList<>()); // Обязательно
        userService.update(user);
    }

    /*
     * Меню опаты заказа
     * */
    private void clientOrderPaymentMenu(User readyUser, Car selectedCar, double orderPrice, int rentalPeriod) {
        boolean orderPaymentMenuExit = false;
        Order newOrder = new Order();
        newOrder.setClient(readyUser);
        newOrder.setCar(selectedCar);
        newOrder.setPrice(orderPrice);
        newOrder.setRentalPeriod(rentalPeriod);
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientOrderPaymentMenuInfo(selectedCar, orderPrice));
            switch (operationNumber) {
                case 1:
                    orderPaymentMenuExit = true;


                    Refund refund = new Refund();
                    refund.setId(1);
                    newOrder.setRefund(refund);
                    newOrder.setOrderStatus(OrderStatusConst.UNDER_CONSIDERATION);
//                    Смена статуса автомобиля на (занято)
                    selectedCar.setEmploymentStatus(EmploymentStatusConst.OCCUPIED);
                    carService.update(selectedCar);
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
    private void clientOrdersMenu(User user) {
        boolean exitClientOrdersMenu = false;
        do {
            if (orderService.findAllOrdersByClient(user).size() != 0) {
                System.out.printf("Ваши заказы:\n" +
                        "-----------------------------------------------------------------------------------------------------------------\n" +
                        "%-6s%-30s%-15s%-15s%-20s%-15s%-6s\n","id", "car", "client", "price", "status","orderDate","rentalPeriod\n" +
                        "-----------------------------------------------------------------------------------------------------------------");
                orderService.findAllOrdersByClient(user).forEach(System.out::println);
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
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
