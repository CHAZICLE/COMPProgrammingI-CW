package me.charles.programmingcw2;

import java.util.HashMap;
import java.util.Map;

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

	public int getCurrentMonth() {
		return currentMonth;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public Product[] getProductsRange() {
		return (Product[]) productsRange.values().toArray();
	}

	public CustomerDetailsList getCustomerDetailsList() {
		return customerDetailsList;
	}

	public PurchaseOrderList[] getPurchaseHistory() {
		return purchaseHistory;
	}

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
		if (currentMonth >= 13)
			currentMonth = 1;
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
