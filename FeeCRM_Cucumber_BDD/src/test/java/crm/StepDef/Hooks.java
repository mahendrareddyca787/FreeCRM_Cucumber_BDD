package crm.StepDef;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import qa.base.FrameworkConstants;
import qa.utils.DriverManager;
import qa.utils.LoggerFile;

public class Hooks {

	public static String[] scenarioName;
	public static String TestCaseID;

	public static HashMap<String, WebDriver> driverInstances = new HashMap<>();
	Logger log = LoggerFile.getLogger(Hooks.class);

	public void getDriverInstance() throws MalformedURLException {
		System.out.println("strBrowser :" + FrameworkConstants.Browser);
		DriverManager obj = DriverManager.getInstance();
		driverInstances.put(String.valueOf(Thread.currentThread().getId()),
				 obj.getDriver(FrameworkConstants.Browser));
	}

	@Before
	public void setUp(Scenario scenario) throws MalformedURLException {
		scenarioName = (scenario.getName()).split(":");
		if (scenarioName[0].trim().contains("BUSAPPSQA")) {
			TestCaseID = scenarioName[0].trim();
		} else {
			TestCaseID = "";
		}
		System.out.println(scenario.getName());
		getDriverInstance();
		System.out.println("strBrowser :" + FrameworkConstants.Browser);
	}

	// @AfterStep
	public void addScreenshot(Scenario scenario) {

		final byte[] screenshot = ((TakesScreenshot) driverInstances
				.get(String.valueOf(Thread.currentThread().getId()))).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "image");

	}

	@After
	public void tearDown(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				scenario.log(scenario.getName() + " : Failed");
				final byte[] screenshot = ((TakesScreenshot) driverInstances
						.get(String.valueOf(Thread.currentThread().getId()))).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "image");
			} else {
				scenario.log(scenario.getName() + " : Passed");
				final byte[] screenshot = ((TakesScreenshot) driverInstances
						.get(String.valueOf(Thread.currentThread().getId()))).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "image");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		driverInstances.get(String.valueOf(Thread.currentThread().getId())).quit();
	}
}
