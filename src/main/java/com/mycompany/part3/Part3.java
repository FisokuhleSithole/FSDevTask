

package com.mycompany.part3;

import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class Part3 {

  static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    static User currentUser = new User();

    public static void main(String[] args) {
        currentUser.registerUser();
        boolean loggedIn = currentUser.loginUser();

        if (loggedIn) {
            JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
            int num = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to process?"));

            for (int i = 0; i < num; i++) {
                Message msg = new Message();
                msg.messageID = msg.generateMessageID();
                msg.recipient = JOptionPane.showInputDialog("Enter recipient number (include international code):");
                msg.messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

                if (!msg.checkRecipientCell()) {
                    JOptionPane.showMessageDialog(null, "Invalid recipient number. Skipping message.");
                    continue;
                }

                if (msg.messageText.length() > 250) {
                    JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (msg.messageText.length() - 250));
                    continue;
                }

                msg.messageHash = msg.createMessageHash();

                String[] options = {"Send", "Disregard", "Store"};
                int choice = JOptionPane.showOptionDialog(null, "Choose action", "Message Options",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0 -> {
                        sentMessages.add(msg);
                        messageHashes.add(msg.messageHash);
                        messageIDs.add(msg.messageID);
                        JOptionPane.showMessageDialog(null, "Message sent:\n" + msg);
                    }
                    case 1 -> {
                        disregardedMessages.add(msg);
                        JOptionPane.showMessageDialog(null, "Message disregarded.");
                    }
                    case 2 -> {
                        storedMessages.add(msg);
                        messageHashes.add(msg.messageHash);
                        messageIDs.add(msg.messageID);
                        JOptionPane.showMessageDialog(null, "Message stored:\n" + msg);
                    }
                }
            }

            showMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting application.");
        }
    }

    public static void showMenu() {
        String[] menu = {"View Sent/Recipient", "View Longest Message", "Search by Message ID", "Search by Recipient", "Delete by Hash", "Report", "Exit"};
        while (true) {
            int opt = JOptionPane.showOptionDialog(null, "Choose an option:", "QuickChat Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);

            switch (opt) {
                case 0 -> displaySendersAndRecipients();
                case 1 -> displayLongestMessage();
                case 2 -> searchByMessageID();
                case 3 -> searchByRecipient();
                case 4 -> deleteByMessageHash();
                case 5 -> showReport();
                default -> System.exit(0);
            }
        }
    }

    public static void displaySendersAndRecipients() {
        StringBuilder sb = new StringBuilder("Senders and Recipients:\n");
        for (Message msg : sentMessages) {
            sb.append("You → ").append(msg.recipient).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void displayLongestMessage() {
        Message longest = sentMessages.stream().max(Comparator.comparingInt(m -> m.messageText.length())).orElse(null);
        if (longest != null) {
            JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.messageText);
        } else {
            JOptionPane.showMessageDialog(null, "No messages found.");
        }
    }

    public static void searchByMessageID() {
        String inputID = JOptionPane.showInputDialog("Enter Message ID:");
        for (Message msg : sentMessages) {
            if (msg.messageID.equals(inputID)) {
                JOptionPane.showMessageDialog(null, "Recipient: " + msg.recipient + "\nMessage: " + msg.messageText);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    public static void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog("Enter recipient number:");
        StringBuilder sb = new StringBuilder();
        for (Message msg : sentMessages) {
            if (msg.recipient.equals(recipient)) {
                sb.append(msg.messageText).append("\n");
            }
        }
        for (Message msg : storedMessages) {
            if (msg.recipient.equals(recipient)) {
                sb.append(msg.messageText).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No messages for this recipient.");
    }

    public static void deleteByMessageHash() {
        String hash = JOptionPane.showInputDialog("Enter Message Hash to delete:");
        for (Message msg : sentMessages) {
            if (msg.messageHash.equals(hash)) {
                sentMessages.remove(msg);
                JOptionPane.showMessageDialog(null, "Message \"" + msg.messageText + "\" successfully deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message hash not found.");
    }

    public static void showReport() {
        StringBuilder sb = new StringBuilder("--- Sent Messages Report ---\n");
        for (Message msg : sentMessages) {
            sb.append("Message ID: ").append(msg.messageID).append("\n")
              .append("Hash: ").append(msg.messageHash).append("\n")
              .append("Recipient: ").append(msg.recipient).append("\n")
              .append("Message: ").append(msg.messageText).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
