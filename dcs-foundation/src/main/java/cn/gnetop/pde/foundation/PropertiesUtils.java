package cn.gnetop.pde.foundation;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtils {
	public static void main(String[] args) {
		Properties pps = new Properties();
		try {
			pps.load(new FileInputStream("Test.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
