package qu.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import qu.context.ContextData;
import qu.context.ScenarioContext;
import qu.core.DriverService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExtentTestManager {

  private static final Logger logger = Logger.getLogger(ExtentTestManager.class);

  static ExtentReports extent;
  private static ThreadLocal<ExtentTest> extent_test = new ThreadLocal<ExtentTest>();

  static {
    try {
      extent = ExtentManager.getInstance();
    } catch (IOException e) {
      logger.error(e, e);
    }
  }

  public static synchronized ExtentTest getTest() {
    return extent_test.get();
  }

  public static synchronized String screenshotsTest() {
    WebDriver driver = DriverService.getDriverInstance();

    String targetLocation = null;
    String nameTest = ScenarioContext.getContext(ContextData.testName).toString();
    String testClassName = ScenarioContext.getContext(ContextData.testNameClass).toString();
    String screenShotName = nameTest + System.nanoTime() + ".png";
    String fileSeperator = System.getProperty("file.separator");
    String reportsPath = ExtentManager.REPORT_PATH + fileSeperator + "screenshots";

    try {
      File file = new File(reportsPath + fileSeperator + testClassName);
      if (!file.exists()) {
        file.mkdirs();
      }

      File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

      targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;

      File targetFile = new File(targetLocation);
      FileHandler.copy(screenshotFile, targetFile);

    } catch (FileNotFoundException e) {
      logger.error("File not found exception occurred while taking screenshot" + e.getMessage(), e);
    } catch (Exception e) {
      logger.error("An exception occurred while taking screenshot" + e.getCause(), e);
    }

    return testClassName + fileSeperator + screenShotName;
  }

  public static synchronized void endTest() {
    extent.flush();
  }

  public static synchronized ThreadLocal<ExtentTest> startTest(String testName, String name) {
    ExtentTest test = extent.createTest(testName.substring(testName.lastIndexOf(".") + 1));
    test.assignCategory(name);
    extent_test.set(test);
    return extent_test;
  }

  public static synchronized void logExtentReportStatusPass(String message) {
    ExtentTestManager.getTest().log(Status.PASS, message);
  }

  public static synchronized void logExtentReportStatusFail(String menssage) throws IOException {
    DriverService.takeScreenshotFail(menssage);
  }
}
