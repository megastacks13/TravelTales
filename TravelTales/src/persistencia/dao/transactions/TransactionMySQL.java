package persistencia.dao.transactions;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;

import utils.SQLPersonalInfo;

public class TransactionMySQL implements Transaction{
	private Connection con;
	private final String path = SQLPersonalInfo.URL;
	private String username = SQLPersonalInfo.USERNAME;
	private String password = SQLPersonalInfo.PASSWORD;
	
	@Override
	public void start() {
		try {
			//DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			con = DriverManager.getConnection(path,username,password);
			con.setAutoCommit(false);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void commit() {
		try {
			if(con != null)
			{
				con.commit();
				con.close();
				TransactionManager.getInstance().eliminaTransaccion();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void rollback() {
		try {
			if(con != null)
			{
				con.rollback();
				con.close();
				TransactionManager.getInstance().eliminaTransaccion();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public Object getResource() throws SQLException {

		if(con == null)
		{
			throw new SQLException("la conexion no se ha realizado todavia (con == null");
		}
		
		return con;
			
		
	}

}
