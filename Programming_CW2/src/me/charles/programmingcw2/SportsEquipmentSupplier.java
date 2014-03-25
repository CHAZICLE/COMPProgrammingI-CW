package me.charles.programmingcw2;

import java.util.HashMap;
import java.util.Map;

import me.charles.programmingcw2.exceptions.CustomerNotFoundException;
import me.charles.programmingcw2.exceptions.IllegalDateFormatException;
import me.charles.programmingcw2.exceptions.IncorrectPurchaseOrderException;

public class SportsEquipmentSupplier {
	private int currentMonth;
	private int currentYear;
	private Map<String, Product> productsRange;
	private CustomerDetailsList customerDetailsList;
	private PurchaseOrderList[] purchaseHistory;

	public SportsEquipmentSupplier(int currentMonth, int currentYear, Product[] products) {
		this.currentMonth = currentMonth;
		this.currentYear = currentYear;
		this.productsRange = new HashMap<String, Product>();
		for (Product product : products)
			productsRange.put(product.getProductCode(), product);
		customerDetailsList = new CustomerDetailsList();
		purchaseHistory = new PurchaseOrderList[13];
	}

	/**
	 * @return The current month index from 1 to 12 (inclusive)
	 */
	public int getCurrentMonth() {
		return currentMonth;
	}

	/**
	 * @return The current year number
	 */
	public int getCurrentYear() {
		return currentYear;
	}

	/**
	 * @return The products offered by this sports equipment supplier
	 */
	public Product[] getProductsRange() {
		return (Product[]) productsRange.values().toArray();
	}

	/**
	 * @return The list of customer details
	 */
	public CustomerDetailsList getCustomerDetailsList() {
		return customerDetailsList;
	}

	/**
	 * Gets the purchase history for the current month and the previous 12
	 * months. The current month is at position 0, the previous months are
	 * stored in the array at an index that matches the month index. E.g. March
	 * would be 3
	 * 
	 * @return The purchase order list array
	 */
	public PurchaseOrderList[] getPurchaseHistory() {
		return purchaseHistory;
	}

	/**
	 * Adds a new customer to the customer list
	 * 
	 * @param details
	 *            The new customer to add
	 */
	public void addNewCustomer(CustomerDetails details) {
		customerDetailsList.add(details);
	}

	/**
	 * Generates a new purchase order record for the current month and updates
	 * record of purchasing customer
	 * 
	 * @param dateStr
	 *            a String with format "dd/mm/yy"
	 * @param customerID
	 *            must be the ID of a customer in the the company's customer
	 *            records
	 * @param productCode
	 *            must be in the company's current product range
	 * @param qty
	 *            the number of items required of the product
	 * @throws IncorrectPurchaseOrderException
	 */
	public void addNewPurchaseOrder(String dateStr, String customerID, String productCode, int qty) throws IncorrectPurchaseOrderException {
		try {
			Product product = productsRange.get(productCode);
			if (product == null)
				throw new IncorrectPurchaseOrderException("Unknown product");
			CustomerDetails customerDetails = customerDetailsList.findCustomer(customerID);
			PurchaseOrder purchaseOrder = new PurchaseOrder(new OrderDate(dateStr), customerDetails, product, qty);
			if (purchaseHistory[0] == null)
				purchaseHistory[0] = new PurchaseOrderList();
			purchaseHistory[0].addPurchaseOrder(purchaseOrder);
			customerDetails.setTotalFullPriceValue(customerDetails.getTotalFullPriceValue() + purchaseOrder.getFullPrice());
		} catch (IllegalDateFormatException e) {
			throw new IncorrectPurchaseOrderException("Invalid date", e);
		} catch (CustomerNotFoundException e) {
			throw new IncorrectPurchaseOrderException("Unknown customer", e);
		}

	}

	/**
	 * increments the index of the current month. 12 (December) is followed by 1
	 * (January). Updates this supplier's records as appropriate.
	 */
	public void updateMonth() {
		PurchaseOrderList tmp = purchaseHistory[currentMonth];
		purchaseHistory[currentMonth] = purchaseHistory[0];
		currentMonth++;
		if (currentMonth >= 13) {
			currentMonth = 1;
			currentYear++;
		}
		// Reuse existing list to save memory
		if (tmp != null)
			tmp.clear();
		else
			tmp = new PurchaseOrderList();
		purchaseHistory[0] = tmp;
		// Update the customer records
		for (CustomerDetails details : customerDetailsList) {
			int totalFullPriceValue = 0;
			for (int i = 1; i < purchaseHistory.length; i++) {
				PurchaseOrderList pal = purchaseHistory[i];
				if (pal == null)
					continue;
				for (PurchaseOrder po : pal.getPurchaseOrders()) {
					if (po.getCustomerID().equals(details.getCustomerID()))
						totalFullPriceValue += po.getFullPrice();
				}
			}
			details.setTotalFullPriceValue(totalFullPriceValue);
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append("SportsEquipment(currentMonth=").append(currentMonth).append(",currentYear=").append(currentYear).append(",#productsRange=").append(productsRange.size()).append(",#customerDetailsList=").append(customerDetailsList.size()).append(",#purchaseHistory=")
				.append(purchaseHistory.length).append(")").toString();
	}
}
