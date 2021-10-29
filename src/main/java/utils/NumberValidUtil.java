package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberValidUtil {

    private final Scanner scanner = new Scanner(System.in);
    private static NumberValidUtil numberValidUtil;

    public static NumberValidUtil getOperationNumberUtil() {
        if (numberValidUtil == null) {
            numberValidUtil = new NumberValidUtil();
        }
        return numberValidUtil;
    }

    /*
     * Метод для проверки данных типа int
     * */
    public int intNumberValid(int number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextInt();
                scanner.nextLine();
                numberValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter integer value...");
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }

    /*
     * Метод для проверки данных типа double
     * */
    public double doubleNumberValid(double number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextDouble();
                scanner.nextLine();
                numberValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter Double value...");
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }
}
