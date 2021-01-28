import org.testng.annotations.*;

public class TestngAnnotaitions {

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }
    @Test
    public void testAnnotaion(){
        System.out.println("Test");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }
    @BeforeGroups
    public void beforeGroups(){
        System.out.println("Before Group");
    }
    @AfterGroups
    public void afterGroups(){
        System.out.println("After Groups");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }
}
