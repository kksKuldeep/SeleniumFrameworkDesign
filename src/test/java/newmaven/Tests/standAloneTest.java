package newmaven.Tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import newMaven.pageObject.landingPage;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException  {
		// TODO Auto-generated method stub
		
		String prodName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		landingPage LandingPage= new landingPage(driver);
		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("kks96.narola@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Kuldeep@1996");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		//find numbers of products
		List<WebElement> products=driver.findElements(By.xpath("//div[@class='row']//div[contains(@class,'col-lg-4')]"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card']")));
		
		WebElement filteredList=products.stream().filter(product-> product.findElement(By.xpath("//div[@class='card']"))
				.getText().contains(prodName)).findFirst().orElse(null);
		//search & click on Button using Css
		filteredList.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//find Product Added Tost message (success Message) and Wait until message display and wait to Loader disappers
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ngx-spinner[@class='ng-tns-c31-0 ng-star-inserted']"))); 
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		//Fetch Cart list and Validate particular product from product list added/available in Cartlist or not
		List<WebElement> CartProds=driver.findElements(By.xpath("//div[@class='cartSection'] //h3"));
		CartProds.contains(prodName);
		Boolean matchProd= CartProds.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(prodName));
		Assert.assertTrue(matchProd);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Checkout']")));
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		//Provide Payment Details and Submit details at Payment Gateway page
		System.out.println(driver.findElement(By.cssSelector(".payment__type--cc")).isSelected());
		driver.findElement(By.xpath("//div[@class='field']//input")).clear();
		driver.findElement(By.xpath("//div[@class='field']//input")).sendKeys("4111111111111111");
		WebElement cardMonth=driver.findElement(By.xpath("//select[@class='input ddl']"));
		Select monthSelect=new Select(cardMonth);
		monthSelect.selectByVisibleText("08");
		WebElement cardYear=driver.findElement(By.xpath("//select[@class='input ddl'][2]"));
		Select yearSelect=new Select(cardYear);
		yearSelect.selectByVisibleText("25");
		driver.findElement(By.xpath("//div[@class='field small']//input")).sendKeys("659");
		driver.findElement(By.xpath("//div[@class='field']//input[@class='input txt']")).sendKeys("Kuldeep Singh");
		driver.findElement(By.xpath("//div[@class='form-group']//input")).sendKeys("Ind");
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class, 'ta-results')]//button")));
		/*List<WebElement> countryList=driver.findElements(By.xpath("//section[contains(@class, 'ta-results')]"));
		for(WebElement country:countryList)
		{
			Thread.sleep(3000);
			if(country.getText().equalsIgnoreCase("India"));
			{
				Thread.sleep(3000);
			country.click();
			}
		}*/
		
		//select Country Using Actions 
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//div[@class='field']//input[@class='input txt']")), "Ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class, 'ta-results')]")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		js.executeScript("window.scrollBy(0,750)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'action__submit')]")));
	driver.findElement(By.xpath("//a[contains(@class, 'action__submit')]")).click();
	
		String OrderSuccess=driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertEquals(OrderSuccess, "THANKYOU FOR THE ORDER.");
		
		//driver.close();
	}

}
