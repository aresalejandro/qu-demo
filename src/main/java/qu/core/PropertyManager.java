package qu.core;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

  private static final Logger logger = Logger.getLogger(PropertyManager.class);
  private static final String PROPERTY_FILE_NAME = "config.properties";
  private static Properties properties;

  private PropertyManager() {
  }

  private static Properties getProperties() {
    if (properties == null) {
      try {
        loadProperties();
      } catch (IOException var1) {
        logger.error(var1, var1);
      }
    }
    return properties;
  }

  public static String getProperty(String propertyKey) {
    String value = getProperties().getProperty(propertyKey);
    return value;
  }

  private static void loadProperties() throws IOException {
    properties = new Properties();
    InputStream inputStream = PropertyManager.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
    properties.load(inputStream);
  }
}
