package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.Basetest;
import Pages.Login;
import Pages.SearchHotel;
import Pages.SelectHotel;
import Pages.BookingHotel;

public class BookingTest extends Basetest {
    private Login loginPage;
    private SearchHotel searchHotelPage;
    private SelectHotel selectHotelPage;
    private BookingHotel bookingHotelPage;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new Login(driver);
        searchHotelPage = new SearchHotel(driver);
        selectHotelPage = new SelectHotel(driver);
        bookingHotelPage = new BookingHotel(driver);
    }

    @Test
    public void testHotelBooking() throws InterruptedException {
        // Perform login
        loginPage.login("Amritha008", "Am238#Mo");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed! Test cannot proceed.");

        // Perform hotel search with delays for better UI interaction
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

        // Verify search success
        Assert.assertTrue(driver.getCurrentUrl().contains("SelectHotel"), "Hotel search failed!");

        // Select the first available hotel and continue
        selectHotelPage.selectFirstHotelAndContinue();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("BookHotel"), "Hotel selection failed!");

        // Fill in booking details with delays for better interaction
        bookingHotelPage.enterFirstName("Bunny");
        Thread.sleep(1000);

        bookingHotelPage.enterLastName("Doe");
        Thread.sleep(1000);

        bookingHotelPage.enterAddress("123 Street, Salem, India");
        Thread.sleep(1000);

        bookingHotelPage.enterCreditCardNumber("4444222233335555");
        Thread.sleep(1000);

        bookingHotelPage.selectCreditCardType("VISA");
        Thread.sleep(1000);

        bookingHotelPage.selectCCExpMonth("December");
        Thread.sleep(1000);

        bookingHotelPage.selectCCExpYear("2026");
        Thread.sleep(1000);

        bookingHotelPage.enterCCCVV("123");
        Thread.sleep(1000);

        bookingHotelPage.clickBookNow();
        Thread.sleep(5000); // Wait for booking confirmation

        // Verify booking success
        Assert.assertTrue(driver.getCurrentUrl().contains("BookingConfirm"), "Booking failed!");
        System.out.println("Hotel booking executed successfully!");

        // Perform logout
        bookingHotelPage.clickLogout();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("Logout"), "Logout failed!");
    }
}
