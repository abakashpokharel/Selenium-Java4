package tests;

import com.relevantcodes.extentreports.LogStatus;
import login.Login;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import tests.utils.ExtentTestManager;

import java.io.IOException;

import static tests.Helper.*;

public class BaseLogin extends BaseTest {

    public void validTest() throws InterruptedException, IOException {
        ExtentTestManager.getTest().setDescription("Valid Login.");
        Login login = new Login(getDriver());
        Logger log = Logger.getLogger(BaseLogin.class);

        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Invoking URL", "");
            //Invoking URL
            invokeBrowser(prop.getProperty("BaseUrl"));

            ExtentTestManager.getTest().log(LogStatus.INFO, "Login Process started", "");
        } catch (Exception errorUrlInvoke) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Failed to Invoke Url", errorUrlInvoke);
        }

        //Entering Valid Login Credentials
        try {

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering username", "");
            login.enterUserName(prop.getProperty("validUserName"));

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering password", "");
            login.enterPassword(decrypt(prop.getProperty("validPassword")));

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering domain", "");
            login.enterDomain(prop.getProperty("domain"));

            ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting Language", "");
            login.clickLanguageSelector();
            login.languageSelection("English (United States)");


            ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on Login Button", "");
            login.clickLoginButon();

            try {
                handelPopUp();
            } catch (UnhandledAlertException popup) {
                ExtentTestManager.getTest().log(LogStatus.WARNING, "Unexpected alert Open", popup.getAlertText());
            }

            //Login success Assertion
            try {
                String mainPageUrl = getDriver().getCurrentUrl();
                Assert.assertEquals(mainPageUrl, prop.getProperty("mainPageUrl"));
                log.info("Login Successful");
                ExtentTestManager.getTest().log(LogStatus.PASS, "Login Successful", "");

            } catch (Exception errorLogin) {
                log.error("Login Failed due to  :: " + errorLogin);
                ExtentTestManager.getTest().log(LogStatus.FAIL, "Login Failed", errorLogin);
            }

        } catch (Exception e) {
            log.error("Failed to enter credentials" + e);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Failed to enter credentials", e);
        }
    }

    private void invokeBrowser(String url) {
        getDriver().get(url);
    }
}
