package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.ExcelUtil;

public class BookingHotel {
    WebDriver driver;

    // Hotel details displayed on the booking page
    @FindBy(xpath = "//*[@id='hotel_name_dis']") private WebElement hotelName;
    @FindBy(xpath = "//*[@id='location_dis']") private WebElement location;
    @FindBy(xpath = "//*[@id='room_type_dis']") private WebElement roomType;
    @FindBy(xpath = "//*[@id='room_num_dis']") private WebElement roomNumber;
    @FindBy(xpath = "//*[@id='total_days_dis']") private WebElement totalDays;
    @FindBy(xpath = "//*[@id='price_night_dis']") private WebElement pricePerNight;
    @FindBy(xpath = "//*[@id='total_price_dis']") private WebElement totalPrice;
    @FindBy(xpath = "//*[@id='gst_dis']") private WebElement gst;
    @FindBy(xpath = "//*[@id='final_price_dis']") private WebElement finalPrice;

    // Booking form fields
    @FindBy(xpath = "//*[@id='first_name']") private WebElement firstName;
    @FindBy(xpath = "//*[@id='last_name']") private WebElement lastName;
    @FindBy(xpath = "//*[@id='address']") private WebElement address;
    @FindBy(xpath = "//*[@id='cc_num']") private WebElement creditCardNumber;
    @FindBy(xpath = "//*[@id='cc_type']") private WebElement creditCardType;
    @FindBy(xpath = "//*[@id='cc_exp_month']") private WebElement ccExpMonth;
    @FindBy(xpath = "//*[@id='cc_exp_year']") private WebElement ccExpYear;
    @FindBy(xpath = "//*[@id='cc_cvv']") private WebElement ccCVV;

    // Buttons
    @FindBy(xpath = "//*[@id='book_now']") private WebElement bookNowBtn;
    @FindBy(xpath = "//*[@id='logout']") private WebElement logoutBtn;

    // Constructor
    public BookingHotel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Get hotel details (for validation)
    public String getHotelName() { return hotelName.getText(); }
    public String getLocation() { return location.getText(); }
    public String getRoomType() { return roomType.getText(); }
    public String getRoomNumber() { return roomNumber.getText(); }
    public String getTotalDays() { return totalDays.getText(); }
    public String getPricePerNight() { return pricePerNight.getText(); }
    public String getTotalPrice() { return totalPrice.getText(); }
    public String getGST() { return gst.getText(); }
    public String getFinalPrice() { return finalPrice.getText(); }

    // Fill booking form
    public void enterFirstName(String fName) { firstName.sendKeys(fName); }
    public void enterLastName(String lName) { lastName.sendKeys(lName); }
    public void enterAddress(String addr) { address.sendKeys(addr); }
    public void enterCreditCardNumber(String ccNum) { creditCardNumber.sendKeys(ccNum); }
    public void selectCreditCardType(String type) { creditCardType.sendKeys(type); }
    public void selectCCExpMonth(String month) { ccExpMonth.sendKeys(month); }
    public void selectCCExpYear(String year) { ccExpYear.sendKeys(year); }
    public void enterCCCVV(String cvv) { ccCVV.sendKeys(cvv); }

    // Click actions
    public void clickBookNow() { bookNowBtn.click(); }
    public void clickLogout() { logoutBtn.click(); }

    // Book hotel and record results into Excel
    public void bookAndRecordResult(String scenarioNo, String testCaseID, String fName, String lName, String addr, String ccNum, String ccType, String expMonth, String expYear, String cvv) {
        enterFirstName(fName);
        enterLastName(lName);
        enterAddress(addr);
        enterCreditCardNumber(ccNum);
        selectCreditCardType(ccType);
        selectCCExpMonth(expMonth);
        selectCCExpYear(expYear);
        enterCCCVV(cvv);
        clickBookNow();

        String status = driver.getCurrentUrl().contains("BookingConfirm") ? "Passed" : "Failed";
        String remarks = status.equals("Passed") ? "Booking successful" : "Booking failed";
        String severity = "High";
        String expectedActualResults = status.equals("Passed") ? "Booking confirmation displayed" : "Booking not completed";
        String passFail = status;
        String complexity = "Medium";

        // Write test result to Excel
        ExcelUtil.writeTestResult(scenarioNo, testCaseID, "Booking Details: " + fName + ", " + lName + ", " + addr, status, remarks, severity, expectedActualResults, passFail, complexity);
    }

	public boolean isBookingConfirmed() {
		// TODO Auto-generated method stub
		return false;
	}
}

