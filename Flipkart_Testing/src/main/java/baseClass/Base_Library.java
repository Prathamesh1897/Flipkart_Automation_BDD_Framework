package baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Library {

	public static Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public Logger logger;

	public void LaunchApplication() throws IOException {
		FileInputStream input = new FileInputStream("src/test/resources/Properties/Config.properties");
		prop = new Properties();
		prop.load(input);
		logger = Logger.getLogger(Base_Library.class);
		PropertyConfigurator.configure("src/test/resources/Properties/log4j.properties");

		String browser = prop.getProperty("browser").toLowerCase();
		String url = prop.getProperty("url");

		try {

			switch (browser) {
			case "chrome":
				 if (driver == null) {
			            WebDriverManager.chromedriver().setup();
			            driver = new ChromeDriver();
					logger.info("***************Chrome Launched****************");
					break;
				}
				/*
				 * case "firefox": System.setProperty("webdriver.gecko.driver",
				 * "C:\\Users\\prathamesh.kide\\Downloads\\geckodriver.exe"); driver = new
				 * FirefoxDriver(); break;
				 */

			default:
				System.out.println("Browser not supported: " + browser);
				return;
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Selenium 3 style
			wait = new WebDriverWait(driver, 20); // Selenium 3 style
			driver.get(url);

		} catch (Exception e) {
			System.out.println("Browser launch failed: " + e.getMessage());
		}
	}

	public void tearDown() {
		if (driver != null) {
			driver.close();
			driver = null;
		} else {
			driver.quit();
		}
	}

}
