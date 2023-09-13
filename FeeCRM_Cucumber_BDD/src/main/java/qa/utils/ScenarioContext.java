package qa.utils;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import qa.utils.Enums.Context;

public class ScenarioContext {
	
	private Map<String, Object> scenarioContext;

	public ScenarioContext() {
		scenarioContext = new HashMap<>();
	}
	
	public void setContext(Context key, Object value) {
		try {
			scenarioContext.put(key.toString(), value);
		} catch (Exception e) {
			ExtentCucumberAdapter.addTestStepLog(TestUtils.getDate("dd-MM-yyyy-hh-mm-ss") + " : Failed to set context " + key.toString());
			System.out.println("Failed to set context " + key.toString() + e.toString());
			Assert.fail(e.getMessage());
		}
		
	}
	
	public Object getContext(Context key) {
		
		Object context = key.toString();
		
		if(isContains(key)) {
			try {
				context = scenarioContext.get(key.toString());
				
			} catch (Exception e) {
				ExtentCucumberAdapter.addTestStepLog(TestUtils.getDate("dd-MM-yyyy-hh-mm-ss") + " : Failed to get context " + key.toString());
				System.out.println("Failed to get context " + key.toString() + e.toString());
				Assert.fail(e.getMessage());
				
			}
		}
		
		return context;
		
	}
	
	public Boolean isContains(Context key) {
		return scenarioContext.containsKey(key.toString());
		
	}
}
