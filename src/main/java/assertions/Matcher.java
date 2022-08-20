package assertions;

import io.qameta.allure.Allure;
import org.testng.Assert;

import java.util.concurrent.Callable;

import static org.testng.internal.EclipseInterface.*;

public class Matcher {

    private static final Character SEP_CHARACTER = ':';

    public static void assertTrue(Callable<Boolean> runMethod, String message) {
        try {
            Assert.assertTrue(runMethod.call(), formatMessage(message));
            addResult(formatMessage(message, Boolean.TRUE));
        } catch (Exception e) {
            addException(e.getMessage());
        }
    }

    public static void assertFalse(Callable<Boolean> runMethod, String message) {
        try {
            Assert.assertFalse(runMethod.call(), formatMessage(message));
            addResult(formatMessage(message, Boolean.FALSE));
        } catch (Exception e) {
            addException(e.getMessage());
        }
    }

    public static void assertEquals(Callable<Object> runMethod, Object expected, String message) {
        try {
            Assert.assertEquals(runMethod.call(), expected, formatMessage(message));
            addResult(formatMessage(message, "ok"));
        } catch (Exception e) {
            addException(e.getMessage());
        }
    }

    public static void assertNotNull(Object object, String message) {
        try {
            Assert.assertNotNull(object, formatMessage(message));
            addResult(formatMessage(message, "ok"));
        } catch (Exception e) {
            addException(e.getMessage());
        }
    }

    private static String formatMessage(String message) {
        String formatted = "";
        if (message != null) {
            formatted = message + SEP_CHARACTER;
        }
        return formatted;
    }

    private static String formatMessage(String message, Object actual) {
        return formatMessage(message) + " " + OPENING_CHARACTER + actual + CLOSING_CHARACTER;
    }

    private static void addResult(String message) {
        Allure.addAttachment("Actual result", message);
    }

    private static void addException(String message) {
        Allure.addAttachment("Exception", message);
    }
}
