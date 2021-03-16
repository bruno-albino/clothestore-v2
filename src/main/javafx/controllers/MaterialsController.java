package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.CategoriesDAO;
import main.javafx.dao.MaterialsDAO;
import main.javafx.models.MaterialsModel;

public class MaterialsController {
	private MaterialsDAO dao;
	
	public MaterialsController() {
		this.dao = new MaterialsDAO();
	}
	
	public List<MaterialsModel> index() throws Exception {
		List<MaterialsModel> materials = new ArrayList<MaterialsModel>();

		ResultSet rdr = this.dao.read();
		while (rdr.next()) {
			MaterialsModel material = this.populate(rdr);
			materials.add(material);
		}

		if (!rdr.isClosed()) {
			rdr.close();
		}

		return materials;
	}

	public MaterialsModel show(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(MaterialsModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(MaterialsModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private MaterialsModel populate(ResultSet rdr) throws SQLException {
		MaterialsModel material = new MaterialsModel();
    	
		material.setDescription(rdr.getString("description"));
    	material.setId(rdr.getInt("id"));
    	
    	return material;
	}
}
