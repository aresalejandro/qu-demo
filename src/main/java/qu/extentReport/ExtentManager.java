package qu.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
  private static ExtentReports extent;
  private static String fileSeperator = System.getProperty("file.separator");
  public static final String REPORT_PATH = System.getProperty("user.dir") + fileSeperator + "TestReport";
  private static Integer Id = 0;

  public static synchronized ExtentReports getInstance() throws IOException {
    if (extent == null)
      createInstance();
    return extent;
  }

  //Create an extent report instance
  public static synchronized ExtentReports createInstance() {
    String reportFileName = "QU-Automation.html";
    String fileName = getReportPath(REPORT_PATH, reportFileName);

    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setDocumentTitle(reportFileName);
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setReportName(reportFileName);
    htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    htmlReporter.config().setCSS("css-string");
    htmlReporter.config().setJS("js-string");

    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);

    return extent;
  }

  //Create the report path
  private static synchronized String getReportPath(String path, String reportFileName) {
    String reportFileLocation = REPORT_PATH + fileSeperator + reportFileName;
    File testDirectory = new File(path);
    if (!testDirectory.exists()) {
      if (testDirectory.mkdirs()) {
        return reportFileLocation;
      } else {
        return System.getProperty("user.dir");
      }
    }
    return reportFileLocation;
  }
}
