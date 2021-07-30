package com.dao;

import java.sql.SQLException;

import com.bean.User;

//interface for Databse operations on UserDetails Table
public interface DAOUserInterface {
	int insertUserData(User user1,int accNo) throws SQLException;
	String checkSigninData(String userName,String passWord)throws SQLException;
	String getPassword(String userName,String securityQuestion,String answer) throws SQLException;
	int setPassword(String userName,String passWord,String confirmPassword)throws SQLException;
}
