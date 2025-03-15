package persistencia.dao.transactions;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction {
	public Connection start();
	public void commit();
	public void rollback();
	public Object getResource() throws SQLException;
}