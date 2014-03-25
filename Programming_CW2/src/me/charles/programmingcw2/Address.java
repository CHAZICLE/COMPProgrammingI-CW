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
	/**
	 * Creates an immutable address
	 */
	public Address(String street, String city, String postcode) {
		this.street = street;
		this.city = city;
		this.postcode = postcode;
	}
	/**
	 * Creates an address copying fields from a given one
	 * @param other The address to duplicate
	 */
	public Address(Address other)
	{
		this.street = other.street;
		this.city = other.city;
		this.postcode = other.postcode;
	}
	/**
	 * @return The house number and street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @return The city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return The postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Address(").append(street).append(",").append(city).append(",").append(postcode).append(")").toString();
	}
}
