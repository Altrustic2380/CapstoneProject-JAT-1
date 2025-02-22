package Utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utils {
    private static WebDriver driver;

    private static final String BROWSER = ConfigRead.getProperty("browser");
    private static final String CHROME_DRIVER_PATH = ConfigRead.getProperty("chromedriver.path");
    private static final String BASE_URL = ConfigRead.getProperty("url");
    private static final int IMPLICIT_WAIT = Integer.parseInt(ConfigRead.getProperty("implicitWait"));

    public static void browserLaunch() {
        if (driver == null) {  
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
            driver.manage().window().maximize();
            driver.get(BASE_URL);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;  
        }
    }
}
