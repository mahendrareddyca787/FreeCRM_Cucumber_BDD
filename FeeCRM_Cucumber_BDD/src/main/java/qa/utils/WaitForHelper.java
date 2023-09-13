package qa.utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class WaitForHelper {
	
	public static WebElement  visibilityOfElement(WebDriver driver,int timeInSeconds,WebElement element) {
		
		WebDriverWait webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		return webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement elementToBeClickable(WebDriver driver,WebElement element2) {
		WebElement element=new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element2));
		return element;
	}
	
	//implicitlywait
	public WebElement presenceOfElement(WebDriver driver,final WebElement element) {
		try {
			return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
		}catch(ElementClickInterceptedException | NoSuchElementException e){
			return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
			
		}catch(Exception ex) {
			System.out.println("Not able to find the element waited upto 20 seconds");
			return null;
		}
	}
	
	public void waitForPageLoaded(WebDriver driver) {
		
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				// System.out.println("Page Loaded... ");
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(3000);
			System.out.println("wait for Page Load sec... ");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
			
		}
	}

}
