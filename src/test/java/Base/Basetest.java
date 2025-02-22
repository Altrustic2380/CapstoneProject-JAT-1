package Base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Utils.Screenshot;
import Utils.Utils;
import Utils.ConfigRead;

public class Basetest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        Utils.browserLaunch();  // Launch browser with URL
        driver = Utils.getDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Screenshot.captureScreenshot(driver, result.getName());
        }
        Utils.closeBrowser();  
    }
}

