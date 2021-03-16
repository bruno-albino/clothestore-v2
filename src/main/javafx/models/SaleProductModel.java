package main.javafx.models;

public class SaleProductModel extends ProductsModel {
	private int quantity;
	private float discount;
	private float total;
	
	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return "Produto: " + this.getDescription() + " | Quantidade: 1 | Total: " + this.getTotal();
	}
}
