package main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    // Method to validate if a string is a valid date
    public static boolean isValidDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Method to format a date to a specific string format
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    // Method to handle exceptions and print custom error messages
    @SuppressWarnings("CallToPrintStackTrace")
    public static void handleException(Exception e, String customMessage) {
        System.err.println(customMessage);
        e.printStackTrace();
    }

    // Method to validate if a string is numeric
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}