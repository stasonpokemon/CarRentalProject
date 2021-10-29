package menu;

import info.InfoAdminMenu;
import pojo.*;
import services.*;
import utils.NumberValidUtil;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final NumberValidUtil numberValidUtil = NumberValidUtil.getOperationNumberUtil();
    private final InfoAdminMenu infoAdminMenu = InfoAdminMenu.getInfoAdminMenu();
    private final AdminService adminService = AdminService.getAdminService();
    private final ClientService clientService = ClientService.getClientService();
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
     * Меню инициализации администратора
     * */
    public void adminInitializationMenu() {
        boolean exitAdminInitializationMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.initializationMenuInfo());
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

    /*
     * Меню входа в учётную запись администраотра
     * */
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
            if (!adminLoginValid) {
                operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.incorrectLoginOrPasswordEntry());
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

    /*
     * Меню администратора
     * */
    private void adminMenu(Admin admin) {
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
                    System.out.println("Список всех автомобилей:");
                    System.out.printf("%-15s%-30s%-15s%-25s%-15s", "id", "model", "pricePerDay", "employmentStatus", "damageStatus");

                    carService.findAllCars().forEach(System.out::println);
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
                    System.out.println("Список свободных автомобилей:");
                    carService.findAllFreeCars().forEach(System.out::println);
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
                    newCar.setEmploymentStatus("FREE");
                    newCar.setDamageStatus("WITHOUT DAMAGE");
                    carService.addNewCar(newCar);
                    System.out.println("Добавлен новый автомобиль: " + newCar);

                    break;
                case 4:
                    int carId = 0;
                    boolean carIdValid = false;
                    System.out.println("Список всех автомобилей:");
                    carService.findAllCars().forEach(System.out::println);
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
                    System.out.println("Список всех заказов:");
                    orderService.findAllOrders().forEach(System.out::println);
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
                    System.out.println("Список заявок на заказ:");
                    orderService.findOrdersUnderConsideration().forEach(System.out::println);
                    do {
                        operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminOrdersUnderConsiderationMenuInfo());
                        switch (operationNumber) {
                            case 1:
                                String message = "Введите номер(id) заявки:";
                                Order selectedOrder = null;
                                int orderId = 0;
                                boolean orderIdTrue = false;
                                orderId = numberValidUtil.intNumberValid(orderId, message);
                                for (Order order : orderService.findOrdersUnderConsideration()) {
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
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminOrderStatusMenuInfo(selectedOrder));
            switch (operationNumber) {
                case 1:
                    orderService.setOrderApprovedStatus(selectedOrder);
                    System.out.println("Заказ одобрен...");
                    /*
                     * !!! Сделать, чтобы при оодобрении заявки к заказу добавлялось время
                     * */
                    exitAdminOrderStatusMenu = true;
                    break;
                case 2:
                    orderService.setOrderRejectStatus(selectedOrder);
                    System.out.println("Заказ отклонён...");
                    /*
                     * Если заказ отменён, то автомобилю возвращается статус(СВОБОДНА)
                     * */
                    Car orderCar = selectedOrder.getCar();
                    carService.setCarStatusToFree(orderCar);
                    exitAdminOrderStatusMenu = true;
                    break;
                case 3:
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
                    if (orderService.findOrdersApproved().size() != 0) {
                        orderService.findOrdersApproved().forEach(System.out::println);
                        String message = "Введите номер(id) заказа:";
                        int orderId = 0;
                        boolean orderIdValid = false;
                        orderId = numberValidUtil.intNumberValid(orderId, message);
                        for (Order order : orderService.findOrdersApproved()) {
                            if (orderId == order.getId()) {
                                orderIdValid = true;
                                registeringRefund(order);
                                break;
                            }
                        }
                        if (!orderIdValid) {
                            System.out.println("Нет заказа с данным номером(id)...");
                        }
                    }else {
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
     * Метод регистарции заказа
     * */
    private void registeringRefund(Order order) {
        Car car = order.getCar();
        Refund newRefund;
        operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.registeringRefund(car));
        switch (operationNumber) {
            case 1:
                /*
                 * Создаём объект возврата
                 * */
                newRefund = new Refund();
                /*
                 * Устанавливаем автомобилю статус(свободно)
                 * */
                carService.setCarStatusToFree(car);
                /*
                 * Устанавливаем заказу статус(возврат)
                 * */
                orderService.setOrderRefundStatus(order);

                newRefund.setOrder(order);
                newRefund.setDamageStatus("WITHOUT DAMAGE");
                newRefund.setPrice(0);
                refundService.addNewRefund(newRefund);
                System.out.println("Возврат оформлен...");
                break;
            case 2:
                double price = 0;
                /*
                 * Создаём объект возврата
                 * */
                newRefund = new Refund();
                /*
                 * Устанавливаем автомобилю статус повреждения(повреждён)
                 * */
                carService.setCarDamageStatusToWithDamage(car);
                /*
                 * Устанавливаем заказу статус(возврат)
                 * */
                orderService.setOrderRefundStatus(order);
                /*
                 * Устанавливаем счёт за ремон
                 * */
                boolean priceValid = false;
                do {
                    price = numberValidUtil.doubleNumberValid(price, "Укажите счёт за ремонт:");
                    if (price > 0) {
                        priceValid = true;
                    } else {
                        System.out.println("Цена не может быть меньше нуля...");
                    }
                } while (!priceValid);
                newRefund.setOrder(order);
                newRefund.setDamageStatus("WITH DAMAGE");
                newRefund.setPrice(price);
                refundService.addNewRefund(newRefund);
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
            clientService.findAllClients().forEach(System.out::println);
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoAdminMenu.adminClientsMenuInfo());
            switch (operationNumber) {
                case 1:
                    Client selectedClient = null;
                    int clientId = 0;
                    boolean clientIdValid = false;
                    String message = "Введите номер(id) клиента:";
                    clientId = numberValidUtil.intNumberValid(clientId, message);
                    for (Client client : clientService.findAllClients()) {
                        if (clientId == client.getId()) {
                            selectedClient = client;
                            clientIdValid = true;
                            break;
                        }
                    }
                    if (clientIdValid) {
                        ClientPassport selectedClientPassport = selectedClient.getPassport();
                        if (selectedClientPassport == null) {
                            System.out.println("Клиент не указывал паспортные данные...");
                        } else {
                            System.out.println(selectedClientPassport);
                        }
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
