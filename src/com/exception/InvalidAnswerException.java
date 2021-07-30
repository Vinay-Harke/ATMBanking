package com.exception;

//Invalid Answer Exception 
public class InvalidAnswerException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	//constructor
	public InvalidAnswerException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "InvalidAnswerException [msg=" + msg + "]";
	}
	
}
