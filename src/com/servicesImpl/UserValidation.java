package com.servicesImpl;

import com.bean.User;
import com.daoImpl.DAOUserImpl;
import com.exception.InvalidAnswerException;
import com.exception.InvalidPasswordException;
import com.exception.InvalidSecurityQuesException;
import com.exception.InvalidUserNameException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Services.UserServices;

public class UserValidation implements UserServices {
	private User user1;
	DAOUserImpl dao;
	//valiadte password using regEx expression 
	public boolean isValidPassword(String passWord) {
        String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$"; 
        Pattern p = Pattern.compile(regex); 
        Matcher m = p.matcher(passWord); 
        return m.matches(); 
	}
	//signUp code
	public String signUp(String userName,String passWord,String securityQuestion,String answer,int accNo)throws SQLException,InvalidPasswordException{
		if(isValidPassword(passWord)) {
			dao =new DAOUserImpl();
			user1 = new User(userName,passWord,securityQuestion,answer);
			dao.insertUserData(user1,accNo); // insert data into database
			return "You Are Signed Up Sucessfully And Your Account Is Created Sucessfully With Account Number : "+accNo;
		}
		else
			throw new InvalidPasswordException("PassWord Constraint Is NOT Followed ");
	}
	//signIn code
	@SignInAnnotaion(name="BusinessLogicFunction",id=2)
	public int signIn(String userName,String passWord) throws InvalidUserNameException,InvalidPasswordException,SQLException {
		dao =new DAOUserImpl();
		String result=dao.checkSigninData(userName, passWord); // check signin results
		if(result=="PASS")
			return 1;
		else if(result=="FAIL")
			throw new InvalidPasswordException("Invalid PassWord ");
		else
			throw new InvalidUserNameException("Enter Correct UserName");
	}
	
	// forget password code
	public String forgetPassword(String userName,String securityQuestion,String answer)throws InvalidUserNameException,InvalidSecurityQuesException,InvalidAnswerException,SQLException {
		dao=new DAOUserImpl();
		String result=dao.getPassword(userName, securityQuestion, answer);//get an password
		if(result == null)
			throw new InvalidUserNameException("Enter Correct UserName");
		else if(result =="FAIL")
			throw new InvalidSecurityQuesException("Please Enter Valid Security Question");
		else if(result=="INVALID ANSWER")
			throw new InvalidAnswerException(result);
		else
			return "Your New Password Is : "+result;
	}

	public String updatePassword(String userName,String passWord,String confirmPassword)throws InvalidUserNameException,InvalidPasswordException,SQLException{
		if(isValidPassword(passWord)&& isValidPassword(confirmPassword)) {
			if(passWord == confirmPassword) {
				dao=new DAOUserImpl();
				if(dao.setPassword(userName, passWord, confirmPassword)!=0)
					return "Password Is Updated Sucessfully....";
				else
					throw new InvalidUserNameException("Enter Correct Username");
			}
			else
				throw new InvalidPasswordException("new password and confirm password are mismatched ");
		}
		else
			throw new InvalidPasswordException("Password Constraint Is NOT Followed ");
	}
	public int getAccountNo(String userName) throws SQLException{
		return dao.getAccountNo(userName);
	}
}
