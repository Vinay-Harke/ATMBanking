package com.providers;

//create an thread for account withdrawl
import com.Services.AccountServiceInterface;
import com.threads.AccountWithdrawalThread;

public class ThreadProviders {
	public static AccountWithdrawalThread getAccountWithdrawalThread(AccountServiceInterface service,int accNo,float amount) {
		return new AccountWithdrawalThread(service,accNo,amount);
	}

}
