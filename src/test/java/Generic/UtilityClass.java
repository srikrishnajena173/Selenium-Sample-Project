package Generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityClass {
	public String samplePropertiesFile(String Skey, String propertiesFileName) {
		try {
			String file_properties = ClassLoader.getSystemResource(propertiesFileName).getFile();
			FileInputStream fis = new FileInputStream(file_properties);
			Properties prop = new Properties();
			prop.load(fis);
			return prop.getProperty(Skey);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] samplePropertiesFile(String[] Skey, String propertiesFileName) {
		try {
			String[] arrStr = new String[Skey.length];
			for (int i = 0; i < Skey.length; i++) {
				String file_properties = ClassLoader.getSystemResource(propertiesFileName).getFile();
				FileInputStream fis = new FileInputStream(file_properties);
				Properties prop = new Properties();
				prop.load(fis);
				arrStr[i] = prop.getProperty(Skey[i]);
			}
			return arrStr;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
