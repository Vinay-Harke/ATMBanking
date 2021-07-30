package com.Services;

import java.sql.SQLException;

import com.exception.InvalidAccountNoException;
import com.exception.InvalidFundException;

// Account service interface which shows operation will be done in AccountServiceImpl
public interface AccountServiceInterface {
	//signature of all services provided by AccountService Class which does actual implementation
	 int openAccount(String accType,float accBal) throws SQLException;
	 String displayAccontDetails(int accNo) throws InvalidAccountNoException, SQLException;
	 float deposite(int accNo,float amt)throws InvalidAccountNoException, SQLException;
	 float withdraw(int accNo,float amt)throws InvalidFundException,InvalidAccountNoException,SQLException;
	 float enquiry(int accNo)throws InvalidAccountNoException,SQLException;
	 String getIfscCode();
}