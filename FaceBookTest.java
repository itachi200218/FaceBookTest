package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public class FaceBookTest {

    WebDriver driver;
    FaceBookPage FB;
    String browserType;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("edge") String browser) {
        browserType = browser;

        if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\2418205\\Downloads\\edgedriver_win32\\msedgedriver.exe");
            driver = new EdgeDriver();
            Reporter.log("Running with Edge browser...", true);
        } else {
            driver = new ChromeDriver();
            Reporter.log("Running with Chrome browser...", true);
        }

        driver.manage().window().maximize();
        driver.get("https://www.facebook.com");
        FB = new FaceBookPage(driver);

    }

    @Test(dataProvider = "signup", dataProviderClass = org.example.data.ExcelDataProvider.class)
    public void runSignupTest(String firstName, String lastName, String day, String month, String year, String gender, String mobile) {
        try {
            Reporter.log("Running Signup flow on " + browserType + "...", true);

            FB.openSignupForm();
            FB.TakeScreenShot(browserType + "_BeforeInfo.png");

            FB.fillBasicDetails1(firstName);
            FB.TakeScreenShot(browserType + "_firstName.png");

            FB.fillBasicDetails2(lastName);
            FB.TakeScreenShot(browserType + "_lastName.png");

            FB.selectDOB1(day);
            FB.TakeScreenShot(browserType + "_day.png");

            FB.selectDOB2(Integer.parseInt(month), year);
            FB.TakeScreenShot(browserType + "_monthYear.png");

            FB.selectGender(gender);
            FB.TakeScreenShot(browserType + "_gender.png");

            FB.fillBasicDetails3(mobile);
            FB.TakeScreenShot(browserType + "_mobile.png");

            FB.submitForm();
            FB.TakeScreenShot(browserType + "_AfterSubmit.png");

            FB.printErrorMessages();

        } catch (Exception e) {
            Reporter.log("Test failed: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        FB.TakeScreenShot(browserType + "_FinalState.png");
        if (driver != null) {
            driver.quit();
        }
    }
}
