package newmaven.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import newMaven.pageObject.landingPage;

public class BaseClass {
	public static WebDriver driver;
	public landingPage LandingPage;

	public WebDriver invokeDriver() throws IOException {
		Properties properties = new Properties();
		// convert file into File Input Stream
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//newMaven//Resources//GlobalData.properties");
		properties.load(file);
		String browserName = System.getProperty("Browser") != null ? System.getProperty("Browser")
				: properties.getProperty("Browser");
		// properties.getProperty("Browser");

		if (browserName.contains("chrome")) {
			ChromeOptions chromeOpts = new ChromeOptions();

			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headLess")) {
				chromeOpts.addArguments("headLess");
			}
			driver = new ChromeDriver(chromeOpts);
			driver.manage().window().setSize(new Dimension(1440,990));

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	public List<HashMap<String, String>> getJSonDataToMap(String filePath) throws IOException {
		// read Json data to String
		String jsonContents = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Converting String to HashMap
		ObjectMapper Mapper = new ObjectMapper();
		List<HashMap<String, String>> mData = Mapper.readValue(jsonContents,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return mData;
	}

	public String getScreenshots(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot src = (TakesScreenshot) driver;
		File source = src.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//Reports" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public landingPage launchWebsite() throws IOException {
		driver = invokeDriver();
		//driver = new ChromeDriver();
		LandingPage = new landingPage(driver);
		LandingPage.goTo();
		return LandingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void closeWebsite() {
		driver.close();
		

	}

}
