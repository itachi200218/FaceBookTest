package org.example.data;

import org.example.ExcelReader;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "signup")
    public Object[][] getSignupData() {
               return ExcelReader.readExcelData("miniProject.xlsx", "SignupSheet", 7);
    }

    @DataProvider(name = "login")
    public Object[][] getLoginData() {
        return ExcelReader.readExcelData("miniProject.xlsx", "LoginSheet", 2);
    }

}
