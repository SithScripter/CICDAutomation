package latestSelenium4Design.Selenium4FrameworkDesign.Tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import latestSelenium4Design.Selenium4FrameworkDesign.PageObjects.CartPage;
import latestSelenium4Design.Selenium4FrameworkDesign.PageObjects.CheckoutPage;
import latestSelenium4Design.Selenium4FrameworkDesign.PageObjects.ConfirmationPage;
import latestSelenium4Design.Selenium4FrameworkDesign.PageObjects.ProductCatalogue;
import latestSelenium4Design.Selenium4FrameworkDesign.TestComponents.BaseTest;
import latestSelenium4Design.Selenium4FrameworkDesign.Utils.AbstractComponents.OrdersPage;

public class SubmitOrderTest extends BaseTest {
//	String userEmail = "forwardtolalit@gmail.com";
//	String userPwd = "##Learning@88";
//	String productName = "iphone";
//	String orderCountry = "India";

	@Test(dataProvider = "getData", groups = "Purchase" )
	public void placeOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("userEmail"), input.get("userPwd"));
		List<WebElement> productsEl = productCatalogue.getProuctList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean itemMatched = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(itemMatched);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(input.get("orderCountry"));
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String orderConfirmationMsg = confirmationPage.getOrderConfirmationMessage();
		Assert.assertTrue(orderConfirmationMsg.equalsIgnoreCase("Thankyou for the order."));
	}

	// verify iphone is dispaying in order page
	@Test(dependsOnMethods = { "placeOrder" },dataProvider = "getData",groups = "Purchase")
	public void orderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("userEmail"), input.get("userPwd"));
		OrdersPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("productName")));
	}
	
	/*
	 * // this data provider i used when i was depending on normal data provider,
	 * below this we are using DataProvider with HashMap // @DataProvider public
	 * Object[][] getData(Method method) {
	 * if(method.getName().equals("orderHistoryTest")) { return new Object[][]
	 * {{"forwardtolalit@gmail.com","##Learning@88","iphone"},{
	 * "forwardtolalit@gmail.com","##Learning@88","adidas"}}; } else { return new
	 * Object[][] {{"forwardtolalit@gmail.com","##Learning@88","iphone","India"},{
	 * "forwardtolalit@gmail.com","##Learning@88","adidas","India"}}; }
	 * 
	 * }
	 */
	
	// Using hashmap
	@DataProvider 
	public Object[][] getData(Method method) throws IOException {
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("userEmail","forwardtolalit@gmail.com");
//		map1.put("userPwd","##Learning@88");
//		map1.put("productName", "adidas");
//		map1.put("orderCountry", "India");
//		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("userEmail","forwardtolalit@gmail.com");
//		map.put("userPwd","##Learning@88");
//		map.put("productName", "iphone");
//		map.put("orderCountry", "India");
		
			List<HashMap<String,String>> data = getJsonDataToHashMap(System.getProperty("user.dir")
					+ "\\src\\test\\java\\latestSelenium4Design\\Selenium4FrameworkDesign\\Data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
}
