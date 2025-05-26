

package com.mycompany.quickchatapp2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class QuickChatApp2 {

     //Objects to work with
 static Scanner sc = new Scanner(System.in);
    static List<Message> sentMessages = new ArrayList<>();
    static User user = new User();
   
    //main class where the program will run from
    public static void main(String[] args) {
       registerUser();
        if (loginUser()) {
            runMenu();
        }
    }
    
    //Method to register the  user 
    static void registerUser() {
        System.out.println("=== Register ===");
        System.out.print("Enter username: ");
        user.username = sc.nextLine();
        if (user.checkUserName()) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            System.exit(0);
        }

        System.out.print("Enter password: ");
        user.password = sc.nextLine();
        if (user.checkPasswordComplexity()) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            System.exit(0);
        }

        System.out.print("Enter SA cell number (+code...): ");
        user.cell = sc.nextLine();
        if (user.checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            System.exit(0);
        }
    }
    //User will now be able to login should all registration requyirements pass

    static boolean loginUser() {
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        if (uname.equals(user.username) && pass.equals(user.password)) {
            System.out.println("Welcome user, it’s great to see you again");
            return true;
        } else {
            System.out.println("Username or password incorrect, try again");
            return false;
        }
    }

    //Menu for the new chat feature we are adding
    static void runMenu() {
        System.out.println("\nWelcome to QuickChat.");
        while (true) {
            System.out.println("\n1) Send Message\n2) Show Recently Sent Messages\n3) Quit");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                sendMessages();
            } else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else if (choice == 3) {
                break;
            }
        }
        System.out.println("Total messages sent: " + sentMessages.size());
    }

    //Message
    static void sendMessages() {
        System.out.print("How many messages to send? ");
        int num = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < num; i++) {
            Message msg = new Message();
            msg.messageID = msg.generateMessageID();
            msg.messageCounter = ++Message.messageCounter;

            System.out.print("Recipient number: ");
            msg.recipient = sc.nextLine();
            if (!msg.checkRecipientCell()) {
                System.out.println("Recipient cell number invalid.");
                i--;
                continue;
            }

            System.out.print("Message: ");
            msg.messageText = sc.nextLine();
            if (msg.messageText.length() > 250) {
                System.out.println("Please enter a message of less than 250 characters.");
                i--;
                continue;
            }

            msg.messageHash = msg.createMessageHash();
            System.out.println("Message ready to send. Hash: " + msg.messageHash);

            System.out.println("1) Send\n2) Disregard\n3) Store for later");
            int action = sc.nextInt();
            sc.nextLine();

            if (action == 1) {
                sentMessages.add(msg);
                JOptionPane.showMessageDialog(null, "MessageID: " + msg.messageID + "\nHash: " + msg.messageHash + "\nRecipient: " + msg.recipient + "\nMessage: " + msg.messageText);
                System.out.println("Message successfully sent.");
            } else if (action == 2) {
                System.out.println("Message disregarded.");
            } else if (action == 3) {
                System.out.println("Message successfully stored.");
            }
        }
    }}