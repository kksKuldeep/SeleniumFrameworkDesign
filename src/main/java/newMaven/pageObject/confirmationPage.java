package newMaven.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import newMaven.AbstractComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent {
	
	WebDriver driver;
	public confirmationPage (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement OrderPlaced;
	
	public String Verifyconfirmation()
	{
		return OrderPlaced.getText();
		
	}

}
