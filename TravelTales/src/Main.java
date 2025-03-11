import java.sql.Connection;

import persistencia.conexion.ConexionBD;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hola Mundo!");
		Connection con = ConexionBD.getConnection();
		System.out.println(con == null);
	}
}
