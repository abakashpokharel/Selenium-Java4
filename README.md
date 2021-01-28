# JavaSeleniumTestFormat
This is a simple format to write test in Java Selenium, with users of Listeners, Threads for parallel testing, Test Suits,Maven Project

Make sure you run selenium grid and provide hub ip in testNg.xml File under hubUrl.

1. Make sure you import all the dependencies first
2. To run the test enter the command (mvn test -DsuiteXmlFile=testng.xm) in the terminal
3. All the page object should be written in src/main/java/(your page Name Folder)/PageName
4. All the test should be written in src/main/test/(your page Name)Test/PageNameTest
5. All the input data should be written in data/config/R7-test-data.xml File
6. All the test Class and test methods should be lited out in testng.xml as maven runs testng.xml first to extrat the test.
7. For extent reports you must initialize 
    ExtentTestManager.getTest().setDescription("Valid Login."); and 
     ExtentTestManager.getTest().log(LogStatus.INFO, "Invoking URL", "");
           for logs.
           
8. All the drivers should be in src/main/resources/Drivers
9. If you work with image assertion all taken screen shots and expected image should be in data/images
10. All the input file that you need in your project should be in data/images/filesForUpload
        
