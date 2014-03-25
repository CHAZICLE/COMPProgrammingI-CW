package me.charles.programmingcw2;

/**
 * A class to represent an Address of a customer
 * 
 * @author charles
 */
public class Address {
	private final String street;
	private final String city;
	private final String postcode;

	public Address(String street, String city, String postcode) {
		this.street = street;
		this.city = city;
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getPostcode() {
		return postcode;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Address(").append(street).append(",").append(city).append(",").append(postcode).append(")").toString();
	}
}
