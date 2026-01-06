package pages;

import baseClass.Base_Library;
import reusableFunctions.SeleniumReusable;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

public class Filters extends Base_Library {

	public Filters(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='_0BvurA']")
	WebElement filterSection;

	// @FindBy(xpath = "//div[contains(text(),' \" + filterCategory + \"')]")
	// WebElement filterName;

	// @FindBy(xpath =
	// "//section//div[contains(text(),'%s')]/following::div[1]/div[contains(@class,'bs1+1t')]")
	// WebElement filterOptions;

	//@FindBy(xpath = "//section//div[contains(text(),'\" + filterCategory + \"')]/following::div[1]/div[contains(@class,'bs1+1t')]/div[contains(@title,'\" + filterOption + \"')]")
	//WebElement filterOptionSelect;

	@FindBy(xpath = "//span[@role='button']")
	WebElement popupClose;

	@FindBy(xpath = "//img[@title='Flipkart']")
	WebElement HomePage;
	
	@FindBy(xpath="//div[@class='nvQqOr']")
	List<WebElement> selectedFiltersList;

	public void HomePage() {
		System.out.println("Home page is displayed " + HomePage.isDisplayed());
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

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public void selectFilter(String filterCategory, String filterOption) {
		try {

			String filterNameHeader = String.format("//div[contains(text(),'%s')]", filterCategory);
			String filterOptions = String.format("//section//div[contains(text(),'%s')]/following::div[1]/div[contains(@class,'bs1+1t')] | //section//div[contains(text(),'RAM')]/following::div[1]/div[contains(@class,'bs1+1t')]",filterCategory);
			String filterOptionXpath = String.format("(//input[@type='checkbox']/following::div[contains(text(),'%s')])[1]", filterOption);

			wait.until(ExpectedConditions.visibilityOf(filterSection));
			System.out.println("filterSection appeared successfully.");
			js.executeScript("arguments[0].scrollIntoView(true);", filterSection);

			WebElement filterOptionContainer = driver.findElement(By.xpath(filterOptions));

			if (!filterOptionContainer.isDisplayed()) {
				js.executeScript("arguments[0].click();", filterNameHeader);
				System.out.println("section Expanded.");
				wait.until(ExpectedConditions.visibilityOf(filterOptionContainer));
			} else {
				System.out.println("Already expanded");
			}
			
			WebElement filterOptionElement = driver.findElement(By.xpath(filterOptionXpath));
			js.executeScript("arguments[0].scrollIntoView(true);", filterOptionElement);
			 js.executeScript("arguments[0].click();", filterOptionElement);
	            System.out.println("Selected filter : " + filterOption);
	            

		} catch (TimeoutException e) {
			System.out.println("filterSection not visible, continuing test execution...");
		} catch (Exception e) {
			System.out.println("Unexpected issue while handling filter: " + e.getMessage());
		}

	}
	
	
	public void appliedFilters() {
		System.out.println("Total applied filter found: " + selectedFiltersList.size());
		System.out.println("****************************************************");
		for(WebElement appliedFilters : selectedFiltersList ) {
			System.out.println(appliedFilters.getText());
			System.out.println("****************************************************");
		}
	}
	
	public void applyFilters(List<Map<String, String>> filtersList) {
		
	}

}
