package utils;

import java.util.Scanner;

//@author SE173593
public class Utils {

    public static String getString(String message) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(message);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("This fill cannot be empty!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getUpdateString(String message, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static String getStringRegex(String message, String pattern, String notification) {
        boolean check = true;
        String result = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message);
            result = sc.nextLine();
            if (result.length() == 0) {
                System.out.println("This fill cannot be empty");
            } else if (!result.matches(pattern)) {
                System.out.println(notification);
            } else {
                check = false;
            }
        } while (check == true);
        return result;
    }

    public static String getUpdateStringRegex(String message, String pattern, String oldData) {
        boolean check = true;
        String result;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message);
            result = sc.nextLine();
            if (result.isEmpty()) {
                result = oldData;
                check = false;
            } else if (!result.matches(pattern)) {
                System.out.println("Input wrong data format, please try it again!");
            } else {
                check = false;
            }
        } while (check == true);
        return result;
    }

    public static int getInt(String message) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Please input only integer!");
            }
        } while (check);
        return number;
    }

    public static int getInt(String message, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Please input only integer!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getUpdateInt(String message, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Please input only integer!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static double getDouble(String message, double min, double max) {
        boolean check = true;
        double number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                number = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Please input only number!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static double getUpdateDouble(String message, double min, double max, double oldData) {
        boolean check = true;
        double number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Please input only number!");
            }
        } while (check || number > max || number < min);
        return number;
    }
}
