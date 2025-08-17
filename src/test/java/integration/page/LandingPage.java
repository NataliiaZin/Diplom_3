package integration.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static integration.core.properties.TestProperties.getAppUrl;

public class LandingPage extends BasePage {

    private static final String LOGIN_BUTTON_XPATH = "//button[normalize-space(text())='Войти в аккаунт']";
    private static final String PERSONAL_ACCOUNT_LINK_XPATH = "//p[text()='Личный Кабинет']";
    private static final String SAUCE_TAB_XPATH = "//span[normalize-space(.)='Соусы']";
    private static final String BUN_TAB_XPATH = "//span[normalize-space(.)='Булки']";
    private static final String FILLINGS_TAB_XPATH = "//span[normalize-space(.)='Начинки']";
    private static final String ELEMENT_PARENT_PATH_ADDITION = "/..";

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу лендинг")
    public void openLandingPage() {
        driver.get(getAppUrl());
    }

    @Step("Клик по ссылочному элементу 'Войти в аккаунт'")
    public void clickOnPersonalAccountLink() {
        WebElement elementWaitingVisibility = findElementWaitingVisibility(By.xpath(PERSONAL_ACCOUNT_LINK_XPATH));
        waitForElementToBeClickable(elementWaitingVisibility);
        elementWaitingVisibility.click();
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        WebElement loginButton = findElementWaitingVisibility(By.xpath(LOGIN_BUTTON_XPATH));
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    @Step("Клик по табу 'Соусы'")
    public void clickOnSauceTab() {
        WebElement sauceTab = findElementWaitingVisibility(By.xpath(SAUCE_TAB_XPATH));
        waitForElementToBeClickable(sauceTab);
        sauceTab.click();
    }

    @Step("Клик по табу 'Начинки'")
    public void clickOnFillingsTab() {
        WebElement fillingsTab = findElementWaitingVisibility(By.xpath(FILLINGS_TAB_XPATH));
        waitForElementToBeClickable(fillingsTab);
        fillingsTab.click();
    }

    public boolean isBunTabActive() {
        return isTabActive(BUN_TAB_XPATH);
    }

    public boolean isFillingsTabActive() {
        return isTabActive(FILLINGS_TAB_XPATH);
    }

    public boolean isSauceTabActive() {
        return isTabActive(SAUCE_TAB_XPATH);
    }

    private boolean isTabActive(String elementPath) {
        WebElement tab = driver.findElement(By.xpath(elementPath + ELEMENT_PARENT_PATH_ADDITION));
        String tabParentElemClass = tab.getAttribute("class");
        if (tabParentElemClass != null && !tabParentElemClass.isEmpty()) {
            return tabParentElemClass.contains("current");
        }
        else  {
            return false;
        }
    }
}
