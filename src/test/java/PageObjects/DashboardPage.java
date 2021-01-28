package PageObjects;

import org.apache.poi.ss.formula.functions.Index;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ExcelReadWrite;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage {
    WebDriver driver;
    @FindBy(xpath = "//a[@class='firstLevelMenu']")
    List<WebElement> menuItems;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String currentUrl() {
        String currUrl = driver.getCurrentUrl();
        return currUrl;
    }

    public void getAllMenuItemsAndVerify() {
        ExcelReadWrite excelReadWrite = new ExcelReadWrite();
        Object[][] data = excelReadWrite.getAllData("DashboardExcelFile", "Dashboard Data");
        for (int i = 0; i < data.length; i++) {
                System.out.println("Menus are :" + data[i][0]);
               for (WebElement element1 : menuItems) {
                    String allMenus = element1.getText();
                    Assert.assertEquals(allMenus, data[i][0]);
                    i++;
                }
            }
        }
    }












