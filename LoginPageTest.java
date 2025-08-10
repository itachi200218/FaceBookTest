package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public class LoginPageTest {
    WebDriver driver;
    LoginPage fb;
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
        fb = new LoginPage(driver);
        FB = new FaceBookPage(driver);
    }

    @Test(dataProvider = "login", dataProviderClass = org.example.data.ExcelDataProvider.class)
    public void loginAccess(String username, String password) {
        Reporter.log("Running Login flow on " + browserType + "...", true);
        try {
            fb.login(username);
            fb.Password(password);
            FB.TakeScreenShot(browserType + "_LoginFilled.png");
            fb.submit();
            FB.TakeScreenShot(browserType + "_AfterLogin.png");
            FB.printErrorMessages();
        } catch (Exception e) {
            Reporter.log("Test Failed "+e.getMessage(),true);
        }
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);
            driver.quit();

    }
}
