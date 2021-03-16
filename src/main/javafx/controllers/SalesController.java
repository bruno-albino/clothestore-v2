package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.SalesDAO;
import main.javafx.models.ClientsModel;
import main.javafx.models.PaymentConditionsModel;
import main.javafx.models.PaymentFormsModel;
import main.javafx.models.SalesModel;

public class SalesController {
	private SalesDAO dao;
	
	public SalesController() {
		this.dao = new SalesDAO();
	}

	public List<SalesModel> index() throws Exception {
		List<SalesModel> sales = new ArrayList<SalesModel>();

		ResultSet rdr = this.dao.read();
		while (rdr.next()) {
			SalesModel sale = this.populate(rdr);
			sales.add(sale);
		}

		if (!rdr.isClosed()) {
			rdr.close();
		}

		return sales;
	}

	public SalesModel show(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(SalesModel obj) throws Exception {
		this.dao.create(obj);
	}

	public void update(SalesModel obj) throws Exception {
		this.dao.update(obj);
	}

	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub

	}
	
	private SalesModel populate(ResultSet reader) throws SQLException {
		SalesModel sale = new SalesModel();
		ClientsModel client = new ClientsModel();
		
    	client.setId(reader.getInt(14));
    	client.setCpfCnpj(reader.getString(17));
    	client.setName(reader.getString(15));
    	client.setPhone(reader.getString(18));
    	client.setSex(reader.getString(19).charAt(0));
    	client.setTradingName(reader.getString(16));
    	
    	PaymentConditionsModel conditionsOfPayment = new PaymentConditionsModel();
    	conditionsOfPayment.setId(reader.getInt(11));
    	conditionsOfPayment.setDescription(reader.getString(12));
    	conditionsOfPayment.setInstallments(reader.getInt(13));
    	
    	PaymentFormsModel paymentForm = new PaymentFormsModel();
    	paymentForm.setId(reader.getInt(9));
    	paymentForm.setDescription(reader.getString(10));
    	
    	sale.setId(reader.getInt(1));
    	sale.setDate(reader.getString(5));
    	sale.setDiscount(reader.getFloat(7));
    	sale.setSubTotal(reader.getFloat(8));
    	sale.setTotal(reader.getFloat(6));
    	
    	sale.setClient(client);
    	sale.setPaymentForms(paymentForm);
    	sale.setPaymentConditions(conditionsOfPayment);
    	
    	return sale;
	}
}
