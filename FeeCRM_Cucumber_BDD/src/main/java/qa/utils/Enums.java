package qa.utils;

public class Enums {

	public enum AccountCurrency {
		UAEDIRHAM("AED - UAE Dirham"), AUSTRALIANDOLLER("AUD - Australian Dollar"),
		CANADIANDOLLAR("CAD - Canadian Dollar"), SWISSFRANC("CHF - Swiss Franc"), CHINESEYUAN("CNY - Chinese Yuan"),
		DANISHKRONE("DKK - Danish Krone"), EGYPTIANPOUND("EGP - Egyptian Pound"), EURO("EUR - Euro"),
		BRITISHPOUND("GBP - British Pound"), HONGKONGDOLLAR("HKD - Hong Kong Dollar"),
		INDONESIANRUPIAH("IDR - Indonesian Rupiah"), INDIANRUPEE("INR - Indian Rupee"),
		JAPANESEYEN("JPY - Japanese Yen"), MEXICANPESO("MXN - Mexican Peso"),
		MALAYSIANRINGGIT("MYR - Malaysian Ringgit"), NORWEGIANKRONE("NOK - Norwegian Krone"),
		NEWZEALANDDOLLAR("NZD - New Zealand Dollar"), PHILIPPINEPESO("PHP - Philippine Peso"),
		QATARRIAL("QAR - Qatar Rial"), SAUDIARABIANRIYAL("SAR - Saudi Arabian Riyal"),
		SWEDISHKRONA("SEK - Swedish Krona"), SINGAPOREDOLLAR("SGD - Singapore Dollar"), THAIBHAT("THB - Thai Baht"),
		TURKISHLIRA("TRY - Turkish Lira (New)"), USDOLLAR("USD - U.S. Dollar"),
		SOUTHAFRICANRAND("ZAR - South African Rand");

		public String get;

		AccountCurrency(String get) {
			this.get = get;
		}
	}

	public enum AccountIndustry {
		AEROSPACEANDDEFENSE("Aerospace and Defense"), AGRICULTUREANDFORESTRY("Agriculture and Forestry"),
		AUTOMOTIVE("Automotive"), BUSINESSSERVICES("Business Services"), CHEMICALS("Chemicals"),
		COLLEGESUNIVERSITIESSCHOOLS("Colleges/Universities/Schools"),
		COMPUTERSITTECHNOLOGYSERVICES("Computers/IT/Technology Services"),
		CONSTRUCTIONANDENGINEERING("Construction and Engineering"), CONSULTING("Consulting"),
		EDUCATIONANDTRAININGSERVICES("Education and Training Services"), ELECTRONICS("Electronics"),
		ENERGYANDUTILITIES("Energy and Utilities"), ENTERTAINMENTTRAVELLEISURE("Entertainment/Travel/Leisure"),
		ENVIRONMENTALSERVICESANDEQUIPMENT("Environmental Services and Equipment"),
		FINANCEBANKINGINSURANCE("Finance/Banking/Insurance"), FOODANDBEVERAGE("Food and Beverage"),
		GOVERNMENTLOCALSTATEFEDERAL("Government-Local/State/Federal"),
		HEALTHCAREMEDICALPHARMABIOTECH("Healthcare/Medical/Pharma/Bio-Tech"), MANUFACTURING("Manufacturing"),
		MARKETINGSALESADVERTISINGPR("Marketing/Sales/Advertising/PR"), MEDIA("Media"),
		MEMBERSHIPORGANIZATIONS("Membership Organizations"), METALSANDMINING("Metals and Mining"),
		REALESTATELEGAL("Real Estate/Legal"), RELIGIOUSORGANIZATIONS("Religious Organizations"),
		RETAILWHOLESALE("Retail/Wholesale"), TELECOMMUNICATIONEQUIPMENTSERVICES("Telecommunication Equipment/Services"),
		TRANSPORTATIONSHIPPINGLOGISTICS("Transportation/Shipping/Logistics"), UNKNOWN("Unknown");

		String get;

		AccountIndustry(String get) {
			this.get = get;
		}

	}

	public enum AccountSegment {
		STRATEGIC("Strategic"), STANDARD("Standard"), SCALE("Scale"), COMPLIANCEDESIGNATED("Compliance - Designated"),
		COMPLIANCEPOOLED("Compliance - Pooled");

		String get;

		AccountSegment(String get) {
			this.get = get;
		}
	}

	public enum AccountType {
		BASE("Base"), BASEEXPIRED("Base Expired"), EXPIREDNOTRENEWED("Expired/Not Renewed"), NEW("New"),
		PROSPECTING("Prospecting");

		String get;

		AccountType(String get) {
			this.get = get;
		}
	}

	public enum ActiveProspectingBy {
		RAE("RAE"), BDC("BDC");

		String get;

		ActiveProspectingBy(String get) {
			this.get = get;
		}
	}

	public enum AgreementTypeOptions {
		RegularAgreement("Regular Agreement"), eAgreement("e-Agreement"), TerminationAgreement("Termination Agreement");

		public String get;

		AgreementTypeOptions(String get) {
			this.get = get;
		}
	}

	public enum CustomerStatus {
		NO("Customer"), YES("Prospect"), EXPIREDFORMERCUSTOMER("Expired/Former Customer");

		String get;

		CustomerStatus(String get) {
			this.get = get;
		}
	}

	enum ForecastCategory {
		Omitted("Omitted"), Upside("Upside"), Probable("Probable"), Commit("Commit"), Closed("Closed");

		String get;

		ForecastCategory(String get) {
			this.get = get;
		}
	}

	public enum GSACustomer {
		NO("No"), YES("Yes");

		String get;

		GSACustomer(String get) {
			this.get = get;
		}
	}

	enum OpportunityStage {
		Qualification("Qualification"), Discovery("Discovery"), Evaluation("Evaluation"), Proposal("Proposal"),
		Negotiation("Negotiation"), ClosedWon("Closed Won"), ClosedLost("Closed Lost");

		String get;

		OpportunityStage(String get) {
			this.get = get;
		}
	}

	public enum OpportunityType {
		AutoReview("Auto Review"), Expire("Expire"), NewBusiness("New Business"), OutClause("Out Clause"),
		Upgrade("Upgrade");

		public String get;

		OpportunityType(String get) {
			this.get = get;
		}
	}

	public enum OutClause {
		NoOut("No Out"), FirstYearOut("First Year Out"), SecondYearOut("Second Year Out"),
		MultiYearOut("Multi Year Out");

		public String get;

		OutClause(String get) {
			this.get = get;
		}
	}

	public enum PrimarySolutionOfInterest {
		BusinessLead("Business & Lead"), TechDev("Tech & Dev"), Compliance("Compliance"), SumTotal("SumTotal"),
		PercipioSkillSoft("Percipio / Skillsoft");

		public String get;

		PrimarySolutionOfInterest(String get) {
			this.get = get;
		}

	}

	public enum SalesMotionOptions {
		Direct("Direct"), ResellerSellThrough("Reseller - Sell Through"), ResellerSellWith("Reseller - Sell With"),
		Ecommerce("Ecommerce");

		public String get;

		SalesMotionOptions(String get) {
			this.get = get;
		}
	}

	public enum Status {
		ACTIVE("Active"), INACTIVE("Inactive"), STALE("Stale");

		String get;

		Status(String get) {
			this.get = get;
		}
	}

	public enum YesNo {
		YES("Yes"), NO("No");

		String get;

		YesNo(String get) {
			this.get = get;
		}
	}

	public enum QLEAnnualUplift {
		CHECKED("checked "), UNCHECKED("hidden");

		public String get;

		QLEAnnualUplift(String get) {
			this.get = get;
		}
	}

	public enum Context {
		CLIENT_COMMUNITY_URL, LA_DEPLOYMENT_JS_URL, LA_INIT_JS_URL, LA_PERCIPIO_DEPLOY_JS_URL, LA_PERCIPIO_INIT_JS_URL,
		LA_PERCIPIO_PRECHAT_JS_URL,

		OPPORTUNITY_NAME,OPPORTUNITY_ID,OPPORTUNITY_NUMBER,
		
		AMEND_QUOTE_ID,
		
		QUOTE_NUMBER,QUOTE_ID,CONTRACT_ID,

		Conga_VisualForce, Conga_Front_End, Standard_VisualForce;

	}
	
	public enum RecordTypeID{
		CHANNEL_PARTNER_ACCOUNT("01230000000p0WzAAI"), CUSTOMER_ACCOUNT("01230000000p0X4AAI");
		
		public String get;
		
		RecordTypeID(String get) {
			this.get = get;
		}
	}

}
