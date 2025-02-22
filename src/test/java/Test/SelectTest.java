package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.Basetest;
import Pages.Login;
import Pages.SearchHotel;
import Pages.SelectHotel;

public class SelectTest extends Basetest {
    private Login loginPage;
    private SearchHotel searchHotelPage;
    private SelectHotel selectHotelPage;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new Login(driver);
        searchHotelPage = new SearchHotel(driver);
        selectHotelPage = new SelectHotel(driver);
    }

    @Test
    public void testSelectHotel() throws InterruptedException {
        // Perform login
        loginPage.login("Amritha008", "Am238#Mo");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed! Test cannot proceed.");
        
        // Perform a valid hotel search
        searchHotelPage.searchHotel(
            "Sydney", "Hotel Creek", "Deluxe", "3 - Three", "22/02/2025", "23/02/2025", "1 - One", "1 - One"
        );
        Thread.sleep(3000); // Wait for search results
        Assert.assertTrue(driver.getCurrentUrl().contains("SelectHotel"), "Hotel search failed!");

        // Select the first hotel and continue
        selectHotelPage.selectFirstHotelAndContinue();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("BookHotel"), "Hotel selection failed!");

        System.out.println("Hotel selection executed successfully!");
    }
}

