package qa.base;

import qa.utils.TestUtils;

public class SampleDataGenerator {

	String testCaseNumber;

	public SampleDataGenerator(String testCaseNumber) {
		this.testCaseNumber = testCaseNumber;
	}

	public String appDesc(int x) {
		return concat(testCaseNumber, "appDescription", x);
	}

	public String appName(int x) {
		return concat(testCaseNumber, "OppclassName", x);
	}

	public String concat(String s1, String s2, int s3) {
		return s1 + " " + TestUtils.randomString().substring(0, 7) + " " + s2 + "_" + TestUtils.numberToWord(s3);

	}

	public int getRandomNumber(int from, int to) {
		return TestUtils.getRandomNumber(from, to);
	}

	public void testing(String st) {
	}
}
