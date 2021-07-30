package com.exception;


// for throwing Invaid Account Number Exception
public class InvalidAccountNoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	//parameterized constructor
	public InvalidAccountNoException(String msg) {
		this.msg=msg;
	}
	public String toString() {
		return "Invallid Account Number  [msg=" + msg + "]";
	}
}
