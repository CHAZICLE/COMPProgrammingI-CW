package me.charles.programmingcw2.exceptions;

public class IncorrectPurchaseOrderException extends Exception {
	private static final long serialVersionUID = 8897932147963177788L;
	public IncorrectPurchaseOrderException(String message) {
		super(message);
	}
	public IncorrectPurchaseOrderException(String message, Exception handle) {
		super(message,handle);
	}
}
