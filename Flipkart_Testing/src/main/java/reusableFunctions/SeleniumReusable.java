package reusableFunctions;

import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import baseClass.Base_Library;
import io.cucumber.java.Scenario;

//In this file we are adding re-usable functions like - 
//take ss, dropdown, scroll, upload, download, handle alerts, windows handle, frames, web-table, drag&drop

public class SeleniumReusable extends Base_Library {
	// creating constructor here and pass webdriver
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions action = new Actions(driver);

	public SeleniumReusable(WebDriver driver) {
		this.driver = driver;
	}

	public void EnterValue(WebElement element, String Text) {

		try {
			element.sendKeys(Text);
			logger.info("***************Text Enter****************");
		} catch (Exception e) {
			System.out.println("No element found");
		}

	}

	public void click(WebElement element) {
		try {
			element.click();
			logger.info("***************Element clicked****************");
		} catch (Exception e) {
			System.out.println("Element not clickable or visible");
		}
	}

	public static void getTitle(WebDriver driver) {
		try {
			String title = driver.getTitle();
			System.out.println("title is " + title);
		} catch (Exception e) {
			System.out.println("Title not visible");
		}

	}

	public void screenShots(String path) {
		TakesScreenshot sreenshot = (TakesScreenshot) driver;
		File source = sreenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(path));
		} catch (Exception e) {
			System.out.println("Not able to take screenshot");

		}
	}

	public void scrollDown(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void mouseHover(WebElement element) {
		action.moveToElement(element).build().perform();
	}

	public static void windowHandle(WebDriver driver) {
		String parentWindow = driver.getWindowHandle();
		System.out.println("This is parent window ID : " + parentWindow);
		
		Set<String>allWindow = driver.getWindowHandles(); //no duplicates so used 'SET' instead of 'List';
		System.out.println("These are all open windows ID : " + allWindow);
		
		for(String childWindow : allWindow) {
			if(!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println("This is child window ID : " + childWindow);
				break;
			}
		}
	}
	
	public static void attachScreenShot(Scenario CucumberScenario, WebDriver driver) {
		final byte[] screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		CucumberScenario.attach(screenShot, "image/png", "flipkart_project"); // here image/png -> path and flipkart_project -> name of img
		
		
	}
	
	public static void navigateBack() {
		driver.navigate().back();
	}

}
