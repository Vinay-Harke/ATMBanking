package com.exception;

//Invalid UserName Exception
public class InvalidUserNameException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	//parameterized constructor
	public InvalidUserNameException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "InvalidUserNameException :  [msg=" + msg + "]";
	}
	

}
