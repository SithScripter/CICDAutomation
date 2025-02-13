package latestSelenium4Design.Selenium4FrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import latestSelenium4Design.Selenium4FrameworkDesign.PageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String userEmail = "forwardtolalit@gmail.com";
		String userPwd = "##Learning@88";
		String itemToAdd = "Banarsi Saree";
		String orderCountry = "India";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(userEmail);
		driver.findElement(By.id("userPassword")).sendKeys(userPwd);
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> productsEl = driver.findElements(By.cssSelector(".col-lg-4"));
		System.out.println(productsEl.size());
		Thread.sleep(10000);
		WebElement desiredItem = productsEl.stream()
				.filter(item -> item.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(itemToAdd)).findFirst()
				.orElse(null);
		desiredItem.findElement(By.cssSelector(".fa-shopping-cart:first-child")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".toast-message"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection h3")));
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean itemMatched = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(itemToAdd));
		Assert.assertTrue(itemMatched);

		// click checkout button
		driver.findElement(By.cssSelector(".subtotal button")).click();

		WebElement country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(country, orderCountry).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		WebElement countryResults = driver.findElement(By.cssSelector(".action__submit"));
		a.click(countryResults).build().perform();
		String confirmMSg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMSg.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}

}
