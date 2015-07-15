package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CursoTest {
	
	Departamento de;
	Curso cu;
	
	

	@Test
	public void testCurso() {
		de = new Departamento("Depto de Computacao","DCC");
		cu = new Curso("Computacao", "CC", de);
	 
	}

	@Test
	public void testCursoStringStringDepartamento() {
		cu = new Curso("Computacao", "CC", de);
		
		assertEquals("Computacao",cu.getNome());	
		
	}

	@Test
	public void testGetNome() {
		 String esperado = "Computacao";
		 cu = new Curso("Computacao", "CC", de);
	
		assertEquals(esperado, cu.getNome());
	}

	@Test
	public void testSetNome() {
		 String esperado = "Letras";
		 cu = new Curso("Computacao", "CC", de);
		 cu.setNome("Letras");
		
	
		assertEquals(esperado, cu.getNome());
		
	}

	@Test
	public void testGetSigla() {
		
		 String esperado = "CC";
		 cu = new Curso("Computacao", "CC", de);
		
		
	
		assertEquals(esperado, cu.getSigla());
	}

	@Test
	public void testSetSigla() {
		 String esperado = "CComp";
		 cu = new Curso("Computacao", "CC", de);
		 cu.setSigla("CComp");
		
	
		assertEquals(esperado, cu.getSigla());
	}

	@Test
	public void testGetDepartamento() {
		String esperado = "DCC";
		de = new Departamento("Depto de Computacao","DCC");
		cu = new Curso("Computacao", "CC", de);
			
		assertEquals(esperado, cu.getDepartamento().getSigla());
	}

	@Test
	public void testSetDepartamento() {
		String esperado = "DCC1";
		Departamento de2 = new Departamento("Depto de Computacao","DCC1");
		cu = new Curso("Computacao", "CC", de);
		cu.setDepartamento(de2);
			
		assertEquals(esperado, cu.getDepartamento().getSigla());
	}

	@Test
	public void testGetIdentificador() {
		int esperado = 2;
		cu = new Curso("Computacao", "CC", de);
		cu.setIdentificador(02);
			
		assertEquals(esperado, cu.getIdentificador());
		
	}

	@Test
	public void testSetIdentificador() {
		int esperado = 2;
		cu = new Curso("Computacao", "CC", de);
		cu.setIdentificador(02);
			
		assertEquals(esperado, cu.getIdentificador());
	}

}
