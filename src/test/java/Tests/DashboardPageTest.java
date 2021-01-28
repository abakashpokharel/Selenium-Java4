package Tests;

import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import PageObjects.QuickLaunchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utils.ExcelReadWrite;

public class DashboardPageTest extends BaseClass {

    @Test
    public void testToVerifyIDDashboardPageISPresentORNot(){

         LoginPage loginPage = new LoginPage(driver);
         DashboardPage dashboardPage = new DashboardPage(driver);
        // QuickLaunchPage quickLaunchPage = new QuickLaunchPage(driver);
        ExcelReadWrite excelReadWrite = new ExcelReadWrite();
       //Object[][] data = excelReadWrite.getAllData("DashboardExelFile","Dashboard Data");
        //String DashboardMenus = data[0][0].toString();
         loginPage.enterUsername("Admin");
         loginPage.enterPassword("admin123");
         loginPage.clickSubmitButton();

         dashboardPage.getAllMenuItemsAndVerify();

     }
}
