package newMaven.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import newMaven.AbstractComponents.AbstractComponent;

public class productPage extends AbstractComponent {
	WebDriver driver;
	public productPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	

	//List<WebElement> products=driver.findElements(By.xpath("//div[@class='row']//div[contains(@class,'col-lg-4')]"));
	@FindBy(xpath="//div[@class='row']//div[contains(@class,'col-lg-4')]")
	List<WebElement> prodList;
	
	@FindBy(css=".ng-animating") 
	WebElement Loader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']") 
	WebElement clickCartBtn;
	
	//By productsBy=By.xpath("//div[@class='row']//div[contains(@class,'col-lg-4')]");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By productAddedToCartSuccessMSg=By.xpath("//div[@id='toast-container']");
	//By clickCartBtn= By.xpath("//button[@routerlink='/dashboard/cart']");
	
	public List<WebElement> getProductList()
	{
		waitTimeForListWebElementToAppear(prodList);
		return prodList;
	}
	public WebElement getProductByName(String productName)
	{
		WebElement filteredList=prodList.stream().filter(product-> product.findElement(By.xpath("//div[@class='card']"))
				.getText().contains(productName)).findFirst().orElse(null);
		return filteredList;
		
	}
	public void addProductToCart(String productName) throws InterruptedException
	{
		
		 WebElement Product=getProductByName(productName);
		 waitTimeUntilElementToBeClickable(addToCart);
		 Product.findElement(addToCart).click();
		 waitTimeForElementAppear(productAddedToCartSuccessMSg);
		 waitTimeforElementDisappear(Loader);
		 clickCartBtn.click();
		
	}
	
	
	
}

