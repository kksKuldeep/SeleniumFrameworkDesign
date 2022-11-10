package newMaven.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import newMaven.AbstractComponents.AbstractComponent;

public class landingPage extends AbstractComponent{
	WebDriver driver;
	public landingPage(WebDriver driver)
	{
		super(driver);
		//Initailize main Driver to current Class
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	/*WebElement email=driver.findElement(By.xpath("//input[@id='userEmail']"));
	WebElement password=driver.findElement(By.xpath("//input[@id='userPassword']"));
	WebElement loginButton=driver.findElement(By.xpath("//input[@id='login']"));*/

	//page factory Annotation 
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;

	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPass;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement LoginBtn;
	
	@FindBy(xpath="//div[contains(@class,'flyInOut ngx-toastr toast-error')]")
	WebElement errorMessage;
	
	public productPage loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		LoginBtn.click();
		productPage ProductPage=new productPage(driver);
		return ProductPage;
		
	}
	public String getErrorValidation()
	{
		waitTimeForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
}

