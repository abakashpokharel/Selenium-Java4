import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelfserviceTravelers {
    public static void main(String[] args) throws InterruptedException {
        // This line will open chrome
        System.setProperty("webdriver.chrome.driver","/Users/abakashpokharel/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://selfservice.travelers.com/login/#/");

       // driver.findElement(By.xpath("//a[@id='dropdownCurrency']/i")).click();

        //Thread.sleep(10000);

        driver.findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys("austinpokharel@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys("Abakashvai@1");
        driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();

    }

}
