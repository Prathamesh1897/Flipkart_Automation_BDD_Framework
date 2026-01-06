package hooks;

import java.io.IOException;

import reusableFunctions.SeleniumReusable;
import baseClass.Base_Library;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;

//purpose of HOOKs is to setting up basic things :
//launch Application, close application, close workbooks, sheets, close BD connection etc

public class Hooks extends Base_Library{
 
	public static Scenario scenario;
	@Before
	public void test(Scenario CucumberScenario) throws IOException {
		scenario = CucumberScenario;
		LaunchApplication();
	}
	
	@After
	public void cleanUp(Scenario CucumberScenario) {
		SeleniumReusable.attachScreenShot(CucumberScenario, driver);
		tearDown();
	}
}
