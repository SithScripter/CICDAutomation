package latestSelenium4Design.Selenium4FrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import latestSelenium4Design.Selenium4FrameworkDesign.Utils.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".col-lg-4")
	List<WebElement> productsEl;
	
	@FindBy(css=".toast-message")
	WebElement toastMessage;
	
	By productsBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".fa-shopping-cart:first-child");
	By toaster = By.id("toast-container");
	By productToGet = By.cssSelector("b");

	public List<WebElement> getProuctList() {
		
		waitForEmelentToAppear(productsBy);
		return productsEl;
	}
	
	public WebElement getProductsByName(String productName) throws InterruptedException {
//		waitForEmelentToAppear(productToGet);
//		Thread.sleep(10000);
		WebElement prod = getProuctList().stream()
				.filter(item -> item.findElement(productToGet).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
//		System.out.println(prod);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductsByName(productName);
//		System.out.println(prod);
		prod.findElement(addToCart).click();
		waitForEmelentToAppear(toaster);
		waitForElementToDisppear(toastMessage);
	}

}
