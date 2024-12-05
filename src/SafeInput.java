import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int value = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                done = true;
            } else {
                trash = pipe.next();
                System.out.println("Invalid input, you entered: " + trash + ". Please try again.");
                pipe.nextLine();
            }
        } while (!done);
        return value;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double value = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                done = true;
            } else {
                trash = pipe.next();
                System.out.println("Invalid input, you entered: " + trash + ". Please try again.");
            }
            pipe.nextLine();
        } while (!done);
        return value;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int value = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                if (value >= low && value <= high) {
                    done = true;
                } else {
                    System.out.println("Error: " + value + " is not between " + low + " and " + high + ". Please try again!");
                }
            } else {
                trash = pipe.next();
                System.out.println("Invalid input, you entered: " + trash + ". Please try again with an integer.");
            }
            pipe.nextLine();
        } while (!done);
        return value;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double value = 0.0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                if (value >= low && value <= high) {
                    done = true;
                } else {
                    System.out.println("Error: " + value + " is not between " + low + " and " + high + ". Please try again!");
                }
            } else {
                trash = pipe.next();
                System.out.println("Invalid input, you entered: " + trash + ". Please try again with a double.");
            }
            pipe.nextLine();
        } while (!done);
        return value;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean validInput = false;
        boolean result = false;
        String input = "";

        do {
            System.out.print("\n" + prompt + "[Y/N]: ");
            input = pipe.nextLine().toLowerCase();

            if (input.equals("y")) {
                validInput = true;
                result = true;
            } else if (input.equals("n")) {
                validInput = true;
                result = false;
            } else {
                System.out.println("Invalid input, you entered: " + input + ". Please enter Y or N!");
            }
        } while (!validInput);
        return result;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String value;
        do {
            System.out.print("\n" + prompt + ": ");
            value = pipe.nextLine();
        } while (!value.matches(regEx));
        return value;
    }

    public static void prettyHeader(String msg) {
        int width = 60;
        int msgLength = msg.length();
        int padding = (width - msgLength - 6) / 2;

        for (int i = 0; i < width; i++) {
            System.out.print("*");
        }
        System.out.println();

        System.out.print("***");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        if ((msgLength + 6) % 2 != 0) {
            System.out.print(" ");
        }
        System.out.println("***");
        for (int i = 0; i < width; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static double CtoF(double celsius) {
        return celsius * 9.0/5.0 + 32;
    }
}
