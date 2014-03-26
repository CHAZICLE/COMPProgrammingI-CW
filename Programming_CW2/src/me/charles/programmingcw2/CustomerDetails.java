package me.charles.programmingcw2;

import java.util.Arrays;
import java.util.List;

import me.charles.programmingcw2.exceptions.IllegalCustomerIDException;

/**
 * An abstract class to define the basic attributes of a customer
 * 
 * @author charles
 * 
 */
public abstract class CustomerDetails {
	private final String customerID;
	private Address address;
	private String regionalCode;
	private double totalFullPriceValue;
	private static final List<String> validRegionCodes = Arrays.asList(new String[] { "SC", "WA", "NI", "NE", "NW", "MI", "EA", "SE", "SW", "GL" });

	/**
	 * @param customerID
	 *            The customer ID in the format aaa-dddd where a is a letter and
	 *            d is a digit
	 * @param address
	 *            The address of the customer
	 * @param regionalCode
	 *            The regional code of the customer
	 * @param totalFullPriceValue
	 *            The total full price value
	 * @throws IllegalCustomerIDException
	 *             If the customer ID is in the wrong format (see above)
	 */
	public CustomerDetails(String customerID, Address address, String regionalCode, double totalFullPriceValue) throws IllegalCustomerIDException {
		// General validation of customerID
		if (!customerID.matches("^[A-Z]{3}-[0-9]{4}$"))
			throw new IllegalCustomerIDException("Customer IDs must be aaa-dddd, where a is a letter and d is a numerical digit");
		if (!validRegionCodes.contains(customerID.substring(1, 3)))
			throw new IllegalCustomerIDException("Unknown region code!");
		this.customerID = customerID;
		this.address = address;
		this.regionalCode = regionalCode;
		this.totalFullPriceValue = totalFullPriceValue;
	}

	/**
	 * @return The customer ID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @return The customers address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @return The customers regional code
	 */
	public String getRegionalCode() {
		return regionalCode;
	}

	/**
	 * @return The total full price of all products purchased over the past year
	 */
	public double getTotalFullPriceValue() {
		return totalFullPriceValue;
	}

	/**
	 * Changes the address of the customer
	 * 
	 * @param address
	 *            The new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Changes the regional code of the customer
	 * 
	 * @param regionalCode
	 *            The new regional code
	 */
	public void setRegionalCode(String regionalCode) {
		this.regionalCode = regionalCode;
	}

	/**
	 * Resets the total full price value for the past 12 months to 0
	 */
	public void resetTotalFullPriceValue() {
		this.totalFullPriceValue = 0;
	}

	/**
	 * Sets the total full price value for the past 12 months
	 * 
	 * @param totalFullPriceValue
	 *            The new total full price value
	 */
	public void setTotalFullPriceValue(double totalFullPriceValue) {
		this.totalFullPriceValue = totalFullPriceValue;
	}

	/**
	 * @return The calculated discount for this customer
	 */
	public abstract int getDiscount();

	@Override
	public String toString() {
		return new StringBuilder().append("CustomerDetails(customerID=").append(customerID).append(",address=").append(address).append(",regionalCode=").append(regionalCode).append(totalFullPriceValue).append(")").toString();
	}
}
