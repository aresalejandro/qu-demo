package qu.core;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import qu.extentReport.ExtentTestManager;

import java.io.File;
import java.io.IOException;

public class DriverService {

  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  private static final Logger logger = Logger.getLogger(DriverService.class);
  private static String BROWSER = PropertyManager.getProperty("platform");

  public static void dismissDriver() {
    try {
      if (driver.get() == null)
        return;
      driver.get().quit();
      driver.set(null);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }

  }

  public static WebDriver getDriverInstance() {
    if (driver.get() == null) {
      try {
        driver.set(setDriver());
      } catch (Exception e) {
        logger.error(e.getMessage());
        dismissDriver();
        driver.set(setDriver());
      }
    }
    return driver.get();

  }

  public static WebDriver setDriver() {
    try {
      ProjectType projectType = ProjectType.get(BROWSER);
      WebDriverManager webDriverManager = WebDriverManager.getInstance(projectType.getDriver());
      webDriverManager.setup();

      ChromeOptions options = new ChromeOptions();
      options.addArguments("--ignore-certificate-errors");
      options.addArguments("--start-maximized");
      options.addArguments("--remote-allow-origins=*");
      options.addArguments("--lang=en");

      return new ChromeDriver(options);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static void takeScreenShot(String menssage) throws IOException {
      final String screenShotPath = ExtentTestManager.screenshotsTest();
      ExtentTestManager.getTest().log(Status.INFO, menssage,
              MediaEntityBuilder.createScreenCaptureFromPath(".\\screenshots" + File.separator + screenShotPath)
                      .build());
  }

  public static void takeScreenshotFail(String menssage) throws IOException {
      final String screenShotPath = ExtentTestManager.screenshotsTest();
      ExtentTestManager.getTest().log(Status.FAIL, menssage,
              MediaEntityBuilder.createScreenCaptureFromPath(".\\screenshots" + File.separator + screenShotPath)
                      .build());
  }

}
