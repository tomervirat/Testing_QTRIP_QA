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

public class test_case04 extends BaseTest {

    static Logger log = Logger.getLogger(test_case04.class);

    @BeforeClass
    public static void setupLogger() {
        BasicConfigurator.configure();
        PropertyConfigurator.configure(test_case02.class.getClassLoader().getResource("log4j.properties"));
        PropertyConfigurator
                .configure("/Users/tomer/crio/maven/QTRIP_QA/QTRIP_QA/src/test/java/qtrip_qa/Utils/log4j.properties");
    }

    @Test(description = "Verify that Booking history can be viewed", dataProvider = "data-provider", dataProviderClass = DP.class, priority = 4, groups = {
            "Reliability Flow" })
    public static void TestCase04(String NewUserName, String Password, String dataset1, String dataset2,
            String dataset3) throws Exception {
        boolean status = false;
        log.info("Started the execution of TestCase04");
        String[][] DS = { dataset1.split(";"), dataset2.split(";"), dataset3.split(";") };
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
        Thread.sleep(3000);
        for (String[] DS1 : DS) {
            home.searchCity(DS1[0]);
            home.selectCity(DS1[0]);
            ExtentReportTestManager.testLogger(LogStatus.INFO, "Search for city is successful");
            adventures.selectAdventure(DS1[1]);
            adDetails.bookAdventure(DS1[2], DS1[3], Integer.parseInt(DS1[4]));
            home.gotoHomePage();
            ExtentReportTestManager.testLogger(LogStatus.INFO, "Booking is successful");
        }
        // home.searchCity(DS2[0]);
        // home.selectCity(DS2[0]);
        // adventures.selectAdventure(DS2[1]);
        // adDetails.bookAdventure(DS2[2], DS2[3], Integer.parseInt(DS2[4]));
        // home.gotoHomePage();
        // home.searchCity(DS3[0]);
        // home.selectCity(DS3[0]);
        // adventures.selectAdventure(DS3[1]);
        // adDetails.bookAdventure(DS3[2], DS3[3], Integer.parseInt(DS3[4]));
        history.gotoHistoryPage();
        var reservations = history.getReservations();
        Assert.assertTrue((reservations.size() == 3), "Failure while verifying the number of reservations");
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
        home.logOutUser();
        log.info("Ended the execution of TestCase04");
    }
}
