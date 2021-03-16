package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.ProductsDAO;
import main.javafx.models.ProductsModel;

public class ProductsController {
	private ProductsDAO dao;

	public ProductsController() {
		this.dao = new ProductsDAO();
	}

	public List<ProductsModel> index() throws Exception {
		List<ProductsModel> products = new ArrayList<ProductsModel>();

		ResultSet rdr = this.dao.read();
		while (rdr.next()) {
			ProductsModel product = this.populate(rdr);
			products.add(product);
		}

		if (!rdr.isClosed()) {
			rdr.close();
		}

		return products;

	}

	public ProductsModel show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(ProductsModel product) throws Exception {
		this.dao.create(product);
	}

	public void update(ProductsModel product) throws Exception {
		this.dao.update(product);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}
	
	private ProductsModel populate(ResultSet reader) throws SQLException {
		ProductsModel product = new ProductsModel();
		product.setId(reader.getInt(1));
		product.setCategoryId(reader.getInt(2));
		product.setProviderId(reader.getInt(3));
		product.setDescription(reader.getString(4));
		product.setSizeId(reader.getInt(5));
		product.setMaterialId(reader.getInt(6));
		product.setPrice(reader.getFloat(7));
		return product;
	}

}
