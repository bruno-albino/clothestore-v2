package main.javafx.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionSingleton {
	Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException;
}
