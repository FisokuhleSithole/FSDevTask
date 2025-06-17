
package com.mycompany.part3;


public class Message {
      public static int messageCounter = 0;
    public String messageID;
    public String recipient;
    public String messageText;
    public String messageHash;

    public String generateMessageID() {
        return String.format("%010d", (int)(Math.random() * 1_000_000_000));
    }

    public boolean checkRecipientCell() {
        return recipient != null && recipient.startsWith("+") && recipient.length() <= 13;
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;
        return (messageID.substring(0, 2) + ":" + messageCounter++ + ":" + first + last).toUpperCase();
    }

    @Override
    public String toString() {
        return "Message ID: " + messageID + "\nHash: " + messageHash + "\nTo: " + recipient + "\nMessage: " + messageText;
    }
}

