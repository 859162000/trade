package com.hbc.data.trade.transfer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConfigLoader{  
	private static Logger log = LoggerFactory.getLogger(ConfigLoader.class);
	private static Properties properties = new Properties();
	private ConfigLoader(){
		String path = Class.class.getClass().getResource("/").getPath();
		loadProperties(path);
	}
	public static void loadProperties(String...folders){
		try {
			for(String fopath : folders){
				File configFolder = new File(fopath);
				if (configFolder.isDirectory()) {
					File[] files = configFolder.listFiles();
					for (File fileF : files) {
						if(!fileF.isDirectory() && fileF.getName().endsWith("properties")){
							properties.load(new FileInputStream(fileF));
						}
					}
				}
			}
		} catch (IOException e) {
			log.error("",e);
		}
	}
	public static String getProperty(String key){
		return (String)properties.get(key);
	}
	public static String getProperty(String key,String defaultVal){
		String value = (String)properties.get(key);
		if(value==null){
			value = defaultVal;
		}
		return value;
	}
	public static int getInt(String key,int defaultIn){
		try{
			int val = Integer.parseInt((String)properties.get(key));
			return val;
		}catch(Exception e){
			log.error("", e);
		}
		return defaultIn;
	}
	
	
	public static double getDouble(String key,double defaultIn){
		try{
			double val = Double.parseDouble((String)properties.get(key));
			return val;
		}catch(Exception e){
			log.error("", e);
		}
		return defaultIn;
	}
	
	public static void setProperty(String key,String value){
		properties.setProperty(key, value);
	}
	
}
