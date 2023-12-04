package qtrip_qa.tests;

import static org.testng.Assert.*;
import com.relevantcodes.extentreports.LogStatus;

import qtrip_qa.BaseTest;
import qtrip_qa.DP;
import qtrip_qa.SeleniumWrapper;
import qtrip_qa.Utils.ExtentReportTestManager;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class test_case02 extends BaseTest {

        static Logger log = Logger.getLogger(test_case02.class);

        @BeforeClass
        public static void setupLogger() {
                BasicConfigurator.configure();
                PropertyConfigurator.configure(test_case02.class.getClassLoader().getResource("log4j.properties"));
                PropertyConfigurator
                                .configure("/Users/tomer/crio/maven/QTRIP_QA/QTRIP_QA/src/test/java/qtrip_qa/Utils/log4j.properties");
        }

        @Test(description = "Verify that Search and filters work fine", dataProvider = "data-provider", dataProviderClass = DP.class, priority = 2, groups = {
                        "Search and Filter flow" })
        public static void TestCase02(String CityName, String Category_Filter, String DurationFilter,
                        String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws Exception {
                log.info("Started the execution of TestCase02");
                home.gotoHomePage();
                Thread.sleep(1000);
                home.searchCity("nowhere");
                Thread.sleep(1000);
                assertTrue(home.isNoCityFound());
                log.info("City search for 'nowhere' is success");
                home.searchCity(CityName);
                home.selectCity(CityName);
                log.info("City search for '" + CityName + "' is success");
                ExtentReportTestManager.testLogger(LogStatus.INFO, "Search for city is successful");
                adventures.setCategoryValue(Category_Filter);
                adventures.setFilterValue(DurationFilter);
                assertTrue(adventures.getResultCount() == Integer.parseInt(ExpectedFilteredResults),
                                "Mismatchg in result count Expected vs actual");
                adventures.clearFilters();
                assertTrue(adventures.getResultCount() == Integer.parseInt(ExpectedUnFilteredResults),
                                "Mismatchg in result count Expected vs actual after clearing filters");
                ExtentReportTestManager.testLogger(LogStatus.INFO, "Filters are working");
                log.info("Filters are working fine");
                test.log(LogStatus.INFO,
                                test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
                log.info("Ended the execution of TestCase02");
        }
}
