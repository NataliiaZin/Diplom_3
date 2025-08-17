package integration.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static integration.core.constant.AppEndpoints.LOGIN_ENDPOINT;
import static integration.core.constant.AppEndpoints.REGISTER_ENDPOINT;
import static integration.core.properties.TestProperties.getAppUrl;

public class RegisterPage extends BasePage {

    private static final String NAME_INPUT_XPATH = "//label[normalize-space(text())='Имя']/following-sibling::input";
    private static final String EMAIL_INPUT_XPATH = "//label[normalize-space(text())='Email']/following-sibling::input";
    private static final String PASSWORD_INPUT_XPATH = "//label[normalize-space(text())='Пароль']/following-sibling::input";
    private static final String REGISTER_BUTTON_XPATH = "//button[normalize-space(text())='Зарегистрироваться']";
    private static final String INCORRECT_PASSWORD_VALIDATION_TEXT_XPATH = "//p[text()='Некорректный пароль']";
    private static final String LOGIN_LINK_XPATH = "//a[normalize-space(text())='Войти']";

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу регистрации")
    public void openRegisterPage() {
        driver.get(getAppUrl() + REGISTER_ENDPOINT);
    }

    @Step("Заполнить поле 'Имя'")
    public void fillNameInput(String text) {
        WebElement nameInput = findElementWaitingVisibility(By.xpath(NAME_INPUT_XPATH));
        nameInput.sendKeys(text);
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

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement registerButton = findElementWaitingVisibility(By.xpath(REGISTER_BUTTON_XPATH));
        waitForElementToBeClickable(registerButton);
        registerButton.click();
    }

    @Step("Получить текст ошибки валидации поля 'Пароль'")
    public String getIncorrectPasswordValidationText() {
        WebElement validationText = findElementWaitingVisibility(By.xpath(INCORRECT_PASSWORD_VALIDATION_TEXT_XPATH));
        return validationText.getText();
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
