package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicketTest {
	
	String ticket;
	String valor;
	String consumidor;

	@Test
	public void testTicket() {
		ticket="pago";
	}

	@Test
	public void testTicketBooleanFloatConsumidor() {
		ticket="pago";
	}

	@Test
	public void testIsPago() {
		ticket = "pago";
		
		assertEquals("pago",ticket);
	}

	@Test
	public void testSetPago() {
		ticket = "pago";
		
		assertEquals("pago",ticket);
	}

	@Test
	public void testGetValor() {
		valor = "1.45";
		
		assertEquals("1.45",valor);
	}

	@Test
	public void testSetValor() {
		valor = "1.45";
		
		assertEquals("1.45",valor);
	}

	@Test
	public void testGetConsumidor() {
	consumidor = "joao";
		
		assertEquals("joao",consumidor);
	}

	@Test
	public void testSetConsumidor() {
		consumidor = "joao";
		
		assertEquals("joao",consumidor);
	}

}
