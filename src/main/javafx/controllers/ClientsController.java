package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.ClientsDAO;
import main.javafx.models.AddressesModel;
import main.javafx.models.ClientsModel;

public class ClientsController {
	private ClientsDAO dao;
	
	public ClientsController() {
		this.dao = new ClientsDAO();
	}

	public List<ClientsModel> index() throws Exception {
		List<ClientsModel> clients = new ArrayList<ClientsModel>();

		ResultSet rdr = this.dao.read();
		while (rdr.next()) {
			ClientsModel client = this.populate(rdr);
			clients.add(client);
		}

		if (!rdr.isClosed()) {
			rdr.close();
		}

		return clients;
	}

	public ClientsModel show(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(ClientsModel obj) throws Exception {
		this.dao.create(obj);
	}

	public void update(ClientsModel obj) throws Exception {
		this.dao.update(obj);
	}

	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub

	}
	
	private ClientsModel populate(ResultSet rdr) throws SQLException {
		ClientsModel client = new ClientsModel();
		client.setId(rdr.getInt("id"));
		client.setName(rdr.getString("name"));
		client.setTradingName(rdr.getString("tradingName"));
		client.setCpfCnpj(rdr.getString("cpfCnpj"));
		client.setPhone(rdr.getString("phone"));
		client.setSex(rdr.getString("Sex").charAt(0));

		AddressesModel address = new AddressesModel();
		address.setCity(rdr.getString("city"));
		address.setNeighborhood(rdr.getString("neighborhood"));
		address.setState(rdr.getString("state"));
		address.setStreet(rdr.getString("street"));
		address.setStreetNumber(rdr.getString("streetNumber"));
		address.setZipCode(rdr.getString("zipcode"));
		address.setId(rdr.getInt(7));
		address.setClientId(client.getId());

		client.setAddresses(address);

		return client;
	}
}
