package stepDefenitions;

import java.io.IOException;

import baseClass.Base_Library;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.Filters;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

public class Filter_testcase extends Base_Library {

	Filters filter = new Filters(driver);
	 

	@Then("User applies filter {string} with option {string}")
	public void user_applies_filter(String filterCategory, String filterOption) {
		filter.selectFilter(filterCategory,filterOption );
	}
	
	@Then("User get the applied filters list")
	public void appliedFilterList() {
		filter.appliedFilters();
	}
	
	@Then("User applies filters")
    public void user_applies_filters(DataTable dataTable) {
        List<Map<String, String>> filtersList = dataTable.asMaps(String.class, String.class);
        filter.applyFilters(filtersList);
    }
}
