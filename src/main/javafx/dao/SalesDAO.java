package main.javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.javafx.config.MySQLConnectionSingleton;
import main.javafx.models.SaleProductModel;
import main.javafx.models.SalesModel;

public class SalesDAO implements IDAO<SalesModel> {
	private Connection connection;
	
	public SalesDAO() {
		this.connection = MySQLConnectionSingleton.getInstance().getConnection();			
	}

	public SalesModel show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(SalesModel sale) throws Exception {
		String query = "INSERT INTO sales(clientId, formOfPaymentId, "
				+ "condOfPaymentId, date, amount, discount, subTotal) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = this.connection.prepareStatement(query);
	
		stmt.setInt(1,  sale.getClient().getId());
		stmt.setInt(2, sale.getFormsOfPayment().getId());
		stmt.setInt(3, sale.getConditionsOfPayment().getId());
		stmt.setString(4, sale.getDate());
		stmt.setFloat(5, sale.getTotal());
		stmt.setFloat(6, sale.getDiscount());
		stmt.setFloat(7, sale.getSubTotal());
		stmt.execute(query, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		
		if(generatedKeys.next()) {
			int saleId = (int) generatedKeys.getLong(1);
			sale.setId(saleId);
//			SalesItemsBus salesItemsBus = new SalesItemsBus();
//			Iterator<SaleProductModel> it = sale.getSaleProducts().iterator();
//			while(it.hasNext()) {
//				SaleProductModel saleProduct = it.next();
//				saleProduct.setSale(sale);s
//				salesItemsBus.create(saleProduct);
//			}
		}
	}

	@Override
	public void update(SalesModel obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet read() throws Exception {
		String query = "SELECT * FROM ClotheStore.Sales s "
				+ "JOIN FormsOfPayment f ON f.id = s.formOfPaymentId "
				+ "JOIN ConditionsOfPayment c ON c.id = s.condOfPaymentId "
				+ "JOIN Clients cli ON cli.id = s.clientId";
		PreparedStatement stmt = this.connection.prepareStatement(query);
		
		return stmt.executeQuery();
	}

	@Override
	public void delete(SalesModel obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
