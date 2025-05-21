package utils;

import org.openqa.selenium.WebDriver;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class WebDriverManager {

    public static WebDriver getDriverFromTest(Object testInstance) {
        if (testInstance == null) {
            return null;
        }

        WebDriver driver = getDriverField(testInstance);
        if (driver != null) {
            return driver;
        }

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
            }
            currentClass = currentClass.getSuperclass();
        }

        try {
            Method getDriverMethod = testInstance.getClass().getMethod("getDriver");
            Object result = getDriverMethod.invoke(testInstance);
            if (result instanceof WebDriver) {
                return (WebDriver) result;
            }
        } catch (Exception e) {
        }

        currentClass = testInstance.getClass().getSuperclass();
        while (currentClass != null && !currentClass.equals(Object.class)) {
            try {
                Method getDriverMethod = currentClass.getMethod("getDriver");
                Object result = getDriverMethod.invoke(testInstance);
                if (result instanceof WebDriver) {
                    return (WebDriver) result;
                }
            } catch (Exception e) {
            }
            currentClass = currentClass.getSuperclass();
        }

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
        }
        return null;
    }
}