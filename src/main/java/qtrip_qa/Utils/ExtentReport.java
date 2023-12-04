package qtrip_qa.Utils;

import java.io.File;

public class ExtentReport {
    static ExtentReport report;
    final static String extentReportFilePath = System.getProperty("user.dir") + File.separator + "Reports"
            + File.separator + "QTRIP_ExtentReport.html";

    private ExtentReport() {
    }
}
