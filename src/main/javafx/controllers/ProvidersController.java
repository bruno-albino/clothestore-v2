package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.ProvidersDAO;
import main.javafx.models.AddressesModel;
import main.javafx.models.ProvidersModel;

public class ProvidersController {
	private ProvidersDAO dao;

	public ProvidersController() {
		this.dao = new ProvidersDAO();
	}

	public List<ProvidersModel> index() throws Exception {
		List<ProvidersModel> providers = new ArrayList<ProvidersModel>();

		ResultSet rdr = this.dao.read();
		while (rdr.next()) {
			ProvidersModel provider = this.populate(rdr);
			providers.add(provider);
		}

		if (!rdr.isClosed()) {
			rdr.close();
		}

		return providers;

	}

	public ProvidersModel show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(ProvidersModel provider) throws Exception {
		this.dao.create(provider);
	}

	public void update(ProvidersModel provider) throws Exception {
//		String query = "UPDATE ClotheStore.Providers SET companyName = ?, " + "tradingName = ?," + "cnpj = ?,"
//				+ "phone = ? " + "WHERE id = ?";
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1, provider.getCompanyName());
//		DBConnection.setString(2, provider.getTradingName());
//		DBConnection.setString(3, provider.getCnpj());
//		DBConnection.setString(4, provider.getPhone());
//		DBConnection.setLong(5, id);
//		DBConnection.executePreparedStatement();
//
//		Addresses address = provider.getAddresses();
//		AddressesBus addressesBus = new AddressesBus();
//		addressesBus.update(address.id, address);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}
	
	private ProvidersModel populate(ResultSet reader) throws SQLException {
		ProvidersModel provider = new ProvidersModel();
    	provider.setId(reader.getInt("id"));
    	provider.setCompanyName(reader.getString("companyName"));
    	provider.setTradingName(reader.getString("tradingName"));
    	provider.setCnpj(reader.getString("cnpj"));
    	provider.setPhone(reader.getString("phone"));
    	
    	AddressesModel address = new AddressesModel();
    	address.setCity(reader.getString("city"));
    	address.setNeighborhood(reader.getString("neighborhood"));
    	address.setState(reader.getString("state"));
    	address.setStreet(reader.getString("street"));
    	address.setStreetNumber(reader.getString("streetNumber"));
    	address.setZipCode(reader.getString("zipcode"));
    	address.setId(reader.getInt(6));
    	address.setProviderId(provider.getId());
    	
    	provider.setAddresses(address);
    	return provider;
	}

}
