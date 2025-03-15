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
	}
	
	@Test
	void testCrearViaje() { //Funciona
		TViaje viaje = new TViaje(1, "Viaje a China", "China", 10, "31/03/2025","12/04/2025");
		
		boolean creado = daoViaje.crearViaje(viaje, true);
		
		assertTrue(creado, "El viaje se ha creado correctamente.");
		
		//Comprobacion rollback (Funciona)
		TViaje viajeComp = daoViaje.leerPorNombre("Viaje a China");
		assertNull(viajeComp); 
	}
	
	@Test
	void testEstaEnLaBD() { //Funciona
		TViaje v1 = new TViaje(3, "Viaje a Japón", "Japón", 6, "17/07/2025",
				"29/07/2025");
		
		boolean newViaje = daoViaje.estaEnLaBD(v1);
		assertFalse(newViaje);
		
		boolean creado = daoViaje.crearViaje(v1);
		assertTrue(creado);
		
		newViaje = daoViaje.estaEnLaBD(v1);
		assertTrue(newViaje);
		daoViaje.borradoFisicoViaje(v1);
	}
}


















