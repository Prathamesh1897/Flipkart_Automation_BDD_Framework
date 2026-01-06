package pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.FindBy;

import baseClass.Base_Library;
import io.appium.java_client.functions.ExpectedCondition;
import reusableFunctions.SeleniumReusable;
import utilities.ExcelUtilities;

public class SearchPage extends Base_Library {
//purpose of initElement is to initialize all elements. pageFactory.initelemnt is static method that
	// takes driver instance of given class and returns page object in fully
	// initialize

	SeleniumReusable se = new SeleniumReusable(driver);

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Search for Products, Brands and More']")
	WebElement searchText;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchButton;

	@FindBy(xpath = "//img[@title='Flipkart']")
	WebElement HomePage;

	@FindBy(xpath = "//div[contains(@data-id,'MOB')] | (//div[contains(@class,'QSCKDh dLgFEE')])[3]//div[@class='nZIRY7']") // div[@class='col
																															// col-7-12']
	List<WebElement> PLP;

	@FindBy(xpath = "//span[@role='button']")
	WebElement popupClose;

	public void searchText(String Text) {
		// se = new SeleniumReusable(driver);
		se.EnterValue(searchText, Text);

	}

	public void clickSearch() {
		searchButton.click();
		
	}

	public boolean HomePage() {
		// System.out.println("Home page is displayed " + HomePage.isDisplayed());
		HomePage.click();
		return HomePage.isDisplayed();
	}

	public void PLPpage() {
		se.screenShots("src/test/resources/screenShots/searchResults.png");
		System.out.println("Total List found: " + PLP.size());
		System.out.println("****************************************************");
		for (WebElement PLPresult : PLP) {
			System.out.println(PLPresult.getText());

		}
	}

	public void HomePageLoginPopup() {
		try {
			wait.until(ExpectedConditions.visibilityOf(popupClose));
			popupClose.click();
			System.out.println("Popup appeared and closed successfully.");

		} catch (TimeoutException e) {
			System.out.println("Popup not visible, continuing test execution...");
		} catch (Exception e) {
			System.out.println("Unexpected issue while handling popup: " + e.getMessage());
		}
	}

	public void EnterSearch(String search) {
		se.EnterValue(searchText, search);
	}
	
	public void searchWithExcel(Map<String, String> testData) {
		try {
			searchText.clear();
			searchText.sendKeys(testData.get("SearchText"));
			searchText.sendKeys(Keys.ENTER);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
