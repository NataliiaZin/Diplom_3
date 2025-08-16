package integration.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static integration.core.properties.TestProperties.getAppUrl;

public class LoginPage extends BasePage {

    private static final String EMAIL_INPUT_XPATH = "//label[normalize-space(text())='Email']/following-sibling::input";
    private static final String PASSWORD_INPUT_XPATH = "//label[normalize-space(text())='Пароль']/following-sibling::input";
    private static final String LOGIN_BUTTON_XPATH = "//button[normalize-space(text())='Войти']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillEmailInput(String text) {
        WebElement nameInput = findElementWaitingVisibility(By.xpath(EMAIL_INPUT_XPATH));
        nameInput.sendKeys(text);
    }

    public void fillPasswordInput(String text) {
        WebElement nameInput = findElementWaitingVisibility(By.xpath(PASSWORD_INPUT_XPATH));
        nameInput.sendKeys(text);
    }

    public void clickLoginButton() {
        WebElement loginButton = findElementWaitingVisibility(By.xpath(LOGIN_BUTTON_XPATH));
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void waitForRedirectToLanding() {
        waitForRedirectTo(getAppUrl());
    }
}
