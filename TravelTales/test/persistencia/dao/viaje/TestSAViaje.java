package persistencia.dao.viaje;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.viaje.SAViajeImp;
import modelo.viaje.TViaje;
import persistencia.dao.*;
import persistencia.dao.transactions.Transaction;
import persistencia.dao.transactions.TransactionFactory;

public class TestSAViaje {
	
	private SAViajeImp saViaje;
	private DAOViajeImp daoViaje;
	
	@BeforeEach
	void setUp() throws Exception {
		daoViaje = new DAOViajeImp();  
        saViaje = new SAViajeImp();  
	}
	@Test
	void testCrearViajeFuturo() {
		//No se si este haría falta tb
	}
	
	@Test
	void testNombreDuplicado() { //Problema, dice que v1 no está en la BD
		TViaje v1 = new TViaje(4, "Viaje a Turín", "Turín", 3, "06/05/2025","16/05/2025");
		daoViaje.crearViaje(v1); //Al finalizar el test hay que borrarlo de la BD
		
		//Verificar si v1 está en la BD (Sí que está)
		boolean existeV1 = daoViaje.estaEnLaBD(v1);
		assertTrue(existeV1, "El v1 existe en la bd");
		
		TViaje v2 = new TViaje(3, "Viaje a Turín", "Turín", 5, "06/05/2025","16/05/2025");
		
		//El problema está en crearViajeFuturo --> no entra en la comprobacion de r (salta excepción)
		int r = saViaje.crearViajeFuturo(v2);
		
		assertTrue(r == -1, "El valor debe ser - 1 pero se obtuvo " + r);
		daoViaje.borradoFisicoViaje(v1); //Borramos v1
	}
	
	@Test 
	void testFechaInicioNoPasada() {
		TViaje viaje = new TViaje(2, "Viaje a Suecia", "Suecia", 30, "14/08/2022",
				"19/08/2025");

        int r = saViaje.crearViajeFuturo(viaje);
        assertTrue(r == -2, "La fecha de inicio no puede ser en el pasado. Esperamos -2 y salió " + r);
	}
	
	@Test 
	void testFechaFinMayorFechaIni() {
		// Fecha de fin antes de la fecha de inicio
        TViaje viaje = new TViaje(2, "Viaje a Suecia", "Suecia", 30, "14/08/2025",
				"11/08/2025");
    
        int r = saViaje.crearViajeFuturo(viaje);
        assertTrue(r == -3, "La fecha de fin no puede ser antes que la de inicio. Esperamos -3 y salió " + r);
	}
	
	@Test
	void testViajesSolapados() {

	}
}

/*Tests: 4
mismo nombre
FechaInicio no es pasada
ViajeSolapado
La fecha de fin es futura a la fecha de inicio
*/
