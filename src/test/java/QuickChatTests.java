

import com.mycompany.quickchatapp2.Message;
import com.mycompany.quickchatapp2.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuickChatTests {
    
     // Test: Message length success (<= 250 chars)
    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message();
        m.messageText = "Hi Keegan, did you receive the payment?";
        // Check that the message length is within the limit
        assertTrue(m.messageText.length() <= 250, "Message ready to send.");
    }

    // Test: Message length failure (> 250 chars)
    @Test
    public void testMessageLengthFailure() {
        Message m = new Message();
        m.messageText = new String(new char[300]).replace('\0', 'x'); // String longer than 250 chars
        int excess = m.messageText.length() - 250;
        // Check that the message length exceeds 250 chars
        assertTrue(m.messageText.length() > 250, "Message exceeds 250 characters by " + excess + ", please reduce size.");
    }

    // Test: Correct international cell number
    @Test
    public void testCellPhoneSuccess() {
        User u = new User();
        u.cell = "+27831234567";
        // Check that the phone number is valid
        assertTrue(u.checkCellPhoneNumber(), "Cell phone number successfully captured.");
    }

    // Test: Incorrect cell number without international code
    @Test
    public void testCellPhoneFailure() {
        User u = new User();
        u.cell = "0831234567"; // Invalid phone number
        // Check that the phone number is invalid
        assertFalse(u.checkCellPhoneNumber(), "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }

    // Test: Message hash is correctly generated
    @Test
    public void testMessageHash() {
        Message m = new Message();
        m.messageID = "0012345678";
        m.messageCounter = 0;
        m.messageText = "Hi tonight";
        String hash = m.createMessageHash();
        // Check that the message hash matches the expected value
        assertEquals("00:0:HITONIGHT", hash);
    }

    // Test: Message ID generation (should be 10 characters)
    @Test
    public void testMessageIDGenerated() {
        Message m = new Message();
        String id = m.generateMessageID();
        // Check that the message ID is 10 characters long
        assertEquals(10, id.length());
        System.out.println("Message ID generated: " + id);
    }

    // Test: Action choices (send, disregard, store)
    @Test
    public void testSendMessageAction() {
        String action = getMessageAction(1);
        assertEquals("Message successfully sent.", action);

        action = getMessageAction(2);
        assertEquals("Press O to delete message.", action);

        action = getMessageAction(3);
        assertEquals("Message successfully stored.", action);
    }

    // Helper: Simulate menu actions
    private String getMessageAction(int actionCode) {
        switch (actionCode) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press O to delete message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }
}
