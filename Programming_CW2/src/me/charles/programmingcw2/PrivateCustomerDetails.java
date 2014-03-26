package me.charles.programmingcw2;

import me.charles.programmingcw2.exceptions.IllegalCustomerIDException;

/**
 * A class to represent the details of a private customer
 * 
 * @author charles
 * 
 */
public class PrivateCustomerDetails extends CustomerDetails {
	private Name name;
	private static final int COMPANY_SPECIFIED_DISCOUNT_VALUE = 500;

	public PrivateCustomerDetails(String customerID, Name name, Address address, String regionalCode, double totalFullPriceValue) throws IllegalCustomerIDException {
		super(customerID, address, regionalCode, totalFullPriceValue);
		if (customerID.charAt(0) != 'P')
			throw new IllegalCustomerIDException("Private customer details must start with the letter P");
		this.name = name;
	}

	/**
	 * @return The name of the private customer
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Changes the name of the private customer
	 * 
	 * @param name
	 *            The new name
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * Calculates the discount for a private customer
	 */
	@Override
	public int getDiscount() {
		if (getTotalFullPriceValue() >= COMPANY_SPECIFIED_DISCOUNT_VALUE)
			return 15;
		return (int) (15 * getTotalFullPriceValue() / COMPANY_SPECIFIED_DISCOUNT_VALUE);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("PrivateCustomerDetails(name=").append(name).append("super=").append(super.toString()).append(")").toString();
	}
}
