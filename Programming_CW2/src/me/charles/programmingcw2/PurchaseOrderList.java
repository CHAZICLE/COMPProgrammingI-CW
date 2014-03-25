package me.charles.programmingcw2;

import java.util.LinkedList;
import java.util.List;

/**
 * A class to represent and maintain a list of purchase orders NOTE VERY unclear
 * what exactly it wants here
 * 
 * @author charles
 * 
 */
public class PurchaseOrderList {
	private List<PurchaseOrder> purchaseOrderList = new LinkedList<>();

	/**
	 * @return an array containing all the purchase orders in this list, if this
	 *         list is not empty, otherwise null.
	 */
	public PurchaseOrder[] getPurchaseOrders() {
		return purchaseOrderList.toArray(new PurchaseOrder[purchaseOrderList.size()]);
	}

	public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
		purchaseOrderList.add(purchaseOrder);
	}

	public void clear() {
		purchaseOrderList.clear();
	}

	@Override
	public String toString() {
		return new StringBuilder().append("PurchaseOrderList(#=").append(purchaseOrderList.size()).append(")").toString();
	}
}