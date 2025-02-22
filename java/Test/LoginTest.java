package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Base.Basetest;
import Pages.Login;
import Utils.ConfigRead;
import Utils.ExcelUtil;

public class LoginTest extends Basetest {
    private Login loginPage;
    private String username;
    private String password;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new Login(driver);  // Initialize LoginPage

        // Fetch credentials from config.properties
        username = ConfigRead.getProperty("username");
        password = ConfigRead.getProperty("password");
    }

    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.loginAndRecordResult("TS001", "TC001", username, password);

        // Verify user is logged in
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed!");
        System.out.println("Login Successful!");
    }

    @Test(priority = 2, dataProvider = "invalidLoginData")
    public void testInvalidLogin(String scenarioNo, String testCaseID, String testUsername, String testPassword) {
        loginPage.loginAndRecordResult(scenarioNo, testCaseID, testUsername, testPassword);

        // Verify login failure
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should have failed, but it passed.");
        System.out.println("Login Failed as expected for: " + testUsername);
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginDataProvider() {
        return new Object[][]{
            {"TS002", "TC002", "InvalidUser", "WrongPass"},   // Invalid username
            {"TS003", "TC003", username, "WrongPass"},        // Correct username, wrong password
            {"TS004", "TC004", "", password},                 // Empty username
            {"TS005", "TC005", username, ""},                 // Empty password
        };
    }
}


