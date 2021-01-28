package Tests;

import PageObjects.DashboardPage;
import PageObjects.LoginPage;
import PageObjects.QuickLaunchPage;
import org.testng.annotations.Test;
import utils.ExcelReadWrite;

public class QuickLaunchPageTest extends BaseClass{

    @Test
    public void testToVerifyIDQuickLaunchPageISPresentOrNot(){
        LoginPage loginPage = new LoginPage(driver);
        //DashboardPage dashboardPage = new DashboardPage(driver);
        QuickLaunchPage quickLaunchPage = new QuickLaunchPage(driver);
        ExcelReadWrite excelReadWrite = new ExcelReadWrite();
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickSubmitButton();

        quickLaunchPage.getALLMenuItemsAndVeriFy();

    }

    

}
