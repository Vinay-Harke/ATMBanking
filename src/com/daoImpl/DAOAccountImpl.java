package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Account;
import com.dao.DAOAccountInterface;
import com.providers.DBconnectionProviders;

public class DAOAccountImpl implements DAOAccountInterface {
	//Create an connection with database 
	Connection con=DBconnectionProviders.getDBConnection();
	
	PreparedStatement pst;
	ResultSet rst;
	String sql=null;
	
	//insert data into table
	@Override
	public int insertAccountData(Account acc) throws SQLException {
		sql="insert into AccountDetails values(?,?,?)";
		pst=con.prepareStatement(sql);
		pst.setInt(1,acc.getAccNo());
		pst.setString(2,acc.getAccType());
		pst.setFloat(3,acc.getAccBal());
		int updatedCount=pst.executeUpdate(); //returns number of rows modified
		return updatedCount;
	}

	//account Details are fetched 
	@Override
	public Account getAccountDetails(int accNo) throws SQLException{
		sql="select * from AccountDetails where accNo=?";
		Account acc=null;
		pst=con.prepareStatement(sql);
		pst.setInt(1,accNo);
		rst=pst.executeQuery();//returns result set 
		if(rst.next()) {
			acc=new Account(rst.getString(2),rst.getFloat(3),accNo);
		}
		return acc;
	}
	
	//Account Balance Is fetched
	@Override
	public float getAccountBalance(int accNo) throws SQLException{
		
		sql="select * from AccountDetails where accNo=?";
		pst=con.prepareStatement(sql);
		pst.setInt(1,accNo);
		rst=pst.executeQuery();
		if(rst.next())
			return rst.getFloat(3);
		return 0.0f;
	}
	
	//Account Balance is updated
	@Override
	public int updateAccountBalance(int accNo, float amt) throws SQLException {
		// TODO Auto-generated method stub
		int updatedCount=0;
		sql="update AccountDetails set accBal=? where accNo=?";
		pst=con.prepareStatement(sql);
		pst.setFloat(1,amt);
		pst.setInt(2,accNo);
		updatedCount=pst.executeUpdate();
		return updatedCount;
	}

	//this function returns an highest account Number and using this highest account number a new account number is created by incrementing this accNo	
	public int getLastAccountNo() throws SQLException {
		sql="select accNo from AccountDetails order by accNo DESC";
		pst=con.prepareStatement(sql);
		rst=pst.executeQuery();
		if(rst.next()) {
			return rst.getInt(1);
		}
		return 0;
	}

	
}
