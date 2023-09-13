package crm.pages;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import qa.base.BasePage;
import qa.base.FrameworkConstants;
import qa.utils.LoggerFile;
import qa.utils.Enums.Context;


/*************************************************************************
 * Page class for the main header objects that are always visible when logged
 * into SFDC
 *
 * @author ecyork
 *
 *************************************************************************/
public class CommonPage extends BasePage {
	Logger log = LoggerFile.getLogger(CommonPage.class);
	//GetObject getObject = new GetObject();

	// Page Header Locators
	@FindBy(xpath = "//a[span/text()='Home']")
	WebElement homeTab;

	@FindBy(xpath = "//*[@class='photoContainer forceSocialPhoto']")
	WebElement lblViewProfile;

	@FindBy(xpath = "(//a[@href='/secur/logout.jsp'])[2]")
	WebElement lblLogout;

	@FindBy(xpath = "//span[contains(text(),'Sandbox:')]")
	WebElement sandboxLabelText;

	// Page Locators
	@FindBy(xpath = "//div[@role='button' and @class='setupGear']")
	WebElement setupGear;

	@FindBy(xpath = "//li[@id='related_setup_app_home']")
	WebElement setupRelatedApp;

	@FindBy(xpath = "//div[@data-message-id='loginAsSystemMessage']")
	WebElement loginAsSystemMessage;

	@FindBy(xpath = "//button[contains(@aria-label,'Search')]")
	WebElement globalSearch;

	// Related List Quick Link Locators
	@FindBy(xpath = "//a[contains(text(), 'Show All')]")
	WebElement showAllQuickLinks;

	@FindBy(xpath = "//a[contains(text(), 'Show Less')]")
	WebElement showLessQuickLinks;

	// vamshi 260722 for Search and Select Contact
	@FindBy(xpath = "//label[text()='Search by object type']/..//input")
	WebElement allDropDown;

	@FindBy(xpath = "(//div[contains(@class,'slds-listbox slds-listbox_vertical slds-dropdown')]//li//span[text()='Contacts'])[1]")
	WebElement selectContact;

	@FindBy(xpath = "//label[text()='Search Contacts']/..//input")
	WebElement entertextonSearch;

	@FindBy(xpath = "//div[@class='slds-truncate instant-result-item__content']//mark")
	WebElement clickOnContactName;

	@FindBy(xpath = "(//div[@class='slds-grid']//span[contains(text(),'Opportunities')])[1]")
	WebElement lnkOpportunities;

	@FindBy(xpath = "(//div[@class='slds-truncate instant-result-item__content'])[1]")
	WebElement searchResults;

	@FindBy(xpath = "//button[@aria-label='Search']")
	WebElement baseGlobalSearch;

	@FindBy(xpath = "(//div[@class='slds-truncate instant-result-item__content'])[1]")
	WebElement filter_Search;

	@FindBy(xpath = "//input[contains(@placeholder,'Search')]")
	WebElement txtglobalSearch;

	@FindBy(xpath = "//div[@class='slds-truncate instant-result-item__content']//mark")
	WebElement clickOnSearchValue;

	@FindBy(xpath = "//div[@class='iframe-parent slds-template_iframe slds-card']//iframe")
	WebElement iFrame;

	@FindBy(xpath = "//a[@id='globalHeaderNameMink']")
	WebElement cls_lblViewProfile;

	@FindBy(xpath = "//a[@title='Logout']")
	WebElement cls_lblLogout;

	@FindBy(xpath = "//a[contains(@class,'switch-to-lightning')]")
	WebElement lightning;

	@FindBy(xpath = "//h2[text()='We hit a snag.']/../../../../../..//li")
	WebElement lblErrorText;

	/**
	 * CommonPage constructor sets WebDriver for use
	 *
	 * @param driver
	 */
	public CommonPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public WebElement getItemInAppList(String appName) {
		return getElement(
				"//div[@class='al-menu-dropdown-list']//*[@class='slds-dropdown__item']//b[text()= '" + appName + "']");
	}

	public WebElement eleMenuItem(String menuItemName) {
		return getElement("/a[span/text()='" + menuItemName + "']");
	}

	public WebElement appLauncher() {
		return getElement("//div[@class='appLauncher slds-context-bar__icon-action']//button//div");
	}

	public WebElement appSearchField() {
		return getElement("//*[@class='al-menu-search-bar']//div//input");
	}

	public WebElement getButtonByText(String button) {
		try {
			return getElement("//button[text()='" + button + "']");
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement getNewButton(String objectName) {
		return driver.findElement(By.xpath("//span[text()='" + objectName
				+ "']/ancestor::div[contains(@class, 'firstHeaderRow')]/following-sibling::div//*[text()='New']"));
	}

	public WebElement getTextFieldLocator(String fieldName) {
		return getElement("//*[text()='" + fieldName + "']/parent::label//following-sibling::input");
	}

	public WebElement element_Lightning() {
		return driver.findElement(By.xpath("//a[contains(@class,'switch-to-lightning')]"));
	}

	public WebElement dropDownArrow(String dropDownName) {
		return getElement("//*[text()='" + dropDownName + "']/parent::*//following-sibling::div//div//div//div//*");
	}

	public WebElement dropDownOption(String option) {
		return getElement("//a[text()='" + option + "']");
	}

	public WebElement dropDownOption_span(String option) {
		return getElement("//span[text()='" + option + "']");
	}

	public WebElement dropDownOptionLink(String option) {
		return getElement("//a[@title='" + option + "']");
	}

	public WebElement getDropDownOption(String fieldName, String option) {
		return driver.findElement(By.xpath("//label[text()='" + fieldName
				+ "']/..//lightning-base-combobox-item//span//span[text()='" + option + "']"));
	}

	public List<String> getAllDropDownOptions(String fieldName) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> GroupList = driver.findElements(
				By.xpath("//label[text()='" + fieldName + "']/..//lightning-base-combobox-item//span//span"));

		for (WebElement element : GroupList) {
			{
				textList.add(element.getText());
			}
		}
		return textList;
	}

	public WebElement navBarObject(String objectName) {
		return driver.findElement(By.xpath("//nav//span[text()='" + objectName + "']"));
	}

	public WebElement getSandBoxName(String sandBoxName) {
		return driver.findElement(By.xpath("//span[text()=' Sandbox: " + sandBoxName + "']"));
	}

	public WebElement editField(String fieldName) {
		return driver.findElement(By.xpath("//span[text()='" + fieldName
				+ "']/../following-sibling::div//span[contains(@class,'inline-edit-trigger-icon')]"));
	}

	public WebElement cls_element_UserProfile() {
		return driver.findElement(By.xpath(
				"//*[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']/span"));
	}

	public WebElement cls_element_Classic() {
		return driver.findElement(By.xpath("//a[@class='profile-link-label switch-to-aloha uiOutputURL']"));
	}

	public WebElement btnContextBarFields(String locatorText) {
		return getElement("//div[@class='slds-context-bar']//a//span[text()='" + locatorText + "']");
	}

	/**
	 * Below method will launch an Application wrt the given parameter(App Name)
	 *
	 * @param appName: Name of the application to launch
	 */

	// this map initialization is to setting up runtime value and getting those
	// value
	public static Map<String, String> map = new HashMap<String, String>();

	public void appLauncher(String appName) {
		click("appLauncher", appLauncher());
		writeText(appSearchField(), appName);
		click("getItemInAppList", getItemInAppList(appName));
	}

	public WebElement cancelButton() {
		return getButtonByText("Cancel");
	}

	/**
	 * Uses the global search bar to search for the supplied String
	 *
	 * @param searchText
	 */
	public void globalSearchString(String searchText) {
		try {
			globalSearch.sendKeys(searchText + Keys.ENTER);
			log.info("Global Searched value is : " + searchText);
		} catch (Exception e) {
			log.error("Failed to Global Search");
			Assert.fail();
		}

	}

	/**
	 * Returns the text present in the system message bar at the top of all SFDC
	 * pages
	 *
	 * @return System Message text as string
	 */
	public String loggedInSandbox() {
		waitForElementToAppear(loginAsSystemMessage);
		return readText(loginAsSystemMessage);
	}

	// Page Actions

	public WebElement nextButton() {
		return getButtonByText("Next");
	}

	/**
	 * Will open the supplied relatedList quick link visible on the page
	 *
	 * @param relatedList
	 */
	public void openRelatedListQuickLink(String relatedList) {
		try {
			// find related list quick link
			WebElement quickLink = getElement(
					"(//div[@class='slds-grid']//span[contains(text(), '" + relatedList + "' )])[1]");
			click("quickLink", quickLink);
		} catch (Exception f) {
			BasePage.printLog("Related list not visible, expanding related list section");
			// check that all quick links are being shown, click Show All if visible
			try {
				waitForElementToBeClickable(showAllQuickLinks);
				click("show All Quick Links", showAllQuickLinks);
				WebElement quickLink = getElement(
						"(//div[@class='slds-grid']//span[contains(text(), '" + relatedList + "' )])[1]");
				click("quickLink", quickLink);
			} catch (Exception e) {
				BasePage.printLog("Related List is not visible on this page");
			}
		}

	}

	/**
	 * Method will Open the Related App Setup
	 * <p>
	 * Steps:
	 * <p>
	 * 1. click setup gear button in the page header<br>
	 * 2. click the Setup button
	 *
	 */
	public void openSetup() {
		click("setupGear", setupGear);
		click("setupRelatedApp", setupRelatedApp);
	}

	public WebElement saveAndNewButton() {
		return getButtonByText("Save & New");
	}

	/**
	 * Below are the list of Common locators used widely
	 *
	 * @return returns location of an element
	 */

	public WebElement saveButton() {
		return getButtonByText("Save");
	}

	/**
	 * Below method helps to select an option from drop down by entering the text in
	 * the field
	 *
	 * @param elementLocation: Name of the drop down web Element
	 * @param text:            text to enter in the field.
	 */
	public void searchSelectDropDownOption(WebElement element, String text) {
		writeText(element, text);
		WebElement ele = getElement(
				"//span[@class='slds-media__body']//span//lightning-base-combobox-formatted-text[@title='" + text
						+ "']");
		click(text, ele);
	}

	public void verifyHomePage() {
		try {
			if (isElementPresent(lightning)) {
				printLog("Clasic mode loaded... ignoring the SandBox :" + FrameworkConstants.sandbox
						+ " name validation");
			} else {
				printLog("Lightning mode loaded... ");
				if (FrameworkConstants.sandbox.equalsIgnoreCase("CPQUAT")) {
					verifyElementPresent("Home Page", getSandBoxName(FrameworkConstants.sandbox));
					printLog(FrameworkConstants.sandbox + " Application Home Page is available");
				}
				if (FrameworkConstants.sandbox.equalsIgnoreCase("CPQDEV")) {
					verifyElementPresent("Home Page", getElement("//span[text()=' Sandbox: CPQDEV']"));
					printLog(FrameworkConstants.sandbox + " Application Home Page is available");
				}
			}

		} catch (Exception e) {
			fail("Failed to verify Application Home Page", e);
		}

	}

	public void clickOnSearchButtonONHomePage() {

		try {
			// driver.navigate().refresh();
			click("global Search text box", globalSearch);
		} catch (Exception e) {
			fail("Failed to click on globalSearch", e);
		}
	}

	public void clickOnAllDropDownOnHomePage() {

		try {
			click("global Search dropdown", allDropDown);
		} catch (ElementNotInteractableException e) {
			clickOnSearchButtonONHomePage();
			waitTill("2");
			click("global Search dropdown", allDropDown);
		} catch (Exception e) {
			fail("Failed to click on allDropDown", e);
		}
	}

	public void selectContactDropDown() {

		try {
			click("selectContact", selectContact);
		} catch (Exception e) {
			fail("Failed to click on selectContact", e);
		}
	}

	public void enterContactName(String contact) {

		try {
			writeText(entertextonSearch, contact);
		} catch (Exception e) {
			fail("Failed to Enter Text in selectContact", e);
		}
	}

	public void clickOnMatchingContactName() {

		try {
			click("clickOnContactName", clickOnContactName);
		} catch (Exception e) {
			fail("Failed to click on clickOnContactName", e);
		}
	}

	public void clickOnOpportunitiesLink() {

		try {
			click("lnkOpportunities", lnkOpportunities);
		} catch (Exception e) {
			fail("Failed to click on lnkOpportunities", e);
		}

	}

	/*
	 * Method: Logout when the Salesforce lighting
	 */
	public void salesforceLogout() {
		try {
			click("View Profile", lblViewProfile);
			click("Log out", lblLogout);
			printLog("logout from Salesforce successful");
		} catch (Exception e) {
			fail("Failed to logout from Salesforce", e);
		}
	}

	/*
	 * Method: Logout when the Salesforce classic mode
	 */
	public void cls_SalesforceLogout() {
		try {
			click("View Profile", cls_lblViewProfile);
			click("Log out", cls_lblLogout);
			printLog("logout from Salesforce successful");
		} catch (Exception e) {
			fail("Failed to logout from Salesforce", e);
		}
	}

	public void enterGlobalSearchComboBox(String dropdwonValue) {

		try {
			writeText(allDropDown, dropdwonValue);

		} catch (Exception e) {
			fail("Failed to select : " + dropdwonValue, e);
		}
	}
	
	/*
	 * Method: Logout when the Salesforce menu item
	 */
	public void clickOMenuItem(String menuItem) {
		try {
			click(menuItem, eleMenuItem(menuItem));
		} catch (Exception e) {
			fail("Failed to click on " + menuItem, e);
		}
	}


	/**
	 * Uses the global search bar to search for the supplied String
	 *
	 * @param searchText
	 */
	public void globalSearch(String dropdownValue, String searchText) {
		try {
			waitForPageLoad();
			clickOnSearchButtonONHomePage();
			clickOnAllDropDownOnHomePage();
			WebElement dropdown = getElement(
					"(//div[contains(@class,'slds-listbox slds-listbox_vertical slds-dropdown')]//li//span[text()='"
							+ dropdownValue + "'])[1]");
			waitForElementToAppear(dropdown);
			clickUsingJavaExecutor(dropdown);
			click("Global Search", txtglobalSearch);
			writeText(txtglobalSearch, searchText);
			waitTill("2");
			clickSandboxLabel();
			clickOnSearchButtonONHomePage();
			waitTill("1");
			click(searchText, clickOnSearchValue);
			// clickOnMatchingContactName();
			printLog("Global search Done...");
		} catch (Exception e) {
			fail("Failed to search : " + searchText, e);
		}
	}

	/**
	 * Uses the global search bar to search for the supplied String
	 *
	 * @param searchText
	 */
	public boolean searchValueInGlobalSearch(String searchText, String dropdownValue) {
		boolean flag = false;
		try {
			waitForPageLoad();
			click("global Search text box", globalSearch);
			waitTill("2");
			click("global Search dropdown", allDropDown);
			WebElement dropdown = getElement(
					"(//div[contains(@class,'slds-listbox slds-listbox_vertical slds-dropdown')]//li//span[text()='"
							+ dropdownValue + "'])[1]");
			waitForElementToAppear(dropdown);
			clickUsingJavaExecutor(dropdown);
			click("Global Search", txtglobalSearch);
			writeText(txtglobalSearch, searchText);
			waitTill("1");
			click("clickOnContactName", clickOnSearchValue);
			printLog("Global search Done for " + searchText);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public double getRoundOffValue(double value) {
		return Math.round(value * 100.00) / 100.00;
	}

	public String addZerosToDecimalValue(double value) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(value);
	}

	public String addZeroToDecimalValue(double value) {
		NumberFormat formatter = new DecimalFormat("#0.0");
		return formatter.format(value);
	}

	public static void setValue(String key, String value) {
		map.put(key, value);
	}

	public static String getKeyValue(String key) {
		return map.get(key);
	}

	/*
	 * code to switch to SalesForce Classic or Lightning
	 */
	public void SwitchToSalesForceClassicOrLightning(String Classic_Lightning) {
		try {
			if (Classic_Lightning.equalsIgnoreCase("Lightning")) {
				try {
					click("Switch to lightning", element_Lightning());
				} catch (Exception e) {
					printLog("Already in lightning");
				}
			} else {
				try {
					click("User Profile", cls_element_UserProfile());
					click("Switch to Classic", cls_element_Classic());
				} catch (Exception e) {
					printLog("Already in Classic");
				}
			}
		} catch (Exception e) {
			fail("Failled to Switch To SalesForce Classic Or Lightning", e);
		}

	}

	/*
	 * Method: Logout from salesforce it will not depends on Lightning or class
	 */
	public void logOut() {
		try {
			if (!isElementPresent(lightning)) {
				salesforceLogout();
			} else {
				cls_SalesforceLogout();
			}
		} catch (Exception e) {
			fail("Failled to Switch To SalesForce Classic Or Lightning", e);
		}

	}

	public void clickOnContextBarButton(String locatorText) {
		click(locatorText, btnContextBarFields(locatorText));
	}

	public void clickOnButton(String buttonName) {
		waitTill("2");
		waitForElementToAppear(getButtonByText(buttonName));
		click(buttonName, getButtonByText(buttonName));
	}
	
	public void clickSandboxLabel() {
		click("Sandbox Label", sandboxLabelText);
	}

	public void openObjectById(String id) {
		driver.get(getURL() + id);
	}


	/*public void validate_QuoteLine_values_with_OpportunityProductLine_values(List<QuoteLine> qle,
			List<OpportunityProductLine> oppProductLine) {
		// storing outclause value from UI
		String outClauseSelectionValue = getKeyValue("checkBoxSelectionValue");

		// Getting API values stored in LIST
		List<Double> customerTotalQLE = new ArrayList<>();
		List<String> StartDateQLE = new ArrayList<>();
		List<String> endDateQLE = new ArrayList<>();
		List<Integer> yearQLE = new ArrayList<>();
		List<String> productNameQLE = new ArrayList<>();
		List<Double> totalPriceOppProductLine = new ArrayList<>();
		List<String> forecastDateOppProductLine = new ArrayList<>();
		List<String> startDateOppProductLine = new ArrayList<>();
		List<String> endDateOppProductLine = new ArrayList<>();
		List<String> termOppProductLine = new ArrayList<>();
		List<String> productNameOppProductLine = new ArrayList<>();
		List<String> revenueTypeOppProductLine = new ArrayList<>();

		// Extracting API record values and storing in LIST
		for (QuoteLine qle1 : qle) {
			customerTotalQLE.add(qle1.getCustomerTotal());
			StartDateQLE.add(qle1.getStartdate());
			endDateQLE.add(qle1.getEndDate());
			yearQLE.add(qle1.getYear());
			productNameQLE.add(qle1.getProductName());
		}
		// Extracting API record values and storing in LIST
		for (OpportunityProductLine oppProductLine1 : oppProductLine) {
			totalPriceOppProductLine.add(oppProductLine1.getTotalPrice());
			forecastDateOppProductLine.add(oppProductLine1.getForecastDate());
			startDateOppProductLine.add(oppProductLine1.getStartDate());
			endDateOppProductLine.add(oppProductLine1.getEndDate());
			termOppProductLine.add(oppProductLine1.getTerm());
			productNameOppProductLine.add(oppProductLine1.getProductName());
			revenueTypeOppProductLine.add(oppProductLine1.getRevenueType());
		}

		// Sorting the LIST
		Collections.sort(customerTotalQLE);
		Collections.sort(StartDateQLE);
		Collections.sort(endDateQLE);
		Collections.sort(yearQLE);
		Collections.sort(productNameQLE);
		Collections.sort(totalPriceOppProductLine);
		Collections.sort(forecastDateOppProductLine);
		Collections.sort(startDateOppProductLine);
		Collections.sort(endDateOppProductLine);
		Collections.sort(termOppProductLine);
		Collections.sort(productNameOppProductLine);

		// Modifying Renew type values as the values get differ when their is outcluase
		List<String> yearsInQLEAfterModifyingForRevenewType = new ArrayList<>();
		for (Integer value : yearQLE) {
			yearsInQLEAfterModifyingForRevenewType.add(value.toString());
		}
		// if out clause is true then enters to if statement....!!!! if not else stmt//
		// compare's values if terms is 0(stub) or 1 then it should be New business else
		// Out clause
		if (outClauseSelectionValue.equalsIgnoreCase("True")) {
			for (int i = 0; i < yearsInQLEAfterModifyingForRevenewType.size(); i++) {
				if (yearsInQLEAfterModifyingForRevenewType.get(i).equals("0")
						|| yearsInQLEAfterModifyingForRevenewType.get(i).equals("1")) {
					yearsInQLEAfterModifyingForRevenewType.set(i, "New Business");
				} else {
					yearsInQLEAfterModifyingForRevenewType.set(i, "Out Clause");
				}
				Collections.sort(yearsInQLEAfterModifyingForRevenewType);
				Collections.sort(revenueTypeOppProductLine, Collections.reverseOrder());
			}
		} else {

			for (int i = 0; i < yearsInQLEAfterModifyingForRevenewType.size(); i++) {
				if (yearsInQLEAfterModifyingForRevenewType.get(i).equals("0")
						|| yearsInQLEAfterModifyingForRevenewType.get(i).equals("1")) {
					yearsInQLEAfterModifyingForRevenewType.set(i, "New Business");
				} else {
					yearsInQLEAfterModifyingForRevenewType.set(i, "Auto Renew");
				}
				Collections.sort(yearsInQLEAfterModifyingForRevenewType, Collections.reverseOrder());
				Collections.sort(revenueTypeOppProductLine, Collections.reverseOrder());

			}

		}

		// // validating the values
		if (qle.size() != 0 && oppProductLine.size() != 0) {
			for (int i = 0; i < qle.size() && i < oppProductLine.size(); i++) {
				Assert.assertEquals(customerTotalQLE.get(i), totalPriceOppProductLine.get(i));
				printLog("CustomerTotal from QLE :-> " + customerTotalQLE.get(i) + " Matches with "
						+ "total Price of OpportunityProductLine :-> " + totalPriceOppProductLine.get(i));
				Assert.assertEquals(StartDateQLE.get(i), startDateOppProductLine.get(i));
				printLog("StartDate from QLE :-> " + StartDateQLE.get(i) + " Matches with "
						+ "Start date of OpportunityProductLine :-> " + startDateOppProductLine.get(i));
				Assert.assertEquals(StartDateQLE.get(i), forecastDateOppProductLine.get(i));
				printLog("StartDate from QLE :-> " + StartDateQLE.get(i) + " Matches with "
						+ "Forecast date of OpportunityProductLine :-> " + forecastDateOppProductLine.get(i));
				Assert.assertEquals(endDateQLE.get(i), endDateOppProductLine.get(i));
				printLog("End Date from QLE :-> " + endDateQLE.get(i) + " Matches with "
						+ "End Date of OpportunityProductLine :-> " + endDateOppProductLine.get(i));
				Assert.assertEquals(yearQLE.get(i).toString(), termOppProductLine.get(i));
				printLog("Year from QLE :-> " + yearQLE.get(i) + " Matches with "
						+ "Term of OpportunityProductLine :-> " + termOppProductLine.get(i));
				Assert.assertEquals(productNameQLE.get(i), productNameOppProductLine.get(i));
				printLog("Product Name from QLE :-> " + productNameQLE.get(i) + " Matches with "
						+ "Product Name of OpportunityProductLine :-> " + productNameOppProductLine.get(i));
				Assert.assertEquals(yearsInQLEAfterModifyingForRevenewType.get(i), revenueTypeOppProductLine.get(i));
				printLog("Term year from QLE :-> " + yearQLE.get(i) + " Matches with "
						+ "Revenue type of OpportunityProductLine :-> " + revenueTypeOppProductLine.get(i));
			}
		} else {
			fail("Records doesn't match");
		}
	}*/

	/*public void validate_OppForecastLine_values_with_OpportunityProductLine_values(
			List<OpportunityForecast> oppForecastLine, List<OpportunityProductLine> oppProductLine) {
		// storing outclause value from UI
		String outClauseSelectionValue = getKeyValue("checkBoxSelectionValue");

		// Getting API values stored in LIST
		List<Double> totalPriceOppProductLine = new ArrayList<>();
		List<String> forecastDateOppProductLine = new ArrayList<>();
		List<String> startDateOppProductLine = new ArrayList<>();
		List<String> endDateOppProductLine = new ArrayList<>();
		List<String> termOppProductLine = new ArrayList<>();
		List<String> productNameOppProductLine = new ArrayList<>();
		List<String> revenueTypeOppProductLine = new ArrayList<>();

		List<Double> coreAmountOppForecast = new ArrayList<>();
		List<String> startDateOppForecast = new ArrayList<>();
		List<String> endDateOppForecast = new ArrayList<>();
		List<String> termOppForecast = new ArrayList<>();
		List<String> productIDOppForecast = new ArrayList<>();
		List<String> revenueTypeOppForecast = new ArrayList<>();

		// Extracting API record values and storing in LIST
		for (OpportunityProductLine oppProductLine1 : oppProductLine) {
			totalPriceOppProductLine.add(oppProductLine1.getTotalPrice());
			forecastDateOppProductLine.add(oppProductLine1.getForecastDate());
			startDateOppProductLine.add(oppProductLine1.getStartDate());
			endDateOppProductLine.add(oppProductLine1.getEndDate());
			termOppProductLine.add(oppProductLine1.getTerm());
			productNameOppProductLine.add(oppProductLine1.getProductName());
			revenueTypeOppProductLine.add(oppProductLine1.getRevenueType());
		}
		// Extracting API record values and storing in LIST
		for (OpportunityForecast oppForecast1 : oppForecastLine) {
			coreAmountOppForecast.add(oppForecast1.getCoreAmount());
			startDateOppForecast.add(oppForecast1.getStartDate());
			endDateOppForecast.add(oppForecast1.getEndDate());
			termOppForecast.add(oppForecast1.getTerm());
			productIDOppForecast.add(oppForecast1.getProductID());
			revenueTypeOppForecast.add(oppForecast1.getRevenueType());
		}

		// Sorting the LIST
		Collections.sort(coreAmountOppForecast);
		Collections.sort(startDateOppForecast);
		Collections.sort(endDateOppForecast);
		Collections.sort(termOppForecast);
		Collections.sort(productIDOppForecast);
		Collections.sort(revenueTypeOppForecast);
		Collections.sort(totalPriceOppProductLine);
		Collections.sort(forecastDateOppProductLine);
		Collections.sort(startDateOppProductLine);
		Collections.sort(endDateOppProductLine);
		Collections.sort(termOppProductLine);
		Collections.sort(productNameOppProductLine);

		// Modifying Renew type values as the values get differ when their is outcluase
		List<String> revenewTypeAfterModifyingasPerTerm = new ArrayList<>();
		for (String value : termOppForecast) {
			revenewTypeAfterModifyingasPerTerm.add(value);
		}
		// if out clause is true then enters to if statement....!!!! if not else stmt//
		// compare's values if terms is 0(stub) or 1 then it should be New business else
		// Out clause
		if (outClauseSelectionValue.equalsIgnoreCase("True")) {
			for (int i = 0; i < revenewTypeAfterModifyingasPerTerm.size(); i++) {
				if (revenewTypeAfterModifyingasPerTerm.get(i).equals("0")
						|| revenewTypeAfterModifyingasPerTerm.get(i).equals("1")) {
					revenewTypeAfterModifyingasPerTerm.set(i, "New Business");
				} else {
					revenewTypeAfterModifyingasPerTerm.set(i, "Out Clause");
				}
				Collections.sort(revenewTypeAfterModifyingasPerTerm);
				Collections.sort(revenueTypeOppProductLine, Collections.reverseOrder());
				Collections.sort(revenueTypeOppForecast, Collections.reverseOrder());

			}
		} else {

			for (int i = 0; i < revenewTypeAfterModifyingasPerTerm.size(); i++) {
				if (revenewTypeAfterModifyingasPerTerm.get(i).equals("0")
						|| revenewTypeAfterModifyingasPerTerm.get(i).equals("1")) {
					revenewTypeAfterModifyingasPerTerm.set(i, "New Business");
				} else {
					revenewTypeAfterModifyingasPerTerm.set(i, "Auto Renew");
				}
				Collections.sort(revenewTypeAfterModifyingasPerTerm, Collections.reverseOrder());
				Collections.sort(revenueTypeOppProductLine, Collections.reverseOrder());
				Collections.sort(revenueTypeOppForecast, Collections.reverseOrder());

			}
		}

		// Getting productNames from OppForecastLineItems where it gives ID's instead of
		// name
		List<String> productNamesFromOppForecast = new ArrayList<>();
		for (int i = 0; i < productIDOppForecast.size(); i++) {
			productNamesFromOppForecast
					.add(getObject.getProductNameByIdFromOpportunityForecastLine(productIDOppForecast.get(i)));
		}
		// Sorting
		Collections.sort(productNamesFromOppForecast);

		// validating the values
		for (int i = 0; i < oppForecastLine.size() && i < oppProductLine.size(); i++) {
			Assert.assertEquals(coreAmountOppForecast.get(i), totalPriceOppProductLine.get(i));
			printLog("Core Amount from opportunity Forecast Line :-> " + coreAmountOppForecast.get(i) + " Matches with "
					+ "total Price of OpportunityProductLine :-> " + totalPriceOppProductLine.get(i));
			Assert.assertEquals(startDateOppForecast.get(i), startDateOppProductLine.get(i));
			printLog("StartDate from opportunity Forecast Line :-> " + startDateOppForecast.get(i) + " Matches with "
					+ "Start date of OpportunityProductLine :-> " + startDateOppProductLine.get(i));
			Assert.assertEquals(startDateOppForecast.get(i), forecastDateOppProductLine.get(i));
			printLog("Start Date from opportunity Forecast Line :-> " + startDateOppForecast.get(i) + " Matches with "
					+ "Forecast date of OpportunityProductLine :-> " + forecastDateOppProductLine.get(i));
			Assert.assertEquals(endDateOppForecast.get(i), endDateOppProductLine.get(i));
			printLog("End Date from opportunity Forecast Line :-> " + endDateOppForecast.get(i) + " Matches with "
					+ "End Date of OpportunityProductLine :-> " + endDateOppProductLine.get(i));
			Assert.assertEquals(termOppProductLine.get(i).toString(), termOppProductLine.get(i));
			printLog("Term from opportunity Forecast Line :-> " + termOppProductLine.get(i) + " Matches with "
					+ "Term of OpportunityProductLine :-> " + termOppProductLine.get(i));
			Assert.assertEquals(productNamesFromOppForecast.get(i), productNameOppProductLine.get(i));
			printLog("Product Name from opportunity Forecast Line :-> " + productNamesFromOppForecast.get(i)
					+ " Matches with " + "Product Name of OpportunityProductLine :-> "
					+ productNameOppProductLine.get(i));
			Assert.assertEquals(revenewTypeAfterModifyingasPerTerm.get(i), revenueTypeOppProductLine.get(i));
			printLog("Term year from opportunity Forecast Line  :-> " + termOppForecast.get(i) + " Matches with "
					+ "Revenue type of OpportunityProductLine :-> " + revenueTypeOppProductLine.get(i));
			Assert.assertEquals(revenewTypeAfterModifyingasPerTerm.get(i), revenueTypeOppForecast.get(i));
			printLog("Term year :-> " + termOppForecast.get(i) + " Matches with "
					+ "Revenue type of opportunity Forecast Lin :-> " + revenueTypeOppForecast.get(i));
		}

	}*/
	public WebElement dropDownArrowBylabel(String dropDownName) {
		return getElement(
				"//label[text()='" + dropDownName + "']/parent::*//following-sibling::div//div//div//div//button");
	}
	

}
