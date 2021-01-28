package login;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ITH on 11/30/2017.
 */
public class Login {

    WebDriver Logindriver;

    @FindBy(id="username")
    WebElement loginTextField;

    @FindBy(id="password")
    WebElement passwordTextField;

    @FindBy(id = "mainDomain")
    WebElement domainTextField;

    @FindBy(xpath = "//ul[@class='languageList dropdown-menu flag-menu']/li/a")
    List<WebElement> selectLanguage;

   @FindBy(xpath = "//div[@class='dropdown language form-group']/a")
    WebElement clickLanguageSelector;

    @FindBy(name = "remember")
    WebElement rememberMe;

    @FindBy(id = "btn_submit")
    WebElement loginButton;

    @FindBy(className = "alert-label")
    WebElement invalidError;

    @FindBy(xpath = "//div[@id='emptyError']/label/span")
    WebElement blankUserPassError;

    public Login(WebDriver driver) {
        this.Logindriver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getUserPassEmptyMsg() {
        String emptyErrorMsg = blankUserPassError.getText();
        System.out.println("the Error message is : " + emptyErrorMsg);
        return emptyErrorMsg;
    }

    public String getInvalidLoginMsg() {
        String invalidLoginMsg = invalidError.getText();
        System.out.println("invalid message is : " + invalidLoginMsg);
        return invalidLoginMsg;
    }

    public void enterUserName(String loginName) {
        WebDriverWait wait = new WebDriverWait(Logindriver, 20);
        wait.until(ExpectedConditions.visibilityOf(loginTextField));
        loginTextField.clear();
        loginTextField.sendKeys(loginName);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(Logindriver, 100);
        wait.until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.clear();
        passwordTextField.sendKeys(password);
    }

    public void enterDomain(String domain) {
        WebDriverWait wait = new WebDriverWait(Logindriver, 100);
        wait.until(ExpectedConditions.visibilityOf(domainTextField));
        domainTextField.clear();
        domainTextField.sendKeys(domain);
    }

    public void clickLanguageSelector() {
        clickLanguageSelector.click();
    }

    public void languageSelection(String language) throws InterruptedException {
        for (WebElement we : selectLanguage) {
            String languageSelection = we.getAttribute("onclick");
            System.out.println("languages are : "+languageSelection);
            System.out.println("input language is : "+language);
            if (languageSelection.contains(language)) {
                ((JavascriptExecutor) Logindriver).executeScript("arguments[0].click();", we);
                break;
            }
        }
    }

    public void clickRememberMe() {
        WebDriverWait wait = new WebDriverWait(Logindriver, 100);
        wait.until(ExpectedConditions.visibilityOf(rememberMe));
        rememberMe.click();
    }

    public void clickLoginButon() {
        loginButton.click();
    }

}
