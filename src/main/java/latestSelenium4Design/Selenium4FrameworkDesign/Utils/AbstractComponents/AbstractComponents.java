package latestSelenium4Design.Selenium4FrameworkDesign.Utils.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import latestSelenium4Design.Selenium4FrameworkDesign.PageObjects.CartPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersHistoryButton;

	public void waitForEmelentToAppear(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findby));
	}
	
	public void waitForWebEmelentToAppear(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public CartPage goToCartPage() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver) ;
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		ordersHistoryButton.click();
		OrdersPage orderPage = new OrdersPage(driver) ;
		return orderPage;
	}
	
	public void waitForElementToDisppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
