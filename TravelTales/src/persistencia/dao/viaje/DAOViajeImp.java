package persistencia.dao.viaje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modelo.viaje.TViaje;
import persistencia.dao.transactions.TransactionFactory;
import persistencia.dao.transactions.TransactionManager;
import persistencia.dao.transactions.Transaction;


public class DAOViajeImp implements DAOViaje{
	
	@Override
	public boolean startConnection() {
		return false;
	}

	@Override
	public boolean crearViaje(TViaje viaje) { //Falta añadirle por algun lado comprobarDatos
		return crearViaje(viaje, false);
	}
	
	public boolean crearViaje(TViaje viaje, boolean rollbackOnEnd) {
		boolean confirmacion = false;
		
		Transaction t = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { 
				
			t = TransactionManager.getInstance().nuevaTransaccion();
			//cuando se implemete TransactionManager sustituir las dos lineas anteriores por esto y 
				//eliminar los commits() y los rollback()
			//trans = TransactionManager.getInstance().getTransaccion();
			//con = (Connection) trans.getResource();
			conn = t.start();
			pstmt = conn.prepareStatement("INSERT INTO viajes (nombre_viaje, destino_viaje, fecha_inicio , fecha_fin,"
					+ "num_personas) VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, viaje.getNombre());
			pstmt.setString(2, viaje.getDestino());
			pstmt.setDate(3, viaje.getFechaIni());
			pstmt.setDate(4, viaje.getFechaFin());
			pstmt.setInt(5, viaje.getNumPersonas());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				confirmacion = true;
			}
			if(!rollbackOnEnd)
				t.commit();
			else
				t.rollback();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return false;
		}
		finally {
			try {
				if(pstmt != null)pstmt.close();
				
				if(rs != null)rs.close();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean estaEnLaBD(TViaje viaje) {
		Transaction t = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			//con = (Connection) trans.getResource();
			conn = t.start();
			pstmt = conn.prepareStatement("SELECT * FROM viajes");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				TViaje v1 = new TViaje(rs.getInt("id"),rs.getString("nombre_viaje"), rs.getString("destino_viaje"),
						rs.getInt("num_personas"),rs.getDate("fecha_inicio").toString(), rs.getDate("fecha_fin").toString());
				
				if(viaje.getNombre().equals(v1.getNombre())) {
					t.commit();
					return true;
					}
			}
			t.rollback();
		}catch(Exception e) {
			e.printStackTrace();
			t.rollback();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				
				if(rs != null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

	public TViaje leerPorNombre(String nombre) {
		TViaje tv = null;
		Transaction t = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String statement = "SELECT * FROM viajes WHERE nombre_viaje = ?"; //FOR UPDATE
		
		try {
			t = TransactionManager.getInstance().nuevaTransaccion(); //da null
			//conn = (Connection) t.getResource();
			conn = t.start();
			ps = conn.prepareStatement(statement);
			int i = 1;
			ps.setString(i++, nombre);
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			rs = ps.executeQuery();
			if (rs.next()) {
				tv = new TViaje();
				tv.setId(rs.getInt("viajes.id"));
				tv.setNombre(rs.getString("viajes.nombre_viaje"));
				tv.setDestino(rs.getString("viajes.destino_viaje"));
				tv.setFechaIni(formato.format(rs.getDate("viajes.fecha_inicio")));
				tv.setFechaFin(formato.format(rs.getDate("viajes.fecha_fin")));
				tv.setNumPersonas(rs.getInt("viajes.num_personas"));			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			t.rollback();
			try {
				if(ps != null)ps.close();
				if(rs != null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tv;
	}
	
	public List<TViaje> leerTodos() {
		TViaje tv = null;
		List<TViaje> lista = null;
		Transaction t = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String statement = "SELECT * FROM viajes WHERE activo = 1 FOR UPDATE";
		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			//conn = (Connection) t.getResource();
			conn = t.start();
			ps = conn.prepareStatement(statement);
			
			ResultSet rs = ps.executeQuery();
			lista = new ArrayList<>();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			while (rs.next()) {
				tv = new TViaje();
				tv.setId(rs.getInt("viajes.id"));
				tv.setNombre(rs.getString("viajes.nombre_viaje"));
				tv.setDestino(rs.getString("viajes.destino_viaje"));
				tv.setFechaIni(formato.format(rs.getDate("viajes.fecha_inicio")));
				tv.setFechaFin(formato.format(rs.getDate("viajes.fecha_fin")));
				tv.setNumPersonas(rs.getInt("viajes.num_personas"));
				lista.add(tv);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void borradoFisicoViaje(TViaje viaje) {	
		Transaction t = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			t = TransactionManager.getInstance().nuevaTransaccion();
			//con = (Connection) trans.getResource();
			conn = t.start();
			pstmt = conn.prepareStatement("SELECT * FROM viajes");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				TViaje v1 = new TViaje(rs.getInt("id"),rs.getString("nombre_viaje"), rs.getString("destino_viaje"),
						rs.getInt("num_personas"),rs.getDate("fecha_inicio").toString(), rs.getDate("fecha_fin").toString());
				
				if(viaje.getNombre().equals(v1.getNombre())) {
					pstmt2 = conn.prepareStatement("DELETE FROM viajes WHERE nombre_viaje = ?");
					pstmt2.setString(1, v1.getNombre());
					pstmt2.executeUpdate();
					t.commit();
					return;
					}
			}
			t.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
			t.rollback();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				
				if(rs != null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
