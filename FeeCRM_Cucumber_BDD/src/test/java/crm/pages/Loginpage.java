package crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.base.BasePage;

public class Loginpage extends CommonPage {

	BasePage basepage = new BasePage(driver);

	// Page Factory
	@FindBy(name = "username")
	WebElement txt_username;

	@FindBy(name = "password")
	WebElement txt_password;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btn_login;

	@FindBy(xpath = "//a[contains(@class,'navbar-brand')]")
	WebElement imgLogo;

	public Loginpage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Actions
	
	public void navigateToOhrm() {
		try {
			basepage.navigateToCRMURL();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void navigateToCRM(String string) throws InterruptedException {
		basepage.navigateToOHRMURL();
	}

	public String validaingPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCRMLogo() {
		return imgLogo.isDisplayed();

	}

	public void Login(String uname, String pwd) {

		// txt_username.sendKeys(uname);
		// txt_password.sendKeys(pwd);
		// btn_login.click();

		writeText(txt_username, uname, "Username");
		writeText(txt_password, pwd, "Password");

	}

	public void clickOnLoginButton() {
		click("Login", btn_login);
	}

}
