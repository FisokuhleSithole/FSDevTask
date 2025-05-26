
package com.mycompany.quickchatapp2;

public class Message {
    public static int messageCounter = 0;
   public String messageID, recipient, messageText, messageHash;

    // Generate 10-digit random message ID
  public  String generateMessageID() {
        return String.valueOf((long)(Math.random() * 1_000_000_0000L));
    }

    // Check if recipient cell number is valid
   public boolean checkRecipientCell() {
        return recipient.startsWith("+") && recipient.length() <= 10;
    }

    // Create message hash: first 2 of ID, message count, first & last words
   public String createMessageHash() {
        String[] words = messageText.split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length -1] : first;
        return (messageID.substring(0,2) + ":" + messageCounter + ":" + first + last).toUpperCase();
    }}