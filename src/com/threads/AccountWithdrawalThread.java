package com.threads;

import java.sql.SQLException;

import com.Services.AccountServiceInterface;
import com.exception.InvalidAccountNoException;
import com.exception.InvalidFundException;
public class AccountWithdrawalThread extends Thread{
	AccountServiceInterface accountant;
	float amount,accBal;
	int accNo;
	public AccountWithdrawalThread(AccountServiceInterface accountant,int accNo, float amount) {
		this.accountant = accountant;
		this.amount = amount;
		this.accNo=accNo;
		Thread t1=new Thread(this);
		t1.start();
	}
	
	public void run() {
			try {
				accBal=accountant.withdraw(accNo,amount);
			} 
			catch (InvalidFundException | InvalidAccountNoException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public String toString() {
		return "The Balance After Withdrawal Is :"+accBal;
	}

}
