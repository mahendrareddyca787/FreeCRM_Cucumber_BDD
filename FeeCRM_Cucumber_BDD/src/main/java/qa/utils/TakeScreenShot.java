package qa.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import qa.base.FrameworkConstants;

public class TakeScreenShot {

	// public WebDriver driver;
	public void captureScreenshot(WebDriver driver) throws IOException {

		Date d = new Date();

		String FileName = "screenshots//" + d.toString().replace(":", "_").replace(" ", "_") + ".png";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileHandler.copy(screenshot, new File(FrameworkConstants.reportsPath + FileName));

	}

	public static String takeScreenShot(WebDriver driver) throws IOException {

		File src = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
		String destination = System.getProperty("user.dir") + "\\Report\\Screenshot\\" + System.currentTimeMillis()
				+ ".png";
		File des = new File(destination);

		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination;
	}

	public byte[] screenshot(WebDriver driver) {

		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	}

}
