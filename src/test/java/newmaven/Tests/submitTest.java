package newmaven.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import newMaven.pageObject.CartPage;
import newMaven.pageObject.OrderPage;
import newMaven.pageObject.checkoutPayment;
import newMaven.pageObject.confirmationPage;
import newMaven.pageObject.landingPage;
import newMaven.pageObject.productPage;
import newmaven.TestComponents.BaseClass;

public class submitTest extends BaseClass {

	String prodName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "orderPlace")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		productPage ProductPage = LandingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = ProductPage.getProductList();
		ProductPage.addProductToCart(input.get("prodName"));
		CartPage cartPage = ProductPage.naviagteCartPage();
		cartPage.cartListProducts();
		Boolean matchProd = cartPage.matchProducts(input.get("prodName"));
		Assert.assertTrue(matchProd);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		checkoutPayment checkoutPage = cartPage.checkoutNavigate();
		checkoutPage.CardDetails("854", "Kuldeep Singh");
		Thread.sleep(2000);
		checkoutPage.selectCountry("INDIA");
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
		confirmationPage verifyOrder = checkoutPage.SubmitOrder();
		verifyOrder.Verifyconfirmation();
		String VerifyconfirmationMessage = verifyOrder.Verifyconfirmation();
		Assert.assertEquals(VerifyconfirmationMessage, "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods = "submitOrder")
	public void ordersHistory() {
		String prodName = "ZARA COAT 3";
		LandingPage.loginApplication("kks96.narola@gmail.com", "Kuldeep@1996");
		OrderPage orderPage = LandingPage.orderHistoryPage();
		orderPage.orderListProdname();
		Boolean matchOdrProd = orderPage.verifyOrderProductDisplay(prodName);
		Assert.assertTrue(matchOdrProd);
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> Data = getJSonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//newmaven//Data//orderPurchage.json");
		return new Object[][] { { Data.get(0) }, { Data.get(1) } };
	}

	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"rohit@mailinator.com", "Test@1234",
	 * "ZARA COAT 3"},{"kks96.narola@gmail.com", "Kuldeep@1996",
	 * "ADIDAS ORIGINAL"}}; }
	 */

	// create Hashmap to send Key value data in pairs directly
//	HashMap<String, String >map=new HashMap<String, String>();
//	map.put("email", "kks96.narola@gmail.com");
//	map.put("password", "Kuldeep@1996");
//	map.put("prodName", "ADIDAS ORIGINAL");
//	
//	HashMap<String, String >map1=new HashMap<String, String>();
//	map1.put("email", "rohit@mailinator.com");
//	map1.put("password", "Test@1234");
//	map1.put("prodName", "ZARA COAT 3");
}
