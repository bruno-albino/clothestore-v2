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
import main.javafx.models.ProvidersModel;

public class ProvidersDAO implements IDAO<ProvidersModel>{
	private Connection connection;
	
	public ProvidersDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();			
	}

	@Override
	public void create(ProvidersModel provider) throws Exception {
		String sql = "INSERT INTO ClotheStore.Providers(companyName, tradingName, cnpj, phone) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt;
		
		stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, provider.getCompanyName());
		stmt.setString(2, provider.getTradingName());
		stmt.setString(3, provider.getCnpj());
		stmt.setString(4, provider.getPhone());
		stmt.execute();

		ResultSet generatedKeys = stmt.getGeneratedKeys();

		if (generatedKeys.next()) {
			int providerId = (int) generatedKeys.getLong(1);
			AddressesModel address = provider.getAddresses();
			address.setProviderId(providerId);

			AddressesDAO addressesDAO = new AddressesDAO();
			addressesDAO.create(address);
		}
	}

	@Override
	public ResultSet read() throws Exception {
		String query = "SELECT * FROM ClotheStore.Providers c JOIN ClotheStore.Addresses a ON c.id = a.providerId";
		PreparedStatement stmt;
        
		try {
			stmt = this.connection.prepareStatement(query);
			return stmt.executeQuery(query);
        } catch(SQLException e) {
        	throw new RuntimeException(e);
        }

	}

	@Override
	public void update(ProvidersModel client) throws Exception {
//		String sql = "UPDATE ClotheStore.Clients SET name = ?, "
//				+ "tradingName = ?,"
//				+ "cpfCnpj = ?,"
//				+ "phone = ?,"
//				+ "sex = ?"
//				+ "WHERE id = ?";
//		PreparedStatement stmt;
//		
//		try {
//			stmt = this.connection.prepareStatement(sql);
//			stmt.setString(1, client.getName());
//			stmt.setString(2,  client.getTradingName());
//			stmt.setString(3, client.getCpfCnpj());
//			stmt.setString(4, client.getPhone());
//			stmt.setString(5, String.valueOf((client.getSex())));
//			stmt.setLong(6,  client.getId());
//			stmt.execute();
//			
//			AddressesModel address = client.getAddresses();
//			AddressesDAO addressDAO = new AddressesDAO();
//			addressDAO.update(address);
//			
//		} catch(SQLException e) {
//        	throw new RuntimeException(e);
//        }
		
	}

	@Override
	public void delete(ProvidersModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
