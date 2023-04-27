package qu.core;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import qu.context.ContextData;
import qu.context.ScenarioContext;
import qu.extentReport.ExtentManager;
import qu.extentReport.ExtentTestManager;

import java.io.File;
import java.io.IOException;

public class TestListener extends TestFactory implements ITestListener {
  private static final Logger logger = Logger.getLogger(TestListener.class);
  private static String previousTest = null;

  public void onStart(ITestContext context) {
    logger.info("* Test Suite " + context.getName() + " started *");

    //Delete the directory
    final String directory = System.getProperty("user.dir") + "/TestReport";
    try {
      FileUtils.deleteDirectory(new File(directory));
    } catch (IOException e) {
      logger.error(e, e);
    }

  }

  public void onFinish(ITestContext context) {
    logger.info(("* Test Suite " + context.getName() + " ending *"));

    ExtentTestManager.endTest();
    try {
      ExtentManager.getInstance().flush();
    } catch (IOException e) {
      logger.error(e, e);
    }
    DriverService.dismissDriver();
  }

  public void onTestStart(ITestResult result) {
    ScenarioContext.setContext(ContextData.testName, result.getName().trim());
    ScenarioContext.setContext(ContextData.testNameClass, result.getInstanceName());

    if (!result.getInstance().toString().equalsIgnoreCase(previousTest)) {
      previousTest = result.getInstance().toString();
      ExtentTestManager.startTest(result.getInstanceName(), result.getName().toString().trim());
      result.getMethod().getDescription();
      logger.info("----- Testing " + result.getInstanceName() + " -----");
    }
  }

  public void onTestSuccess(ITestResult result) {
    String successText = "* Test passed success " + result.getMethod().getMethodName();
    logger.info("* Test took " + result.getEndMillis() / 1000 + " seconds");
    logger.info(successText);
  }

  public void onTestFailure(ITestResult result) {
    String failureText = "* Test failed " + result.getMethod().getMethodName();
    DriverService.dismissDriver();
  }

  public void onTestSkipped(ITestResult result) {
    logger.info("* Test " + result.getMethod().getMethodName() + " skipped...");
    ExtentTestManager.getTest().log(Status.SKIP, result.getMethod().getMethodName());
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    logger.info("* Test failed but within percentage % " + result.getMethod().getMethodName());
  }

  public static void onTestFinish() {
    logger.info("* Test ending *");
    ExtentTestManager.endTest();
    DriverService.dismissDriver();
  }
}
