package it.reloia.tecnogui.dataparsing.food;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ExpiryDateChecker {
    public static boolean isDateExpired(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        try {
            LocalDate expiryDate = LocalDate.parse(dateString, formatter);
            LocalDate today = LocalDate.now();
            return expiryDate.isBefore(today);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + dateString);
            return false; // Treat invalid dates as non-expired
        }
    }
}
