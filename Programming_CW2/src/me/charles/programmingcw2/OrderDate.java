package me.charles.programmingcw2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		String[] dateParams = date.split("/");
		int day = Integer.parseInt(dateParams[0]);
		int month = Integer.parseInt(dateParams[1]);
		int year = Integer.parseInt(dateParams[2]);
		if(month>12 || month<=0)
			throw new IllegalDateFormatException("Invalid month");
		int monthDays = 31;
		switch(month)
		{
		case 9://Sep
		case 4://Apr
		case 6://Jun
		case 11://Nov
			monthDays = 30;
			break;
		case 2://Feb
			monthDays = (year%4==0 && year%100!=0 || year%400==0) ? 29 : 28;
			break;
		}
		if(day>monthDays)
			throw new IllegalDateFormatException("There arn't that many days in that month");
		this.date = date;
	}

	public String getDate() {
		return date;
	}
	
	public Date getDateObject()
	{
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			// Shouldn't happen, validated in constructor
			return null;
		}
	}

	@Override
	public String toString() {
		return date;
	}
}
