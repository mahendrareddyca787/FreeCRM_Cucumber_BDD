$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/Features/LoginPage.feature");
formatter.feature({
  "name": "Test CRM Login Page Functionality",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@logintestcases"
    }
  ]
});
formatter.scenario({
  "name": "CRM Loginpage functionality with valid credetials",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@logintestcases"
    },
    {
      "name": "@loginbutton"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I launch CRM application and login with \"Admin\" user",
  "keyword": "Given "
});
formatter.match({
  "location": "crm.StepDef.LoginPageStepDef.i_launch_crm_application(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter username and password",
  "keyword": "When "
});
formatter.match({
  "location": "crm.StepDef.LoginPageStepDef.enter_username_password()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on login button",
  "keyword": "And "
});
formatter.match({
  "location": "crm.StepDef.LoginPageStepDef.i_click_on_login_button()"
});
formatter.result({
  "status": "passed"
});
formatter.write("CRM Loginpage functionality with valid credetials : Passed");
formatter.embedding("image/png", "embedded0.png", "image");
formatter.after({
  "status": "passed"
});
});