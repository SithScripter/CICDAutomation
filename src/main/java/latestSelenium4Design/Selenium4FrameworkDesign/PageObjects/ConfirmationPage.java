package latestSelenium4Design.Selenium4FrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import latestSelenium4Design.Selenium4FrameworkDesign.Utils.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement orderConfirmationMessage;
	
	public String getOrderConfirmationMessage() {
		String confirmMsg = orderConfirmationMessage.getText();
		return confirmMsg;
	}
	
	

}
