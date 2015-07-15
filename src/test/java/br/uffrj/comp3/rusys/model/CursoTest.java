package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CursoTest {
	
	Departamento de;
	Curso cu;
	
	

	@Test
	public void testCurso() throws Exception {
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
	}

	@Test
	public void testCursoStringStringDepartamento() throws Exception {
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		
		assertEquals("computacao",cu.getNome());	
		
	}

	@Test
	public void testGetNome() throws Exception {
		String esperado = "computacao";
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
	
		assertEquals(esperado, cu.getNome());
	}

	@Test
	public void testSetNome() throws Exception {
		String esperado = "Letras";
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		cu.setNome("Letras");
		
	
		assertEquals(esperado, cu.getNome());
		
	}

	@Test
	public void testGetSigla() throws Exception {
		
		 String esperado = "CC";
		 de = new Departamento(1, "Depto", "DCC");
			cu = new Curso(1, "computacao", "CC", de);
		
		
	
		assertEquals(esperado, cu.getSigla());
	}

	@Test
	public void testSetSigla() throws Exception {
		 String esperado = "CComp";
		 de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		 cu.setSigla("CComp");
		
	
		assertEquals(esperado, cu.getSigla());
	}

	@Test
	public void testGetDepartamento() throws Exception {
		String esperado = "DCC";
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
			
		assertEquals(esperado, cu.getDepartamento().getSigla());
	}

	@Test
	public void testSetDepartamento() throws Exception {
		String esperado = "DCC1";
		Departamento de2 = new Departamento(2,"Depto de Computacao","DCC1");
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		cu.setDepartamento(de2);
			
		assertEquals(esperado, cu.getDepartamento().getSigla());
	}

	@Test
	public void testGetIdentificador() throws Exception {
		int esperado = 1;
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
			
		assertEquals(esperado, cu.getIdentificador());
		
	}

	@Test
	public void testSetIdentificador() throws Exception {
		int esperado = 1;
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
			
		assertEquals(esperado, cu.getIdentificador());
	}

}
