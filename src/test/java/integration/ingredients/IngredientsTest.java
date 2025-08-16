package integration.ingredients;

import integration.BaseTest;
import integration.core.constant.DriverName;
import integration.page_object.LandingPage;
import integration.core.provider.WebDriverProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Epic("Stellar Burgers Integration")
@Feature("Ингридиенты")
@RunWith(Parameterized.class)
public class IngredientsTest extends BaseTest {

    private final LandingPage landingPage;

    public IngredientsTest(DriverName driverName) {
        driver = WebDriverProvider.createWebDriver(driverName);
        landingPage = new LandingPage(driver);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {DriverName.CHROME},
                {DriverName.YANDEX},
        });
    }

    @Before
    public void setUp() {
        landingPage.openLandingPage();
        PageFactory.initElements(driver, this);
    }

    @Test
    @Story("Корректная работа таба 'Булки'")
    @Description("Успешная навигация по табу 'Булки' к одноименному контенту")
    public void bunTabTest() {
        landingPage.clickOnFillingsTab();
        assertFalse(landingPage.isBunHeaderVisible());
        landingPage.clickOnBunTab();
        assertTrue(landingPage.isBunHeaderVisible());
    }

    @Test
    @Story("Корректная работа таба 'Соусы'")
    @Description("Успешная навигация по табу 'Соусы' к одноименному контенту")
    public void sauceTabTest() {
        landingPage.clickOnFillingsTab();
        assertFalse(landingPage.isSauceHeaderVisible());
        landingPage.clickOnSauceTab();
        assertTrue(landingPage.isSauceHeaderVisible());
    }

    @Test
    @Story("Корректная работа таба 'Начинки'")
    @Description("Успешная навигация по табу 'Начинки' к одноименному контенту")
    public void fillingsTabTest() {
        assertFalse(landingPage.isFillingsHeaderVisible());
        landingPage.clickOnFillingsTab();
        assertTrue(landingPage.isFillingsHeaderVisible());
    }
}
