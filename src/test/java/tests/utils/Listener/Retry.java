package tests.utils.Listener;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import tests.Helper;
import tests.utils.ExtentTestManager;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static int maxTry = 1; //run failed test 2 times

    @Override
    public boolean retry(ITestResult iTestResult) {
        //THIS IS CALLED WHEN A TEST IS FAILED.
        if (!iTestResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                extendReportsFailOperations(iTestResult);
                return true;
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    public void extendReportsFailOperations(ITestResult iTestResult) {
        WebDriver webDriver = Helper.getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        String errorMessage = iTestResult.getThrowable().getMessage();
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Re-Test Failed Again  : <br/>" + errorMessage,
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }
}
