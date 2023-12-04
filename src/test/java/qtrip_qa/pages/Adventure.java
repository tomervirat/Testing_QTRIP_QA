package qtrip_qa.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
// import qtriptest.SeleniumWrapper;

public class Adventure {
    RemoteWebDriver driver;

    @FindBy(id = "duration-select")
    WebElement durationFilter;

    @FindBy(id = "category-select")
    WebElement categoryFilter;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement clearDuration;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement clearCategory;

    public Adventure(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void setFilterValue(String value) {
        durationFilter.click();
        Select FilterDropdown = new Select(durationFilter);
        FilterDropdown.selectByVisibleText(value);
    }

    public void setCategoryValue(String value) {
        categoryFilter.click();
        Select FilterDropdown = new Select(categoryFilter);
        FilterDropdown.selectByVisibleText(value);
    }

    public int getResultCount() {
        List<WebElement> resultGrid = driver.findElements(By.xpath("//div[@id='data']/div"));
        return resultGrid.size();
    }

    public void selectAdventure(String adventureName) throws InterruptedException {
        Thread.sleep(2000);
        WebElement adventure = driver
                .findElement(By.xpath(String.format("//h5[text()='%s']/ancestor::div/img", adventureName)));
        adventure.click();
        Thread.sleep(2000);
    }

    public void clearFilters() throws InterruptedException {
        Thread.sleep(1000);
        clearCategory.click();
        Thread.sleep(1000);
        clearDuration.click();
        Thread.sleep(1000);
    }
}
