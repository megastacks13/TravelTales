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
	void testNombreDuplicado() { //Problema, dice que v1 no está en la BD
		TViaje v1 = new TViaje(4, "Viaje a Turín", "Turín", 3, "06/05/2025","16/05/2025");
		daoViaje.crearViaje(v1); //Al finalizar el test hay que borrarlo de la BD
		
		//Verificar si v1 está en la BD (Sí que está)
		boolean existeV1 = daoViaje.estaEnLaBD(v1);
		assertTrue(existeV1, "El v1 existe en la bd");
		
		TViaje v2 = new TViaje(3, "Viaje a Turín", "Turín", 5, "06/05/2025","16/05/2025");
		
		//El problema está en crearViajeFuturo --> no entra en la comprobacion de r
		int r = saViaje.crearViajeFuturo(v2);
		
		assertTrue(r == -1, "El valor debe ser - 1 pero se obtuvo " + r);
	}
	
}

/*Tests: 4
mismo nombre
FechaInicio no es pasada
ViajeSolapado
La fecha de fin es futura a la fecha de inicio
*/
