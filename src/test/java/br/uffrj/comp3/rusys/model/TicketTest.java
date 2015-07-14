package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicketTest {
	
	Ticket ti;
	Consumidor con;

	@Test
	public void testTicket() {
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		ti = new Ticket(true, 1, con);
	}


	@Test
	public void testTicketBooleanFloatConsumidor() {
		boolean esperado = true;
		 ti = new Ticket(true, 2, con);
	
		assertEquals(esperado, ti.isPago());
	}

	@Test
	public void testIsPago() {
		boolean esperado = true;
		 ti = new Ticket(true, 2, con);
	
		assertEquals(esperado, ti.isPago());
	}

	@Test
	public void testSetPago() {
		boolean esperado = false;
		 ti = new Ticket(true, 2, con);
		 ti.setPago(false);
	
		assertEquals(esperado, ti.isPago());
	}

	@Test
	public void testGetValor() {
		 float esperado = 2;
		 ti = new Ticket(true, 2, con);
		
		assertEquals(esperado, ti.getValor(), 1);
	}

	@Test
	public void testSetValor() {
		float esperado = 2;
		 ti = new Ticket(true, 2, con);
		
		assertEquals(esperado, ti.getValor(), 1);
	}

	@Test
	public void testGetConsumidor() {
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		ti = new Ticket(true, 1, con);
		
		assertEquals("Jessica",ti.getConsumidor().getNome());
	}

	@Test
	public void testSetConsumidor() {
		Consumidor con2 = new Consumidor("Julia", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		ti = new Ticket(true, 1, con);
		ti.setConsumidor(con2);
		
		assertEquals("Julia",ti.getConsumidor().getNome());
	}

}
