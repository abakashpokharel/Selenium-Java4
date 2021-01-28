package tests.LoginTest;

import com.relevantcodes.extentreports.LogStatus;
import login.Login;
import org.apache.log4j.RollingFileAppender;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import org.apache.log4j.Logger;
import tests.utils.ExtentTestManager;

import static tests.Helper.*;

/**
 * Created by ITH on 11/30/2017.
 */
public class LoginTest extends BaseTest {
    private RollingFileAppender appender;

    //  Login Test For Valid Username and Password
    @Test(description = "Test for Valid username and Password", dataProvider = "validData")
    public void test_For_Valid_Username_And_Password(String username, String password, String domain, String BaseUrl, String language, String mainUrl) throws InterruptedException {
        ExtentTestManager.getTest().setDescription("Test for Valid username and Password");
        Login login = new Login(getDriver());
        Logger log = Logger.getLogger(LoginTest.class);
        LoginTest loginTest = new LoginTest();
        loginTest.invokeUrl(BaseUrl);

        //Entering Valid Login Credentials
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering username", "");
            log.info("ENTERING username");
            login.enterUserName(username);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Password", "");
            log.info("Entering password");
            login.enterPassword(decrypt(password));

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Domain", "");
            log.info("ENTERING domain");
            login.enterDomain(domain);
            //working till here

            ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting Language", "");
            log.info("Selecting Language");
            login.clickLanguageSelector();
            Thread.sleep(5000);
            login.languageSelection(language);

            Thread.sleep(5000);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on Login Button", "");
            log.info("Clicked on Login Button");
            login.clickLoginButon();
            Thread.sleep(2000);

            String popup = handelPopUp();

            //Login success Assertion
            Thread.sleep(10000);
            try {
                String mainPageUrl = getDriver().getCurrentUrl();
                Assert.assertEquals(mainPageUrl, mainUrl);
                log.info("Login Successful");
                ExtentTestManager.getTest().log(LogStatus.PASS, "Login Successful.", "");
            } catch (Exception errorLogin) {
                log.info("Login Failed due to  :: " + errorLogin.getMessage());
                if (popup != null) {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "UnExpected Alert Open  : <br/>", popup);
                } else {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "Login Failed due to : <br/>" + errorLogin.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to enter credentials" + e.getMessage());
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to enter credentials get message:</br>  " + e.getMessage());
        }
    }

    //  Test For Invalid Username and Password
    @Test(description = "Test for Invalid Username and Password", dataProvider = "InvalidData", dataProviderClass = DataProviderClass.class)
    public void test_For_Invalid_Username_And_Password(String username, String password, String domain, String BaseUrl, String language, String invalidMsg) throws InterruptedException {
        ExtentTestManager.getTest().setDescription("Test for Invalid Username and Password");
        Logger log = Logger.getLogger(LoginTest.class);
        Login login = new Login(getDriver());
        LoginTest loginTest = new LoginTest();
        loginTest.invokeUrl(BaseUrl);

        //Entering Invalid Login Credentials
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering username", "");
            log.info("ENTERING username");
            login.enterUserName(username);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering password", "");
            log.info("Entering password");
            login.enterPassword(password);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering Domain", "");
            log.info("ENTERING domain");
            login.enterDomain(domain);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting Language", "");
            log.info("Selecting Language");
            login.clickLanguageSelector();
            Thread.sleep(5000);
            login.languageSelection(language);
            ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on Login Button", "");
            log.info("Clicked on Login Button");
            login.clickLoginButon();

            Thread.sleep(2000);
            //login Error Assertion
            try {
                String message = login.getInvalidLoginMsg();
                Assert.assertEquals(message, invalidMsg);
                log.info("Invalid Login Test Passed");
                ExtentTestManager.getTest().log(LogStatus.PASS, "Invalid Login Test Passed", "");
            } catch (Exception errorInvalidLogin) {
                log.info("Login Failed due to  :: " + errorInvalidLogin.getMessage());
                String popup = handelPopUp();
                if (popup != null) {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "UnExpected Alert Open  : <br/>", popup);
                } else {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "Login Failed due to : <br/>", errorInvalidLogin.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to enter credentials" + e);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to enter credentials", e.getMessage());
        }
    }

    //  Test If Username OR Password is Empty
    @Test(description = "Test for Blank Username and Valid Password", dataProvider = "user_Empty_Pass_Valid", dataProviderClass = DataProviderClass.class)
    public void test_For_Blank_Username_And_Valid_Password(String password, String domain, String BaseUrl, String language, String invalidMsg) throws InterruptedException {
        ExtentTestManager.getTest().setDescription("Test for blank username and valid password");
        Logger log = Logger.getLogger(LoginTest.class);
        Login login = new Login(getDriver());
        LoginTest loginTest = new LoginTest();
        loginTest.invokeUrl(BaseUrl);

        //Entering Invalid Login Credentials
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Username left blank", "");
            log.info("username left blank");

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering valid password", "");
            log.info("Entering password");
            login.enterPassword(password);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering domain", "");
            log.info("ENTERING domain");
            login.enterDomain(domain);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting language", "");
            log.info("Selecting Language");
            login.clickLanguageSelector();
            Thread.sleep(5000);
            login.languageSelection(language);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on login button", "");
            log.info("Clicked on Login Button");
            login.clickLoginButon();
            //Blank username password  Error Assertion
            try {
                String message = login.getUserPassEmptyMsg();
                Assert.assertEquals(message, invalidMsg);
                ExtentTestManager.getTest().log(LogStatus.PASS, "Blank username and valid password passed.", "");
                log.info("Blank UserName and Pasword  Test Passed");
            } catch (Exception errorInvalidLogin) {
                log.info("Login Failed due to  :: " + errorInvalidLogin.getMessage());
                String popup = handelPopUp();
                if (popup != null) {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "UnExpected Alert Open  : <br/>", popup);
                } else {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "Login Failed due to : <br/>", errorInvalidLogin.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to enter credentials" + e.getMessage());
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to enter credentials", e.getMessage());
        }
    }


    //  Test If Username/Password is Empty
    @Test(description = "Test for blank username and blank password", dataProvider = "userPassEmpty", dataProviderClass = DataProviderClass.class)
    public void test_For_Blank_Username_And_Blank_Password(String domain, String BaseUrl, String language, String blankUserPassErrorMsg) throws InterruptedException {
        ExtentTestManager.getTest().setDescription("Test for blank username and password");
        Logger log = Logger.getLogger(LoginTest.class);
        Login login = new Login(getDriver());
        LoginTest loginTest = new LoginTest();
        loginTest.invokeUrl(BaseUrl);

        //Leaving username and password empty
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Username Left Blank", "");
            log.info("Left Username Field Empty");

            ExtentTestManager.getTest().log(LogStatus.INFO, "Password Left blank", "");
            log.info("Left Password Field Empty");

            log.info("ENTERING domain");
            ExtentTestManager.getTest().log(LogStatus.INFO, "Entering domain", "");
            login.enterDomain(domain);

            log.info("Selecting Language");
            ExtentTestManager.getTest().log(LogStatus.INFO, "Selecting Language", "");
            login.clickLanguageSelector();
            Thread.sleep(5000);
            login.languageSelection(language);

            log.info("Clicked on Login Button");
            ExtentTestManager.getTest().log(LogStatus.INFO, "Clicked on Login button", "");
            login.clickLoginButon();

            Thread.sleep(2000);
            //Blank username password  Error Assertion
            try {
                String message = login.getUserPassEmptyMsg();
                Assert.assertEquals(message, blankUserPassErrorMsg);
                log.info("Blank UserName and Pasword  Test Passed");
                ExtentTestManager.getTest().log(LogStatus.PASS, "Blank username and password passed  ", "");
            } catch (Exception errorInvalidLogin) {
                log.info("Login Failed due to  :: " + errorInvalidLogin.getMessage());
                String popup = handelPopUp();
                if (popup != null) {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "UnExpected Alert Open  : <br/>", popup);
                } else {
                    ExtentTestManager.getTest().log(LogStatus.FAIL, "Login Failed due to : <br/>", errorInvalidLogin.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to enter credentials" + e.getMessage());
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to enter credentials", e.getMessage());
        }
    }

    public void invokeUrl(String baseUrl) {
        Logger log = Logger.getLogger(LoginTest.class);
        try {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Invoking Url", "");
            log.info("*****INVOKING URL*******" + Thread.currentThread().getId());
            invokeBrowser(baseUrl);
            Thread.sleep(5000);

            ExtentTestManager.getTest().log(LogStatus.INFO, "Starting Test", "");
            log.info("Starting  Test");

        } catch (Exception errorInvoke) {
            log.error("cannot invoke url due to : " + errorInvoke.getMessage());
            ExtentTestManager.getTest().log(LogStatus.ERROR, "Cannot Invoke Url .", errorInvoke.getMessage());
        }
    }

    private void invokeBrowser(String url) {
        getDriver().get(url);
    }
}
