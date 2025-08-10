package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import java.time.Duration;

public class FaceBookPage {
    WebDriver driver;
    WebDriverWait wait;

    public FaceBookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openSignupForm() {
        WebElement signup = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid='open-registration-form-button']")));
        signup.click();
    }

    public void fillBasicDetails1(String first) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname"))).sendKeys(first);
    }

    public void fillBasicDetails2(String lastname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("lastname"))).sendKeys(lastname);
    }

    public void fillBasicDetails3(String number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("reg_email__"))).sendKeys(number);
    }

    public void selectDOB1(String dayVal) {
        WebElement dayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("birthday_day")));
        new Select(dayElement).selectByVisibleText(dayVal);
    }

    public void selectDOB2(int monthIndex, String yearVal) {
        WebElement monthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("birthday_month")));
        new Select(monthElement).selectByIndex(monthIndex);

        WebElement yearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("birthday_year")));
        new Select(yearElement).selectByValue(yearVal);
    }

    public void selectGender(String value) {
        // value: "1" = Female, "2" = Male, "-1" = Custom
        WebElement genderRadio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='sex' and @value='" + value + "']")));
        genderRadio.click();

        if (value.equals("-1")) {
            WebElement pronounDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("preferred_pronoun")));
            Select pronounSelect = new Select(pronounDropdown);
            pronounSelect.selectByValue("6"); // "They: 'Wish them a happy birthday!'"
        }
    }

    public void submitForm() {
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.name("websubmit")));
        submit.click();
    }

    public void TakeScreenShot(String fileName) {
        String folderPath = "C:\\Users\\2418205\\OneDrive - Cognizant\\Pictures\\Screenshots";
        String filePath = folderPath + File.separator + fileName;

        try {
            File screenshotDir = new File(folderPath);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
                System.out.println("Created screenshot directory: " + folderPath);
            }

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);

            if (destination.exists()) {
                destination.delete();
            }

            FileHandler.copy(source, destination);
            System.out.println("Screenshot saved: " + filePath);
        } catch (Exception e) {
            System.out.println("Screenshot error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void printErrorMessages() {
        try {
            WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("password_field_error_text")));
            System.out.println("Password Error: " + passwordError.getText());
        } catch (Exception e) {
            System.out.println("Error messages not found.");
        }
    }
}
