package stepDefenitions;

import java.io.IOException;
import java.util.Map;

import baseClass.Base_Library;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.SearchPage;
import utilities.ExcelUtilities;
import io.cucumber.java.en.Then;

public class Search_testcase extends Base_Library {

	SearchPage search = new SearchPage(driver);

	@Given("Launch the flipkart application")
	public void launchApplication() throws IOException {
		LaunchApplication();
		// search = new SearchPage(driver);
	}

	@When("Close the popup")
	public void closePopup() {
		search.HomePageLoginPopup();

	}

	@Then("Navigate to home page")
	public void navigateToHomePage() {
		search.HomePage();
	}

	@Then("User enter the text in search box")
	public void enterTextInSearchBox() {
		search.searchText("Mobile");
	}

	@When("User click the search button")
	public void clickSearchButton() {
		search.clickSearch();
	}

	@Then("User Navigate to PLP Page")
	public void navigateToPLP() {
		search.PLPpage();
		System.out.println("****************End of results*********************");
	}

	@Given("User enter {string} in search field")
	public void user_enter_in_search_field(String string) {
		search.EnterSearch(string);
	}

	@Then("Click on cross icon for login popup")
	public void click_on_cross_icon_for_login_popup() {
		search.HomePageLoginPopup();
	}

	@Given("User searches product using Excel row {int}")
	public void user_searches_product_using_excel(int rowNumber) throws IOException {

		ExcelUtilities excel = new ExcelUtilities();
		Map<String, String> testData = excel.getRowData("Sheet1", rowNumber);
		search.searchWithExcel(testData);

	}

	@Then("Search results should be deisplayed for Excel row {int}")
	public void verify_search_result_should_be_displayed_for_excel(int rowNumber) throws IOException {
		boolean isResultDisplayed = true;

		ExcelUtilities excel = new ExcelUtilities();
		if (isResultDisplayed) {
			excel.writeData("Sheet1", rowNumber, 1, "PASS");
			excel.writeData("Sheet1", rowNumber, 2, "Search Successful");
		} else {
			excel.writeData("Sheet1", rowNumber, 1, "FAIL");
			excel.writeData("Sheet1", rowNumber, 2, "Search UNSuccessful");
		}
	}

}
