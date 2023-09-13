package qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import qa.utils.WaitForHelper;


public class Assertions {
	public WebDriver driver;
	WaitForHelper wait = new WaitForHelper();
	BasePage bp = new BasePage(driver);
	WebElement wb;

	public Assertions(WebDriver driver, WebElement element) {
		this.wb = element;
		this.driver = driver;
	}

	public void shouldBeEnabled() {
		Assert.assertEquals(wait.presenceOfElement(driver, wb).isEnabled(), true);
	}

	public void shouldBeSelected() {
		Assert.assertEquals(wait.presenceOfElement(driver, wb).isSelected(), true);
	}

	public void shouldBeVisible() {
		Assert.assertEquals(wait.presenceOfElement(driver, wb).isDisplayed(), true);
		BasePage.printLog("Element is successfully located on webpage");
	}

	public void shouldBeInvisible() {
		wait.waitForPageLoaded(driver);
		Assert.assertEquals(wb.isDisplayed(), false);
		BasePage.printLog("Given webElement is not located on webpage");
	}

	public void textShouldBe(String text) {
		for (int i = 0; i <= 3; i++) {
			if (wait.presenceOfElement(driver, wb).getText().equalsIgnoreCase(text)) {
				Assert.assertEquals(wait.presenceOfElement(driver, wb).getText(), text);
				BasePage.printLog("Expected value" + text + "is matched with the actual value"
						+ wait.presenceOfElement(driver, wb).getText() + "");
				break;
			} else {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}