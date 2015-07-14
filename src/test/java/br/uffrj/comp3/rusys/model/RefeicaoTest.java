package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefeicaoTest {
	
	String descricao;
	String op;
	String turno;
	String id;
	String ticket;
	

	@Test
	public void testRefeicao() {
		descricao = "arroz";
		
	}

	@Test
	public void testGetDescricao() {
		descricao = "arroz";
		
		assertEquals("arroz",descricao);
	}

	@Test
	public void testSetDescricao() {
		descricao = "arroz";
		
		assertEquals("arroz",descricao);
	}

	@Test
	public void testGetOpcaoVeg() {
		op = "arroz";
		
		assertEquals("arroz",op);
	}

	@Test
	public void testSetOpcaoVeg() {
		op = "arroz";
		
		assertEquals("arroz",op);
	}

	@Test
	public void testGetTurno() {
		turno = "manha";
		
		assertEquals("manha",turno);
	}

	@Test
	public void testSetTurno() {
		turno = "manha";
		
		assertEquals("manha",turno);
	}

	@Test
	public void testGetTickets() {
		ticket = "pago";
		
		assertEquals("pago",ticket);
	}

	@Test
	public void testSetTickets() {
		ticket = "pago";
		
		assertEquals("pago",ticket);
	}

	@Test
	public void testGetIdentificador() {
		id = "01";
		
		assertEquals("01",id);
	}

	@Test
	public void testSetIdentificador() {
		id = "01";
		
		assertEquals("01",id);
	}

}
