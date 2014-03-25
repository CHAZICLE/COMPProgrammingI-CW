package me.charles.programmingcw2.exceptions;

public class IllegalDateFormatException extends Exception{
	private static final long serialVersionUID = -3651910830895178212L;
	public IllegalDateFormatException(String message) {
		super(message);
	}
}
