package com.servicesImpl;
import java.sql.SQLException;

import com.Services.AccountServiceInterface;
import com.bean.Account;
import com.daoImpl.DAOAccountImpl;
import com.exception.InvalidAccountNoException;
import com.exception.InvalidFundException;

public class AccountServices implements AccountServiceInterface {
	
	// create an object of Object Carrier class 
	private Account acc;
	DAOAccountImpl dao;
	// openaccount function for opening new account and returns Account number
	public int openAccount(String accType,float accBal) throws SQLException {
		dao=new DAOAccountImpl();
		int accNo=dao.getLastAccountNo()+1;
		acc=new Account(accType,accBal,accNo);
		dao.insertAccountData(acc);
		return acc.getAccNo();
	}
	
	// displays account details 
	public String displayAccontDetails(int accNo) throws InvalidAccountNoException,SQLException {
		String details=null;
		dao=new DAOAccountImpl();
		Account acc=dao.getAccountDetails(accNo);
		if(acc!=null){
			details=acc.toString();
		}
		else
			throw new InvalidAccountNoException("Invalid Account Number");
		return details;
	}
	
	//deposit money to account number provided
	public float deposite(int accNo,float amt) throws InvalidAccountNoException,SQLException{
		dao=new DAOAccountImpl();
		float balance=0.0f;
		balance=dao.getAccountBalance(accNo);
		if(balance != 0.0f) {
			balance += amt;
			dao.updateAccountBalance(accNo,balance);
		}
		else
			throw new InvalidAccountNoException("Invalid Account Number");
		return dao.getAccountBalance(accNo);
	}
	
	// withdraw money from account number provided
	public float withdraw(int accNo,float amt) throws InvalidFundException,InvalidAccountNoException,SQLException {
		dao=new DAOAccountImpl();
		float balance=0.0f;
		balance=dao.getAccountBalance(accNo);
		if(balance != 0.0f){
			if(balance>amt) {
				synchronized(this) {
					balance -= amt;
					dao.updateAccountBalance(accNo,balance);
					try {
						Thread.sleep(100);
					} 
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else
				throw new InvalidFundException(" Withdrawal Amount Is Higher ");
		}
		else 
					throw new InvalidAccountNoException("Incorrect Account Information ");
		return dao.getAccountBalance(accNo);
	}
	
	// Inquiry account balance 
	public float enquiry(int accNo) throws InvalidAccountNoException,SQLException{
		dao=new DAOAccountImpl();
		float balance=0.0f;
		balance=dao.getAccountBalance(accNo);
		if(balance != 0.0f)
			return balance;
		else
			throw new InvalidAccountNoException("Incorrect Account Information ");
	}
	
	// get ifsc code using Class Name 
	public String getIfscCode() {
		return Account.ifscCode;
	}
}
