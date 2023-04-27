package qu.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qu.core.DriverService;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public abstract class pageCommons {

  private static final Logger logger = Logger.getLogger(pageCommons.class);

  public static void goToBaseUrl(String url) {
    DriverService.getDriverInstance().get(url);
  }

  public static WebElement getElement(By locator) {
    return DriverService.getDriverInstance().findElement(locator);
  }

  public static List<WebElement> getElements(By locator) {
    return DriverService.getDriverInstance().findElements(locator);
  }

  public static String getText(By locator) {
    waitForElementPresent(locator);
    WebElement element = getElement(locator);
    return getText(element);
  }

  public static String getText(WebElement element) {
    return element.getText();
  }

  public static void setInput(By locator, String value) {
    waitForElementPresent(locator);
    WebElement element = getElement(locator);
    element.clear();
    element.sendKeys(value);
  }

  public static void click(By locator) {
    waitForElementPresent(locator);
    WebElement element = getElement(locator);
    element.click();
  }

  public static void waitForElementPresent(By locator) {
    WebDriverWait wait = new WebDriverWait(DriverService.getDriverInstance(),
            Duration.ofSeconds(25));
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public static void waitForElementChange(By locator,String text) {
    WebDriverWait wait = new WebDriverWait(DriverService.getDriverInstance(),
            Duration.ofSeconds(25));
    wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
  }

  public static String getAlphabetRandom(int size){
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    StringBuilder finalString = new StringBuilder();

    Random random = new Random();

    for (int i = 0; i < size; i++) {
      int index = random.nextInt(alphabet.length());
      char character = alphabet.charAt(index);
      finalString.append(character);
    }

    return finalString.toString();
  }

  public static boolean alertIsPresent(){
    try{
      Thread.sleep(2000);
      Alert alert = DriverService.getDriverInstance().switchTo().alert();
      alert.accept();
      return true;
    }catch (Exception e){
      logger.info("Alert is not present");
      return false;
    }
  }

}
