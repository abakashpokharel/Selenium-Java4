package tests;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestNGException;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;

import static tests.Helper.*;

/**
 * Created by ITH on 11/30/2017.
 */
public class BaseTest {
    static String rURL;
    static String browser;
    static String confFile;
    static String Hub;

   @BeforeSuite
    @Parameters({"configFile", "HubUrl"})
    public void getConfigFile(String configFile, String HubUrl) {
        confFile = configFile;
        Hub = HubUrl;
    }

    //Before Suite
    @BeforeSuite(alwaysRun = true, dependsOnMethods = "getConfigFile")
    public static void readConfigFile() throws Exception {
        try {
            readConfig(confFile);
            System.out.println("Config File that is used is :" + confFile);
        } catch (Exception errorReadConfig) {
            System.out.println("Cannot Read config File :" + errorReadConfig);
        }
    }

    @BeforeMethod(alwaysRun =true)
    @Parameters({"browserName"})
    public void getRemoteUrl(String browserName) {
        try {
            System.out.println("Browser to be used is : " + browserName);
            browser = browserName;
        } catch (Exception errorGetRemoteUrl) {
            System.out.println("Failed to get RemoteUrl : " + errorGetRemoteUrl);
        }
    }

    static WebDriver createInstance() throws IOException, EmailException {
        WebDriver driver = null;
        try {
            System.out.println("Creating Instances For the test");
            if (browser.equalsIgnoreCase("chrome")) {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setBrowserName(browser);
                cap.setPlatform(Platform.WIN10);

                ChromeOptions options = new ChromeOptions();
                options.merge(cap);
                options.addArguments("--start-maximized");

                driver = new RemoteWebDriver(new URL(Hub), options);
                return driver;
            }
        } catch (Exception errorCreateInstance) {
            System.out.println("Cannot create Instance due to :" + errorCreateInstance);
        }
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        try {
            WebDriver driver = Helper.getDriver();
            if (driver != null) {
              driver.quit();
            }
        } catch (TestNGException e) {
            e.printStackTrace();
        }
    }
}

