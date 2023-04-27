package qu.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum ProjectType {

  CHROME {
    @Override
    public Class<? extends WebDriver> getDriver() {
      return ChromeDriver.class;
    }

    @Override
    public WebDriver initDriver() {
      return new ChromeDriver();
    }
  },
  FIREFOX {
    @Override
    public Class<? extends WebDriver> getDriver() {
      return FirefoxDriver.class;
    }

    @Override
    public WebDriver initDriver() {
      return new FirefoxDriver();
    }
  },
  EDGE {
    @Override
    public Class<? extends WebDriver> getDriver() {
      return EdgeDriver.class;
    }

    @Override
    public WebDriver initDriver() {
      return new EdgeDriver();
    }
  };

  public abstract Class<? extends WebDriver> getDriver();

  public abstract WebDriver initDriver();

  public static ProjectType get(String key) {
    try {
      return Enum.valueOf(ProjectType.class, key);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Invalid value for enum ProjectTypeEnum : " + key);
    }
  }

}