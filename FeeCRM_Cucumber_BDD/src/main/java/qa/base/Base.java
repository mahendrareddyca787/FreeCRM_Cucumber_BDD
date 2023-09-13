package qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import qa.utils.WaitForHelper;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public Base(WebDriver driver) {
		
		this.driver=driver;
		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream(
					"E:\\Batch 19042023\\FeeCRM_Cucumber_BDD\\src\\main\\java\\qa\\config\\config.properties");

			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void initilization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "E:\\Batch 19042023\\Selenium Jars\\chromedriver_win32 (12)\\chromedriver.exe");
			driver=new ChromeDriver();
		} else if (browserName.equals("ff")) {

			System.setProperty("webdriver.gecko.driver", "C:\\Selenium Training\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	public void click(String lableName,WebElement element) {
		waitForPageLoad();
	}
	
	public void waitForPageLoad() {
		new WaitForHelper().waitForPageLoaded(driver);
	}
	
}

	

