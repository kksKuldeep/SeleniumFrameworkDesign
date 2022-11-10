package newMaven.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import newMaven.AbstractComponents.AbstractComponent;

public class checkoutPayment extends AbstractComponent {

	WebDriver driver;
	public checkoutPayment (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//div[@class='field small']//input")
	WebElement cardCVVNumber;
	
	@FindBy(xpath="//div[@class='field']//input[@class='input txt']")
	WebElement cardName;
	
	@FindBy(xpath="//div[@class='form-group']//input")
	WebElement shippingCountry;
	
	
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectShippingCountry;
	
	
	
	@FindBy(xpath="//a[contains(@class, 'action__submit')]")
	WebElement submitPayment;
	
	
	By countryList=By.xpath("//section[contains(@class, 'ta-results')]");
	
	
	
	public void CardDetails(String CVVnumber, String cardUserName)
	{
		
		cardCVVNumber.sendKeys(CVVnumber);
		cardName.sendKeys(cardUserName);
	}
	public void selectCountry(String countryName) throws InterruptedException
	{
		Actions a=new Actions(driver);
		Thread.sleep(2000);
		a.sendKeys(shippingCountry, countryName).build().perform();
		waitTimeForElementAppear(By.xpath("//section[contains(@class, 'ta-results')]"));
		
		selectShippingCountry.click();
		
		
		
	}
	
	public confirmationPage SubmitOrder()
	{
		waitTimeUntilElementClickable(submitPayment);
		submitPayment.click();
		return new confirmationPage(driver);
		
		
	}
}
