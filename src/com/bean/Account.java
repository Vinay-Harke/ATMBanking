package com.bean;

public class Account {
	//data abstraction .....instance variable/object data---data members
	private int accNo;
	private String accType;
	private float accBal;
	public static String ifscCode;// static variable for global data
	
	// static block for invoking before main function as it is loaded before main function by class loader for initializing of static data
	static {
		ifscCode="MAHB5080S";
	}
	
	
	// constructor of an account class
	public Account(String accType,float accBal,int accNo){
		this.accNo=accNo;
		this.accBal=accBal;
		this.accType=accType;
	}
	
	//setter methods of instance variable
	public void setAccNo(int accNo) {
		this.accNo=accNo;
	}
	public void setAccType(String accType) {
		this.accType=accType;
	}
	public void setAccBal(float accBal) {
		this.accBal=accBal;
	}
	
	//getters of instance variables
	public int getAccNo() {
		return this.accNo;
	}
	public String getAccType() {
		return this.accType;
	}
	public float getAccBal() {
		return this.accBal;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Account Number : "+accNo+"\n"+"Account Type : "+accType+"\n"+"Account Balance : "+accBal;
	}	
}
