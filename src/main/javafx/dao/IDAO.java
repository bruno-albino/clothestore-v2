package main.javafx.dao;
import java.sql.ResultSet;
import java.util.List;

public interface IDAO<T>
{
	void create(T obj) throws Exception;
	
	ResultSet read() throws Exception;

    void update(T obj) throws Exception;

    void delete(T obj) throws Exception;
}