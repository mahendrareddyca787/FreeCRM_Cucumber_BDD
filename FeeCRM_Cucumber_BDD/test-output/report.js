$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/Features/OhrmLogin.feature");
formatter.feature({
  "name": "Tho test the OHRM Login page functionality",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Verify Login button functionality",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@ohrmlogin"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I launch Ohrm application",
  "keyword": "Given "
});
formatter.match({
  "location": "crm.StepDef.OhrmLoginPageSteDef.i_lunch_ohrm_application()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter username as \"Admin\" and password as \"admin123\" for Ohrm login",
  "keyword": "When "
});
formatter.match({
  "location": "crm.StepDef.OhrmLoginPageSteDef.i_enter_username_and_password(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on Login button",
  "keyword": "And "
});
formatter.match({
  "location": "crm.StepDef.OhrmLoginPageSteDef.i_click_on_button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify \"Dashboard\" page is displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "crm.StepDef.OhrmLoginPageSteDef.i_verify_dashboard_page(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.write("Verify Login button functionality : Passed");
formatter.embedding("image/png", "embedded0.png", "image");
formatter.after({
  "status": "passed"
});
});