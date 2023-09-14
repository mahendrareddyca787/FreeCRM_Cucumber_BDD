package qa.base;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import qa.dataread.EnvironmentFactory;
import qa.dataread.ExcelDataRead;
import qa.utils.LoggerFile;
import qa.utils.PropertyReader;
import qa.utils.TestUtils;
import qa.utils.WaitForHelper;

/*************************************************************************
 * Base class for common operations that will be performed by all page classes
 *
 * @author ecyork
 *
 *************************************************************************/
public class BasePage {
	public WebDriver driver;
	static Logger log = LoggerFile.getLogger(BasePage.class);
	PropertyReader pr = new PropertyReader();
	WaitForHelper wait = new WaitForHelper();
	public static String inputpath;
	public static ArrayList<Map<String, String>> rowData;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public Assertions assertion(WebElement element) {
		return new Assertions(driver, element);

	}

	/*
	 * ####################### Basic click methods #######################
	 */
	/**
	 * Clicks the supplied WebElement
	 *
	 * @param elementLocation WebElement element Click on
	 */

	public void click(String fieldLable, WebElement element) {
		try {
			waitForPageLoad();
			waitForElementToAppear(element);
			wait.elementToBeClickable(driver, element);
			element.click();
			printLog("Clicked on : " + fieldLable);

		} catch (ElementClickInterceptedException e) {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
			printLog("Clicked on using Actions: " + fieldLable);
		} catch (Exception e) {
			System.out.println("In catch block");
			clickUsingJavaExecutor(element);
			printLog("Clicked on using JavaScrpit : " + fieldLable);
		}

	}

	/**
	 * Click on any WebElement found with the supplied String text
	 *
	 * @param linkText String text of the expected hyperlink to be clicked
	 */
	public void clickByLinkText(String linkText) {
		waitForElementToAppear(driver.findElement(By.linkText(linkText)));
		driver.findElement(By.linkText(linkText)).click();
	}

	/**
	 * Click on any WebElement found with the supplied String text
	 *
	 * @param linkText String text of the expected hyperlink to be clicked
	 */
	public void clickByPartialLinkText(String linkText) {
		waitForElementToAppear(driver.findElement(By.partialLinkText(linkText)));
		driver.findElement(By.partialLinkText(linkText)).click();
	}

	public void clickUsingJavaExecutor(WebElement element) {
		wait.elementToBeClickable(driver, element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
		executor.executeScript("arguments[0].click();", element);
	}

	public void scrollByVisibilityOfElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public boolean jseClick(WebElement element, String message) {
		boolean flag = false;
		try {
			waitForElementToAppear(element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			printLog(message + " is clicked");
			flag = true;
		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				System.out.println("Click Action is performed");
			} else if (!flag) {
				System.out.println("Click Action is not performed");
			}
		}
		return flag;
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	/*
	 * ################### Select/Deselect methods ###########################
	 *
	 */

	/** Deselects all the selected options in the dropDown */
	public void deselectAllDropDownOptions(String dropDownName) {
		new Select(dropDown(dropDownName)).deselectAll();
	}

	/** Deselect the dropDown option with value */
	public void deselectDropDownByValue(String dropDownName, String value) {
		new Select(dropDown(dropDownName)).deselectByValue(value);
	}

	/** Deselect the dropDown option with text */
	public void deselectDropDownByVisibleText(String dropDownName, String text) {
		new Select(dropDown(dropDownName)).deselectByVisibleText(text);
	}

	/**
	 * Locates dropDown WebElement
	 *
	 * @param dropDownName name of the dropDown returns dropDowns WebElement
	 */

	public WebElement dropDown(String dropDownName) {
		return driver.findElement(By.xpath("//*[text()='" + dropDownName
				+ "']/parent::lightning-combobox//div[@class='slds-combobox__form-element slds-input-has-icon slds-input-has-icon_right']"));
	}

	/** Get all the Selected options of the DropDown */
	public List<WebElement> getAllSelectedOptionsFromDropDown(String dropDownName) {
		return new Select(dropDown(dropDownName)).getAllSelectedOptions();
	}

	public List<String> getAllAvailableOptionsFromDropDown(WebElement element) {
		List<WebElement> options = new Select(element).getOptions();
		List<String> s = new ArrayList<String>();
		for (WebElement one : options) {
			System.out.println(one.getText());
			s.add(one.getText());
		}

		return s;
	}

	/**
	 * Checks whether a dropDown is multiSelect or not returns boolean value
	 */
	public boolean isDropDwonMultipleSelection(String dropDownName) {
		return new Select(dropDown(dropDownName)).isMultiple();
	}

	/** Selects the dropDown option by index */
	public void selectDropDownByIndex(String dropDownName, int index) {
		new Select(dropDown(dropDownName)).selectByIndex(index);
	}

	/** Selects the dropDown option by value */
	public void selectDropDownByValue(String dropDownName, String value) {
		new Select(dropDown(dropDownName)).selectByValue(value);
	}

	/** Select the dropDown option with visible text */
	public void selectDropDownByVisibleText(String dropDownName, String text) {
		new Select(dropDown(dropDownName)).selectByVisibleText(text);
	}

	public void selectMultipleValues(WebElement element, String... visibletext) {

		Select value = new Select(element);

		for (String d : visibletext) {
			if (value.isMultiple()) {

				// Selecting multiple values by index
				value.selectByVisibleText(d);
				// value.selectByVisibleText(d);
			}

		}

	}

	public void selectValueFromDropdown(WebElement element, String visibletext) {
		try {
			Select value = new Select(element);
			value.selectByVisibleText(visibletext);
			printLog("select value as :" + visibletext);
		} catch (Exception e) {
			fail("Not able to select : " + visibletext, e);
		}

	}

	public void selectDropDown(String value, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + value + "'", ele);
		printLog("Selected value from Dropdown : " + value);
	}

	public String getSelectedOption(String feildName, WebElement ele) {
		Select se = new Select(ele);
		String selectedOPtion = se.getFirstSelectedOption().getText();
		printLog("Selected value of " + feildName + " is : " + selectedOPtion);
		return selectedOPtion;

	}

	public void selectValueFromDropdownByText(WebElement element, String visibletext, String fieldName) {
		try {
			Select value = new Select(element);
			value.selectByVisibleText(visibletext);
			printLog("select value as :" + visibletext + " for " + fieldName);
		} catch (Exception e) {
			fail("Not able to select : " + visibletext + " for " + fieldName, e);
		}

	}

	public void selectValueFromDropdownByIndex(WebElement element, int index, String fieldName) {
		try {
			Select value = new Select(element);
			value.selectByIndex(index);
			printLog("select value as :" + index + " for " + fieldName);
		} catch (Exception e) {
			fail("Not able to select : " + index + " for " + fieldName, e);
		}

	}

	public String getElementValueAttribute(WebElement element) {
		return element.getAttribute("value");
	}

	/*
	 * ######################## Mouse Actions methods ###################
	 */

	/**
	 * Move the cursor to the supplied WebElement By locator.
	 *
	 * @param elementLocation WebElement by locator
	 */
	public void moveToElement(WebElement element) {
		new Actions(driver).moveToElement(element).build().perform();
	}

	/**
	 * double click on the supplied WebElement.
	 *
	 * @param elementLocation WebElement
	 */

	public void doubleClick(WebElement element) {
		new Actions(driver).doubleClick(element).build().perform();
	}

	/**
	 * Move the cursor to the supplied WebElement.
	 *
	 * @param elementLocation WebElement
	 */

	public void mouseHoverJScript(WebElement HoverElement) {
		try {
			if (isElementPresent(HoverElement)) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element with " + HoverElement + "is not attached to the page document" + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering" + e.getStackTrace());
		}
	}

	/*
	 * ######################### Read/Write Text methods #####################
	 */

	/**
	 * Clears the existing text, if any, within the specified WebElement By locator
	 * before sending the supplied text.
	 *
	 * @param elementLocation WebElement By locator
	 * @param text            String text to be sent to the WebElement
	 */
	public void writeText(WebElement element, String text) {
		try {
			waitForElementToAppear(element);
			element.clear();
			element.sendKeys(text);
			printLog("Entered text as : " + text);

		} catch (Exception e) {
			fail("Not able to enter text : " + text, e);
		}

	}

	public void writeText(WebElement element, String text, String fieldName) {
		try {
			waitForElementToAppear(element);
			element.clear();
			element.sendKeys(text);
			printLog("Entered text as : " + text + "  for " + fieldName + "");

		} catch (Exception e) {
			fail("Not able to enter text : " + text + "  for " + fieldName + "", e);
		}

	}
	
	public void backSpaceClear(WebElement element) {
		int lenght = element.getText().length();
		while (lenght > 0) {
			element.sendKeys(Keys.BACK_SPACE);
			lenght--;
		}
	}

	/**
	 * Clears the existing text, if any, within the specified WebElement By locator
	 * before sending the supplied text.
	 *
	 * @param elementLocation WebElement By locator
	 * @param text            String text to be sent to the WebElement
	 */
	public void writeDate(String filedLabel, WebElement element, String text) {
		try {
			waitForElementToAppear(element);
			element.clear();
			element.sendKeys(text + Keys.ENTER);
			printLog("Entered " + filedLabel + " as : " + text);

		} catch (Exception e) {
			fail("Not able to enter " + filedLabel + " : " + text, e);
		}

	}

	public void enterEncodePassword(WebElement element, String text) {
		try {
			waitForElementToAppear(element);
			element.clear();
			element.sendKeys(TestUtils.decode(text));
			printLog("Entered encoded text as : " + text);

		} catch (Exception e) {
			fail("Not able to enter encoded text : " + text, e);
		}

	}

	/**
	 * Returns the text found on the supplied WebElement By locator
	 *
	 * @param elementLocation WebElement By locator
	 * @return WebElement text() as String
	 */
	public String readText(WebElement element) {
		String getvalue = null;
		try {
			getvalue = element.getText();
		} catch (NoSuchElementException e) {
			getvalue = wait.presenceOfElement(driver, element).getText();
		} catch (Exception e) {
			getvalue = wait.presenceOfElement(driver, element).getAttribute("value");
		}
		printLog("Got the text from the Element: " + getvalue);
		return getvalue;

	}

	/**
	 * Returns the text found on the supplied WebElement By locator
	 *
	 * @param elementLocation as string (Shadow Element as JSPath)
	 * @return WebElement text() as String
	 */

	public String readText(String element) {
		String getvalue = null;
		try {

			getvalue = getShadowElement(element).getText();

		} catch (NoSuchElementException e) {
			getvalue = getShadowElement(element).getAttribute("value");
		} catch (Exception e) {
			getvalue = getShadowElement(element).getAttribute("textContent");
		}
		printLog("Get the text from the ShadowElement: " + getvalue);
		return getvalue;

	}

	/**
	 * Returns the text found on the supplied WebElement By locator
	 *
	 * @param element and attributeType
	 * @return WebElement text() as String
	 */
	public String readText(WebElement element, String attributeType) {
		String getvalue = null;
		try {

			getvalue = wait.presenceOfElement(driver, element).getAttribute(attributeType);
		} catch (Exception e) {

		}

		return getvalue;

	}

	/*
	 * ####################### Wait methods #############################
	 */

	/**
	 * Waits for the supplied WebElement By locator to appear
	 *
	 * @param element WebElement By locator
	 */
	public void waitForElementToAppear(WebElement element) {
		new WaitForHelper().presenceOfElement(driver, element);
	}

	/**
	 * Waits for the supplied WebElement By locator to be clickable
	 *
	 * @param elementLocation WebElement By locator
	 */
	public void waitForElementToBeClickable(WebElement element) {
		new WaitForHelper().elementToBeClickable(driver, element);
	}

	/**
	 * Sets the implicit wait default before a NoSuchElement Exception will occur
	 * <p>
	 * TODO: Update this method to take a specified duration so the implicit wait
	 * can be controlled
	 */
	public void waitForPageLoad() {
		new WaitForHelper().waitForPageLoaded(driver);
	}
	/*
	 * Wait untill Duration in seconds
	 *
	 */

	public void waitTill(String durationInSecond) {
		try {
			Thread.sleep(Integer.parseInt(durationInSecond) * 1000);
		} catch (Exception e) {
		}
	}

	/*
	 * wait Dotted Spinner To Disappear
	 */
	@SuppressWarnings("unchecked")
	public void waitDottedSpinnerToDisappear() {
		By element = By.xpath("//div[@ng-show='grid.isServerActionInProgress()']");
		int attempt = 0;

		while (attempt < 3) {

			long start = System.currentTimeMillis();
			try {
				@SuppressWarnings({ "rawtypes" })
				Wait wait = new FluentWait(driver)

						.withTimeout(Duration.ofSeconds(5))

						.pollingEvery(Duration.ofSeconds(1))

						.ignoring(Exception.class).ignoring(StaleElementReferenceException.class);

				wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
				break;
			} catch (Exception e) {
				printLog("Spinner is disappeared");
			}
			attempt++;
			long end = System.currentTimeMillis();
			long duration = end - start;
			duration = duration / 1000;
			printLog("duration of spinner = " + duration + "secs");
		}

	}

	/*
	 * fluentWait check for every 2 seconds until 50 Seconds
	 */
	public void waitUntilElementisDisplayed(WebElement element) {

		int attempt = 0;
		while (attempt < 2) {
			long start = System.currentTimeMillis();
			try {
				FluentWait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
						.pollingEvery(Duration.ofSeconds(3)).ignoring(ElementNotInteractableException.class)
						.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
						.ignoring(WebDriverException.class);
				wait1.until(ExpectedConditions.visibilityOf(element));
			} catch (Exception e) {

			}
			attempt++;
			long end = System.currentTimeMillis();
			long duration = end - start;
			duration = duration / 1000;
			printLog("duration of Element = " + duration + "secs");
		}

	}

	public void waitTillTextUpdated(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return ele.getText().length() != 0;
			}
		});
	}

	/*
	 *
	 * ####################### basic Verify Element methods ######################
	 *
	 */

	public boolean verifyElementPresent(String fieldLable, WebElement element) {
		boolean elementPresent = false;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}
		try {
			if (element.isDisplayed()) {
				TestUtils.highlightElement(driver, element);
				printLog("Element is present and Displayed");
				elementPresent = true;
			} else {
				printLog("verifyElementPresent: " + element + " is Not Present or Not Displayed");

			}
		} catch (Exception e) {
			fail("Element is Not Present or Not Displayed", e);
		}
		return elementPresent;
	}

	/*
	 * Description: This method used to verify Message Contains
	 *
	 * @prop: verify the expected and actual values
	 */
	public void verifyMessageContains(String avalue, String evalue) {
		// System.out.println(avalue + " " + evalue);
		if (evalue.contains(avalue)) {
			printLog("Verified Actual text : " + avalue + " text contains Expected text : " + evalue);
			Assert.assertTrue(true, "Verified Actual text : " + avalue + " text contains Expected text : " + evalue);

		} else {
			printLog("Actual text : " + avalue + " Does NOT contains Expected text : " + evalue);
			Assert.assertEquals(avalue, evalue, "FAIL");
			Assert.fail("Actual text : " + avalue + " Does NOT contains Expected text : " + evalue);
		}
	}

	/*
	 * Description: This method used to verify Message Contains
	 *
	 * @prop: verify the expected and actual values then field name
	 */

	public void verifyTextValidation(String avalue, String evalue, String field) {
		if (evalue.equals(avalue)) {
			printLog("Verified Actual text : " + avalue + " is matching with Expected text : " + evalue + "  for "
					+ field);
			Assert.assertTrue(true, "Verified Actual text : " + avalue + " is matching with Expected text : " + evalue
					+ "  for " + field);

		} else {
			fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
			Assert.assertEquals(avalue, evalue, "FAIL");
			Assert.fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
		}
	}

	public void verifyTextValidation(int avalue, int evalue, String field) {
		if (evalue == avalue) {
			printLog("Verified Actual value : " + avalue + " is matching with Expected value : " + evalue + "  for "
					+ field);
			Assert.assertTrue(true, "Verified Actual value : " + avalue + " is matching with Expected value : " + evalue
					+ "  for " + field);

		} else {
			fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
			Assert.assertEquals(avalue, evalue, "FAIL");
			Assert.fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
		}
	}

	public void verifyTextValidation(Double avalue, Double evalue, String field) {
		if (evalue.equals(avalue)) {
			printLog("Verified Actual text : " + avalue + " is matching with Expected text : " + evalue + "  for "
					+ field);
			Assert.assertTrue(true, "Verified Actual text : " + avalue + " is matching with Expected text : " + evalue
					+ "  for " + field);

		} else {
			fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
			Assert.assertEquals(avalue, evalue, "FAIL");
			Assert.fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
		}
	}

	public void verifyTextValidation(SortedSet<Double> avalue, SortedSet<Double> evalue, String field) {
		if (evalue.equals(avalue)) {
			printLog("Verified Actual text : " + avalue + " is matching with Expected text : " + evalue + "  for "
					+ field);
			Assert.assertTrue(true, "Verified Actual text : " + avalue + " is matching with Expected text : " + evalue
					+ "  for " + field);

		} else {
			fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
			Assert.assertEquals(avalue, evalue, "FAIL");
			Assert.fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
		}
	}

	public void verifyTextValidation(Object avalue, Object evalue, String field) {
		if (evalue.equals(avalue)) {
			printLog("Verified Actual text : " + avalue + " is matching with Expected text : " + evalue + "  for "
					+ field);
			Assert.assertTrue(true, "Verified Actual text : " + avalue + " is matching with Expected text : " + evalue
					+ "  for " + field);

		} else {
			fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
			Assert.assertEquals(avalue, evalue, "FAIL");
			Assert.fail("Actual text : " + avalue + " DO NOT match Expected text : " + evalue + "  for " + field);
		}
	}

	public void isSelected(WebElement element, String message) {

		boolean flag = false;
		flag = element.isDisplayed();
		if (flag) {
			flag = wait.presenceOfElement(driver, element).isSelected();
			// flag = driver.findElement(elementLocation).isSelected();
			if (flag) {
				Assert.assertTrue(flag);
				printLog(message + " is Selected");
			} else {
				fail(message + " is not selected");
			}
		} else {
			fail(message + " is not displayed");
			Assert.fail(message + " is not displayed");
		}
	}

	public void isNotSelected(WebElement element, String message) {
		boolean flag = false;
		flag = element.isDisplayed();
		if (flag) {
			flag = wait.presenceOfElement(driver, element).isSelected();
			// flag = driver.findElement(elementLocation).isSelected();
			if (!flag) {
				Assert.assertEquals(flag, false);
				printLog(message + " is not selected");
			} else {
				fail(message + "is selected");
			}
		} else {
			fail(message + " is not displayed");
			Assert.fail(message + " is not displayed");
		}
	}

	public void isNotDisplayed(List<WebElement> elements, String message) {

		if (elements.size() < 1) {
			Assert.assertTrue(true, message);
			printLog(message + " is not displayed");
		} else {
			Assert.assertTrue(false);
			fail(message + " is displayed");
		}
	}

	public void isDisplayed(WebElement element, String message) {
		if (isElementPresent(message, element)) {
			Assert.assertTrue(true, message);
			printLog(message + " is displayed");
		} else {
			Assert.assertFalse(true);
			fail(message + " is not displayed");
		}

	}

	/*
	 * Description: This method used to get whether the element is visible or not
	 *
	 * @ele: act on element,
	 */
	public void verifyElementIsDisplayed(String elemntName, WebElement element) throws Exception {
		try {
			WaitForHelper.visibilityOfElement(driver, 0, element);
			if (element.isDisplayed()) {
				printLog("The Element " + elemntName + " is present");
				Assert.assertTrue(true, "The Element " + elemntName + " is present");

			} else {
				printLog(" warn: The Element " + elemntName + " is not present");
				Assert.assertFalse(true, elemntName + " is not present");
			}
		} catch (Exception e) {
			fail(elemntName + " Element Not displayed....", e);
		}

	}

	/*
	 * Verify Element Present
	 */
	public boolean isElementPresent(String fieldLable, WebElement element) {
		boolean elementPresent = false;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}
		try {
			elementPresent = element.isDisplayed();
			if (elementPresent) {
				TestUtils.highlightElement(driver, element);
				elementPresent = true;
			}
		} catch (Exception e) {
		}
		return elementPresent;

	}

	/*
	 * Verify Element Present
	 */
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}

	/*
	 * ####################### Basic webDriver methods #######################
	 */

	/**
	 * Opens the supplied URL.
	 *
	 * @param url URL to be opened with the active WebDriver instance
	 */
	public void goToUrl(String url) {
		driver.get(url);
		printLog("Opens the supplied URL");
	}

	public void navigateToCRMURL() throws InterruptedException {
		try {
			printLog("AutomationConstants.sandbox :" + FrameworkConstants.sandbox);
			driver.get(getURL());
			printLog("Launch the URL : " + getURL());
		} catch (Exception e) {
			fail("FAIL: Failed to Launch URL ", e);
		}
	}

	public void navigateToPricer() throws InterruptedException {
		try {
			printLog("AutomationConstants.sandbox :" + FrameworkConstants.sandbox);
			driver.get(getPricerURL());
			printLog("Launch the URL : " + getPricerURL());
		} catch (Exception e) {
			fail("FAIL: Failed to Launch URL ", e);
		}
	}

	public static String getURL() {

		String url = null;
		switch (FrameworkConstants.sandbox.toLowerCase()) {
		case "Test":
			url = EnvironmentFactory.getConfigValue("salesforceUrl");
			break;
		case "sksfull1":
			url = EnvironmentFactory.getConfigValue("salesforceUrl");
			break;
		case "staging":
			url = EnvironmentFactory.getConfigValue("salesforceUrl");
			break;
		case "sksfull2":
			url = EnvironmentFactory.getConfigValue("salesforceUrl");
			break;
		case "onecrmdata":
			url = EnvironmentFactory.getConfigValue("salesforceUrl");
			break;
		case "dev2":
			url = EnvironmentFactory.getConfigValue("salesforceUrl");
			break;
		case "sksfull3":
			url = EnvironmentFactory.getConfigValue("salesforceUrl");
			break;
		default:
			url = FrameworkConstants.URL;
			break;
		}
		return url;

	}

	public static String getPricerURL() {

		String url = null;
		switch (FrameworkConstants.sandbox.toLowerCase()) {
		case "cpqdev":
			url = EnvironmentFactory.getConfigValue("PricerURL");
			break;
		case "cpquat":
			url = EnvironmentFactory.getConfigValue("PricerURL");
			break;
		case "staging":
			url = EnvironmentFactory.getConfigValue("PricerURL");
			break;
		case "onecrmuat":
			url = EnvironmentFactory.getConfigValue("PricerURL");
			break;
		case "onecrmdata":
			url = EnvironmentFactory.getConfigValue("PricerURL");
			break;
		case "onecrmdev":
			url = EnvironmentFactory.getConfigValue("PricerURL");
			break;
		case "sodev":
			url = EnvironmentFactory.getConfigValue("PricerURL");
			break;
		default:
			url = FrameworkConstants.URL;
			break;
		}
		return url;

	}

	public WebElement getElement(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public List<WebElement> getElements(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	public By getBy(String xPath) {
		return By.xpath(xPath);
	}

	public void refreshPage() {
		driver.get(driver.getCurrentUrl());
	}

	// the below method returns current url of the browser
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	/*
	 * Get the shadow elements using javascript path
	 */
	public WebElement getShadowElement(String javascriptPath) {
		WebElement element = null;
		for (int i = 0; i <= 50; i++) {
			try {
				JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
				element = (WebElement) javascriptExecutor.executeScript(javascriptPath);
				if (element == null) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.err.println("javascript Path shadow Element is not Present :" + e.getMessage());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
				continue;
			}
		}
		return element;
	}

	/*
	 * Get the shadow elements using javascript path
	 */
	@SuppressWarnings("unchecked")
	public List<WebElement> getShadowElements(String javascriptPath) {
		List<WebElement> element = null;
		for (int i = 0; i <= 50; i++) {
			try {
				JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
				element = (List<WebElement>) javascriptExecutor.executeScript(javascriptPath);
				if (element == null) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.err.println("javascript Path shadow Element is not Present :" + e.getMessage());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
				continue;
			}
		}
		return element;
	}

	public WebElement returnShadowElement(String jsPath) {
		WebElement ele = null;
		try {
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
			ele = (WebElement) javascriptExecutor.executeScript(jsPath);
		} catch (Exception e) {
			ele = null;
		}
		return ele;
	}

	/*
	 * ####################### switch methods #######################
	 */

	/*
	 * code to switch to frame using index
	 */
	public void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
			printLog("Switched to frame using index...  ");
		} catch (Exception e) {
			fail("Failed to SwitchFrame using Index", e);
		}
	}

	/*
	 * code to switch to frame using name
	 */
	public boolean switchToFrame(String frameName) {
		boolean flag = false;
		try {
			driver.switchTo().frame(frameName);
			flag = true;
			return true;
		} catch (Exception e) {
			fail("Failed to SwitchFrame using Name", e);
			return false;
		} finally {
			if (flag) {
				printLog("Frame with Name \"" + frameName + "\" is selected");
			} else if (!flag) {
				printLog("Frame with Name \"" + frameName + "\" is not selected");
			}
		}
	}

	/**
	 * switch to frame by frame WebElement
	 * 
	 */
	public void switchToFrame(WebElement frame) {
		try {
			driver.switchTo().frame(frame);
			printLog("Switched to frame using WebElement...  ");
		} catch (Exception e) {
			fail("Failed to SwitchFrame using WebElement", e);
		}
	}

	/*
	 * code to switch to frame using WebElement
	 */
	public void switchToFrame(String attributeType, String attributeValueBy, int index) {
		try {
			WebElement frameElement = driver.findElement(
					By.xpath("(//iframe[@" + attributeType + "='" + attributeValueBy + "'])[" + index + "]"));
			driver.switchTo().frame(wait.presenceOfElement(driver, frameElement));
			printLog("Switched to frame using webElement...  ");
		} catch (Exception e) {
			fail("Failed to SwitchFrame using WebElement", e);
		}
	}

	/*
	 * code to switch to childTab
	 */
	public String switchToChildTab() {
		String defaultWindow = driver.getWindowHandle();

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!defaultWindow.equalsIgnoreCase(ChildWindow)) {
				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				printLog("Switched to childwindow...  ");
			}
		}
		return defaultWindow;
	}

	/*
	 * ####################### basic log methods #######################
	 */
	public static void printLog(String message) {
		log.info("PASS : " + message);
		ExtentCucumberAdapter.addTestStepLog(TestUtils.getDate("dd-MM-yyyy-hh-mm-ss") + " : " + message);
		System.out.println(message);

	}

	public void fail(String message, Exception e) {
		log.error("FAIL : " + message, e);
		ExtentCucumberAdapter.addTestStepLog(TestUtils.getDate("dd-MM-yyyy-hh-mm-ss") + " : " + message);
		System.out.println(message + " : " + e.toString());
		Assert.fail(e.getMessage());
	}

	public void fail(String message) {
		log.fatal("FAIL : " + message);
		ExtentCucumberAdapter.addTestStepLog(TestUtils.getDate("dd-MM-yyyy-hh-mm-ss") + " : " + message);
		System.out.println(message);
		Assert.fail(message);
	}

	/*
	 * ####################### readData from Excel #######################
	 */

	public void startTestExecution(String excelFileName, int row) {
		try {

			inputpath = FrameworkConstants.projectPath + "\\TestData\\cpq\\" + excelFileName;
			rowData = ExcelDataRead.getRowData(inputpath, "Sheet1", row);
			printLog("Start the Execution Test Data loaded : " + inputpath);
		} catch (Exception e) {
			fail("Failed to load TestData the Execution", e);
		}
	}

	public String getValue(String KeyValue) {
		String value = null;
		try {
			value = ExcelDataRead.getValue(rowData, KeyValue).trim();
			printLog("get text value" + value);
		} catch (Exception e) {
			value = null;
		}
		if (value != null) {
			return value;
		} else {
			return KeyValue;
		}
	}

	public boolean retryingText(WebElement ele, String text) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				ele.click();
				ele.clear();
				waitTill("3");
				ele.sendKeys(text);
				result = true;
				break;
			} catch (Exception e) {
			}
			attempts++;
		}
		return result;
	}

	public static String generateRandomNumber() {
		int min = 10001;
		int max = 99999;
		int num = (int) (Math.random() * (max - min + 1) + min);
		String value = String.valueOf(num);
		return value;

	}

	/**
	 * Will return the number of days in a year based on the specified number of
	 * days from today.
	 * 
	 * If there is a leap year between today and the set number of days from today
	 * returns 366 else returns 365
	 * 
	 * @param numDays
	 */
	public int getYearLength(int numDays) {
		LocalDate today = LocalDate.now();
		LocalDate specifiedDate = today.plusDays(numDays);

		Boolean isLeapYear = false;
		LocalDate leapDay = null;// LocalDate.of(today.getYear(), 2, 29);

		if (today.isLeapYear()) {
			leapDay = LocalDate.of(today.getYear(), 2, 29);
			isLeapYear = true;
		} else if (specifiedDate.isLeapYear()) {
			leapDay = LocalDate.of(specifiedDate.getYear(), 2, 29);
			isLeapYear = true;
		}

		if (isLeapYear) {
			if (leapDay.isAfter(specifiedDate.minusYears(1)) && leapDay.isBefore(specifiedDate)) {
				return 366;
			} else {
				return 365;
			}
		} else {
			return 365;
		}

	}

	public void waitForElementToDisappear(By element) {
		WebDriverWait amendWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			amendWait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		} catch (Exception e) {

		}
	}

	// This method is to convert null values in a map as per the need
	public static <K, V> void convertNullValuesToString(Map<K, V> map, V nullReplacement) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (entry.getValue() == null) {
				entry.setValue(nullReplacement);
			}
		}
	}
}