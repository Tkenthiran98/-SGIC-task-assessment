package com.sgic.java.util;

import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        // Validate Email Address
        System.out.print("Enter an email address: ");
        String email = scanner.nextLine();
        String validationResult = Utility.validateEmail(email);
        System.out.println("Validation Result: " + validationResult);

        // Change Null to Empty String
        System.out.print("Enter a string (can be null): ");
        String inputString = scanner.nextLine();
        String updatedString = Utility.changeNullToString(inputString);
        System.out.println("Updated String: " + updatedString);

        // Convert Date String to Date
        System.out.print("Enter a date string (in format 'DD-MM-YYYY'): ");
        String dateString = scanner.nextLine();
        Date date = Utility.convertStringToDate(dateString);
        System.out.println("Parsed Date: " + date);

        // Add Two Numbers
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        double sum = Utility.add(num1, num2);
        System.out.println("Sum: " + sum);

        scanner.close();
    }
}
