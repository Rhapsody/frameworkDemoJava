package com.napster.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesManager {
	private static final String configPropName = "configprop";

	static {
		addPropertiesToSystem();
	}

	private static void addPropertiesToSystem() {
		String serverConfigFileName = System.getProperty(configPropName);
		if (serverConfigFileName == null || serverConfigFileName.equals("${" + configPropName + "}")) {
			serverConfigFileName = "int.properties";
			System.setProperty(configPropName, serverConfigFileName);
		}

		List<String> propertiesFileNames = new ArrayList<>();
		propertiesFileNames.add("common.properties");
		propertiesFileNames.add(serverConfigFileName);
		boolean allLoadedSuccessfully = addProperties(propertiesFileNames, PropertiesManager.class);
		if (allLoadedSuccessfully) {
			System.out.println("Using Server Configuration Properties: '" + serverConfigFileName + "'\n");
		}
	}

	public static void refresh(){
		addPropertiesToSystem();
	}

	public static void addOverrideProperties(Class cls) {
		String serverConfigFileName = System.getProperty(configPropName);
		List<String> propertiesFileNames = new ArrayList<String>();
		propertiesFileNames.add("override." + serverConfigFileName);
		addProperties(propertiesFileNames, cls);
	}

	private static boolean addProperties(List<String> propertiesFileNames, Class cls) {
		Properties properties = System.getProperties();

		boolean allLoadedSuccessfully = true;
		for (String filename : propertiesFileNames) {
			try {
				InputStream propFileStream = cls.getResourceAsStream("/" + filename);
				if (propFileStream != null) {
					properties.load(propFileStream);
					setOverride(properties);
				} else {
					System.err.println("Unable to open Properties file: " + filename);
					allLoadedSuccessfully = false;
				}
			} catch (IOException e) {
				System.err.println("IOException occurred while trying to load property file: " + filename + "\n");
				allLoadedSuccessfully = false;
			}
		}
		return allLoadedSuccessfully;
	}

	private static void setOverride(Properties properties){
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()){
			Object key = keys.nextElement();
			if(key instanceof String){
				if(key.toString().startsWith("rdsoverride.")){
					Object value = properties.get(key);
					String newkey = key.toString().replace("rdsoverride.", "");
					properties.put(newkey, value);
				}
			}
		}
	}

	public static String getEnvName(){
		return System.getProperty(configPropName).split("\\.")[0];
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	public static String getProperty(String key, String def) {
		String property = System.getProperty(key, def);
		if (property != null) {
			property = property.trim();
		}
		return property;
	}

	private PropertiesManager() {}
}
