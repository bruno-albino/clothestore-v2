package main.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import main.javafx.config.MySQLConnectionSingleton;
import main.javafx.models.AddressesModel;

public class AddressesDAO implements IDAO<AddressesModel>{
	private Connection connection;
	
	public AddressesDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();			
	}
	
	@Override
	public void delete(AddressesModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet read() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void create(AddressesModel address) throws Exception {
		String sql = "INSERT INTO ClotheStore.Addresses(clientId, providerId, street, streetNumber, "
				+ "neighborhood, zipCode, city, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			if(address.getClientId() == null) {
				stmt.setNull(1, 0);
			} else {
				stmt.setLong(1, address.getClientId());
			}
			
			if(address.getProviderId() == null) {
				stmt.setNull(2, 0);
			} else {
				stmt.setLong(2, address.getProviderId());
			}
			
			stmt.setString(3,  address.getStreet());
			stmt.setString(4, address.getStreetNumber());
			stmt.setString(5, address.getNeighborhood());
			stmt.setString(6, address.getZipCode());
			stmt.setString(7, address.getCity());
			stmt.setString(8, address.getState());
			stmt.execute();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public void update(AddressesModel address) throws Exception {
		String sql = "UPDATE ClotheStore.Addresses SET street = ?,"
				+ "streetNumber = ?, neighborhood = ?, zipCode = ?, "
				+ "city = ?, "
				+ "state = ? "
				+ "WHERE id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1,  address.getStreet());
			stmt.setString(2, address.getStreetNumber());
			stmt.setString(3, address.getNeighborhood());
			stmt.setString(4, address.getZipCode());
			stmt.setString(5, address.getCity());
			stmt.setString(6, address.getState());
			stmt.setLong(7,  address.getId());
			stmt.execute();	
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
