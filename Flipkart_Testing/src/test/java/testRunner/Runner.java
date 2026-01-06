package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//plugin add for reporting system under cucumberOptions

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/SearchFunctionality.feature", glue = { "stepDefenitions",
		"hooks" }, plugin = { "pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, tags = { "@ExcelDemo1" }

)

public class Runner {

}
