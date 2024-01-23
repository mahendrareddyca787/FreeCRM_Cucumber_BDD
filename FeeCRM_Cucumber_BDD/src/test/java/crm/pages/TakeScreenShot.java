package crm.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import qa.base.BasePage;

public class TakeScreenShot extends BasePage {

	public TakeScreenShot(WebDriver driver) {
		super(driver);
		
	}

	public static void main(String[] args) throws IOException {
	
		 WebDriver driver= new ChromeDriver();
	           
	     //Capturing the screenshot
	 
	   File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	   
	   FileUtils.copyFile(f, new File("C:/Users/Chait/Desktop/Screenshots/screenshot01.png"));
		
	      //screenshot copied from buffer is saved at the mentioned path.
	 
	     System.out.println("The Screenshot is captured.");
	         
	        }

	}


