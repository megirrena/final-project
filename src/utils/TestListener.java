package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName() + ". Taking screenshot...");

        try {
            Object testInstance = result.getInstance();
            WebDriver driver = WebDriverManager.getDriverFromTest(testInstance);

            if (driver != null) {
                takeScreenshot(driver, result.getName());
            } else {
                System.err.println("Could not capture screenshot: WebDriver is null");
            }
        } catch (Exception e) {
            System.err.println("Exception while taking screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        createScreenshotDirectory();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test execution completed: " + context.getName());
    }

    private void createScreenshotDirectory() {
        try {
            Path screenshotDir = Paths.get(System.getProperty("user.dir"), "screenshots");
            Files.createDirectories(screenshotDir);
        } catch (IOException e) {
            System.err.println("Failed to create screenshots directory: " + e.getMessage());
        }
    }

    private void takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destPath = Paths.get(screenshotDir, screenshotName);

            Files.copy(srcFile.toPath(), destPath);
            System.out.println("Screenshot saved to: " + destPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}