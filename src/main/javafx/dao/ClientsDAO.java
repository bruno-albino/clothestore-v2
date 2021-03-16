package main.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.javafx.config.MySQLConnectionSingleton;
import main.javafx.models.AddressesModel;
import main.javafx.models.ClientsModel;

public class ClientsDAO implements IDAO<ClientsModel>{
	private Connection connection;
	
	public ClientsDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();			
	}

	@Override
	public void create(ClientsModel client) throws Exception {
		String sql = "INSERT INTO ClotheStore.Clients(name, tradingName, cpfCnpj, phone, sex) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, client.getName());
			stmt.setString(2,  client.getTradingName());
			stmt.setString(3, client.getCpfCnpj());
			stmt.setString(4, client.getPhone());
			stmt.setString(5, Character.toString(client.getSex()));
			stmt.execute();
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				int clientId = (int) generatedKeys.getLong(1);
				AddressesModel address = client.getAddresses();
				address.setClientId(clientId);
				
				AddressesDAO addressesDAO = new AddressesDAO();
				addressesDAO.create(address);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResultSet read() throws Exception {
		String sql = "SELECT * FROM ClotheStore.Clients c JOIN ClotheStore.Addresses a ON c.id = a.clientId";
        PreparedStatement stmt;
        
		try {
			stmt = this.connection.prepareStatement(sql);
			return stmt.executeQuery(sql);
        } catch(SQLException e) {
        	throw new RuntimeException(e);
        }

	}

	@Override
	public void update(ClientsModel client) throws Exception {
		String sql = "UPDATE ClotheStore.Clients SET name = ?, "
				+ "tradingName = ?,"
				+ "cpfCnpj = ?,"
				+ "phone = ?,"
				+ "sex = ?"
				+ "WHERE id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, client.getName());
			stmt.setString(2,  client.getTradingName());
			stmt.setString(3, client.getCpfCnpj());
			stmt.setString(4, client.getPhone());
			stmt.setString(5, String.valueOf((client.getSex())));
			stmt.setLong(6,  client.getId());
			stmt.execute();
			
			AddressesModel address = client.getAddresses();
			AddressesDAO addressDAO = new AddressesDAO();
			addressDAO.update(address);
			
		} catch(SQLException e) {
        	throw new RuntimeException(e);
        }
		
	}

	@Override
	public void delete(ClientsModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
