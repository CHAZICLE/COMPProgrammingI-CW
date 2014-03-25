package me.charles.programmingcw2;

/**
 * A class to represent a sports club, holding its details
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

	public String getClubName() {
		return name;
	}

	@Override
	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("SportsClubDetails(name=").append(name).append(",discount=").append(discount).append(")").toString();
	}
}
