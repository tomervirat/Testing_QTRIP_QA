package qtrip_qa.tests;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import qtrip_qa.BaseTest;
import qtrip_qa.DP;
import qtrip_qa.SeleniumWrapper;
import qtrip_qa.Utils.ExtentReportTestManager;

public class test_case01 extends BaseTest {
    static String lastgeneratedUsername = "";
    static Logger log = Logger.getLogger(test_case01.class);

    @BeforeClass
    public static void setupLogger() {
        BasicConfigurator.configure();
        PropertyConfigurator.configure(test_case01.class.getClassLoader().getResource("log4j.properties"));
        PropertyConfigurator
                .configure("/Users/tomer/crio/maven/QTRIP_QA/QTRIP_QA/src/test/java/qtrip_qa/Utils/log4j.properties");
    }

    @Test(description = "Verify user logged in", dataProvider = "data-provider", dataProviderClass = DP.class, priority = 1, groups = {
            "Login Flow" })
    @Parameters({ "TC1_userMail", "TC1_userPassword" })
    public void TestCase01(String TC1_userMail, String TC1_userPassword) throws InterruptedException {
        boolean status = false;
        log.info("Started the execution of TestCase01");
        home.gotoHomePage();
        home.clickRegister();
        status = register.registerUser(TC1_userMail, TC1_userPassword, true);
        log.info("Completed the registration: " + status);
        if (status)
            ExtentReportTestManager.testLogger(LogStatus.PASS, "Registration is successful");
        lastgeneratedUsername = register.lastGeneratedUsername;
        login.navigateToLoginPage();
        status = login.performLogin(lastgeneratedUsername, TC1_userPassword);
        status = home.isUserLoggedIn();
        if (status)
            ExtentReportTestManager.testLogger(LogStatus.PASS, "Login is successful");
        else
            ExtentReportTestManager.testLogger(LogStatus.FAIL, "Login is successful");
        log.info("Completed the login: " + status);
        home.logOutUser();
        log.info("Logging out the user");
        System.out.println("value of test -" + BaseTest.test + "-");
        BaseTest.test.log(LogStatus.INFO,
                BaseTest.test.addScreenCapture(SeleniumWrapper.takeScreenshot(BaseTest.driver, "PASS", "Login")));
        log.info("Ended the execution of TestCase01");
        ExtentReportTestManager.testLogger(LogStatus.PASS, "TestCase 01 is success");
    }
}