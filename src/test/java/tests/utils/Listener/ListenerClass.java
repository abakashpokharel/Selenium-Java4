package tests.utils.Listener;


import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.mail.EmailException;
import org.testng.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import tests.Helper;
import tests.utils.ExtentManager;
import tests.utils.ExtentTestManager;


import javax.mail.internet.AddressException;

import static tests.Helper.prop;
import static tests.Helper.sentAttachmentEmail;


public class ListenerClass extends TestListenerAdapter {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", Helper.getDriver());
    }

    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
        try {
            sentAttachmentEmail(prop.getProperty("Email"),System.getProperty("user.dir")+prop.getProperty("path till report"),"Test automation Report");
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //Start operation for extentreports.
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //Extentreports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test Completed", "");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //HERE WHEN A TEST IS FAILED IT TAKES THE SCREEN SHOT AND LOGS IN EXTENTMANGER PAGE.

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = Helper.getDriver();
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        //Extentreports log and screenshot operations for failed tests.
        String errorMessage = iTestResult.getThrowable().getMessage();
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed due to  : <br/>" + errorMessage, ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //Extentreports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}

