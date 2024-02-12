package com.sgic.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    
    // Method to validate email address
    public static String validateEmail(String email) {
        if (email == null || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return "Email is Invalid";
        }
        return null; // Return null if email is valid
    }
    
    // Method to change null to empty string
    public static String changeNullToString(String str) {
        return str == null ? "" : str;
    }
    
    // Method to convert date string to Date object with format 'DD-MM-YYYY'
    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(dateString);
    }
    
    // Generic method to add two numbers of any numeric type
    @SuppressWarnings("unchecked")
    public static <T extends Number> T add(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() + b.intValue());
        } else if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
        } else if (a instanceof Float) {
            return (T) Float.valueOf(a.floatValue() + b.floatValue());
        } else if (a instanceof Long) {
            return (T) Long.valueOf(a.longValue() + b.longValue());
        } else {
            throw new IllegalArgumentException("Unsupported numeric type");
        }
    }
}
