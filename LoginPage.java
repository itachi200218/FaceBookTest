package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void login(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys(username);
    }

    public void Password(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys(password);
    }

    public void submit() {
        driver.findElement(By.name("login")).click();
    }

    public void TakeScreenShot(String filename) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("./screenshots/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
