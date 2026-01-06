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
import org.testng.Assert;
import org.openqa.selenium.support.FindBy;

public class AddToCartPLP extends Base_Library {

	SeleniumReusable se = new SeleniumReusable(driver);

	public AddToCartPLP(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "((//div[contains(@class,'QSCKDh dLgFEE')])[3]//div[@class='nZIRY7']/div)")
	List<WebElement> lineItems;
	
	/*@FindBy(xpath="//button[contains(text(),'Add to cart')]")
	WebElement AddToCartBtn;
	*/


	
	public void selectLineItem(String lineItem) {
		try {
			SeleniumReusable.getTitle(driver);
			int index = Integer.parseInt(lineItem); // "1" -> 1
			wait.until(ExpectedConditions.visibilityOfAllElements(lineItems));
			lineItems.get(index - 1).click();
			SeleniumReusable.windowHandle(driver);
			System.out.println("LineItem got clicked");
		} catch (Exception e) {
			System.out.println("lineItems not loaded :" + e.getMessage());
		}
	}
	
	public void verifyAddToCartButton() {
		try {
			//se.scrollDown(AddToCartBtn);
			//wait.until(ExpectedConditions.visibilityOf(AddToCartBtn));
			By addToCartLocator = By.xpath("//button[contains(text(),'Add to cart')]");
			WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartLocator));
			Assert.assertTrue(addToCartBtn.isDisplayed());
		}catch (TimeoutException e) {
			Assert.fail("Add to cart btn not visible");
		}
	}
}
