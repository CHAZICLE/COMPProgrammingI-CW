package me.charles.programmingcw2;

public class IllegalCustomerIDException extends Exception{
	private static final long serialVersionUID = 5428038101347380486L;
	public IllegalCustomerIDException(String message) {
		super(message);
	}
}
