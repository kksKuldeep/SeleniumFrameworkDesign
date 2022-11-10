package newMaven.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import newMaven.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	// By CartProds= By.xpath("//div[@class='cartSection'] //h3");

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement HeaderCartButton;

	@FindBy(xpath = "//div[@class='cartSection'] //h3")
	private List<WebElement> productNames;

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement checkOutBtn;

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement CheckoutBtn;

	// List<WebElement>
	// CartProds=driver.findElements(By.xpath("//div[@class='cartSection'] //h3"));

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> cartListProducts() {
		List<WebElement> CartProds = driver.findElements(By.xpath("//div[@class='cartSection'] //h3"));
		return CartProds;
	}

	public boolean matchProducts(String prodName) {
		Boolean matchProd = productNames.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(prodName));
		return matchProd;
	}

	public checkoutPayment checkoutNavigate() {
		waitTimeUntilElementClickable(CheckoutBtn);
		CheckoutBtn.click();
		return new checkoutPayment(driver);

	}

}
