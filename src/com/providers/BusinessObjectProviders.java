package com.providers;

import java.io.FileInputStream;
import java.util.Properties;

import com.Services.AccountServiceInterface;
import com.Services.UserServices;

public class BusinessObjectProviders {
	
	public static UserServices getUserObject() {
		UserServices s=null;
		try {
			FileInputStream fis=new FileInputStream(".//resources//Info.properties");//reads an file info.properties
			Properties prop =new Properties();
			prop.load(fis);															//fis is loaded
			String userClass=prop.getProperty("userBusiness");//gets an value of userBusiness key
			s=(UserServices)Class.forName(userClass).getConstructor().newInstance();//new interface is created 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	public static AccountServiceInterface getAccountObject() {
		AccountServiceInterface s=null;
		try {
			FileInputStream fis=new FileInputStream(".//resources//Info.properties");//reads an file info.properties
			Properties prop =new Properties();
			prop.load(fis);
			String userClass=prop.getProperty("accountBusiness");//gets an value of accountBusiness key
			s=(AccountServiceInterface)Class.forName(userClass).getConstructor().newInstance();//new interface is created 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
}
