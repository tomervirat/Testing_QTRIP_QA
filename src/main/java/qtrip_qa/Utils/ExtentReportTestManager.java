package qtrip_qa.Utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportTestManager {
    private static ExtentReports report;
    private static ExtentTest extentTest;
    private static ExtentReportTestManager instanceOfExtentReport = null;
    final static String extentReportFilePath = System.getProperty("user.dir") + File.separator + "Reports"
            + File.separator + "QTRIP_ExtentReport.html";
    final static String reportCustomizationPath = System.getProperty("user.dir") + File.separator + "Reports"
            + File.separator + "extentCustomization.xml";

    private ExtentReportTestManager() {
        report = new ExtentReports(extentReportFilePath, true);
        report.loadConfig(new File(reportCustomizationPath));
    }

    public static ExtentReportTestManager getInstance() {
        if (instanceOfExtentReport == null) {
            instanceOfExtentReport = new ExtentReportTestManager();
        }
        return instanceOfExtentReport;
    }

    public ExtentReports getReports() {
        return report;
    }

    public static synchronized ExtentTest startTest(String testName) {
        extentTest = report.startTest(testName);
        return extentTest;
    }

    public static synchronized void testLogger(LogStatus status, String description) {
        extentTest.log(status, description);
    }

    public static synchronized void endTest() {
        report.endTest(extentTest);
    }
}
