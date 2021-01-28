package Tests;

import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import PageObjects.QuickLaunchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ExcelReadWrite;


public class LoginPageTest extends BaseClass {

    @Test
    public void testToVerifyLogin(){
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        QuickLaunchPage quickLaunchPage = new QuickLaunchPage(driver);
        // Read Excel shet and fetch data for login

        ExcelReadWrite excelReadWrite = new ExcelReadWrite();
        Object[][] data = excelReadWrite.getAllData("NewExcelFile","Dashboard Data");
        String validUserName = data[0][0].toString();
        String validPassword = data[0][1].toString();

        loginPage.enterUsername(validUserName);
        loginPage.enterPassword(validPassword);
        loginPage.clickSubmitButton();


    }

   // @Test
    public void testToverifyIdDashboardMenuIsPresentOrNot(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.getAllMenuItemsAndVerify();

    }
  // @Test
    public void testToverifyIdQuickLaunchPageIspresentOrNot(){
        QuickLaunchPage quickLaunchPage = new QuickLaunchPage(driver) ;
       // QuickLaunchPage.getAllMenuItems();
    }


}
