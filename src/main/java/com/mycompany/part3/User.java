
package com.mycompany.part3;

import javax.swing.JOptionPane;

//this will check the users details etc
public class User {
   public String username;
    public String password;
    public String cell;

    public boolean checkUsername() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=]).{8,}");
    }

    public boolean checkCellPhoneNumber() {
        return cell != null && cell.startsWith("+") && cell.length() > 10;
    }

    public void registerUser() {
        while (true) {
            username = JOptionPane.showInputDialog("Enter a username (must contain underscore and be no more than 5 characters):");
            if (!checkUsername()) {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
                continue;
            } else {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
            }

            password = JOptionPane.showInputDialog("Enter a password (min 8 characters, 1 capital, 1 number, 1 special character):");
            if (!checkPasswordComplexity()) {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
                continue;
            } else {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
            }

            cell = JOptionPane.showInputDialog("Enter your cell phone number (must include international code):");
            if (!checkCellPhoneNumber()) {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                continue;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully captured.");
                break;
            }
        }
    }

    public boolean loginUser() {
        String loginUsername = JOptionPane.showInputDialog("Enter your username to login:");
        String loginPassword = JOptionPane.showInputDialog("Enter your password to login:");

        if (username.equals(loginUsername) && password.equals(loginPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome " + username + ", it is great to see you again.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect, try again.");
            return false;
        }
    }}
