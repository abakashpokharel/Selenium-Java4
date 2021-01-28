import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class OrangeHRMLogin {
    public static void main(String[] args) {
        // This line will open chrome
        System.setProperty("WebDriver.chrome.driver","/Users/abakashpokharel/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

        driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("admin123");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

       // Select select = new Select(driver.findElement(By.xpath("//select[@name='cars']")));
       // select.selectByIndex(2);
      //  select.selectByValue("Valvo");

        //driver.quit();
    }
}
