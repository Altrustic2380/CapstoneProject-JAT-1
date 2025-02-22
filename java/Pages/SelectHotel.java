package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import Utils.ExcelUtil;

public class SelectHotel {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='radiobutton_0']")
    private WebElement firstHotelRadioButton;

    @FindBy(xpath = "//*[@id='continue']")
    private WebElement continueButton;

    // Constructor
    public SelectHotel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to select the first available hotel
    public void selectFirstHotel() {
        if (firstHotelRadioButton.isDisplayed() && firstHotelRadioButton.isEnabled()) {
            firstHotelRadioButton.click();
        } else {
            throw new RuntimeException("Radio button for hotel selection is not available.");
        }
    }

    // Method to click on continue after selection
    public void clickContinue() {
        if (continueButton.isDisplayed() && continueButton.isEnabled()) {
            continueButton.click();
        } else {
            throw new RuntimeException("Continue button is not available.");
        }
    }

    // Combined method for selecting hotel and proceeding
    public void selectFirstHotelAndContinue() {
        selectFirstHotel();
        clickContinue();
    }

    // Method to record test result into Excel
    public void selectHotelAndRecordResult(String scenarioNo, String testCaseID) {
        selectFirstHotelAndContinue();

        String status = driver.getCurrentUrl().contains("BookHotel") ? "Passed" : "Failed";
        String remarks = status.equals("Passed") ? "Hotel selection successful" : "Hotel selection failed";
        String severity = "High";
        String expectedActualResults = status.equals("Passed") ? "User navigated to booking page" : "Hotel selection not processed";
        String passFail = status;
        String complexity = "Medium";

        // Write test result to Excel
        ExcelUtil.writeTestResult(scenarioNo, testCaseID, "Hotel selection performed", status, remarks, severity, expectedActualResults, passFail, complexity);
    }

	public boolean isSelectionSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
}
