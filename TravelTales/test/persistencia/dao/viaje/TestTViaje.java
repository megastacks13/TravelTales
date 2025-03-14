package persistencia.dao.viaje;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import modelo.viaje.TViaje;



public class TestTViaje {
	TViaje t1 = new TViaje(0,"Viaje a Cancún", "Cancún", 10, "29/08/2025",
			"05/09/2025");

	@Test
	public void testNombre() { //Funciona
		assertEquals("Viaje a Cancún",t1.getNombre());
		t1.setNombre("Viaje Fin de Curso");
		assertEquals("Viaje Fin de Curso",t1.getNombre());
	}
	
	@Test
	public void testId() { //Funciona
		assertEquals(0, t1.getId());
		t1.setId(7);
		assertEquals(7, t1.getId());
	}
	
	@Test
	public void testDestino() { //Funciona
		 assertEquals("Cancún", t1.getDestino());
		 t1.setDestino("Madrid");
		 assertEquals("Madrid", t1.getDestino());
	}
	
	@Test
	public void testFechaIni() throws ParseException { //Funciona
		assertEquals(java.sql.Date.valueOf("2025-08-29"), t1.getFechaIni());
		t1.setFechaIni("27/09/2003");
		assertEquals(java.sql.Date.valueOf("2003-09-27"), t1.getFechaIni());
	}
	
	@Test 
	public void testFechaFin() throws ParseException { //Funciona
		assertEquals(java.sql.Date.valueOf("2025-09-05"), t1.getFechaFin());
		t1.setFechaFin("19/02/2004");
		assertEquals(java.sql.Date.valueOf("2004-02-19"), t1.getFechaFin());
	}

}
