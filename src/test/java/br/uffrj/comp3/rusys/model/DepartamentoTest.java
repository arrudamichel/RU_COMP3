package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepartamentoTest {

	Departamento  de;
	
	@Test
	public void testHashCode() throws Exception {
		int esperado = 2043512536;
		de = new Departamento(1,"Depto", "DE");
		
		assertEquals(esperado,de.hashCode());
	}

	@Test
	public void testDepartamento() throws Exception {
		de = new Departamento(1,"Depto", "DE");
			
	}

	@Test
	public void testDepartamentoStringString() throws Exception {
		String esperado = "Depto";
		de = new Departamento(1,"Depto", "DE");

		assertEquals(esperado,de.getNome());
	}

	@Test
	public void testGetNome() throws Exception {
		String esperado = "Depto";
		de = new Departamento(1,"Depto", "DE");	

		assertEquals(esperado,de.getNome());
	}

	@Test
	public void testSetNome() throws Exception {
		String esperado = "Depto 2";
		de = new Departamento(1,"Depto", "DE");
		de.setNome("Depto 2");
		

		assertEquals(esperado,de.getNome());
	}

	@Test
	public void testGetSigla() throws Exception {
		String esperado = "DE";
		de = new Departamento(1,"Depto", "DE");
		

		assertEquals(esperado,de.getSigla());
	}

	@Test
	public void testSetSigla() throws Exception {
		String esperado = "DER";
		de = new Departamento(1,"Depto", "DE");
		de.setSigla("DER");
		

		assertEquals(esperado,de.getSigla());
	}

	@Test
	public void testGetIdentificador() throws Exception {
		int esperado = 1;
		de = new Departamento(1,"Depto", "DE");
		
		
		assertEquals(esperado,de.getIdentificador());
		
	}

	@Test
	public void testSetIdentificador() throws Exception {
		int esperado = 2;
		de = new Departamento(1,"Depto", "DE");
		de.setIdentificador(2);
		
		assertEquals(esperado,de.getIdentificador());
	}

	@Test
	public void testEqualsObject() throws Exception {
		boolean esperado = true;
		de = new Departamento(1,"Depto", "DE");
		
		assertEquals(esperado,de.equals(de));
	}

	@Test
	public void testGetId() throws Exception {
		int esperado = 1;
		de = new Departamento(1,"Depto", "DE");
		
		
		assertEquals(esperado,de.getId());
		
	}


}
