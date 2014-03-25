package me.charles.programmingcw2;

public class CustomerNotFoundException extends Exception{
	private static final long serialVersionUID = -1133141898290105634L;
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
