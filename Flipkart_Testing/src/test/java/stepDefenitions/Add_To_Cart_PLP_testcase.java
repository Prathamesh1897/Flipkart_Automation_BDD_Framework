package stepDefenitions;

import java.io.IOException;

import baseClass.Base_Library;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.AddToCartPLP;


public class Add_To_Cart_PLP_testcase extends Base_Library{
	
	AddToCartPLP addItemToCart = new AddToCartPLP(driver);
	
	@Then("user click on line item {string}")
	public void user_click_on_line_item(String string) {
	   addItemToCart.selectLineItem(string);
	}

	@Then("verify add to cart button visible")
	public void verify_add_to_cart_button_visible() {
	    addItemToCart.verifyAddToCartButton();
	}
	
}
