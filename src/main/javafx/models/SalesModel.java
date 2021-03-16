package main.javafx.models;

import java.util.ArrayList;
import java.util.List;

public class SalesModel {
	private Integer id;
	private PaymentFormsModel paymentForms;
	private PaymentConditionsModel paymentConditions;
	private ClientsModel client;
	private List<SaleProductModel> saleProducts = new ArrayList<>();
	
	private String date;
	private float subTotal;
	private float discount;
	private float total;
	
	public PaymentFormsModel getFormsOfPayment() {
		return paymentForms;
	}
	public void setPaymentForms(PaymentFormsModel paymentForms) {
		this.paymentForms = paymentForms;
	}
	public PaymentConditionsModel getConditionsOfPayment() {
		return paymentConditions;
	}
	public void setPaymentConditions(PaymentConditionsModel paymentConditions) {
		this.paymentConditions = paymentConditions;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
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
	public ClientsModel getClient() {
		return client;
	}
	public void setClient(ClientsModel client) {
		this.client = client;
	}
	
	public String toString() {
		return "Cliente: " + getClient().getName() + 
				" | Total: " + this.getTotal() + 
				" | Forma de Pagamento: " + this.paymentForms.getDescription() + 
				" | Condição de Pagamento: " + this.paymentConditions.getDescription();
	}
	public List<SaleProductModel> getSaleProducts() {
		return saleProducts;
	}
	public void setSaleProducts(List<SaleProductModel> saleProducts) {
		this.saleProducts = saleProducts;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
