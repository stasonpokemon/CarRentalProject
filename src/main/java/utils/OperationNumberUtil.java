package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OperationNumberUtil {

    private static Scanner scanner = new Scanner(System.in);
    private static OperationNumberUtil operationNumberUtil;

    public static OperationNumberUtil getOperationNumberUtil() {
        if (operationNumberUtil == null){
            operationNumberUtil = new OperationNumberUtil();
        }
        return operationNumberUtil;
    }

    public int operationNumberValid(int operationNumber, String textInfo){
        boolean operationNumberValid = false;
        do {
            try {
                System.out.println(textInfo);
                operationNumber = scanner.nextInt();
                scanner.nextLine();
                operationNumberValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Enter integer value...");
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!operationNumberValid);
        return operationNumber;
    }
}
