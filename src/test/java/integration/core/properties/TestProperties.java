package integration.core.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private static final String PROPERTIES_FILE_PATH = "src/test/resources/test.properties";
    private static final String APP_URL_PROPERTY_NAME = "application.url";
    private static final String CHROME_BROWSER_VERSION_PROPERTY_NAME = "chrome.browser.version";
    private static final String YANDEX_BROWSER_VERSION_PROPERTY_NAME = "yandex.browser.version";
    private static final String YANDEX_BROWSER_BINARY_VERSION_PROPERTY_NAME = "yandex.browser.binary.path";
    private static final String YANDEX_DRIVER_PATH_PROPERTY_NAME = "yandex.driver.path";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read properties file.", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getAppUrl() {
        return getProperty(APP_URL_PROPERTY_NAME);
    }

    public static String getChromeBrowserVersion() {
        return getProperty(CHROME_BROWSER_VERSION_PROPERTY_NAME);
    }

    public static String getYandexBrowserVersion() {
        return getProperty(YANDEX_BROWSER_VERSION_PROPERTY_NAME);
    }

    public static String getYandexBrowserBinaryPath() {
        return getProperty(YANDEX_BROWSER_BINARY_VERSION_PROPERTY_NAME);
    }
    public static String getYandexDriverPath() {
        return getProperty(YANDEX_DRIVER_PATH_PROPERTY_NAME);
    }
}
