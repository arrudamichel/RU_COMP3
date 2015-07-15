package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicketTest {
	
	Ticket ti;
	Consumidor con;
	Refeicao re;

	@Test
	public void testTicket() throws Exception {
		con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		re= new Refeicao(1,"arroz"){};
		
		ti = new Ticket(1,true, con, re);
	}


	@Test
	public void testTicketBooleanFloatConsumidor() throws Exception {
		boolean esperado = true;
		con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		re= new Refeicao(1,"arroz"){};
		
		ti = new Ticket(1,true, con, re);
	
		assertEquals(esperado, ti.isPago());
	}

	@Test
	public void testIsPago() throws Exception {
		boolean esperado = true;
		con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		re= new Refeicao(1,"arroz"){};
		
		ti = new Ticket(1,true, con, re);
	
		assertEquals(esperado, ti.isPago());
	}

	@Test
	public void testSetPago() throws Exception {
		boolean esperado = false;
		con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		re= new Refeicao(1,"arroz"){};
		
		ti = new Ticket(1,true, con, re); 
		 ti.setPago(false);
	
		assertEquals(esperado, ti.isPago());
	}

	@Test
	public void testGetValor() throws Exception {
		 float esperado = 2;
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
			re= new Refeicao(1,"arroz"){};
			
			ti = new Ticket(1,true, con, re);
		
		assertEquals(esperado, ti.getValor(), 1);
	}

	@Test
	public void testSetValor() throws Exception {
		float esperado = 2;
		con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		re= new Refeicao(1,"arroz"){};
		
		ti = new Ticket(1,true, con, re);
		
		assertEquals(esperado, ti.getValor(), 1);
	}

	@Test
	public void testGetConsumidor() throws Exception {
		con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		re= new Refeicao(1,"arroz"){};
		
		ti = new Ticket(1,true, con, re);
		
		assertEquals("Jessica",ti.getConsumidor().getNome());
	}

	@Test
	public void testSetConsumidor() throws Exception {
		con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		Consumidor con2 = new Consumidor(1, "Julia", 2011785144, "2011"){};
		re= new Refeicao(1,"arroz"){};
		
		ti = new Ticket(1,true, con, re);
		ti.setConsumidor(con2);
		
		assertEquals("Julia",ti.getConsumidor().getNome());
	}

}
