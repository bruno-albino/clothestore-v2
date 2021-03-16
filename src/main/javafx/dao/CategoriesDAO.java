package main.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.config.MySQLConnectionSingleton;
import main.javafx.models.CategoriesModel;

public class CategoriesDAO implements IDAO<CategoriesModel> {
	private Connection connection;
	
	public CategoriesDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();			
	}

	@Override
	public void create(CategoriesModel obj) throws Exception {
//		String sql = "INSERT INTO ClotheStore.Categories(description) VALUES(?)";
//		PreparedStatement stmt;
//		
//		try {
//			stmt = this.connection.prepareStatement(sql);
//			stmt.setString(1,  obj.getDescription());
//			stmt.execute();
//		} catch(SQLException e) {
//			throw new RuntimeException(e);
//		}
	}

	@Override
	public ResultSet read() throws Exception {
		String query = "SELECT * FROM ClotheStore.Categories";
		try {
	        PreparedStatement stmt = this.connection.prepareStatement(query);
        	return stmt.executeQuery();
        } catch(SQLException e) {
        	throw new RuntimeException(e);
        }
	}

	@Override
	public void update(CategoriesModel obj) throws Exception {
//		String sql = "UPDATE ClotheStore.Categories SET description=? WHERE id = ?";
//		PreparedStatement stmt;
//		
//		try {
//			stmt = this.connection.prepareStatement(sql);
//			stmt.setString(1,  obj.getDescription());
//			stmt.setInt(2,  obj.getId());
//			stmt.execute();
//		} catch(SQLException e) {
//			throw new RuntimeException(e);
//		}
	}

	@Override
	public void delete(CategoriesModel obj) throws Exception {
//		if(obj.getId() == null) {
//			throw new IllegalStateException("ID da categoria não pode ser nulo");
//		}
//		
//		String sql = "DELETE FROM ClotheStore.Categories WHERE id = ?";
//		PreparedStatement stmt;
//		
//		try {
//			stmt = this.connection.prepareStatement(sql);
//			stmt.setLong(1, obj.getId());
//			stmt.execute();
//		} catch(SQLException e) {
//			throw new RuntimeException(e);
//		}
	}
}
