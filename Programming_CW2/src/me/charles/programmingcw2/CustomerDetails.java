package me.charles.programmingcw2;

import java.util.Arrays;
import java.util.List;

public abstract class CustomerDetails {
	private final String customerID;
	private Address address;
	private final String regionalCode;
	private double totalFullPriceValue;
	private static final List<String> validRegionCodes = Arrays.asList(new String[] { "SC", "WA", "NI", "NE", "NW", "MI", "EA", "SE", "SW", "GL" });

	public CustomerDetails(String customerID, Address address, String regionalCode, double totalFullPriceValue) throws IllegalCustomerIDException {
		// General validation of customerID
		if (!customerID.matches("[A-Z]{3}-[0-9]{4}"))
			throw new IllegalCustomerIDException("Customer IDs must be aaa-dddd, where a is a letter and d is a numerical digit");
		if (!validRegionCodes.contains(customerID.substring(1, 3)))
			throw new IllegalCustomerIDException("Unknown region code!");
		this.customerID = customerID;
		this.address = address;
		this.regionalCode = regionalCode;
		this.totalFullPriceValue = totalFullPriceValue;
	}

	public String getCustomerID() {
		return customerID;
	}

	public Address getAddress() {
		return address;
	}

	public String getRegionalCode() {
		return regionalCode;
	}

	public double getTotalFullPriceValue() {
		return totalFullPriceValue;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void resetTotalFullPriceValue() {
		this.totalFullPriceValue = 0;
	}

	public void setTotalFullPriceValue(double totalFullPriceValue) {
		this.totalFullPriceValue = totalFullPriceValue;
	}

	public abstract int getDiscount();

	@Override
	public String toString() {
		return new StringBuilder().append("CustomerDetails(customerID=").append(customerID).append(",address=").append(address).append(",regionalCode=").append(regionalCode).append(totalFullPriceValue).append(")").toString();
	}
}
