package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FuncionarioTest {

	String depto;
	
	public void testFuncionarioStringIntStringSexoEnumTituloEnumStringDepartamento() {
		depto = "DCC";
	}

	@Test
	public void testFuncionario() {
		depto="DCC";
	}

	@Test
	public void testGetDepartamento() {
		depto = "DCC";
		
		assertEquals("DCC",depto);
	}

	@Test
	public void testSetDepartamento() {
		depto = "DCC";
		
		assertEquals("DCC",depto);
	}

}
