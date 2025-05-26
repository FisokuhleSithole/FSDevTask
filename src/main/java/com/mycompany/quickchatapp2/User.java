
package com.mycompany.quickchatapp2;

public class User {
     public String username, password, cell;

    // Check if username has underscore and is <= 5 chars
   public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Check if password meets complexity: 8+, uppercase, number, special char
  public  boolean checkPasswordComplexity() {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    // Check if cell number has +code and >10 digits
   public boolean checkCellPhoneNumber() {
        return cell.startsWith("+") && cell.length() > 10 && cell.matches("\\+\\d+");
    }
}