package service;

import javax.swing.JOptionPane;

public class FormValidation {

	public static boolean validateSignupFields(String username, String email, char[] password) {
        
        if (username.isEmpty() || email.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (username.length() < 4 || username.length() > 20) {
            JOptionPane.showMessageDialog(null, "Username must be between 4 and 20 characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.length < 8 || password.length > 20) {
            JOptionPane.showMessageDialog(null, "Password must be between 8 and 20 characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isPasswordComplex(password)) {
            JOptionPane.showMessageDialog(null, "Password must contain at least one uppercase letter, one lowercase letter, and one digit.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private static boolean isPasswordComplex(char[] password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;

        for (char c : password) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasDigit;
    }
}
