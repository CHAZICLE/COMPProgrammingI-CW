package me.charles.programmingcw2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.charles.programmingcw2.exceptions.CustomerNotFoundException;

/**
 * A class to hold the customer details
 * 
 * @author charles
 * 
 */
public class CustomerDetailsList implements Iterable<CustomerDetails> {
	private Map<String, CustomerDetails> detailsCache = new HashMap<String, CustomerDetails>();

	/**
	 * Adds a new customer to the list
	 * 
	 * @param customerDetails
	 *            The customer to add
	 */
	public void add(CustomerDetails customerDetails) {
		detailsCache.put(customerDetails.getCustomerID(), customerDetails);
	}

	/**
	 * @return The number of customers in this list
	 */
	public int size() {
		return detailsCache.size();
	}

	/**
	 * 
	 * @param givenID
	 *            the ID of a customer
	 * @return the customer's details if found, exception thrown otherwise.
	 */
	public CustomerDetails findCustomer(String givenID) throws CustomerNotFoundException {
		CustomerDetails dets = detailsCache.get(givenID);
		if (dets == null)
			throw new CustomerNotFoundException("Couldn't find a customer with ID " + givenID);
		return dets;
	}

	@Override
	public Iterator<CustomerDetails> iterator() {
		return detailsCache.values().iterator();
	}

	@Override
	public String toString() {
		return new StringBuilder().append("CustomerDetailsList(#=").append(detailsCache.size()).append(")").toString();
	}
}
