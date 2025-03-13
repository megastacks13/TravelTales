package persistencia.dao.viaje;

import static org.junit.Assert.*;

import java.text.ParseException;

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
		assertEquals(0, t1.getId());
		t1.setId(1);
		assertEquals(1, t1.getId());
	}
	
	@Test
	public void testDestino() {
		assertEquals("Cancún", t1.getDestino());
		t1.setDestino("Japón");
		assertEquals("Japón", t1.getDestino());
	}
	
	@Test
	public void testFechaIni() {
		try {
			assertEquals("29/08/2025", t1.getFechaIni());
			t1.setFechaIni("28/08/2025");
			assertEquals("28/08/2025", t1.getFechaIni());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFechaFin() {
		try {
			assertEquals("05/09/2025", t1.getFechaFin());
			t1.setFechaFin("06/09/2025");
			assertEquals("06/09/2025", t1.getFechaFin());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNumPersonas() {
		assertEquals(35, t1.getNumPersonas());
		t1.setNumPersonas(30);
		assertEquals(30, t1.getNumPersonas());
	}

}
