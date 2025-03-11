package persistencia.dao.viaje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import modelo.TViaje;
import persistencia.dao.transactions.TransactionFactory;


public class DAOViajeImp implements DAOViaje{
	
	@Override
	public boolean startConnection() {
		return false;
	}

	@Override
	public boolean crearViaje(TViaje viaje) { //Falta añadirle por algun lado comprobarDatos
		boolean confirmacion = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
				
			con = (Connection) TransactionFactory.getInstance().newTransaction().getResource();
			
			pstmt = con.prepareStatement("INSERT INTO VIAJE (nombre_viaje, destino_viaje, fecha_inicio , fecha_fin) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, viaje.getNombre());
			pstmt.setString(2, viaje.getDestino());
			pstmt.setDate(3, Date.valueOf(viaje.getFechaIni()));
			pstmt.setDate(4, Date.valueOf(viaje.getFechaFin()));
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				confirmacion = true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				
				if(rs != null)rs.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return confirmacion;
	}

	@Override
	public boolean comprobarDatos(TViaje viaje) {
		boolean nuevo = true;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = (Connection) TransactionFactory.getInstance().newTransaction().getResource();
			pstmt = con.prepareStatement("SELECT* FROM VIAJE");
			rs = pstmt.executeQuery();
			while(rs.next() && nuevo)
			{
				TViaje v1 = new TViaje(rs.getInt("id"),rs.getString("nombre_viaje"), rs.getString("destino_viaje"),
						rs.getDate("fecha_inicio").toString(), rs.getDate("fecha_fin").toString());
				
				if(viaje.equals(v1))
					nuevo = false;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				
				if(rs != null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return nuevo;
	}

}
