package integration.core.provider;

import integration.core.constant.DriverName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static integration.core.properties.TestProperties.*;

public class WebDriverProvider {

    public static WebDriver createWebDriver() {
        DriverName driverName = getDriverName();
        switch (driverName) {
            case CHROME:
                String chromeBrowserVersion = getChromeBrowserVersion();
                WebDriverManager.chromedriver().browserVersion(chromeBrowserVersion).setup();
                return new ChromeDriver();
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", getYandexDriverPath());
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBrowserVersion(getYandexBrowserVersion());
                chromeOptions.setBinary(getYandexBrowserBinaryPath());
                return new ChromeDriver(chromeOptions);
            default:
                throw new IllegalArgumentException("Unknown driver name: " + driverName);
        }
    }
}
