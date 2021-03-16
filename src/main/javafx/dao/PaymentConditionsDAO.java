package main.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.config.MySQLConnectionSingleton;
import main.javafx.models.PaymentConditionsModel;

public class PaymentConditionsDAO implements IDAO<PaymentConditionsModel> {
	private Connection connection;

	public PaymentConditionsDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();
	}

	@Override
	public void create(PaymentConditionsModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet read() throws Exception {
		String query = "SELECT * FROM ClotheStore.ConditionsOfPayment";
		PreparedStatement stmt = this.connection.prepareStatement(query);
		return stmt.executeQuery();
	}

	@Override
	public void update(PaymentConditionsModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PaymentConditionsModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
//	@Override
//	public List<ConditionsOfPayment> index() throws Exception {
//		String query = "SELECT * FROM ClotheStore.ConditionsOfPayment";
//        List<ConditionsOfPayment> conditionsOfPayment = new ArrayList<ConditionsOfPayment>();
//
//        ResultSet reader = DBConnection.executeReader(query);
//        
//        while(reader.next()) {
//        	ConditionsOfPayment condition = new ConditionsOfPayment();
//        	condition.setDescription(reader.getString("description"));
//        	condition.id = reader.getInt("id");
//        	condition.setInstallments(reader.getInt("installments"));
//        	
//        	conditionsOfPayment.add(condition);
//        }
//
//        if(!reader.isClosed())
//        {
//            reader.close();
//        }
//
//        return conditionsOfPayment;
//	}
//
//	@Override
//	public ConditionsOfPayment show(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void create(ConditionsOfPayment conditionOfPayment) throws Exception {
//		String query = "INSERT INTO ClotheStore.ConditionsOfPayment(description, installments) VALUES(?, ?)";
//		
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1,  conditionOfPayment.getDescription());
//		DBConnection.setInt(2,  conditionOfPayment.getInstallments());
//		DBConnection.executePreparedStatement();
//	}
//
//	@Override
//	public void update(int id, ConditionsOfPayment conditionOfPayment) throws SQLException {
//		String query = "UPDATE ClotheStore.ConditionsOfPayment SET description = ?, installments = ? WHERE id = ?";
//
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1,  conditionOfPayment.getDescription());
//		DBConnection.setInt(2,  conditionOfPayment.getInstallments());
//		DBConnection.setInt(3,  id);
//		DBConnection.executePreparedStatement();
//	}
//
//	@Override
//	public void delete(int id) {
//		// TODO Auto-generated method stub
//		
//	}

}
