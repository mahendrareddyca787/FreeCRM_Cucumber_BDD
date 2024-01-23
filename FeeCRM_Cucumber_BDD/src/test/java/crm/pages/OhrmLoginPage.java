package crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.base.BasePage;

public class OhrmLoginPage extends BasePage {
	
	@FindBy(xpath="//input[@name='username']")
	WebElement txtuname;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement button;
	
	@FindBy(xpath="//h6[text()='Dashboard']")
	WebElement txtDashboard;

	public OhrmLoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	

	public void navigateToOhrm() {
		try {
			navigateToCRMURL();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enterUserNameAndPassword(String Uname,String Pwd) {
		writeText(txtuname, Uname,"Username");
		writeText(txtpassword, Pwd, "Password");
		
	}
	
	public void clickOnbutton() {
		click("Login", button);	
	}
	public void verifyDashboradPage(String name) {
		String txtdashboard=readText(txtDashboard);
		verifyTextValidation(txtdashboard, name, "DashBoard Page");
	}
	
	public void clickOnLink(String lnkText) {
		click("PIM", getElementByspantext(lnkText));
		
	}
	
	public void enterfirstnameAndLastName(String fname,String lname) {
		writeText(getElementByName("firstName"), fname);
		writeText(getElementByName("lastName"), lname);
	}

}
