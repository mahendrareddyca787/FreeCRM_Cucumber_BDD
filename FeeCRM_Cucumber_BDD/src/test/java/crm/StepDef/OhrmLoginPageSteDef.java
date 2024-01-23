package crm.StepDef;

import org.openqa.selenium.WebDriver;

import crm.pages.CommonPage;
import crm.pages.Loginpage;
import crm.pages.OhrmLoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OhrmLoginPageSteDef {

	public WebDriver driver;
	public OhrmLoginPage ohrmloginpage;
	public CommonPage commonpage;

	public OhrmLoginPageSteDef() {
		driver = Hooks.driverInstances.get(String.valueOf(Thread.currentThread().getId()));
		ohrmloginpage = new OhrmLoginPage(driver);
		commonpage = new CommonPage(driver);

	}

	@Given("I launch Ohrm application")
	public void i_lunch_ohrm_application() {
		ohrmloginpage.navigateToOhrm();
	}

	@When("I enter username as {string} and password as {string} for Ohrm login")
	public void i_enter_username_and_password(String uname, String pwd) {
		ohrmloginpage.enterUserNameAndPassword(uname, pwd);
	}

	@And("I click on {string} button")
	public void i_click_on_button() {
		ohrmloginpage.clickOnbutton();
	}

	@Then("I verify {string} page is displayed")
	public void i_verify_dashboard_page(String pageName) {
		ohrmloginpage.verifyDashboradPage(pageName);
	}

	@And("I click on {string} link")
	public void i_click_on_pim_link(String lnkText) {
		ohrmloginpage.clickOnLink(lnkText);
	}

	@And("I enter FirstName as {string} and LastName as {string}")

	public void i_enter_firstname_and_lastName(String fname, String lname) {
		ohrmloginpage.enterfirstnameAndLastName(fname,lname);
	}


}
