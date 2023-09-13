package qa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerFile {

	private static boolean root = false;
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
	}

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class cls) {
		if (root) {
			return Logger.getLogger(cls);
		}
		String path = System.getProperty("user.dir");
		PropertyConfigurator.configure(path + "\\src\\main\\resources\\Log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}
}
