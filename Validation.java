package MasterPackge;

import java.util.Scanner;

/**
 *
 * @author GMT
 */
public class Validation {

    private final static Scanner in = new Scanner(System.in);


    public static int checkInputIntLimit(int min, int max) {

        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }


    public static String checkInputString() {

        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static String getString(String input, String error) {
        System.out.print(input);
        String output = new Scanner(System.in).nextLine().trim();
        if (!output.isEmpty()) {
            return output;
        }
        System.err.println(error);
        return getString(input, error);
    }

    public static int getInteger(String input, String error) {
        try {
            return Integer.parseInt(getString(input, error));
        } catch (Exception e) {
            System.out.println(error);
            return getInteger(input, error);
        }
    }
    public static double getDouble(String input, String error) {
        try {
            return Double.parseDouble(getString(input, error));
        } catch (Exception e) {
            System.err.println(error);
            return getDouble(input, error);
        }
    }

    public static double getDouble(String input, String error, double min) {
        double output = getDouble(input, error);
        if (output >= min) {
            return output;
        }
        System.out.println("Just input a number from " + min);
        return getDouble(input, error, min);
    }

    public static double getDouble(String input, String error, double min, double max) {
        double output = getDouble(input, error, min);
        if (output <= max) {
            return output;
        }
        System.out.println("Just input a number from " + min + " to " + max);
        return getDouble(input, error, min, max);
    }
    

    public static String getInputYN(String input, String error, boolean nothing){
        boolean t = true;
        String result = null;
        do {            
            String a = getString(input, error);
            if(a.equalsIgnoreCase("Y")||a.equalsIgnoreCase("N")){
                result = a;
                t = false;
            }
        } while (t);
        return result;
    }
    
}