package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ExcelUtil;
import java.time.Duration;

public class Login {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators using XPath
    @FindBy(xpath = "//*[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'auth_error')]")
    private WebElement errorMessage;

    // Constructor
    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Explicit wait
        PageFactory.initElements(driver, this);
    }

    // Login method with waits
    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // Check if login was successful
    public boolean isLoginSuccessful() {
        return !driver.getCurrentUrl().contains("Login");
    }

    // Get error message if login fails
    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        } catch (Exception e) {
            return "No error message displayed";
        }
    }

    // Login and log results into Excel
    public void loginAndRecordResult(String scenarioNo, String testCaseID, String username, String password) {
        login(username, password);

        String status = isLoginSuccessful() ? "Passed" : "Failed";
        String remarks = isLoginSuccessful() ? "Login successful" : getErrorMessage();
        String severity = "High";
        String expectedActualResults = isLoginSuccessful() ? "User successfully logged in" : "Login failed";
        String passFail = status;
        String complexity = "Medium";

        // Write test result to Excel
        ExcelUtil.writeTestResult(scenarioNo, testCaseID, "Username: " + username + ", Password: " + password, status, remarks, severity, expectedActualResults, passFail, complexity);
    }

	public boolean isLogoutSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isUserLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}
}


