package persistencia.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/TravelTales";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConection() {
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
