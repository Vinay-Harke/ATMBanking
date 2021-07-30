package com.Services;

import java.sql.SQLException;
import com.exception.InvalidAnswerException;
import com.exception.InvalidPasswordException;
import com.exception.InvalidSecurityQuesException;
import com.exception.InvalidUserNameException;

//userServices interface which shows operation will be done in UserValidation class
public interface UserServices {
	//signature of all services provided by UserVallidation Class which does actual implementation
	String signUp(String userName,String passWord,String securityQuestion, String answer,int accNo) throws SQLException, InvalidPasswordException;
	int signIn(String userName,String passWord)throws InvalidUserNameException,InvalidPasswordException, SQLException;
	String forgetPassword(String userName,String securityQuestion, String answer)throws InvalidSecurityQuesException,InvalidAnswerException, SQLException, InvalidUserNameException;
	String updatePassword(String userName,String passWord,String confirmPassword)throws InvalidUserNameException,InvalidPasswordException, SQLException;
	public int getAccountNo(String userName) throws SQLException;
}
