package me.charles.programmingcw2;

public class InvalidProductCodeException extends Exception {
	private static final long serialVersionUID = -1360451214978397103L;
	public InvalidProductCodeException(String message) {
		super(message);
	}
}
