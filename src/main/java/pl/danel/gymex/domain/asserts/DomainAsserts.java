package pl.danel.gymex.domain.asserts;

import java.util.regex.Pattern;

public class DomainAsserts {

    public static void assertState(boolean stateCheckResult, String message) {
        if (!stateCheckResult) {
            throw new InvalidStateException(message);
        }
    }

    public static void assertArgumentNotNull(Object value, String message) {
        if (value == null) {
            throw new InvalidStateException(message);
        }
    }

    public static void assertArgumentNotTooLong(String value, int maxLength, String message) {
        if (value == null || value.trim().length() > maxLength) {
            throw new InvalidStateException(message);
        }
    }

    public static void assertArgumentMatchesPattern(String regex, String text, String message) {
        if (!Pattern.matches(regex, text)) {
            throw new InvalidStateException(message);
        }
    }

}
