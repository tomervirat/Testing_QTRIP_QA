package qtrip_qa.tests;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import qtrip_qa.BaseTest;
import qtrip_qa.DP;
import qtrip_qa.SeleniumWrapper;
import qtrip_qa.Utils.ExtentReportTestManager;

public class test_case03 extends BaseTest {

    static Logger log = Logger.getLogger(test_case02.class);

    @BeforeClass
    public static void setupLogger() {
        BasicConfigurator.configure();
        PropertyConfigurator.configure(test_case02.class.getClassLoader().getResource("log4j.properties"));
        PropertyConfigurator
                .configure("/Users/tomer/crio/maven/QTRIP_QA/QTRIP_QA/src/test/java/qtrip_qa/Utils/log4j.properties");
    }

    @Test(description = "Verify that adventure booking and cancellation works fine", dataProvider = "data-provider", dataProviderClass = DP.class, priority = 3, groups = {
            "Booking and Cancellation Flow" })
    @Parameters({ "NewUserName", "TC1_userPassword" })
    public static void TestCase03(String NewUserName, String Password, String SearchCity, String AdventureName,
            String GuestName, String Date, String count) throws Exception {
        boolean status = false;
        log.info("Started the execution of TestCase03");
        home.gotoHomePage();
        home.clickRegister();
        status = register.registerUser(NewUserName, Password, true);
        log.info("Completed the registration: " + status);
        if (status)
            ExtentReportTestManager.testLogger(LogStatus.PASS, "Registration is successful");
        else
            ExtentReportTestManager.testLogger(LogStatus.FAIL, "Registration is unsuccessful");
        String username = register.lastGeneratedUsername;
        status = login.performLogin(username, Password);
        Thread.sleep(3000);
        status = home.isUserLoggedIn();
        log.info("Completed the login: " + status);
        if (status)
            ExtentReportTestManager.testLogger(LogStatus.PASS, "Login is successful");
        else
            ExtentReportTestManager.testLogger(LogStatus.FAIL, "Login is unsuccessful");
        home.searchCity(SearchCity);
        home.selectCity(SearchCity);
        ExtentReportTestManager.testLogger(LogStatus.INFO, "Search for city is successful");
        adventures.selectAdventure(AdventureName);
        adDetails.bookAdventure(GuestName, Date, Integer.parseInt(count));
        history.gotoHistoryPage();
        var reservations = history.getReservations();
        Assert.assertTrue((reservations.size() == 1), "Failure while verifying the number of reservations");
        ExtentReportTestManager.testLogger(LogStatus.INFO, "Advernture reservation is successful");
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
        home.logOutUser();
        log.info("Ended the execution of TestCase03");
    }

}
