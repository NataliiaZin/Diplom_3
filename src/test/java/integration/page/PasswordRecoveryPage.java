package integration.page;

import io.qameta.allure.Step;
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

    @Step("Открыть страницу востановление пароля")
    public void openPasswordRecoveryPage() {
        driver.get(getAppUrl() + PASSWORD_RECOVERY_ENDPOINT);
    }

    @Step("Клик по ссылочному тексту 'Войти'")
    public void clickOnLoginLink() {
        WebElement loginLink = findElementWaitingVisibility(By.xpath(LOGIN_LINK_XPATH));
        waitForElementToBeClickable(loginLink);
        loginLink.click();
    }

    @Step("Ожидание редиректа на страницу авторизации")
    public void waitForRedirectToLoginPage() {
        waitForRedirectTo(getAppUrl() + LOGIN_ENDPOINT);
    }
}
