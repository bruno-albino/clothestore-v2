package main.javafx.models;

public class ProvidersModel {
	private Integer id;
	private String phone;
	private String companyName;
	private String tradingName;
	private String cnpj;
	private AddressesModel addresses;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTradingName() {
		return tradingName;
	}
	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public AddressesModel getAddresses() {
		return addresses;
	}
	public void setAddresses(AddressesModel addresses) {
		this.addresses = addresses;
	}
	
	public String toString() {
		return getTradingName();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
