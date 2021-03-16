package main.javafx.models;

import java.util.Observable;

public class ClientsModel extends Observable {
	private Integer id;
	private String phone;
	private AddressesModel addresses;
	private String name;
	private String tradingName;
	private String cpfCnpj;
	private char sex;
	
	public String toString() {
		return name;
	}
	

	public AddressesModel getAddresses() {
		return addresses;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setAddresses(AddressesModel addresses) {
		this.addresses = addresses;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;

	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
}
