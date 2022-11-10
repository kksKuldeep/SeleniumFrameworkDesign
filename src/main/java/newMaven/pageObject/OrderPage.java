package newMaven.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import newMaven.AbstractComponents.AbstractComponent;

public class OrderPage {
	
	WebDriver driver;
	public OrderPage (WebDriver driver)
	{
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 
	@FindBy(xpath="//tr //td[2]")
	List<WebElement> orderProdName;
	
	public List<WebElement> orderListProdname() {
		List<WebElement> orderProducts = driver.findElements(By.xpath("//tr //td[2]"));
		return orderProducts;
	}
	public boolean verifyOrderProductDisplay(String prodName) {
		Boolean matchOdrProd = orderProdName.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(prodName));
		return matchOdrProd;
	
	
	
	
	}
}
