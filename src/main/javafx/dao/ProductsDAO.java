package main.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.javafx.config.MySQLConnectionSingleton;
import main.javafx.models.ProductsModel;

public class ProductsDAO implements IDAO<ProductsModel> {
	private Connection connection;

	public ProductsDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();
	}

	@Override
	public void create(ProductsModel product) throws Exception {
		String sql = "INSERT INTO ClotheStore.Products(categoryId, "
				+ "providerId, description, sizeId, materialId, price) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		stmt = this.connection.prepareStatement(sql);

		stmt.setInt(1, product.getCategoryId());
		stmt.setInt(2, product.getProviderId());
		stmt.setString(3, product.getDescription());
		stmt.setInt(4, product.getSizeId());
		stmt.setInt(5, product.getMaterialId());
		stmt.setFloat(6, product.getPrice());
		stmt.execute();
	}

	@Override
	public ResultSet read() throws Exception {
//		String sql = "SELECT * FROM ClotheStore.Products p " + "JOIN Materials a ON a.id = p.materialId "
//				+ "JOIN Providers pro ON pro.id = p.providerId " + "JOIN Sizes s ON s.id = p.sizeId "
//				+ "JOIN Categories c ON c.id = p.categoryId ";
		String sql = "SELECT * FROM ClotheStore.Products";

		PreparedStatement stmt;
		stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		return stmt.executeQuery(sql);
	}

	@Override
	public void update(ProductsModel product) throws Exception {
		String sql = "UPDATE ClotheStore.Products SET categoryId = ?, "
				+ "providerId = ?, description = ?, sizeId = ?, materialId = ?, "
				+ "price = ? WHERE id = ?";
		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setInt(1,  product.getCategoryId());
		stmt.setInt(2,  product.getProviderId());
		stmt.setString(3,  product.getDescription());
		stmt.setInt(4,  product.getSizeId());
		stmt.setInt(5,  product.getMaterialId());
		stmt.setFloat(6, product.getPrice());
		stmt.setInt(7,  product.getId());
		stmt.execute();

	}

	@Override
	public void delete(ProductsModel obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
