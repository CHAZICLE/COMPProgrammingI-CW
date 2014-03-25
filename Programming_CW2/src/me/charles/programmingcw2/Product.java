package me.charles.programmingcw2;

/**
 * A class to model a product
 * @author charles
 *
 */
public class Product {
	private final String productCode;
	private double pricePerUnit;

	public Product(String productCode, double pricePerUnit) throws InvalidProductCodeException {
		if (!productCode.matches("[a-zA-Z]{2}/[0-9]{3}"))
			throw new InvalidProductCodeException("Product codes must be in format aa/ddd where a in a letter and d in a numerical digit");
		this.productCode = productCode;
		this.pricePerUnit = pricePerUnit;
	}

	public String getProductCode() {
		return productCode;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Product(code=").append(productCode).append(",pricePerUnit=").append(pricePerUnit).append(")").toString();
	}
}
