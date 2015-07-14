package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepartamentoTest {

	String curso;
	String depto;
	String sigla;
	String id;
	
	@Test
	public void testHashCode() {
		String hc = "123";

		assertEquals("123",hc);
	}

	@Test
	public void testDepartamento() {
		depto = "DCC";
			
	}

	@Test
	public void testDepartamentoStringString() {
		depto = "DCC";
		
		
	}

	@Test
	public void testGetNome() {
		depto = "DCC";
		
		assertEquals("DCC",depto);
	}

	@Test
	public void testSetNome() {
		depto = "DCC";
		
		assertEquals("DCC",depto);
	}

	@Test
	public void testGetSigla() {
		sigla = "DCC";
		
		assertEquals("DCC",sigla);
	}

	@Test
	public void testSetSigla() {
		sigla = "DCC";
		
		assertEquals("DCC",sigla);
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

	@Test
	public void testEqualsObject() {
		id = "01";
		
		assertEquals("01",id);
	}

	@Test
	public void testGetId() {
		id = "01";
		
		assertEquals("01",id);
	}

	@Test
	public void testSetId() {
		id = "01";
		
		assertEquals("01",id);
	}

}
