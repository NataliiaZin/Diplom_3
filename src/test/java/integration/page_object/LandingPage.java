package integration.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static integration.core.properties.TestProperties.getAppUrl;

public class LandingPage extends BasePage {

    private static final String LOGIN_BUTTON_XPATH = "//button[normalize-space(text())='Войти в аккаунт']";
    private static final String PERSONAL_ACCOUNT_LINK_XPATH = "//p[text()='Личный Кабинет']";
    private static final String SAUCE_TAB_XPATH = "//span[normalize-space(.)='Соусы']";
    private static final String BUN_TAB_XPATH = "//span[normalize-space(.)='Булки']";
    private static final String FILLINGS_TAB_XPATH = "//span[normalize-space(.)='Начинки']";
    private static final String BUN_HEADER = "//h2[normalize-space(.)=\"Булки\"]";
    private static final String SAUCE_HEADER = "//h2[normalize-space(.)=\"Соусы\"]";
    private static final String FILLINGS_HEADER = "//h2[normalize-space(.)=\"Начинки\"]";

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void openLandingPage() {
        driver.get(getAppUrl());
    }

    public void clickOnPersonalAccountLink() {
        WebElement elementWaitingVisibility = findElementWaitingVisibility(By.xpath(PERSONAL_ACCOUNT_LINK_XPATH));
        waitForElementToBeClickable(elementWaitingVisibility);
        elementWaitingVisibility.click();
    }

    public void clickLoginButton() {
        WebElement loginButton = findElementWaitingVisibility(By.xpath(LOGIN_BUTTON_XPATH));
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void clickOnSauceTab() {
        WebElement sauceTab = findElementWaitingVisibility(By.xpath(SAUCE_TAB_XPATH));
        waitForElementToBeClickable(sauceTab);
        sauceTab.click();
        waitForScrollEndAfterTabClick();
    }

    public void clickOnBunTab() {
        WebElement bunTab = findElementWaitingVisibility(By.xpath(BUN_TAB_XPATH));
        waitForElementToBeClickable(bunTab);
        bunTab.click();
        waitForScrollEndAfterTabClick();
    }

    public void clickOnFillingsTab() {
        WebElement fillingsTab = findElementWaitingVisibility(By.xpath(FILLINGS_TAB_XPATH));
        waitForElementToBeClickable(fillingsTab);
        fillingsTab.click();
        waitForScrollEndAfterTabClick();
    }

    public boolean isBunHeaderVisible() {
        WebElement bunHeader = findElementWaitingVisibility(By.xpath(BUN_HEADER));
        return isElementInView(bunHeader);
    }

    public boolean isFillingsHeaderVisible() {
        WebElement fillingsHeader = findElementWaitingVisibility(By.xpath(FILLINGS_HEADER));
        return isElementInView(fillingsHeader);
    }

    public boolean isSauceHeaderVisible() {
        WebElement sauceHeader = findElementWaitingVisibility(By.xpath(SAUCE_HEADER));
        return isElementInView(sauceHeader);
    }

    private boolean isElementInView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "var elem = arguments[0];" +
                        "var rect = elem.getBoundingClientRect();" +
                        "return rect.top >= 0 && rect.bottom <= window.innerHeight;",
                element);
    }

    private void waitForScrollEndAfterTabClick() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
