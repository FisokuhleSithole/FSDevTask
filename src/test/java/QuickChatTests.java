import com.mycompany.part3.Message;
import com.mycompany.part3.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickChatTests {
// all tests for the features will run
    @Test
    public void testUsernameFormatValid() {
        User user = new User();
        user.username = "abc_";
        assertTrue(user.checkUsername());
    }

    @Test
    public void testUsernameFormatInvalid() {
        User user = new User();
        user.username = "abcdef";
        assertFalse(user.checkUsername());
    }

    @Test
    public void testPasswordComplexityValid() {
        User user = new User();
        user.password = "Passw0rd!";
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordComplexityInvalid() {
        User user = new User();
        user.password = "password";
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneValid() {
        User user = new User();
        user.cell = "+27831234567";
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneInvalid() {
        User user = new User();
        user.cell = "0831234567";
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message();
        msg.messageText = "Hello!";
        assertTrue(msg.messageText.length() <= 250);
    }

    @Test
    public void testMessageLengthTooLong() {
        Message msg = new Message();
        msg.messageText = "x".repeat(300);
        assertTrue(msg.messageText.length() > 250);
    }

    @Test
    public void testMessageHashFormat() {
        Message msg = new Message();
        msg.messageID = "0012345678";
        Message.messageCounter = 0;
        msg.messageText = "Hi tonight";
        String expectedHash = "00:0:HITONIGHT";
        assertEquals(expectedHash, msg.createMessageHash());
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message();
        String id = msg.generateMessageID();
        assertEquals(10, id.length());
    }

    @Test
    public void testRecipientCellValidation() {
        Message msg = new Message();
        msg.recipient = "+27834567890";
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellInvalid() {
        Message msg = new Message();
        msg.recipient = "0834567890";
        assertFalse(msg.checkRecipientCell());
    }
}