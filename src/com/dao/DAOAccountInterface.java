package com.dao;

import java.sql.SQLException;

import com.bean.Account;
import com.exception.InvalidAccountNoException;

//interface for Database operation on AccountDetails Table
public interface DAOAccountInterface {
	int insertAccountData(Account acc) throws SQLException;
	Account getAccountDetails(int accNo)throws SQLException;
	int updateAccountBalance(int accNo,float amt)throws SQLException;
	float getAccountBalance(int accNo) throws SQLException, InvalidAccountNoException;
	int getLastAccountNo() throws SQLException;
}