package me.charles.programmingcw2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import me.charles.programmingcw2.exceptions.IllegalDateFormatException;

/**
 * A class to represent a date that an order was made
 * 
 * @author charles
 * 
 */
public class OrderDate {
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	private final String date;

	/**
	 * Creates a new OrderDate
	 * 
	 * @param date
	 *            must have the format "dd/mm/yy" otherwise
	 * @throws IllegalDateFormatException
	 */
	public OrderDate(String date) throws IllegalDateFormatException {
		if (!date.matches("[0-9]{2}/[0-9]{2}/[0-9]{2}"))
			throw new IllegalDateFormatException("Date must be in format dd/mm/yy");
		try {
			dateFormat.parse(date);
		} catch (ParseException e) {
			throw new IllegalDateFormatException("Invalid date");
		}
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return date;
	}
}
