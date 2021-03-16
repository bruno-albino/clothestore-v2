package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.SizesDAO;
import main.javafx.models.SizesModel;

public class SizesController {
	private SizesDAO dao;
	
	public SizesController() {
		this.dao = new SizesDAO();
	}
	
	public List<SizesModel> index() throws Exception {
		List<SizesModel> sizes = new ArrayList<SizesModel>();

		ResultSet rdr = this.dao.read();
		while (rdr.next()) {
			SizesModel size = this.populate(rdr);
			sizes.add(size);
		}

		if (!rdr.isClosed()) {
			rdr.close();
		}

		return sizes;
	}

	public SizesModel show(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(SizesModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(SizesModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private SizesModel populate(ResultSet reader) throws SQLException {
		SizesModel size = new SizesModel();
		
    	size.setSize(reader.getString("size"));
    	size.setHeight(reader.getFloat("height"));
    	size.setWidth(reader.getFloat("width"));
    	size.setId(reader.getInt("id"));
    	
    	return size;
	}
}
