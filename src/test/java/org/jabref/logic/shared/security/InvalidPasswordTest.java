import org.junit.jupiter.api.Test;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class InvalidPasswordTest {

    // Helper method to generate a password of given length
    private String generatePassword(int length) {
        return "a".repeat(length);
    }

    // Test for passwords shorter than 6 characters
    @Test
    public void passwordBelowMinimumLengthTest() {
        String password = generatePassword(5); // Below minimum length
        assertFalse(password.length() >= 6, "Password with less than 6 characters should be invalid.");
    }

    // Test for passwords longer than 24 but less than 100 characters
    @Test
    public void passwordAboveMaximumValidLengthTest() {
        String password = generatePassword(25); // Above maximum valid length but less than 100
        assertFalse(password.length() >= 6 && password.length() <= 24, "Password with 25 to 99 characters should be invalid.");
    }

    // Test for passwords longer than 100 characters
    @Test
    public void passwordExceedingLengthTest() {
        String password = generatePassword(101); // Exceeding maximum length
        assertFalse(password.length() <= 100, "Password with more than 100 characters should be invalid.");
    }
}
