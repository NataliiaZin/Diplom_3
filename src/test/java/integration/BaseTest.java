package integration;

import integration.core.properties.TestProperties;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import static integration.core.constant.AppEndpoints.API_ENDPOINT;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = TestProperties.getAppUrl() + API_ENDPOINT;
    }

    @After
    public void afterEach() {
        if (driver != null) {
            driver.quit();
        }
    }
}