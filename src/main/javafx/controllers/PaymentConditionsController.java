package main.javafx.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.dao.PaymentConditionsDAO;
import main.javafx.models.PaymentConditionsModel;

public class PaymentConditionsController {
	private PaymentConditionsDAO dao;

	public PaymentConditionsController() {
		this.dao = new PaymentConditionsDAO();
	}

	public List<PaymentConditionsModel> index() throws Exception {
		List<PaymentConditionsModel> paymentConditions = new ArrayList<PaymentConditionsModel>();

		ResultSet reader = this.dao.read();

		while (reader.next()) {
			PaymentConditionsModel formOfPayment = this.populate(reader);
			paymentConditions.add(formOfPayment);
		}

		if (!reader.isClosed()) {
			reader.close();
		}
		return paymentConditions;
	}

	private PaymentConditionsModel populate(ResultSet reader) throws SQLException {
		PaymentConditionsModel formOfPayment = new PaymentConditionsModel();
		formOfPayment.setDescription(reader.getString("description"));
		formOfPayment.setId(reader.getInt("id"));
		return formOfPayment;
	}

	public PaymentConditionsModel show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(PaymentConditionsModel formOfPayment) throws Exception {
//		String query = "INSERT INTO ClotheStore.FormsOfPayment(description) VALUES(?)";
//
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1, formOfPayment.getDescription());
//		DBConnection.executePreparedStatement();
	}

	public void update(PaymentConditionsModel formOfPayment) throws SQLException {
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
