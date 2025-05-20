package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for taking screenshots
 */
public class ScreenshotUtil {

    /**
     * Takes a screenshot and saves it to the screenshots directory
     *
     * @param driver WebDriver instance
     * @param testName Name of the test for file naming
     * @return Path to saved screenshot or null if failed
     */
    public static Path takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.err.println("Cannot take screenshot: WebDriver is null");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";

        // Ensure directory exists
        Path dirPath = Paths.get(screenshotDir);
        try {
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            System.err.println("Failed to create screenshots directory: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        Path destPath = dirPath.resolve(screenshotName);

        try {
            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), destPath);
            System.out.println("Screenshot saved to: " + destPath.toAbsolutePath());
            return destPath;
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}