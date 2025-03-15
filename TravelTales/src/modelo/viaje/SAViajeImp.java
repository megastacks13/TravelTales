package modelo.viaje;

import java.util.ArrayList;
import java.util.List;

import persistencia.dao.transactions.Transaction;
import persistencia.dao.transactions.TransactionManager;
import persistencia.dao.viaje.DAOViaje;
import persistencia.dao.viaje.DAOViajeImp;
import persistencia.dao.factoriadao.FactoriaDAO;

public class SAViajeImp implements SAViaje {

	@Override
	public Integer crearViajeFuturo(TViaje tViaje) {
		// Añadir comprobaciones:
		// 1. No hay viaje con el mismo nombre (return -1)
		// 2. Las fecha de inicio del viaje no es una fecha pasada (return -2)
		// 3. La fecha de fin es futura a la fecha de inicio (return -3)
		// 4. Las fechas de este viaje no se solapan con las de otro viaje (return -4)
		//Si todo OK -> return el ID del viaje
		
		int r = 0;
		boolean correct = false;
		Transaction t = null;
		TransactionManager tm = null;
		DAOViaje daoViaje = FactoriaDAO.getInstancia().crearDAOViaje();
		try {
			tm = TransactionManager.getInstance();
			t = tm.nuevaTransaccion();
			t.start();
			
			TViaje aux = daoViaje.leerPorNombre(tViaje.getNombre());
			List<TViaje> lista = daoViaje.leerTodos();
			
			if (aux != null)
				r = -1;
			else if (tViaje.getFechaIni().before(new java.sql.Date(System.currentTimeMillis())))
				r = -2;
			else if(tViaje.getFechaIni().after(tViaje.getFechaFin()))
				r = -3;
			else if (viajesSolapados(lista, tViaje))
				r = -4;
			else {
				correct = true;
				daoViaje.crearViaje(tViaje);
				t.commit();
			}
			
			if(!correct)
				t.rollback();
			
		} catch (Exception e) {
			t.rollback();
		}
		return r;
	}

	private boolean viajesSolapados(List<TViaje> lista, TViaje viaje) throws Exception {
		boolean solapado = false;
		try {
			for(TViaje aux : lista) {
				//el nuevo viaje empieza en medio de otro ya existente
				if((viaje.getFechaIni().after(aux.getFechaIni()) && viaje.getFechaIni().before(aux.getFechaFin())) || 
				//el nuevo viaje termina en medio de otro ya existente
						(viaje.getFechaFin().after(aux.getFechaIni()) && viaje.getFechaFin().before(aux.getFechaFin())))
					solapado = true;
			}
		}
		catch (Exception e) {
			throw new Exception();
		}
		return solapado;
	}
	
}
