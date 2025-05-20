package utils;

import org.openqa.selenium.WebDriver;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Utility class to handle WebDriver retrieval from test classes for screenshots
 */
public class WebDriverManager {

    /**
     * Gets the WebDriver instance from a test class
     *
     * @param testInstance The test class instance
     * @return WebDriver instance or null if not found
     */
    public static WebDriver getDriverFromTest(Object testInstance) {
        if (testInstance == null) {
            return null;
        }

        // First try to get driver from the test class itself
        WebDriver driver = getDriverField(testInstance);
        if (driver != null) {
            return driver;
        }

        // If not found, try to get it from parent class (e.g., BaseTest)
        Class<?> currentClass = testInstance.getClass().getSuperclass();
        while (currentClass != null && !currentClass.equals(Object.class)) {
            try {
                Field driverField = currentClass.getDeclaredField("driver");
                driverField.setAccessible(true);
                Object fieldValue = driverField.get(testInstance);
                if (fieldValue instanceof WebDriver) {
                    return (WebDriver) fieldValue;
                }
            } catch (Exception e) {
                // Try next parent
            }
            currentClass = currentClass.getSuperclass();
        }

        // Try to get driver through a getter method
        try {
            Method getDriverMethod = testInstance.getClass().getMethod("getDriver");
            Object result = getDriverMethod.invoke(testInstance);
            if (result instanceof WebDriver) {
                return (WebDriver) result;
            }
        } catch (Exception e) {
            // No getter method found, continue with other approaches
        }

        // Try to find driver in parent class through getter
        currentClass = testInstance.getClass().getSuperclass();
        while (currentClass != null && !currentClass.equals(Object.class)) {
            try {
                Method getDriverMethod = currentClass.getMethod("getDriver");
                Object result = getDriverMethod.invoke(testInstance);
                if (result instanceof WebDriver) {
                    return (WebDriver) result;
                }
            } catch (Exception e) {
                // Try next parent
            }
            currentClass = currentClass.getSuperclass();
        }

        // If still not found, return null
        return null;
    }

    private static WebDriver getDriverField(Object testInstance) {
        try {
            Field driverField = testInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            Object fieldValue = driverField.get(testInstance);
            if (fieldValue instanceof WebDriver) {
                return (WebDriver) fieldValue;
            }
        } catch (Exception e) {
            // Driver field not found or not accessible
        }
        return null;
    }
}