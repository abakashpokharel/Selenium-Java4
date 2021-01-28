package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.ExcelReadWrite;

import java.awt.*;
import java.util.List;

public class QuickLaunchPage {
    WebDriver driver;
    @FindBy(xpath = "//div[@class='quickLaunge']")
    List<WebElement> menuIteMS;

    public QuickLaunchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String currentUrl() {
        String curUrl = driver.getCurrentUrl();
        return curUrl;
    }

    public void getALLMenuItemsAndVeriFy() {
        ExcelReadWrite excelReadWrite = new ExcelReadWrite();
        Object[][] data = excelReadWrite.getAllData("DashboardExcelFile", "Dashboard Data");
        int i = 0;
        int j = 1 ;
            for (WebElement element1 : menuIteMS) {
                String allMenus = element1.getText();
                System.out.println(data[i][j]);
                    Assert.assertEquals(allMenus, data[i][j]);
                    i++;

            }

        }

    }


// Data file
// Read
// Use the data in the data file
// output will be written in data file