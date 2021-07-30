package com.exception;

//Invalid Password Exception
public class InvalidPasswordException  extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	//parameterized constructor
	public InvalidPasswordException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "InvalidPasswordException [msg=" + msg + "]";
	}
	
}
