package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Email {

    private String emailId;
    private String password;


    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
//    Check the ASCII value of each character for the following conditions:
//If the ASCII value lies in the range of [65, 90], then it is an uppercase letter.
//If the ASCII value lies in the range of [97, 122], then it is a lowercase letter.
//If the ASCII value lies in the range of [48, 57], then it is a number.
//If the ASCII value lies in the ranges [32, 47], [58, 64], [91, 96] or [123, 126], then it is a special character
        boolean isdigit = false;
        boolean islowerCase = false;
        boolean isUpperCase = false;
        boolean isSpecialChar = false;
        if (oldPassword.equals(password)) {
            // then eligible to change the password
            for (int i = 0; i < newPassword.length(); i++) {
                char ch = newPassword.charAt(i);
                if (!isdigit) {
                    if (Character.isDigit(ch)) {
                        isdigit = true;
                        continue;
                    }
                }
                if (!isUpperCase) {
                    if (Character.isUpperCase(ch)) {
                        isUpperCase = true;
                        continue;
                    }
                }
                if (!islowerCase) {
                    if (Character.isLowerCase(ch)) {
                        islowerCase = true;
                        continue;
                    }
                }
                if (!isSpecialChar) {
                    if (!Character.isDigit(ch) && (!Character.isLetter(ch)) && (!Character.isWhitespace(ch))) {
                        isSpecialChar = true;
                    }
                }
            }

            if (isdigit && islowerCase && isUpperCase && isSpecialChar) {
                this.password = newPassword;
            }
        }
    }
}