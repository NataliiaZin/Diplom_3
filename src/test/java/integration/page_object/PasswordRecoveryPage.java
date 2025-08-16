package integration.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static integration.core.constant.AppEndpoints.LOGIN_ENDPOINT;
import static integration.core.constant.AppEndpoints.PASSWORD_RECOVERY_ENDPOINT;
import static integration.core.properties.TestProperties.getAppUrl;

public class PasswordRecoveryPage extends BasePage {

    private static final String LOGIN_LINK_XPATH = "//a[normalize-space(text())='Войти']";

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    public void openPasswordRecoveryPage() {
        driver.get(getAppUrl() + PASSWORD_RECOVERY_ENDPOINT);
    }

    public void clickOnLoginLink() {
        WebElement loginLink = findElementWaitingVisibility(By.xpath(LOGIN_LINK_XPATH));
        waitForElementToBeClickable(loginLink);
        loginLink.click();
    }

    public void waitForRedirectToLoginPage() {
        waitForRedirectTo(getAppUrl() + LOGIN_ENDPOINT);
    }
}
