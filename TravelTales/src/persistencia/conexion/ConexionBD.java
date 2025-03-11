package persistencia.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.SQLPersonalInfo;

public class ConexionBD {
    // Datos de conexión
    private static final String URL = SQLPersonalInfo.URL;
    private static final String USERNAME = SQLPersonalInfo.USERNAME;
    private static final String PASSWORD = SQLPersonalInfo.PASSWORD;

    public static Connection getConnection() {
    		try {
    			//DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
    			Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
    			con.setAutoCommit(false);
    			return con;
    		}catch(SQLException e)
    		{
    			e.printStackTrace();
    		}
    		
        return null;
    }

}
