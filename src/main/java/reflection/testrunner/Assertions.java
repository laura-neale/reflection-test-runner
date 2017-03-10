package reflection.testrunner;

public class Assertions {

    public static void assertEquals(Object expected, Object actual) {
        if (!actual.equals(expected)) {
            String message = String.format("Expected and actual did not match: (expected = %s) (actual = %s)", expected, actual);
            throw new AssertionError(message);
        }
    }

}
