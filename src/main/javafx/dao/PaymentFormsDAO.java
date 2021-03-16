package main.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.javafx.config.MySQLConnectionSingleton;
import main.javafx.models.PaymentFormsModel;


public class PaymentFormsDAO implements IDAO<PaymentFormsModel> {
	private Connection connection;

	public PaymentFormsDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();
	}
	
	@Override
	public ResultSet read() throws Exception {
		String query = "SELECT * FROM ClotheStore.FormsOfPayment";
        PreparedStatement stmt = this.connection.prepareStatement(query);
        return stmt.executeQuery();
//        List<PaymentFormsModel> formsOfPayment = new ArrayList<PaymentFormsModel>();
        
//        while(reader.next()) {
//        	PaymentFormsModel formOfPayment = new PaymentFormsModel();
//        	formOfPayment.setDescription(reader.getString("description"));
//        	formOfPayment.id = reader.getInt("id");
//        	
//        	formsOfPayment.add(formOfPayment);
//        }
//
//        if(!reader.isClosed())
//        {
//            reader.close();
//        }
//        return formsOfPayment;
	}

	public PaymentFormsModel show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(PaymentFormsModel formOfPayment) throws Exception {
//		String query = "INSERT INTO ClotheStore.PaymentFormsModel(description) VALUES(?)";
//		
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1,  formOfPayment.getDescription());
//		DBConnection.executePreparedStatement();
	}

	@Override
	public void update(PaymentFormsModel formOfPayment) throws SQLException {
//		String query = "UPDATE ClotheStore.PaymentFormsModel SET description = ? WHERE id = ?";
//
//		DBConnection.prepareStatement(query);
//		DBConnection.setString(1,  formOfPayment.getDescription());
//		DBConnection.setInt(2,  id);
//		DBConnection.executePreparedStatement();
	}


	@Override
	public void delete(PaymentFormsModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
