package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.PaymentFormsDAO;
import main.javafx.models.PaymentFormsModel;

public class PaymentFormsController {
	private PaymentFormsDAO dao;

	public PaymentFormsController() {
		this.dao = new PaymentFormsDAO();
	}

	public List<PaymentFormsModel> index() throws Exception {
		List<PaymentFormsModel> formsOfPayment = new ArrayList<PaymentFormsModel>();

		ResultSet reader = this.dao.read();

		while (reader.next()) {
			PaymentFormsModel formOfPayment = this.populate(reader);
			formsOfPayment.add(formOfPayment);
		}

		if (!reader.isClosed()) {
			reader.close();
		}
		return formsOfPayment;
	}

	private PaymentFormsModel populate(ResultSet reader) throws SQLException {
		PaymentFormsModel formOfPayment = new PaymentFormsModel();
		formOfPayment.setDescription(reader.getString("description"));
		formOfPayment.setId(reader.getInt("id"));
		return formOfPayment;
	}

	public PaymentFormsModel show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(PaymentFormsModel formOfPayment) throws Exception {
//		String query = "INSERT INTO ClotheStore.FormsOfPayment(description) VALUES(?)";
//
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1, formOfPayment.getDescription());
//		DBConnection.executePreparedStatement();
	}

	public void update(PaymentFormsModel formOfPayment) throws SQLException {
//		String query = "UPDATE ClotheStore.FormsOfPayment SET description = ? WHERE id = ?";
//
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1, formOfPayment.getDescription());
//		DBConnection.setInt(2, id);
//		DBConnection.executePreparedStatement();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
