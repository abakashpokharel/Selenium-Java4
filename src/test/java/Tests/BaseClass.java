package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver","/Users/abakashpokharel/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
