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

	public OrderDate getDateOrdered() {
		return orderDate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public int getCustomerDiscount() {
		return customerDiscount;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getDiscountedPrice() {
		// Percentage discount?
		return (product.getPricePerUnit() * quantity * (100 - getCustomerDiscount())) / 100;
	}

	public double getFullPrice() {
		return product.getPricePerUnit() * quantity;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("PurchaseOrder(orderDate=").append(orderDate).append(",customerDiscount=").append(customerDiscount).append(",product=").append(product).append(",quantity=").append(quantity).append(")").toString();
	}
}
