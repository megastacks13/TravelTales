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
		boolean confirmacion = false;
		
		Transaction trans = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
				
			trans = TransactionFactory.getInstance().newTransaction();
			trans.start();
			con = (Connection) trans.getResource();
			
			pstmt = con.prepareStatement("INSERT INTO viajes (nombre_viaje, destino_viaje, fecha_inicio , fecha_fin,"
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
			trans.commit();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}catch (ParseException e) {
			e.printStackTrace();
			trans.rollback();
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
		return confirmacion;
	}

	@Override
	public boolean comprobarDatos(TViaje viaje) {
		boolean nuevo = true;
		
		Transaction trans = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			trans = TransactionFactory.getInstance().newTransaction();
			trans.start();
			con = (Connection) trans.getResource();
			pstmt = con.prepareStatement("SELECT * FROM viaje");
			rs = pstmt.executeQuery();
			while(rs.next() && nuevo)
			{
				TViaje v1 = new TViaje(rs.getInt("id"),rs.getString("nombre_viaje"), rs.getString("destino_viaje"),
						rs.getInt("num_personas"),rs.getDate("fecha_inicio").toString(), rs.getDate("fecha_fin").toString());
				
				if(viaje.equals(v1)) {
					nuevo = false;
					break;
					}
			}
			trans.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
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
	

	public TViaje leerPorNombre(String nombre) {
		TViaje tv = null;
		Transaction t = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String statement = "SELECT * FROM viajes WHERE nombre_viaje = ? FOR UPDATE";
		try {
			t = TransactionManager.getInstance().getTransaccion();
			conn = (Connection) t.getResource();
			ps = conn.prepareStatement(statement);
			int i = 1;
			ps.setString(i++, nombre);
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tv = new TViaje();
				tv.setId(rs.getInt("viajes.id"));
				tv.setNombre(rs.getString("viajes.nombre_viaje"));
				tv.setDestino(rs.getString("viajes.destino_viaje"));
				tv.setFechaIni(formato.format(rs.getDate("viajes.fecha_inicio")));
				tv.setFechaFin(formato.format(rs.getDate("viajes.fecha_fin")));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			t = TransactionManager.getInstance().getTransaccion();
			conn = (Connection) t.getResource();
			ps = conn.prepareStatement(statement);
			
			ResultSet rs = ps.executeQuery();
			lista = new ArrayList<>();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			if (rs.next()) {
				tv = new TViaje();
				tv.setId(rs.getInt("viajes.id"));
				tv.setNombre(rs.getString("viajes.nombre_viaje"));
				tv.setDestino(rs.getString("viajes.destino_viaje"));
				tv.setFechaIni(formato.format(rs.getDate("viajes.fecha_inicio")));
				tv.setFechaFin(formato.format(rs.getDate("viajes.fecha_fin")));
				lista.add(tv);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
