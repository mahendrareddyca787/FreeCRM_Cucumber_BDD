package qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*************************************************************************
 * Configuration Management class for loading .properties files
 *
 *
 * @author ecyork
 *
 *************************************************************************/
public class PropertyReader {

	static Properties properties;

	/**
	 * Returns the value for the supplied key Name
	 *
	 * @param propertyName key name to be looked up
	 * @return
	 */
	public static String readItem(String propertyName) {
		return properties.getProperty(propertyName);
	}

	public PropertyReader() {
		loadAllProperties();
	}

	/**
	 * Loads all config.properties
	 * <p>
	 * TODO: implement dynamic property loading to handle multiple different
	 * environment/application configuration properties
	 */
	public void loadAllProperties() {
		properties = new Properties();
		// Read the file cpqdevatg_config.properties
		try {
			String fileName = System.getProperty("user.dir") + "/src/test/resources/application.properties";
			properties.load(new FileInputStream(fileName));
		} catch (IOException e) {
			throw new RuntimeException("Not able to Find the File");
		}
	}

}
