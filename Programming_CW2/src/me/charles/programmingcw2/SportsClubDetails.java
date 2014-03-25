package me.charles.programmingcw2;

import me.charles.programmingcw2.exceptions.IllegalCustomerIDException;

/**
 * A class to represent a sports clubs details
 * 
 * @author charles
 * 
 */
public class SportsClubDetails extends CustomerDetails {
	private final String name;
	private int discount;

	public SportsClubDetails(String customerID, String name, Address address, String regionalCode, int discount, double totalFullPriceValue) throws IllegalCustomerIDException {
		super(customerID, address, regionalCode, totalFullPriceValue);
		if (customerID.charAt(0) != 'C')
			throw new IllegalCustomerIDException("Sports club details must start with the letter C");
		this.discount = discount;
		this.name = name;
	}

	/**
	 * @return the name of the club
	 */
	public String getClubName() {
		return name;
	}

	@Override
	public int getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount value for this sports club
	 * 
	 * @param discount
	 *            the new discount value
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("SportsClubDetails(name=").append(name).append(",discount=").append(discount).append(")").toString();
	}
}
