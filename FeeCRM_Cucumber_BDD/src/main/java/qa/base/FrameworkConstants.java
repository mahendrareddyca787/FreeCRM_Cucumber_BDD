package qa.base;

public interface FrameworkConstants {

	String projectPath = System.getProperty("user.dir");

	public static String sandbox = System.getProperty("baseEnv", "Test"); // DEV1 //SKSFULL1 //DEV2 //STAGING
																				// //SKSFULL2 //ONECRMQA //ONECRMUAT

	public static final String Browser = System.getProperty("browser", "chrome"); // chrome firefox internetExplorer
																					// Edge

	public static final String TimeOut = "40";

	public static final String URL = "https://classic.crmpro.com/index.html";

	public static final String SettingsURL = "lightning/setup/SetupOneHome/home";

	public static final String configpath = projectPath + "\\src\\main\\resources\\config.xml";

	public static final String reportsPath = projectPath + "\\test-output\\";

	public static final String testDataPath = projectPath + "\\TestData\\cpq\\";

	public static final String ssoConfigPath = projectPath + "\\TestData\\sso\\";
	// set to true to import results to qmetry
	public static final Boolean qmetryImport = false;

	// ******************API path param's***************************************
	public static final String queryURI = "services/data/v54.0/query/?q=";

	public static final String opportunityURI = "services/data/v54.0/sobjects/Opportunity";

	public static final String accountURI = "services/data/v54.0/sobjects/Account";

	public static final String contactURI = "services/data/v54.0/sobjects/Contact";

	public static final String accountAddressURI = "services/data/v54.0/sobjects/Account_Addresses__c";

	public static final String partnerBusinessUnitURI = "services/data/v54.0/sobjects/Partner_Business_Unit__c";

	public static final String createQuoteURI = "services/data/v54.0/sobjects/SBQQ__Quote__c";

	public static final String relatedListsURI = "services/data/v54.0/ui-api/related-list-records";

	public static final String productURI = "services/data/v54.0/sobjects/Product2";
	
	public static final String settingURI = "services/data/v54.0/sobjects/Setting__c";
}