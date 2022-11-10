package newmaven.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import newMaven.pageObject.CartPage;
import newMaven.pageObject.checkoutPayment;
import newMaven.pageObject.confirmationPage;
import newMaven.pageObject.landingPage;
import newMaven.pageObject.productPage;
import newmaven.TestComponents.BaseClass;

public class StepDefinationsImplementations extends BaseClass {

	public landingPage LandingPage;
	public productPage ProductPage;
	public confirmationPage verifyOrder;

	@Given("I landed on Ecommerce website page ")
	public void I_landed_on_Ecommerce_website_page() throws IOException {
		LandingPage = launchWebsite();
	}

	@Given("^Logged In with Valid Username (.+) and Password (.+) $")
	public void Logged_In_with_Valid_Username__and_Password_(String username, String password) {
		ProductPage = LandingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart $")
	public void Add_Product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = ProductPage.getProductList();
		ProductPage.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit order $")
	public void Checkout_Order(String productName) throws InterruptedException {
		CartPage cartPage = ProductPage.naviagteCartPage();
		cartPage.cartListProducts();
		Boolean matchProd = cartPage.matchProducts(productName);
		Assert.assertTrue(matchProd);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		checkoutPayment checkoutPage = cartPage.checkoutNavigate();
		checkoutPage.CardDetails("854", "Kuldeep Singh");
		checkoutPage.selectCountry("INDIA");
		js.executeScript("window.scrollBy(0,1000)");
		verifyOrder = checkoutPage.SubmitOrder();

	}

	@Then("{string} Verify the displayed confirmation message $")
	public void Verify_Order_Confirmation_Message(String string) {
		verifyOrder.Verifyconfirmation();
		String VerifyconfirmationMessage = verifyOrder.Verifyconfirmation();
		Assert.assertTrue(VerifyconfirmationMessage.equalsIgnoreCase(string));
	}
}
