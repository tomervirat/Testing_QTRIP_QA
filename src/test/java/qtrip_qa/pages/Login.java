package qtrip_qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    @FindBy(id = "floatingInput")
    WebElement username_txt_box;

    @FindBy(xpath = "//input[@id='floatingPassword']")
    WebElement password_text_box;

    @FindBy(xpath = "//button[normalize-space()='Login to QTrip']")
    WebElement login_button;

    public Login(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean performLogin(String username, String password) throws InterruptedException {
        username_txt_box.sendKeys(username);
        password_text_box.sendKeys(password);
        login_button.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/")));
        } catch (Exception e) {
            return false;
        }

        return this.driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/");
    }
}
