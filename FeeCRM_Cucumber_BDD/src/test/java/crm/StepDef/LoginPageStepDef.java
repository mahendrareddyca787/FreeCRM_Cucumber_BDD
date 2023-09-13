package crm.StepDef;

import org.openqa.selenium.WebDriver;

import crm.pages.CommonPage;
import crm.pages.Loginpage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginPageStepDef {
	public WebDriver driver;
	public Loginpage loginpage;
	public CommonPage commonpage;
	
	public LoginPageStepDef() {
		driver=Hooks.driverInstances.get(String.valueOf(Thread.currentThread().getId()));
		loginpage=new Loginpage(driver);
		commonpage=new CommonPage(driver);
		
	}
	
	@Given("I launch CRM application and login with {string} user")
	public void i_launch_crm_application(String string) throws InterruptedException {
		
		loginpage.navigateToCRM(string);
	}
	@When("I enter username and password")
	public void enter_username_password(){
		loginpage.Login("Mahendrareddy", "Test@123");
	}
	@And("I click on login button")
	public void i_click_on_login_button() {
		
	}
	

}
