package newmaven.Tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import newMaven.pageObject.checkoutPayment;
import newMaven.pageObject.confirmationPage;
import newMaven.pageObject.landingPage;
import newMaven.pageObject.productPage;
import newmaven.TestComponents.BaseClass;
import newmaven.TestComponents.RetryClass;

public class errorMessageValidations extends BaseClass {
	

	@Test(groups = { "ErrorMessageHandling" }, retryAnalyzer=RetryClass.class)
	public void loginError() throws IOException, InterruptedException {

		
		LandingPage.loginApplication("kks96.narola@gmail.com", "kuldeep@1996");
		AssertJUnit.assertEquals("Incorrect email or password.", LandingPage.getErrorValidation());

	}

	@Test
	public void productMatchValidation() throws IOException, InterruptedException {

		String prodName = "ZARA COAT 3";
		productPage ProductPage = LandingPage.loginApplication("kks96.narola@gmail.com", "Kuldeep@1996");

		List<WebElement> products = ProductPage.getProductList();
		ProductPage.addProductToCart(prodName);
		CartPage cartPage = ProductPage.naviagteCartPage();

		cartPage.cartListProducts();
		Boolean matchProd = cartPage.matchProducts("ZARA COAT");
		Assert.assertFalse(matchProd);

	}
	@Test
	public void productMatchValidation1() throws IOException, InterruptedException {

		String prodName = "ZARA COAT 3";
		productPage ProductPage = LandingPage.loginApplication("kks96.narola@gmail.com", "Kuldeep@1996");

		List<WebElement> products = ProductPage.getProductList();
		ProductPage.addProductToCart(prodName);
		CartPage cartPage = ProductPage.naviagteCartPage();

		cartPage.cartListProducts();
		Boolean matchProd = cartPage.matchProducts("ZARA COAT");
		Assert.assertTrue(matchProd);

	}

}
