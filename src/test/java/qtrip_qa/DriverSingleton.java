package qtrip_qa;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DriverSingleton {
    private static DriverSingleton instanceOfSingletonBrowserClass = null;
    private RemoteWebDriver driver;
    static Logger log = Logger.getLogger(DriverSingleton.class);

    DriverSingleton() {
        try {
            URL log4jConfigFile = DriverSingleton.class.getClassLoader().getResource("log4j.properties");
            BasicConfigurator.configure();
            PropertyConfigurator.configure(log4jConfigFile);
            PropertyConfigurator
                    .configure(
                            "/Users/tomer/crio/maven/QTRIP_QA/QTRIP_QA/src/test/java/qtrip_qa/Utils/log4j.properties");
            DriverSingleton.log.info("----------Starting the execution------------");
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(Platform.MAC);
            capabilities.setBrowserName("chrome");
            log.info("Initializing the driver");
            driver = new RemoteWebDriver(new URL("http://192.168.0.190:4444/wd/hub"), capabilities);
            log.info("Driver initialized : " + driver);
            driver.manage().window().maximize();
        } catch (Exception e) {
        }
    }

    public static synchronized DriverSingleton getInstanceOfSingletonBrowserClass() throws MalformedURLException {
        if (instanceOfSingletonBrowserClass == null) {
            instanceOfSingletonBrowserClass = new DriverSingleton();
            log.info("Creating an instance of driver");
        }
        return instanceOfSingletonBrowserClass;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

}
