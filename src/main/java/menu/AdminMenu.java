package menu;

import info.InfoAdminMenu;
import pojo.*;
import pojo.constant.DamageStatusConst;
import pojo.constant.EmploymentStatusConst;
import pojo.constant.OrderStatusConst;
import services.*;
import utils.NumberValidUtil;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class AdminMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final NumberValidUtil numberValidUtil = NumberValidUtil.getOperationNumberUtil();
    private final InfoAdminMenu infoAdminMenu = InfoAdminMenu.getInfoAdminMenu();
    private final UserService userService = UserService.getClientService();
    private final CarService carService = CarService.getCarService();
    private final OrderService orderService = OrderService.getOrderService();
    private final RefundService refundService = RefundService.getRefundService();
    private static AdminMenu menu = null;
    private int operationNumber;

    public static AdminMenu getMenu() {
        if (menu == null) {
            menu = new AdminMenu();
        }
        return menu;

    }

    /*
     * Меню администратора
     * */
    public void adminMenu(User admin) {
        boolean exitAdminMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminMenuInfo(admin));
            switch (operationNumber) {
                case 1:
                    adminCarsMenu();
                    break;
                case 2:
                    adminOrdersMenu();
                    break;
                case 3:
                    adminClientsMenu();
                    break;
                case 4:
                    exitAdminMenu = true;
                    System.out.println("Exit to the home menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitAdminMenu);
    }

    /*
     * Меню администратора для взаимодействя с автомобилями
     * */
    private void adminCarsMenu() {
        boolean exitAdminCarsMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminCarsMenuInfo());
            switch (operationNumber) {
                case 1:
                    boolean exitAllCarMenu = false;
                    System.out.printf("Список всех автомобилей:\n" +
                            "---------------------------------------------------------------------------------------------------\n" +
                            "%-15s%-30s%-15s%-25s%-15s\n" +
                            "---------------------------------------------------------------------------------------------------\n" +
                            "", "id", "model", "pricePerDay", "employmentStatus", "damageStatus");
                    carService.findAllCars().forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------------------------------------------");

                    do {
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, "1. Назад");
//                        Вместо if был switch
                        if (operationNumber == 1) {
                            System.out.println("Exit to the admin cars menu...");
                            exitAllCarMenu = true;
                        } else {
                            System.out.println("There is no such operation. Try again");
                        }
                    } while (!exitAllCarMenu);
                    break;
                case 2:
                    boolean exitAllFreeCars = false;
                    System.out.printf("Список свободных автомобилей:\n" +
                            "---------------------------------------------------------------------------------------------------\n" +
                            "%-15s%-30s%-15s%-25s%-15s\n" +
                            "---------------------------------------------------------------------------------------------------\n" +
                            "", "id", "model", "pricePerDay", "employmentStatus", "damageStatus");
                    carService.findAllFreeCars().forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    do {
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, "1. Назад");
//                        Вместо if был switch
                        if (operationNumber == 1) {
                            System.out.println("Exit to the admin cars menu...");
                            exitAllFreeCars = true;
                        } else {
                            System.out.println("There is no such operation. Try again");
                        }
                    } while (!exitAllFreeCars);
                    break;
                case 3:
                    String model;
                    double pricePerDay = 0;
                    System.out.println("Добавление нового автомобиля...\n" +
                            "Введите модель автомобиля:");
                    scanner.nextLine();
                    model = scanner.nextLine();
                    boolean priceValid = false;
                    do {
                        pricePerDay = numberValidUtil.doubleNumberValid(pricePerDay, "Введите цену за сутки:");
                        if (pricePerDay >= 0) {
                            priceValid = true;
                        } else {
                            System.out.println("Цена не может быть меньше 0...");
                        }
                    } while (!priceValid);

                    Car newCar = new Car();
                    newCar.setModel(model);
                    newCar.setPricePerDay(pricePerDay);
                    newCar.setEmploymentStatus(EmploymentStatusConst.FREE);
                    newCar.setDamageStatus(DamageStatusConst.WITHOUT_DAMAGE);
                    carService.addNewCar(newCar);
                    System.out.println("Добавлен новый автомобиль: " + newCar);

                    break;
                case 4:
                    int carId = 0;
                    boolean carIdValid = false;
                    System.out.printf("Список всех автомобилей:\n" +
                            "---------------------------------------------------------------------------------------------------\n" +
                            "%-15s%-30s%-15s%-25s%-15s\n" +
                            "---------------------------------------------------------------------------------------------------\n" +
                            "", "id", "model", "pricePerDay", "employmentStatus", "damageStatus");
                    carService.findAllCars().forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    carId = numberValidUtil.intNumberValid(carId, "Введите номер(id) автомобиля, которвый хотите удалить:");
                    for (Car car : carService.findAllCars()) {
                        if (carId == car.getId()) {
                            carIdValid = true;
                            carService.deleteCar(car);
                            System.out.println("Удаление автомобиля " + car);
                        }
                    }
                    if (!carIdValid) {
                        System.out.println("Нет автомобиля с данным номером(id)...");
                    }
                    break;
                case 5:
                    exitAdminCarsMenu = true;
                    System.out.println("Exit to the admin menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitAdminCarsMenu);
    }

    /*
     * Меню администратора для взаимодействя с заказами
     * */
    private void adminOrdersMenu() {
        boolean exitAdminOrdersMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminOrdersMenuInfo());
            switch (operationNumber) {
                case 1:
                    boolean exitAllOrdersMenu = false;
                    System.out.printf("Список всех заказов:\n" +
                            "---------------------------------------------------------------------------------------------------------------------\n" +
                            "%-6s%-30s%-15s%-15s%-20s%-15s%-6s\n", "id", "car", "client", "price", "status", "orderDate", "rentalPeriod\n" +
                            "---------------------------------------------------------------------------------------------------------------------");
                    orderService.findAllOrders().forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
                    do {
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, "1. Назад");
//                        Вместо if был switch
                        if (operationNumber == 1) {
                            System.out.println("Exit to the admin orders menu...");
                            exitAllOrdersMenu = true;
                        } else {
                            System.out.println("There is no such operation. Try again");
                        }
                    } while (!exitAllOrdersMenu);
                    break;
                case 2:
                    boolean exitOrdersUnderConsiderationMenu = false;
                    System.out.printf("Список заявок на заказ:\n" +
                            "---------------------------------------------------------------------------------------------------------------------\n" +
                            "%-6s%-30s%-15s%-15s%-20s%-15s%-6s\n", "id", "car", "client", "price", "status", "orderDate", "rentalPeriod\n" +
                            "---------------------------------------------------------------------------------------------------------------------");
                    orderService.findOrdersByStatus(OrderStatusConst.UNDER_CONSIDERATION).forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
                    do {
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminOrdersUnderConsiderationMenuInfo());
                        switch (operationNumber) {
                            case 1:
                                String message = "Введите номер(id) заявки:";
                                Order selectedOrder = null;
                                int orderId = 0;
                                boolean orderIdTrue = false;
                                orderId = numberValidUtil.intNumberValid(orderId, message);
                                for (Order order : orderService.findOrdersByStatus(OrderStatusConst.UNDER_CONSIDERATION)) {
                                    if (order.getId() == orderId) {
                                        selectedOrder = order;
                                        orderIdTrue = true;
                                    }
                                }
                                if (orderIdTrue) {
                                    adminOrderStatusMenu(selectedOrder);
                                } else {
                                    System.out.println("Заявки с данным номером(id) не существует...");
                                }


                            case 2:
                                System.out.println("Exit to the admin orders menu...");
                                exitOrdersUnderConsiderationMenu = true;
                                break;
                            default:
                                System.out.println("There is no such operation. Try again");
                                break;
                        }
                    } while (!exitOrdersUnderConsiderationMenu);
                    break;
                case 3:
                    adminRegisteringRefundMenu();
                    break;
                case 4:
                    System.out.println("Список возвратов:");
                    if (refundService.findAllRefund().size() == 0) {
                        System.out.println("Возвратов пока не имеется...");
                    }
                    refundService.findAllRefund().forEach(System.out::println);
                    boolean exit = false;
                    do {
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, "1. Назад");
                        if (operationNumber == 1) {
                            exit = true;
                        } else {
                            System.out.println("There is no such operation. Try again");
                        }
                    } while (!exit);
                    break;
                case 5:
                    exitAdminOrdersMenu = true;
                    System.out.println("Exit to the admin menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitAdminOrdersMenu);
    }

    /*
     * Меню администратора для указания статуса заказа
     */
    private void adminOrderStatusMenu(Order selectedOrder) {
        boolean exitAdminOrderStatusMenu = false;
        do {

            System.out.printf("Заказ №" + selectedOrder.getId() + ":\n" +
                    "---------------------------------------------------------------------------------------------------------------------\n" +
                    "%-6s%-30s%-15s%-15s%-20s%-15s%-6s\n", "id", "car", "client", "price", "status", "orderDate", "rentalPeriod\n" +
                    "---------------------------------------------------------------------------------------------------------------------\n" +
                    selectedOrder + "\n" +
                    "---------------------------------------------------------------------------------------------------------------------");
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminOrderStatusMenuInfo());
            switch (operationNumber) {
                case 1:
//                     Установка заказу статуса(ОДОБРЕНО)
                    selectedOrder.setOrderStatus(OrderStatusConst.APPROVES);
//                    Установка времени заказа
                    selectedOrder.setOrderDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
//                    У заказа пока нет возврата
//                    selectedOrder.setRefund(null);
                    orderService.update(selectedOrder);
                    System.out.println("Заказ одобрен...");
                    /*
                     * !!! Сделать, чтобы при оодобрении заявки к заказу добавлялось время
                     * */
                    exitAdminOrderStatusMenu = true;
                    break;
                case 2:
//                    Установка заказу статуса(ОТКЛОНЕНО)
                    selectedOrder.setOrderStatus(OrderStatusConst.REJECT);
//                    Установка времени заказа
                    selectedOrder.setOrderDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
//                    Уотклонённого заказа нет возврата
//                    selectedOrder.setRefund(null);
                    orderService.update(selectedOrder);
                    System.out.println("Заказ отклонён...");
                    /*
                     * Если заказ отменён, то автомобилю возвращается статус(СВОБОДНА)
                     * */
                    Car orderCar = selectedOrder.getCar();
//                    Смена статуса автомобиля на (свободно)
                    orderCar.setEmploymentStatus(EmploymentStatusConst.FREE);
                    carService.update(orderCar);
                    exitAdminOrderStatusMenu = true;
                    break;
                case 3:
                    boolean passportExit = false;
                    do {
                        System.out.printf("-----------------------------------------------------------------------------------------\n" +
                                "%-20s%-20s%-20s%-20s%-20s\n", "name","surname", "patronymic", "birthday", "address\n" +
                                "-----------------------------------------------------------------------------------------\n" +
                                ClientPassportService.getPassportService().read(selectedOrder.getClient().getPassport()) + "\n" +
                                "-----------------------------------------------------------------------------------------");
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, "1. Назад");
                        if (operationNumber == 1) {
                            System.out.println("Назад...");
                            passportExit = true;
                        } else {
                            System.out.println("There is no such operation. Try again");
                        }
                    } while (!passportExit);
                    break;
                case 4:
                    exitAdminOrderStatusMenu = true;
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitAdminOrderStatusMenu);
    }

    /*
     * Меню администратора для ренистрации возврата автомобиля
     * */
    private void adminRegisteringRefundMenu() {
        boolean exitRegisteringRefundMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminRegisteringRefundMenuInfo());
            switch (operationNumber) {
                case 1:
                    if (orderService.findOrdersByStatus(OrderStatusConst.APPROVES).size() != 0) {


                        System.out.printf("Список активных заказов:\n" +
                                "-----------------------------------------------------------------------------------------------\n" +
                                "%-6s%-30s%-15s%-15s%-20s%-15s\n", "id", "car", "client", "price", "status", "orderDate\n" +
                                "-----------------------------------------------------------------------------------------------");
                        orderService.findOrdersByStatus(OrderStatusConst.APPROVES).forEach(System.out::println);
                        System.out.println("-----------------------------------------------------------------------------------------------");
                        String message = "Введите номер(id) заказа:";
                        int orderId = 0;
                        boolean orderIdValid = false;
                        orderId = numberValidUtil.intNumberValid(orderId, message);
                        for (Order order : orderService.findOrdersByStatus(OrderStatusConst.APPROVES)) {
                            if (orderId == order.getId()) {
                                orderIdValid = true;
                                registeringRefund(order);
                                break;
                            }
                        }
                        if (!orderIdValid) {
                            System.out.println("Нет заказа с данным номером(id)...");
                        }
                    } else {
                        System.out.println("Нет активных заказов...");
                        exitRegisteringRefundMenu = true;
                    }
                    break;
                case 2:
                    exitRegisteringRefundMenu = true;
                    System.out.println("Exit to the admin orders menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitRegisteringRefundMenu);
    }

    /*
     * Метод регистарции возврата заказа
     * */
    private void registeringRefund(Order order) {
        Car car = order.getCar();
        Refund newRefund;
        operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.registeringRefund(car));
        switch (operationNumber) {
            case 1:
//                Создаём объект возврата
                newRefund = new Refund();
                newRefund.setDamageStatus(DamageStatusConst.WITHOUT_DAMAGE);
                newRefund.setPrice(0);
                refundService.addNewRefund(newRefund);

//                Устанавливаем заказу статус(возврат)
                order.setOrderStatus(OrderStatusConst.REFUND);
//                Добавляем заказу возврат
                order.setRefund(newRefund);
                orderService.updateWithRefund(order);

//                Добавляем возврату заказ
                newRefund.setOrder(order);
                refundService.update(newRefund);

//              Устанавливаем автомобилю статус(свободно)
                car.setEmploymentStatus(EmploymentStatusConst.FREE);
                carService.update(car);

                System.out.println("Возврат оформлен...");
                break;
            case 2:
                double price = 0;
                String typeDamage;
//                Создаём объект возврата
                newRefund = new Refund();
//                Указываем тип повреждения
                System.out.println("Укажите тип повреждения:");
                typeDamage = scanner.nextLine();
//                Устанавливаем счёт за ремон
                boolean priceValid = false;
                do {
                    price = numberValidUtil.doubleNumberValid(price, "Укажите счёт за ремонт:");
                    if (price > 0) {
                        priceValid = true;
                    } else {
                        System.out.println("Цена не может быть меньше нуля...");
                    }
                } while (!priceValid);
                newRefund.setDamageStatus(DamageStatusConst.WITH_DAMAGE);
                newRefund.setTypeDamage(typeDamage);
                newRefund.setPrice(price);
//                Сохраняем возврат в бд
                refundService.addNewRefund(newRefund);

//               Устанавливаем заказу статус(возврат)
                order.setOrderStatus(OrderStatusConst.REFUND);
//                Добавляем заказу возврат
                order.setRefund(newRefund);
                orderService.updateWithRefund(order);

//                Добавляем возврату заказ
                newRefund.setOrder(order);
                refundService.update(newRefund);

//                Устанавливаем автомобилю статус повреждения(повреждён)
                car.setDamageStatus(DamageStatusConst.WITH_DAMAGE);
                carService.update(car);

                System.out.println("Возврат оформлен...");
                break;
            default:
                System.out.println("There is no such operation. Try again");
                break;
        }
    }

    /*
     * Меню администратора для взаимодействя с клиентами
     * */
    private void adminClientsMenu() {
        boolean exitAdminClientsMenu = false;
        do {
//        Список всех клиентов
            System.out.printf("Список всех клиентов:\n" +
                    "--------------------------------------------\n" +
                    "%-6s%-30s%-15s\n", "id", "login", "password\n" +
                    "--------------------------------------------");
            userService.findAllClients().forEach(System.out::println);
            System.out.println("--------------------------------------------");
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminClientsMenuInfo());
            switch (operationNumber) {
                case 1:
                    User selectedUser = null;
                    int clientId = 0;
                    boolean clientIdValid = false;
                    String message = "Введите номер(id) клиента:";
                    clientId = numberValidUtil.intNumberValid(clientId, message);
                    for (User user : userService.findAllClients()) {
                        if (clientId == user.getId()) {
                            selectedUser = user;
                            clientIdValid = true;
                            break;
                        }
                    }
                    if (clientIdValid) {
                        boolean exitPassportMenu = false;
                        do {
                            ClientPassport selectedClientPassport = selectedUser.getPassport();
                            if (selectedClientPassport == null) {
                                System.out.println("Клиент не указывал паспортные данные...");
                            } else {
//                                Паспорт клиента
                                System.out.printf("Паспорт клиента(" + selectedUser.getLogin() + "):\n " +
                                        "-----------------------------------------------------------------------------------------------\n" +
                                        "%-6s%-20s%-20s%-20s%-20s%-20s", "id", "name", "surname", "patronymic", "birthday", "address\n" +
                                        "------------------------------------------------------------------------------------------------\n" +
                                        selectedClientPassport + "\n" +
                                        "------------------------------------------------------------------------------------------------\n");
//                                System.out.println(selectedClientPassport);
                            }
                            operationNumber = numberValidUtil.intNumberValid(operationNumber, "1. Назад");
                            if (operationNumber == 1) {
                                exitPassportMenu = true;
                            } else {
                                System.out.println("There is no such operation. Try again");
                            }
                        } while (!exitPassportMenu);
                    } else {
                        System.out.println("Нет клиента с данным номером(id)...");
                    }
                    break;
                case 2:
                    /*
                     * Реализовать метод, чтобы при удалении клиента удалялись его заказы, а у автомобилей из заказов появлялся статус FREE
                     * */
                    break;
                case 3:
                    exitAdminClientsMenu = true;
                    System.out.println("Exit to the admin menu...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitAdminClientsMenu);
    }


}
