package latestSelenium4Design.Selenium4FrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import latestSelenium4Design.Selenium4FrameworkDesign.Utils.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	Actions actions;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actions = new Actions(driver);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	private WebElement country;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	private WebElement selectCountry;

	@FindBy(css = ".action__submit")
	private WebElement placeOrderButton;

	private By countryResults = By.cssSelector(".ta-results");

	

	public void selectCountry(String orderCountry) {
		actions.sendKeys(country, orderCountry).build().perform();
		waitForEmelentToAppear(countryResults);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {
		actions.click(placeOrderButton).build().perform();
		return new ConfirmationPage(driver);
	}
	

//	WebElement country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
//	Actions a = new Actions(driver);
//	a.sendKeys(country, orderCountry).build().perform();

//	waitf(countryReults));
//	selectCountry.click();
//	WebElement countryResults = driver.findElement(By.cssSelector(".action__submit"));
//	a.click(placeOrderButton).build().perform();

}
