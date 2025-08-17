package integration.page;

import io.qameta.allure.Step;
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

    @Step("Заполнить поле 'Email'")
    public void fillEmailInput(String text) {
        WebElement nameInput = findElementWaitingVisibility(By.xpath(EMAIL_INPUT_XPATH));
        nameInput.sendKeys(text);
    }

    @Step("Заполнить поле 'Пароль'")
    public void fillPasswordInput(String text) {
        WebElement nameInput = findElementWaitingVisibility(By.xpath(PASSWORD_INPUT_XPATH));
        nameInput.sendKeys(text);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        WebElement loginButton = findElementWaitingVisibility(By.xpath(LOGIN_BUTTON_XPATH));
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    @Step("Ожидание редиректа на страницу лендинг")
    public void waitForRedirectToLanding() {
        waitForRedirectTo(getAppUrl());
    }
}
