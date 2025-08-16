package integration.user;

import integration.BaseTest;
import integration.core.constant.DriverName;
import integration.core.provider.WebDriverProvider;
import integration.page_object.LandingPage;
import integration.page_object.LoginPage;
import integration.page_object.PasswordRecoveryPage;
import integration.page_object.RegisterPage;
import integration.user.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static integration.core.properties.TestProperties.getAppUrl;
import static integration.core.util.ApiUtils.deleteUser;
import static integration.core.util.ApiUtils.registerUser;
import static integration.core.util.TestUtils.initTestUser;
import static org.junit.Assert.assertEquals;

@Epic("Stellar Burgers Integration")
@Feature("Авторизация пользователя")
@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    private final LandingPage landingPage;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;
    private final PasswordRecoveryPage passwordRecoveryPage;
    private User testUser;

    public LoginTest(DriverName driverName) {
        driver = WebDriverProvider.createWebDriver(driverName);
        this.landingPage = new LandingPage(driver);
        this.loginPage = new LoginPage(driver);
        this.registerPage = new RegisterPage(driver);
        this.passwordRecoveryPage = new PasswordRecoveryPage(driver);
    }


    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{{DriverName.CHROME}, {DriverName.YANDEX},});
    }

    @Before
    public void setUp() {
        landingPage.openLandingPage();
        testUser = initTestUser();
        registerUser(testUser);
    }

    @After
    public void tearDown() {
        deleteUser(testUser);
    }

    @Test
    @Story("Успешная авторизация с лендинга")
    @Description("Успешная авторизация пользователя через кнопку \"Войти в аккаунт\"")
    public void loginUserByLandingLoginButtonTest() {
        landingPage.clickLoginButton();
        loginPage.fillEmailInput(testUser.getEmail());
        loginPage.fillPasswordInput(testUser.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForRedirectToLanding();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = getAppUrl();
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    @Story("Успешная авторизация с лендинга")
    @Description("Успешная авторизация пользователя через линк \"Личный кабинет\"")
    public void loginUserByPersonalAccountLinkTest() {
        landingPage.clickOnPersonalAccountLink();
        loginPage.fillEmailInput(testUser.getEmail());
        loginPage.fillPasswordInput(testUser.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForRedirectToLanding();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = getAppUrl();
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    @Story("Успешная авторизация со страницы регистрации")
    @Description("Успешная авторизация пользователя через линк \"Войти\"")
    public void loginUserByLinkOnRegisterPageTest() {
        registerPage.openRegisterPage();
        registerPage.clickOnLoginLink();
        registerPage.waitForRedirectToLoginPage();
        loginPage.fillEmailInput(testUser.getEmail());
        loginPage.fillPasswordInput(testUser.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForRedirectToLanding();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = getAppUrl();
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    @Story("Успешная авторизация со страницы восстановления пароля")
    @Description("Успешная авторизация пользователя через линк \"Войти\"")
    public void loginUserByPasswordRecoveryLinkTest() {
        passwordRecoveryPage.openPasswordRecoveryPage();
        passwordRecoveryPage.clickOnLoginLink();
        passwordRecoveryPage.waitForRedirectToLoginPage();
        loginPage.fillEmailInput(testUser.getEmail());
        loginPage.fillPasswordInput(testUser.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForRedirectToLanding();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = getAppUrl();
        assertEquals(expectedUrl, currentUrl);
    }
}
