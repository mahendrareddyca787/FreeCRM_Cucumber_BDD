package qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import qa.base.FrameworkConstants;

public class TestUtils {

	public static void captureScreenshot(WebDriver driver) throws IOException {

		Date d = new Date();

		String FileName = "screenshots//" + d.toString().replace(":", "_").replace(" ", "_") + ".png";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileHandler.copy(screenshot, new File(FrameworkConstants.reportsPath + FileName));

	}

	/*
	 * Password decode
	 */
	public static String decode(String passwordToHash) {
		return StringUtils.newStringUtf8(org.apache.commons.codec.binary.Base64.decodeBase64(passwordToHash));
	}
	/*
	 * Password encode
	 */

	public static String encode(String passwordToHash) {
		return StringUtils
				.newStringUtf8(org.apache.commons.codec.binary.Base64.encodeBase64(passwordToHash.getBytes()));
	}

	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmmss");
		return sdf1.format(date);

	}

	public static int getRandomNumber(int min, int max) {
		return (int) Math.floor(Math.random() * (max - min + 1)) + min;
	}

	/*
	 * highlight the Element
	 */
	public static void highlightElement(WebDriver driver, WebElement element) {
		String presentColor = element.getCssValue("backgroundColor");
		String newCoclor = "rgb(255,255,0)";

		for (int i = 1; i <= 3; i++) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='" + newCoclor + "'",
					element);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='" + presentColor + "'",
					element);
		}
	}

	public static String numberToWord(int num) {

		String words = "";
		String unitarr[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
		String tensarr[] = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
				"ninety" };
		if (num == 0) {
			return "zero";
		}

		if (num < 0) {
			// converting the number to a string
			String numberStr = "" + num;
			// removing minus before the number
			numberStr = numberStr.substring(1);
			// add minus before the number and convert the rest of number
			return "minus " + numberToWord(Integer.parseInt(numberStr));
		}
		// conditon for divisible by 1 million
		if ((num / 1000000) > 0) {
			words += numberToWord(num / 1000000) + " million ";
			num %= 1000000;
		}
		// conditon for divisible by 1 thousand
		if ((num / 1000) > 0) {
			words += numberToWord(num / 1000) + " thousand ";
			num %= 1000;
		}
		// conditon for divisible by 1 hundred
		if ((num / 100) > 0) {
			words += numberToWord(num / 100) + " hundred ";
			num %= 100;
		}
		if (num > 0) {
			if (num < 20) {
				words += unitarr[num];
			} else {
				words += tensarr[num / 10];
				if ((num % 10) > 0) {
					words += "-" + unitarr[num % 10];
				}
			}
		}
		return words;
	}

	public static String randomString() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String todayDate() {
		DateFormat Date = DateFormat.getDateInstance();
		Calendar cals = Calendar.getInstance();
		return Date.format(cals.getTime()).toString();
	}

	public static String addDaysToTodayDate(int numberOfDays) {
		DateFormat currentDate = DateFormat.getDateInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, numberOfDays);
		return currentDate.format(c.getTime()).toString();
	}

	public static String getTodayDate() {
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		return sdf1.format(date);
	}

	public String selectDate() {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
		String startdate = formatter.format(date);
		return startdate;
	}

	public static String getDate(String simpleDateFormat) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(simpleDateFormat);
		return formatter.format(date);

	}
	/*
	 * Method: return the date as String with Number of days added to it. Date:
	 * 17/11/22
	 */

	public static String getTodayDateFromNobDays(String simpleDateFormat, int numberOfDays) {
		if (simpleDateFormat.equals("")) {
			simpleDateFormat = "MM/dd/yyyy";
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, numberOfDays);
			Date date = calendar.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat(simpleDateFormat);
			return format1.format(date);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, numberOfDays);
			Date date = calendar.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat(simpleDateFormat);
			return format1.format(date);
		}

	}

	/*
	 * Method: return the date as String with Number of days added to it. Date:
	 * 05/09/22
	 */
	public static String getTodayDateFromNobDays(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, i);
		int d = calendar.get(Calendar.DATE);
		calendar.add(Calendar.MONTH, 1);
		int m = calendar.get(Calendar.MONTH);
		String date = m + "/" + d + "/" + calendar.get(Calendar.YEAR);
		return date;
	}

	/*
	 * Method: return the date as String with Number of days added to it. Date:
	 * 05/09/22
	 */

	public static String getTodayDateFromNobDays(String i) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, Integer.parseInt(i));
		int d = calendar.get(Calendar.DATE);
		calendar.add(Calendar.MONTH, 1);
		int m = calendar.get(Calendar.MONTH);
		String date = m + "/" + d + "/" + calendar.get(Calendar.YEAR);
		return date;
	}

	public static String getYearDate(int day, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		int d = calendar.get(Calendar.DATE);
		calendar.add(Calendar.MONTH, 1);
		int m = calendar.get(Calendar.MONTH);
		calendar.add(Calendar.YEAR, year);
		int y = calendar.get(Calendar.YEAR);
		String date = m + "/" + d + "/" + y;
		return date;
	}

	public static String getDate(int i) {
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, i);
		Date date = calendar.getTime();
		System.out.println(format1.format(date));
		return format1.format(date);
	}

	public static void zipSingleFile(Path source, String zipFileName) throws IOException {

		if (!Files.isRegularFile(source)) {
			System.err.println("Please provide a file.");
			return;
		}

		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));
				FileInputStream fis = new FileInputStream(source.toFile());) {

			ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
			zos.putNextEntry(zipEntry);

			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			zos.closeEntry();
		}

	}

	public static void deleteDirectory(File directory) {
		/*
		 * Arrays.stream(Objects.requireNonNull(directory.listFiles())).filter(Predicate
		 * .not(File::isDirectory)) .forEach(File::delete);
		 */
	}

	/*
	 * return the start date , end date based on Terms and stub days
	 */
	public static String getStartAndEndDate(String startDate1, String AnniversaryDate1, int StubDays,
			int TermsInYears) {
		String startEndDate = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter myfromat = DateTimeFormatter.ofPattern("M/d/yyyy");
			LocalDate startDate = LocalDate.parse(startDate1, formatter);
			LocalDate AnniversaryDate = LocalDate.parse(AnniversaryDate1, formatter);
			if (TermsInYears == 0) {
				startEndDate = myfromat.format(startDate) + "r" + myfromat.format(startDate.plusDays(StubDays - 1));
				return startEndDate;
			} else {
				startEndDate = myfromat.format(AnniversaryDate.plusYears(TermsInYears - 1)) + "r"
						+ myfromat.format(AnniversaryDate.plusYears(TermsInYears).minusDays(1));
				return startEndDate;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return startEndDate;

	}

}
