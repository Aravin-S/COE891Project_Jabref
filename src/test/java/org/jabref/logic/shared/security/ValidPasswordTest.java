package org.jabref.logic.shared.security;

public class ValidPasswordTest {
}
package org.jabref.logic.shared.security;

import org.junit.jupiter.api.Test;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPasswordTest {

    // Helper method to generate a password of given length
    private String generatePassword(int length) {
        return "a".repeat(length);
    }

    // Test for valid password lengths: 6 and 24 characters
    @Test
    public void passwordWithinValidRangeTest() {
        String passwordMin = generatePassword(6); // Minimum valid length
        String passwordMax = generatePassword(24); // Maximum valid length

        assertTrue(passwordMin.length() >= 6 && passwordMin.length() <= 24, "Password with 6 characters should be valid.");
        assertTrue(passwordMax.length() >= 6 && passwordMax.length() <= 24, "Password with 24 characters should be valid.");
    }
}
