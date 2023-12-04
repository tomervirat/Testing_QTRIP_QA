package qtrip_qa;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SeleniumWrapper {
    public static String takeScreenshot(WebDriver driver, String screenshotType, String description) {
        try {
            System.out.println("--wrapper: " + driver);
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, description);
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
            return DestFile.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("-----=======-------");
            e.printStackTrace();
            return "";
        }
    }

}
