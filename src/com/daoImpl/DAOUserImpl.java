package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.bean.User;
import com.dao.DAOUserInterface;
import com.providers.DBconnectionProviders;

//DAOUser class 
public class DAOUserImpl implements DAOUserInterface {
	//establish connection with Oracle database 
	Connection con=DBconnectionProviders.getDBConnection();
	PreparedStatement pst;
	ResultSet rst;
	String sql=null;
	@Override
	
	//insert user datas
	public int insertUserData(User user1,int accNo) throws SQLException {
		// TODO Auto-generated method stub
		int updatedCount=0;
		sql="insert into UserDetails values(?,?,?,?,?)";
		pst=con.prepareStatement(sql);
		pst.setString(1,user1.getUserName());
		pst.setString(2,user1.getPassWord());
		pst.setString(3,user1.getSecurityQuestion());
		pst.setString(4,user1.getAnswer());
		pst.setInt(5,accNo);
		updatedCount=pst.executeUpdate();
		return updatedCount;
		
	}
	
	//checks for signing in 
	@Override
	public String checkSigninData(String userName, String passWord) throws SQLException {
		sql="select * from UserDetails where uName=?";
		pst=con.prepareStatement(sql);
		pst.setString(1,userName);
		rst=pst.executeQuery();
		if(rst.next())
			if(rst.getString(2).equals(passWord))
				return "PASS";
			else
				return "FAIL";
		return null;
	}

	//returns a new password for forgotten password
	@Override
	public String getPassword(String userName, String securityQuestion, String answer) throws SQLException {
		sql="select * from UserDetails where uName=?";
		pst=con.prepareStatement(sql);
		pst.setString(1,userName);
		rst=pst.executeQuery();
		if(rst.next()) {
			if(rst.getString(3).equals(securityQuestion))
				if(rst.getString(4).equals(answer))
					return rst.getString(2);
				else
					return "INVALID ANSWER";
			else
				return "FAIL";
		}
		return null;
	}
	
	//set an password which is given by end user using change password menu
	@Override
	public int setPassword(String userName, String passWord, String confirmPassword) throws SQLException {
		int updatedCount=0;
		sql="update UserDetails set uPass=? where uName=?";
		pst=con.prepareStatement(sql);
		pst.setString(1,confirmPassword);
		pst.setString(2,userName);
		updatedCount=pst.executeUpdate();
		return updatedCount;
	}
	
	//get an user name using accNo
	public String getUserName(int accNo) throws SQLException {
		sql="select * from UserDetails where accNo=?";
		pst=con.prepareStatement(sql);
		pst.setInt(1,accNo);
		rst=pst.executeQuery();
		if(rst.next()) {
			String userName=rst.getString(1);
			return userName;
		}
		return null;
	}
	
	//get an accNo using Username
	public int getAccountNo(String userName) throws SQLException{
		sql="select accNo from UserDetails where uname=?";
		pst=con.prepareStatement(sql);
		pst.setString(1,userName);
		rst=pst.executeQuery();
		if(rst.next()) {
			int accNo=rst.getInt(1);
			return accNo;
		}
		return 0;
	}
}
