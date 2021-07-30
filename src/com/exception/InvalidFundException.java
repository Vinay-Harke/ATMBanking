package com.exception;

//Invalid Fund Exception
public class InvalidFundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	//parameterized constructor
	public InvalidFundException(String msg) {
		this.msg=msg;
	}
	public String toString() {
		return "Withdrawal Amount Is Higher Than Actual Balance [msg=" + msg + "]";
	}
}
