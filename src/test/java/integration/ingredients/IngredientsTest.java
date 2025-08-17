package integration.ingredients;

import integration.BaseTest;
import integration.core.provider.WebDriverProvider;
import integration.page.LandingPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

@Epic("Stellar Burgers Integration")
@Feature("Ингридиенты")
public class IngredientsTest extends BaseTest {

    private LandingPage landingPage;

    @Before
    public void setUp() {
        driver = WebDriverProvider.createWebDriver();
        landingPage = new LandingPage(driver);
        landingPage.openLandingPage();
        PageFactory.initElements(driver, this);
    }

    @Test
    @Story("Корректная работа таба 'Булки'")
    @Description("Успешная навигация по табу 'Булки' к одноименному контенту")
    public void bunTabTest() {
        assertTrue(landingPage.isBunTabActive());
    }

    @Test
    @Story("Корректная работа таба 'Соусы'")
    @Description("Успешная навигация по табу 'Соусы' к одноименному контенту")
    public void sauceTabTest() {
        landingPage.clickOnSauceTab();
        assertTrue(landingPage.isSauceTabActive());
    }

    @Test
    @Story("Корректная работа таба 'Начинки'")
    @Description("Успешная навигация по табу 'Начинки' к одноименному контенту")
    public void fillingsTabTest() {
        landingPage.clickOnFillingsTab();
        assertTrue(landingPage.isFillingsTabActive());
    }
}
