package Test;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.Basetest;
import Pages.Login;
import Pages.SearchHotel;
import Utils.ConfigRead;
import Utils.Screenshot;

public class SearchTest extends Basetest {
    private WebDriverWait wait;
    private Login loginPage;
    private SearchHotel searchHotelPage;

    @BeforeMethod
    public void setUpTest() throws InterruptedException {
        // Initialize wait and pages
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new Login(driver);
        searchHotelPage = new SearchHotel(driver);

        // Perform login before searching hotels
        String username = ConfigRead.getProperty("username");
        String password = ConfigRead.getProperty("password");
        loginPage.login(username, password);
        Thread.sleep(2000); // Wait for login to complete

        // Capture screenshot after successful login
        Screenshot.captureScreenshot(driver, "Login_Success");
        System.out.println("Login successful. Proceeding with hotel search.");
    }

    @Test
    public void testHotelSearch() throws InterruptedException {
        // Perform hotel search with delays for better visibility
        searchHotelPage.selectLocation("Sydney");
        Thread.sleep(1000);

        searchHotelPage.selectHotel("Hotel Creek");
        Thread.sleep(1000);

        searchHotelPage.selectRoomType("Deluxe");
        Thread.sleep(1000);

        searchHotelPage.selectNumberOfRooms("3 - Three");
        Thread.sleep(1000);

        searchHotelPage.enterCheckInDate("22/02/2025");
        Thread.sleep(1000);

        searchHotelPage.enterCheckOutDate("23/02/2025");
        Thread.sleep(1000);

        searchHotelPage.selectAdultsPerRoom("1 - One");
        Thread.sleep(1000);

        searchHotelPage.selectChildrenPerRoom("1 - One");
        Thread.sleep(1000);

        searchHotelPage.clickSearch();
        Thread.sleep(3000); // Wait for search results

        // Capture screenshot after search
        Screenshot.captureScreenshot(driver, "Hotel_Search_Result");

        // Verify search success
        Assert.assertTrue(searchHotelPage.isSearchSuccessful(), "Hotel search failed!");
        System.out.println("Hotel search executed successfully!");
    }
}


