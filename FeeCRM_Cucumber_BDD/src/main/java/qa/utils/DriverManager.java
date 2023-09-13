package qa.utils;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import qa.base.FrameworkConstants;

/*************************************************************************
 * Driver Management class for common operations that will be performed by all
 * instances of WebDriver
 *
 * @author ecyork
 *
 *************************************************************************/
public class DriverManager {

	private static DriverManager instance = null;

	private static RemoteWebDriver driver;
	Logger log = LoggerFile.getLogger(DriverManager.class);
	PropertyReader pr = new PropertyReader();
	ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	@SuppressWarnings("deprecation")
	public RemoteWebDriver getDriver(String browserName){

		Map<String, String> mobileEmulation = new HashMap<>();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("--window-size=1920,1080");
		chromeOptions.addArguments("--disable-web-security");
		chromeOptions.addArguments("--remote-allow-origins=*");
		System.out.println("Selected Browser as : " + FrameworkConstants.Browser);
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver();
			break;
		case "internetExplorer":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;

		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "iPhone":
			WebDriverManager.chromedriver().setup();
			mobileEmulation.put("deviceName", "iPhone 6");
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			driver = new ChromeDriver(chromeOptions);
			break;

		case "Android":
			WebDriverManager.chromedriver().setup();
			mobileEmulation.put("deviceName", "Nexus 5");
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			driver = new ChromeDriver(chromeOptions);
			break;

		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return driver;
	}

	public static void setClose() {
		if (driver != null) {
			System.out.println("Closing the browser : " + FrameworkConstants.Browser);
			driver.quit();
			driver = null;
		}
	}

	public static DriverManager getInstance() {
		if (instance == null) {
			return instance = new DriverManager();
		}
		return instance;
	}

	public WebDriver getDriverInstance(String Browser) throws MalformedURLException {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		webDriver.set(new ChromeDriver(chromeOptions));
		return webDriver.get();

	}
}
