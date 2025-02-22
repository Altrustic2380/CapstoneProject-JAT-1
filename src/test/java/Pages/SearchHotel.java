package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utils.ExcelUtil;

public class SearchHotel {
    private WebDriver driver;

    // Locators using XPath
    @FindBy(xpath = "//*[@id='location']")
    private WebElement locationDropdown;

    @FindBy(xpath = "//*[@id='hotels']")
    private WebElement hotelsDropdown;

    @FindBy(xpath = "//*[@id='room_type']")
    private WebElement roomTypeDropdown;

    @FindBy(xpath = "//*[@id='room_nos']")
    private WebElement numberOfRoomsDropdown;

    @FindBy(xpath = "//*[@id='datepick_in']")
    private WebElement checkInDateField;

    @FindBy(xpath = "//*[@id='datepick_out']")
    private WebElement checkOutDateField;

    @FindBy(xpath = "//*[@id='adult_room']")
    private WebElement adultsPerRoomDropdown;

    @FindBy(xpath = "//*[@id='child_room']")
    private WebElement childrenPerRoomDropdown;

    @FindBy(xpath = "//*[@id='Submit']")
    private WebElement searchButton;

    // Constructor
    public SearchHotel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Select methods for dropdowns
    public void selectLocation(String location) {
        new Select(locationDropdown).selectByVisibleText(location);
    }

    public void selectHotel(String hotel) {
        new Select(hotelsDropdown).selectByVisibleText(hotel);
    }

    public void selectRoomType(String roomType) {
        new Select(roomTypeDropdown).selectByVisibleText(roomType);
    }

    public void selectNumberOfRooms(String numberOfRooms) {
        new Select(numberOfRoomsDropdown).selectByVisibleText(numberOfRooms);
    }

    // Input methods for date fields
    public void enterCheckInDate(String checkInDate) {
        checkInDateField.clear();
        checkInDateField.sendKeys(checkInDate);
    }

    public void enterCheckOutDate(String checkOutDate) {
        checkOutDateField.clear();
        checkOutDateField.sendKeys(checkOutDate);
    }

    // Select methods for guest details
    public void selectAdultsPerRoom(String adults) {
        new Select(adultsPerRoomDropdown).selectByVisibleText(adults);
    }

    public void selectChildrenPerRoom(String children) {
        new Select(childrenPerRoomDropdown).selectByVisibleText(children);
    }

    // Click method for search button
    public void clickSearch() {
        searchButton.click();
    }

    // Method to perform hotel search
    public void searchHotel(String location, String hotel, String roomType, String numberOfRooms, 
                            String checkInDate, String checkOutDate, String adults, String children) throws InterruptedException {
        selectLocation(location);
        Thread.sleep(1000);
        selectHotel(hotel);
        Thread.sleep(1000);
        selectRoomType(roomType);
        Thread.sleep(1000);
        selectNumberOfRooms(numberOfRooms);
        Thread.sleep(1000);
        enterCheckInDate(checkInDate);
        Thread.sleep(1000);
        enterCheckOutDate(checkOutDate);
        Thread.sleep(1000);
        selectAdultsPerRoom(adults);
        Thread.sleep(1000);
        selectChildrenPerRoom(children);
        Thread.sleep(1000);
        clickSearch();
    }

    // Method to check if the search was successful
    public boolean isSearchSuccessful() {
        return driver.getCurrentUrl().contains("SelectHotel");
    }

    // Method to perform hotel search and record results into Excel
    public void searchAndRecordResult(String scenarioNo, String testCaseID, String location, String hotel, 
                                      String roomType, String numberOfRooms, String checkInDate, String checkOutDate, 
                                      String adults, String children) throws InterruptedException {
        searchHotel(location, hotel, roomType, numberOfRooms, checkInDate, checkOutDate, adults, children);
        
        String status = isSearchSuccessful() ? "Passed" : "Failed";
        String remarks = status.equals("Passed") ? "Search successful" : "Search failed";
        String severity = "High";
        String expectedActualResults = status.equals("Passed") ? "Hotel list displayed" : "No results found";
        String passFail = status;
        String complexity = "Medium";

        // Write test result to Excel
        ExcelUtil.writeTestResult(scenarioNo, testCaseID, "Search Details: " + location + ", " + hotel + ", " + roomType, 
                                  status, remarks, severity, expectedActualResults, passFail, complexity);
    }
}
