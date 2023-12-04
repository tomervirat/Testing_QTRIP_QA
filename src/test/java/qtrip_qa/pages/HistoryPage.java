package qtrip_qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryPage {
    RemoteWebDriver driver;

    @FindBy(id = "reservation-table")
    WebElement reservationTable;

    public HistoryPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void gotoHistoryPage() {
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public List<ReservationHistory> getReservations() throws InterruptedException {
        List<ReservationHistory> history = new ArrayList<>();
        List<WebElement> tableRows = reservationTable.findElements(By.xpath("//*[@id='reservation-table']/tr"));
        for (WebElement row : tableRows) {
            String tr_id, booking_name, adventure, person, date, price, bookingtime;
            tr_id = row.findElement(By.xpath("//th")).getText();
            booking_name = row.findElement(By.xpath("//td[1]")).getText();
            adventure = row.findElement(By.xpath("//td[2]")).getText();
            person = row.findElement(By.xpath("//td[3]")).getText();
            date = row.findElement(By.xpath("//td[4]")).getText();
            price = row.findElement(By.xpath("//td[5]")).getText();
            bookingtime = row.findElement(By.xpath("//td[6]")).getText();
            ReservationHistory rs = new ReservationHistory();
            var historyRecord = rs.CreateReservationHistory(tr_id, booking_name, adventure, person, date, price,
                    bookingtime);
            history.add(historyRecord);
        }
        Thread.sleep(3000);
        return history;

    }

    public void cancelReservation(String transactionId) {
        // TODO: Implement Cancellation
    }

    private class ReservationHistory {
        String transactionId, bookingName, adventure, persons, date, price, bookingtime;

        public ReservationHistory CreateReservationHistory(String transactionId, String bookingName, String adventure,
                String persons, String date, String price, String bookingtime) {
            this.transactionId = transactionId;
            this.bookingName = bookingName;
            this.adventure = adventure;
            this.persons = persons;
            this.date = date;
            this.price = price;
            this.bookingtime = bookingtime;
            return this;
        }
    }
}
