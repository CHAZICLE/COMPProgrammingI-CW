package me.charles.programmingcw2;

import me.charles.programmingcw2.exceptions.IllegalDateFormatException;

public class PurchaseOrder {
	private final OrderDate orderDate;
	private final String customerID;
	private final int customerDiscount;
	private final Product product;
	private final int quantity;

	public PurchaseOrder(OrderDate orderDate, CustomerDetails customer, Product product, int quantity) throws IllegalDateFormatException {
		this.orderDate = orderDate;
		this.customerID = customer.getCustomerID();
		this.product = product;
		this.quantity = quantity;
		customerDiscount = customer.getDiscount();
	}

	/**
	 * @return the date that this order was made
	 */
	public OrderDate getDateOrdered() {
		return orderDate;
	}

	/**
	 * @return The customer ID associated with this order
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @return the cached discount value
	 */
	public int getCustomerDiscount() {
		return customerDiscount;
	}

	/**
	 * @return the product that was ordered
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @return the quantity of the product that was ordered
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the final calculated price after the discount has been taken
	 */
	public double getDiscountedPrice() {
		return (getFullPrice() * (100 - getCustomerDiscount())) / 100;
	}

	/**
	 * @return the full price of this order without the discount
	 */
	public double getFullPrice() {
		return product.getPricePerUnit() * quantity;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("PurchaseOrder(orderDate=").append(orderDate).append(",customerDiscount=").append(customerDiscount).append(",product=").append(product).append(",quantity=").append(quantity).append(")").toString();
	}
}
