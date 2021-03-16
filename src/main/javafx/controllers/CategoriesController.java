package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.CategoriesDAO;
import main.javafx.models.CategoriesModel;

public class CategoriesController {
	private CategoriesDAO dao;
	
	public CategoriesController() {
		this.dao = new CategoriesDAO();
	}
	
	public List<CategoriesModel> index() throws Exception {
		List<CategoriesModel> categories = new ArrayList<CategoriesModel>();

		ResultSet rdr = this.dao.read();
		while (rdr.next()) {
			CategoriesModel category = this.populate(rdr);
			categories.add(category);
		}

		if (!rdr.isClosed()) {
			rdr.close();
		}

		return categories;
	}

	public CategoriesModel show(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(CategoriesModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(CategoriesModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private CategoriesModel populate(ResultSet rs) throws SQLException {
		CategoriesModel category = new CategoriesModel();
    	category.setDescription(rs.getString("description"));
    	category.setId(rs.getInt("id"));
		return category;
	}
}
