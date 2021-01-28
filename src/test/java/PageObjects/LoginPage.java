package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@name='txtUsername']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@name='txtPassword']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
         wait = new WebDriverWait(driver,50);
    }

    public void enterUsername(String uname){
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(uname);
    }
    public void enterPassword(String pss){

        passwordField.sendKeys(pss);
    }
    public void clickSubmitButton(){
        submitButton.click();
    }

}
