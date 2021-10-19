package pl.danel.gymex.domain.asserts;

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

}
