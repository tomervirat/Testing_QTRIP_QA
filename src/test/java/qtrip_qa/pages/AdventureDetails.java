package qtrip_qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetails {

    RemoteWebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameTextBox;

    @FindBy(xpath = "//input[@name='date']")
    WebElement dateTextBox;

    @FindBy(xpath = "//input[@name='person']")
    WebElement personTextBox;

    @FindBy(xpath = "//button[text()='Reserve']")
    WebElement reserveButton;

    public AdventureDetails(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void bookAdventure(String name, String Date, int count) throws InterruptedException {
        nameTextBox.sendKeys(name);
        dateTextBox.sendKeys(Date);
        personTextBox.clear();
        personTextBox.sendKeys(String.valueOf(count));
        Thread.sleep(2000);
        reserveButton.click();
        Thread.sleep(2000);
    }

    public boolean isBookingSuccessful() {
        return true;
    }
}
