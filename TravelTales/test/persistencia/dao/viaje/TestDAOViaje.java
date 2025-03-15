package persistencia.dao.viaje;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.viaje.TViaje;
import persistencia.dao.*;
import persistencia.dao.transactions.Transaction;
import persistencia.dao.transactions.TransactionFactory;

class TestDAOViaje {
	
	TViaje t1 = new TViaje(0,"Viaje a Cancún", "Cancún", 35, "29/08/2025",
			"05/09/2025");

	private DAOViaje daoViaje;
	private Connection con;
    private Transaction trans; 

	@BeforeEach 
	void setUp() throws Exception {
		
	    daoViaje = new DAOViajeImp(); 

	    //Pruebas
	    trans = TransactionFactory.getInstance().newTransaction();
	    trans.start();
	    con = (Connection) trans.getResource();
	}
	
	@Test
	void testCrearViaje() { //FALTA DESHACER CAMBIOS DESPUES DE CADA TEST
		TViaje viaje = new TViaje(1, "Viaje a China", "China", 10, "31/03/2025","12/04/2025");
		
		boolean creado = daoViaje.crearViaje(viaje);
		
		assertTrue(creado, "El viaje se ha creado correctamente.");
		
		trans.rollback();
	}
	
	@Test
	void testComprobarDatos() { //FALTA DESHACER CAMBIOS DESPUES DE CADA TEST
		TViaje v1 = new TViaje(1, "Viaje a Japón", "Japón", 6, "17/07/2025",
				"29/07/2025");
		
		boolean newViaje = daoViaje.comprobarDatos(v1);
		assertTrue(newViaje, "El viaje no se encuentra en la BD");
		
		////Eliminarlo a mano de la BD
		boolean creado = daoViaje.crearViaje(v1);
		assertTrue(creado, "Los datos del viaje son correctos.");
		
		trans.rollback();
	}
}


















