package me.charles.programmingcw2;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import me.charles.programmingcw2.exceptions.CustomerNotFoundException;
import me.charles.programmingcw2.exceptions.IllegalCustomerIDException;
import me.charles.programmingcw2.exceptions.IncorrectPurchaseOrderException;

public class SportsEquipmentDemo {
	public static void main(String[] args) {
		// Part I
		LinkedList<Product> tempProducts = new LinkedList<>();
		try (InputFileData fir = new InputFileData("productData.txt")) {
			try {
				int count = Integer.parseInt(fir.next());
				String[] codes = fir.next().split("#");
				String[] pricesPerUnit = fir.next().split("#");
				for (int i = 0; i < count; i++) {
					tempProducts.add(new Product(codes[i], (int) (100 * Double.parseDouble(pricesPerUnit[i]))));
				}
			} catch (Exception e) {
				System.err.println("Error reading product data");
				e.printStackTrace();
				System.exit(1);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't open productData.txt");
			System.exit(1);
		}
		Product[] products = tempProducts.toArray(new Product[tempProducts.size()]);
		// Part II
		SportsEquipmentSupplier ses = new SportsEquipmentSupplier(1, 2013, products);
		// Part III
		try (InputFileData fir = new InputFileData("CustomerData.txt")) {
			for (String line : fir) {
				String[] customerDetails = line.split("/");
				try {
					if (customerDetails.length == 7) {
						// 0:id,1:title,2:initials,3:surname,4:address1,5:address,6:address
						ses.addNewCustomer(new PrivateCustomerDetails(customerDetails[0], new Name(customerDetails[1], customerDetails[2], customerDetails[3]), new Address(customerDetails[4], customerDetails[5], customerDetails[6]), customerDetails[0].substring(1, 3), 0));
					} else {
						// 0:id,1:name,2:address,3:address,4:address,5:discount
						ses.addNewCustomer(new SportsClubDetails(customerDetails[0], customerDetails[1], new Address(customerDetails[2], customerDetails[3], customerDetails[4]), customerDetails[0].substring(1, 3), Integer.parseInt(customerDetails[5]), 0));
					}
				} catch (IllegalCustomerIDException e) {
					e.printStackTrace();
					System.exit(1);
					return;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't open productData.txt");
			System.exit(1);
			return;
		}
		// Part IV
		try (InputFileData fir = new InputFileData("PurchaseOrderData.txt")) {
			for (String line : fir) {
				String[] data = line.split("#");
				try {
					// 0:dateStr, 1:customerID, 2:productCode, 3:qty
					boolean monthEnd = data[3].endsWith("@");
					if (monthEnd)
						data[3] = data[3].substring(0, data[3].length() - 1);
					ses.addNewPurchaseOrder(data[0], data[1], data[2], Integer.parseInt(data[3]));
					CustomerDetails cus = ses.getCustomerDetailsList().findCustomer(data[1]);
					if (cus instanceof PrivateCustomerDetails) {
						PrivateCustomerDetails pcd = (PrivateCustomerDetails) cus;
						System.out.println("Customer " + pcd.getCustomerID() + " has discount " + pcd.getDiscount() + " after purchase");
					}
					if (monthEnd)
						ses.updateMonth();
				} catch (NumberFormatException | IncorrectPurchaseOrderException | CustomerNotFoundException e) {
					System.err.println("Error purchasing product");
					e.printStackTrace();
					// System.exit(1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public String toString() {
		// This is just getting silly
		return new StringBuffer().append("SportsEquipmentDemo").toString();
	}
}
