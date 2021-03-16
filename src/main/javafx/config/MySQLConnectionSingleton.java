package main.javafx.config;

import java.sql.Connection;
import java.sql.DriverManager;

import main.javafx.utils.WarningMessage;

public final class MySQLConnectionSingleton implements IConnectionSingleton {
	private static MySQLConnectionSingleton instance = null;
	private static Connection connection = null;
	final private static String host = "localhost";
 	final private static String user = "root";
 	final private static String password = "masterkey";
 	
 	private MySQLConnectionSingleton() {
		System.out.println("Conectando...");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://" 
			+ host + 
			"/clothestore?useTimezone=true&serverTimezone=UTC&user=" 
			+ user + 
			"&password=" 
			+ password;
			
			connection = (Connection) DriverManager.getConnection(url);
			System.out.println("Connection opened");
		} catch(Exception e) {
			System.out.println(e.toString());
			WarningMessage.show("Ocorreu um erro inesperado. Por favor, reinicie o sistema.");
		}
 	}
 	
	public static MySQLConnectionSingleton getInstance() {
		if(instance == null) {
			instance = new MySQLConnectionSingleton();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
