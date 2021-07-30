package com.exception;

//Invalid SecurityQuestion  Exception
public class InvalidSecurityQuesException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	//parameterized constructor
	public InvalidSecurityQuesException(String msg) {
		super();
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "InvalidSecurityQuesException [msg=" + msg + "]";
	}
}
