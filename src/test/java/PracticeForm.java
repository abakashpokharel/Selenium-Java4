import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PracticeForm {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/abakashpokharel/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("Abakash");
       // driver.findElement(By.xpath("//div[@class='col-md-1 col-sm-0']")).sendKeys("Abakash");
        driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("pokharel");
        driver.findElement(By.xpath("//*[@id=\"userEmail\"]")).sendKeys("austinpokharel@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label")).click();
        driver.findElement(By.xpath("//*[@id=\"userNumber\"]")).sendKeys("9808336597");
       // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Button Click



      WebElement element = driver.findElement(By.xpath("//form[@id='userForm']/div/div[2]/input"));
    //  element.findElements(By.tagName("input")).get(0).sendKeys("UserName");

        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys("UserName");

        //Run next line



    }
}
