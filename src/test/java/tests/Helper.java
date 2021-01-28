package tests;

//import org.apache.commons.io.FileUtils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.Strings;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Helper {

    public static Properties prop;
    public static String dataFolderBasePath = "data/config/";

    static {
        prop = new Properties();
    }

    public static void readConfig(String configFileName) throws IOException {
        try {
            if (configFileName == null || Strings.isNullOrEmpty(configFileName)) {
                configFileName = "default-test-data.xml";
            }
            File file = new File(dataFolderBasePath + configFileName);

            if (!file.exists()) {
                configFileName = "default-test-data.xml";
                file = new File(dataFolderBasePath + configFileName);
            }
            FileInputStream fileInput = new FileInputStream(file);
            prop.loadFromXML(fileInput);

            fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //To send Email without attachment
    public static void sendEmail(String message) throws EmailException {

        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(prop.getProperty("emailId"), decrypt(prop.getProperty("passwordEmail"))));
        email.setSSLOnConnect(true);
        email.setFrom(prop.getProperty("emailId"));
        email.setSubject("Test mail from R7 Automation");
        email.setMsg(message);
        email.addTo(prop.getProperty("emailSentTo"));
        email.send();
    }

    //To send Email with Attachment
    public static void sentAttachmentEmail(String message, String screenshotAttachment, String subject) throws EmailException, AddressException {

        final Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(prop.getProperty("emailId"), decrypt(prop.getProperty("passwordEmail")));
                    }
                });
        try {

            Message messages = new MimeMessage(session);
            messages.setFrom(new InternetAddress(prop.getProperty("emailId")));
            messages.setRecipients(Message.RecipientType.TO, InternetAddress.parse(prop.getProperty("emailSentTo")));
            messages.setSubject(subject);

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText(message);
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = screenshotAttachment;
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart1);
            messages.setContent(multipart);

            Transport.send(messages);
            System.out.println("=====Email Sent=====");
            //Extent report
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //Take screenshot for failed test
    public static String takeScreenShot(String imageFileName) throws IOException, InterruptedException {
        TakesScreenshot screenshot = (TakesScreenshot) Helper.getDriver();
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\data\\images\\screenShotImage\\" + imageFileName + ".jpg"));
        String filePath = System.getProperty("user.dir") + "\\data\\images\\screenShotImage\\" + imageFileName + ".jpg";
        return filePath;
    }


    //Wait for Page to Load

    public static void waitForPageToBeReady() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        // Initially below given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            return;
        }
        // This loop will rotate for 25 times to check If page Is ready after every 1 second.
        int waitTime = 30; // 30 secs
        for (int i = 0; i < waitTime; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }

    //wait time for pop up
    public static String handelPopUp() {
        int waitTime = 20; //Default wait time

        return handelPopUp(waitTime);
    }

    //Pop up Handel pop up
    public static String handelPopUp(int waitTime) {
        String alterText;
        try {
            new WebDriverWait(getDriver(), waitTime).until(ExpectedConditions.alertIsPresent());

            Alert alert = getDriver().switchTo().alert();
            alterText = getDriver().switchTo().alert().getText();
            alert.accept();

            return alterText;
        } catch (UnhandledAlertException e) {
            Helper.getDriver().switchTo().alert().accept();
            return "";
        } catch (NoAlertPresentException e) {
            return "";
        } catch (TimeoutException e) {
            return "";
        } catch (Exception e) {
            return "";
        }
    }


    private static Random random = new SecureRandom();

    public static String encrypt(String userId) {
        BASE64Encoder encoder = new BASE64Encoder();

        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return encoder.encode(salt) +
                encoder.encode(userId.getBytes());
    }

    public static String decrypt(String encryptKey) {
        // ignore salt
        if (encryptKey.length() > 12) {
            String cipher = encryptKey.substring(12);
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                return new String(decoder.decodeBuffer(cipher));
            } catch (IOException e) {
                //  throw new InvalidImplementationException(
                //    "Failed to perform decryption for key ["+encryptKey+"]",e);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        System.out.println("Encrypted Password is : " + encrypt(password));
        System.out.println("Real Passwor is : " + decrypt(encrypt(password)));
    }

    public static String dateTimeStamp() {

        //Creating Date Time stamp
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        String dateTimeStamp = dateFormat.format(date);
        //System.out.println("Current date and time is " +dateTimeStamp);
        return dateTimeStamp;
    }

    public static String dateStamp() {

        //Creating Date Time stamp
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStamp = dateFormat.format(date);
        //System.out.println("Current date and time is " +dateTimeStamp);
        return dateStamp;
    }

    public static String timeStamp() {

        //Creating Date Time stamp
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String timeStamp = timeFormat.format(date);
        //System.out.println("Current date and time is " +dateTimeStamp);
        return timeStamp;
    }


    public static long startTime() {
        StopWatch pageLoad = new StopWatch();
        pageLoad.start();
        long pageLoadTime_ms = pageLoad.getTime();

        return pageLoadTime_ms;
    }

    public static long finishTime(){
        StopWatch pageLoad = new StopWatch();
        pageLoad.start();
        long pageLoadTime_ms = pageLoad.getTime();

        return pageLoadTime_ms;

    }
    public static void calculateLoadingTime(String pageName){
        Logger log = Logger.getLogger(LoginPageTest.class);
         long startTime=startTime();
        System.out.println("returned star time is : "+startTime);
        long finishTime=finishTime();
        System.out.println("returned finish time is : "+startTime);
        int TotalTime= (int) (finishTime-startTime);
        log.info("Total time taken to "+pageName+  ":" +TotalTime);
        ExtentTestManager.getTest().log(LogStatus.INFO, "Total time taken to "+pageName + ":", ""+TotalTime);

    }

    // LocalWebDriverManager
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}

