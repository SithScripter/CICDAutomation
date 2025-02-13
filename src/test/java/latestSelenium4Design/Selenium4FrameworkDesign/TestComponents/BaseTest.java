package latestSelenium4Design.Selenium4FrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import latestSelenium4Design.Selenium4FrameworkDesign.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\latestSelenium4Design\\Selenium4FrameworkDesign\\Resources\\GlobalData.properties");
		properties.load(fis);
		// here we are using Java Ternary Operation
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");
//		String browserName = properties.getProperty("browser"); //commented this as above line we used maven paremeters command to pass browser on the go

//		if (browserName.contains("healess")) {
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless");
//			driver = new ChromeDriver(options);
//			driver.manage().window().setSize(new Dimension(1400, 900));
//		}
//
//		else if (browserName.startsWith("chrome")) {
//			driver = new ChromeDriver();
//		}

		if (browserName.startsWith("chrome")) {
			ChromeOptions option = new ChromeOptions();
			if (browserName.contains("headless")) {
				option.addArguments("--headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// define edge code as defined for chrome
		} else if (browserName.equalsIgnoreCase("safari")) {
			// define safari code as defined for chrome
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + testCaseName
				+ ".png";
//		File file = new File(System.getProperty("user.dir")+"\\Reports\\"+testCaseName+ ".png");
		File file = new File(filePath);
		FileUtils.copyFile(source, file);
		return filePath;
	}

	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {
		// read json to string
		String jsonData = Files.readString(Path.of(filePath));
		// convert jsondata to hashmap using jackson databind api(do add dependency in
		// pom from maven- name of it "jackson databind"
//		why this api, Jackson Databind is the best way to convert JSON to HashMap in Java. Handles both simple & nested JSON structures easily. 
//		No manual parsing needed – just use objectMapper.readValue().

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	@BeforeMethod()
	public LandingPage launchApplication() throws IOException {
		WebDriver driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
//		alwaysRun = true
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
