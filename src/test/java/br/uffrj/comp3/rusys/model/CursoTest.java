package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CursoTest {
	
	String curso;
	String depto;
	String sigla;
	String id;
	
	

	@Test
	public void testCurso() {
	 curso = "computacao";
	 depto = "DCC";
	 sigla = "CC";
	 id="01";
	}

	@Test
	public void testCursoStringStringDepartamento() {
		 curso = "computacao";
		 depto = "DCC";
		
		assertEquals("computacao",curso);	
		assertEquals("DCC", depto);
	}

	@Test
	public void testGetNome() {
		 curso = "computacao";
	
		assertEquals("computacao", curso);
	}

	@Test
	public void testSetNome() {
		 curso = "computacao";
		 	
		assertEquals("computacao", curso);
		
	}

	@Test
	public void testGetSigla() {
		
		 sigla = "CC";
		
		assertEquals("CC", sigla);
	}

	@Test
	public void testSetSigla() {
	
		 sigla = "CC";
		
		assertEquals("CC", sigla);
	}

	@Test
	public void testGetDepartamento() {
	
		 depto = "DCC";
		
		assertEquals("DCC", depto);
	}

	@Test
	public void testSetDepartamento() {
		
		 depto = "DCC";
		
		assertEquals("DCC", depto);
	}

	@Test
	public void testGetIdentificador() {
		
		 id="01";
		assertEquals("01",id);
	}

	@Test
	public void testSetIdentificador() {
		 
		 id="01";
		assertEquals("01",id);
	}

}
