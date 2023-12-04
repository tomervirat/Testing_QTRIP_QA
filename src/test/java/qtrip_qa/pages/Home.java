package qtrip_qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerButton;

    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutButton;

    @FindBy(id = "autocomplete")
    WebElement searchBox;

    @FindBy(xpath = "//h5[text()='No City found']")
    WebElement noCityfound;

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void gotoHomePage() throws InterruptedException {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public void clickRegister() throws InterruptedException {
        registerButton.click();
    }

    public Boolean isUserLoggedIn() {
        try {
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logOutUser() throws InterruptedException {
        Thread.sleep(2000);
        logoutButton.click();
    }

    public void searchCity(String cityName) throws InterruptedException {
        searchBox.clear();
        searchBox.sendKeys(cityName);
        Thread.sleep(2000);
    }

    public void selectCity(String cityName) throws Exception {
        Thread.sleep(2000);
        // By by = new By.ByXPath(String.format("//li[@id='%s']",
        // cityName.toLowerCase()));
        // WebElement x = SeleniumWrapper.findElementWithRetry(this.driver, by, 3);
        WebElement city = driver.findElement(By.xpath(String.format("//li[@id='%s']", cityName.toLowerCase())));
        city.click();
        Thread.sleep(2000);
    }

    public boolean isNoCityFound() {
        try {
            return noCityfound.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // public boolean assertAutoCompleteText(String cityName) throws Exception {
    // By by = new By.ByXPath(String.format("//li[@id='%s']", cityName));
    // WebElement x = SeleniumWrapper.findElementWithRetry(this.driver, by, 3);
    // return x.isDisplayed();
    // }
}
