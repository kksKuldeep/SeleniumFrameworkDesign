package newMaven.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import newMaven.pageObject.CartPage;
import newMaven.pageObject.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement headerCartBtn;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement headerOdrBtn;
	
	public void waitTimeForElementAppear(By findBy) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	
	
	public void waitTimeForWebElementToAppear(WebElement eleAppear) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(eleAppear));
		}
	
	public void waitTimeForListWebElementToAppear(List<WebElement> eleListAppear) 
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(eleListAppear));
		}
	public void waitTimeforElementDisappear(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	public void waitTimeUntilElementClickable(WebElement elementClick)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(elementClick));
	}
	public void waitTimeUntilElementToBeClickable(By ByElementClick) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ByElementClick));
		}
	
	public CartPage naviagteCartPage()
	{
		headerCartBtn.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage orderHistoryPage()
	{
		headerOdrBtn.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
		
	}
	
}
