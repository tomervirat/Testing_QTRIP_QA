package qtrip_qa;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import qtrip_qa.Utils.ExtentReportTestManager;
import qtrip_qa.pages.Adventure;
import qtrip_qa.pages.AdventureDetails;
import qtrip_qa.pages.HistoryPage;
import qtrip_qa.pages.Home;
import qtrip_qa.pages.Login;
import qtrip_qa.pages.Register;

public class BaseTest {
    protected static RemoteWebDriver driver;
    protected static ExtentReports report;
    protected static ExtentTest test;

    protected static Register register;
    protected static Home home;
    protected static Login login;
    protected static Adventure adventures;
    protected static AdventureDetails adDetails;
    protected static HistoryPage history;

    @BeforeSuite
    public static void setup() throws MalformedURLException {
        DriverSingleton.log.info("----------Starting the execution------------");
        DriverSingleton ds = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = ds.getDriver();
        register = new Register(driver);
        home = new Home(driver);
        login = new Login(driver);
        adventures = new Adventure(driver);
        adDetails = new AdventureDetails(driver);
        history = new HistoryPage(driver);
    }

    @BeforeMethod(alwaysRun = true, enabled = true)
    public void beforeTest(Method method) throws MalformedURLException {
        ExtentReportTestManager er = ExtentReportTestManager.getInstance();
        report = er.getReports();
        test = ExtentReportTestManager.startTest(method.getName());
        ExtentReportTestManager.testLogger(LogStatus.INFO, "Starting " + method.getName());
    }

    @AfterMethod(alwaysRun = true, enabled = true)
    public static void extentReportEnd(Method method) {
        ExtentReportTestManager.testLogger(LogStatus.INFO, "Ending " + method.getName());
        ExtentReportTestManager.endTest();
    }

    @AfterSuite
    public static void tearDown() {
        driver.quit();
        report.flush();
        driver = null;
        DriverSingleton.log.info("----------Closing the execution-------------");
    }
}
