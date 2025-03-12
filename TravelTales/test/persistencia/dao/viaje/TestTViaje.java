package persistencia.dao.viaje;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.viaje.TViaje;

public class TestTViaje {
	TViaje t1 = new TViaje(0,"Viaje a Cancún", "Cancún", 35, "29/08/2025",
			"05/09/2025");

	@Test
	public void testNombre() {
		assertEquals("Viaje a Cancún",t1.getNombre());
		t1.setNombre("Viaje Fin de Curso");
		assertEquals("Viaje Fin de Curso",t1.getNombre());
	}
	
	@Test
	public void testId() {
	}
	
	@Test
	public void testDestino() {
	}
	
	@Test
	public void testFechaIni() {
	}
	
	@Test
	public void testFechaFin() {
	}

}
