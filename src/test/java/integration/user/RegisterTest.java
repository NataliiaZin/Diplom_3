package integration.user;

import integration.BaseTest;
import integration.core.provider.WebDriverProvider;
import integration.core.util.ApiUtils;
import integration.page.RegisterPage;
import integration.user.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static integration.core.constant.AppEndpoints.LOGIN_ENDPOINT;
import static integration.core.properties.TestProperties.getAppUrl;
import static integration.core.util.TestUtils.generate;
import static integration.core.util.TestUtils.initTestUser;
import static org.junit.Assert.assertEquals;

@Epic("Stellar Burgers Integration")
@Feature("Регистрация пользователя")
@RunWith(Parameterized.class)
public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;
    private User testUser;

    @Before
    public void setUp() {
        driver = WebDriverProvider.createWebDriver();
        this.registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        testUser = initTestUser();
    }

    @Test
    @Story("Регистрация")
    @Description("Успешная регистрация пользователя")
    public void registerUserTest() {
        registerPage.fillEmailInput(testUser.getEmail());
        registerPage.fillNameInput(testUser.getName());
        registerPage.fillPasswordInput(testUser.getPassword());
        registerPage.clickRegisterButton();
        registerPage.waitForRedirectToLoginPage();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = getAppUrl() + LOGIN_ENDPOINT;
        assertEquals(expectedUrl, currentUrl);
        ApiUtils.deleteUser(testUser);
    }

    @Test
    @Story("Регистрация c некорректным полем")
    @Description("Неуспешная регистрация с паролем менее 6 символов")
    public void failedRegisterUserWithIncorrectPasswordLengthTest() {
        testUser.setPassword(generate(5));
        registerPage.fillEmailInput(testUser.getEmail());
        registerPage.fillNameInput(testUser.getName());
        registerPage.fillPasswordInput(testUser.getPassword());
        registerPage.clickRegisterButton();
        String expectedValidationText = "Некорректный пароль";
        String validationText = registerPage.getIncorrectPasswordValidationText();
        assertEquals(expectedValidationText, validationText);
    }
}
