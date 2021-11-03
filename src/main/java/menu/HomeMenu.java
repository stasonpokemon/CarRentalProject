package menu;

import info.InfoClientMenu;
import info.InfoHomeMenu;
import pojo.User;
import pojo.constant.UserRoleConst;
import services.UserService;
import utils.NumberValidUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final NumberValidUtil numberValidUtil = NumberValidUtil.getOperationNumberUtil();
    private final InfoHomeMenu infoHomeMenu = InfoHomeMenu.getInfoHomeMenu();
    private final InfoClientMenu infoClientMenu = InfoClientMenu.getInfoClientMenu();
    private final UserService userService = UserService.getClientService();
    private final ClientMenu clientMenu = ClientMenu.getMenu();
    private final AdminMenu adminMenu = AdminMenu.getMenu();
    private static HomeMenu homeMenu = null;
    private static int operationNumber;

    public static HomeMenu getHomeMenu() {
        if (homeMenu == null) {
            homeMenu = new HomeMenu();
        }
        return homeMenu;
    }


    public void menu() {
        boolean exitMenu = false;
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber, infoHomeMenu.input());
            switch (operationNumber) {
                case 1:
                    userLoginMenu();
                    break;
                case 2:
                    userRegistrationMenu();
                    break;
                case 3:
                    exitMenu = true;
                    System.out.println("Close...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitMenu);
    }

    private void userLoginMenu() {
        User authorizedUser = null;
        String userLogin;
        String userPassword;
        boolean exitUserLogin = false;
        boolean userLoginValid = false;
        do {
            System.out.println("Enter login...");
            userLogin = scanner.next();

            System.out.println("Enter password...");
            userPassword = scanner.next();

            List<User> allUsers = userService.findAllUsers();
            for (User user : allUsers) {
                if (user.getLogin().equals(userLogin)) {
                    if (user.getPassword().equals(userPassword)) {
                        exitUserLogin = true;
                        userLoginValid = true;
                        authorizedUser = user;
                        break;
                    }
                }
            }
            if (userLoginValid) {
                if (UserRoleConst.CLIENT_ROLE.equals(authorizedUser.getRole())) {
                    clientMenu.clientMenu(authorizedUser);
                }
                if (UserRoleConst.ADMIN_ROLE.equals(authorizedUser.getRole())) {
                    adminMenu.adminMenu(authorizedUser);
                }
            }
            if (!userLoginValid) {
                operationNumber = numberValidUtil.intNumberValid(operationNumber, infoClientMenu.clientLoginOrPasswordIsIncorrect());
                switch (operationNumber) {
                    case 1:
                        break;
                    case 2:
                        exitUserLogin = true;
                        System.out.println("Exit to the initialization menu...");
                    default:
                        System.out.println("There is no such operation. Try again");
                        break;
                }
            }
        } while (!exitUserLogin);
    }

    private void userRegistrationMenu() {
        User newUser;
        String clientLogin;
        String clientPassword;
        String repeatClientPassword;
        boolean exitClientRegistration = false;
        do {
            boolean clientPasswordValid = false;
            boolean clientLoginValid = true;
            System.out.println("Enter login...");
            clientLogin = scanner.next();
            /*
             * Проверка, есть ли данный логин среди всех users
             * */
            for (User user : userService.findAllUsers()) {
                if (clientLogin.equals(user.getLogin())) {
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
                    clientPassword = scanner.next();
                    System.out.println("Repeat password...");
                    repeatClientPassword = scanner.next();
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
                        newUser = new User();
                        newUser.setLogin(clientLogin);
                        newUser.setPassword(clientPassword);
                        newUser.setRole(UserRoleConst.CLIENT_ROLE);
//                        newUser.setOrders(new ArrayList<>());
                        /*
                         * Добавление нового клиента в бд
                         * */
                        userService.clientRegistration(newUser);
                        /*
                         * После регистрации вызываем меню аккаунта клиента
                         * */
                        clientMenu.clientMenu(newUser);
                    }
                } while (!clientPasswordValid);
            } else {
                System.out.println("Пользователь с таким логином существует...");
            }
        } while (!exitClientRegistration);
    }
}
