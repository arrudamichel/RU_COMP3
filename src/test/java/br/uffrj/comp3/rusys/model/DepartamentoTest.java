package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepartamentoTest {

	Departamento  de;
	
	@Test
	public void testHashCode() {
		int esperado = 2043512536;
		de = new Departamento("Depto", "DE");
		
		assertEquals(esperado,de.hashCode());
	}

	@Test
	public void testDepartamento() {
		 de = new Departamento("Depto", "DE");
			
	}

	@Test
	public void testDepartamentoStringString() {
		String esperado = "Depto";
		de = new Departamento("Depto", "DE");
		

		assertEquals(esperado,de.getNome());
	}

	@Test
	public void testGetNome() {
		String esperado = "Depto";
		de = new Departamento("Depto", "DE");
		

		assertEquals(esperado,de.getNome());
	}

	@Test
	public void testSetNome() {
		String esperado = "Depto 2";
		de = new Departamento("Depto", "DE");
		de.setNome("Depto 2");
		

		assertEquals(esperado,de.getNome());
	}

	@Test
	public void testGetSigla() {
		String esperado = "DE";
		de = new Departamento("Depto", "DE");
		

		assertEquals(esperado,de.getSigla());
	}

	@Test
	public void testSetSigla() {
		String esperado = "DER";
		de = new Departamento("Depto", "DE");
		de.setSigla("DER");
		

		assertEquals(esperado,de.getSigla());
	}

	@Test
	public void testGetIdentificador() {
		int esperado = 1;
		de = new Departamento("Depto", "DE");
		de.setIdentificador(1);
		
		assertEquals(esperado,de.getIdentificador());
		
	}

	@Test
	public void testSetIdentificador() {
		int esperado = 1;
		de = new Departamento("Depto", "DE");
		de.setIdentificador(1);
		
		assertEquals(esperado,de.getIdentificador());
	}

	@Test
	public void testEqualsObject() {
		boolean esperado = true;
		de = new Departamento("Depto", "DE");
		System.out.println(de.equals(de));
		
		assertEquals(esperado,de.equals(de));
	}

	@Test
	public void testGetId() {
		String esperado = "1";
		de = new Departamento("Depto", "DE");
		de.setId("1");
		
		assertEquals(esperado,de.getId());
		
	}

	@Test
	public void testSetId() {
		String esperado = "1";
		de = new Departamento("Depto", "DE");
		de.setId("1");
		
		assertEquals(esperado,de.getId());
	}

}
