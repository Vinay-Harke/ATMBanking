package com.bean;

public class User {
	
	//instance variables
	private String userName,passWord,securityQuestion,answer;
	
	//parameterized constructor of class User
	public User(String userName,String passWord,String securityQuestion,String answer) {
		this.answer=answer;
		this.passWord=passWord;
		this.securityQuestion=securityQuestion;
		this.userName=userName;
	}
	
	//Getters and Setters of Instance Variables 
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	//toString method 
	@Override
	public String toString() {
		return "User [userName=" + userName + ", passWord=" + passWord + ", securityQuestion=" + securityQuestion
				+ ", answer=" + answer + "]";
	}
	
}
