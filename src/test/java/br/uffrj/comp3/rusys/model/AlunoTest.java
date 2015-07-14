package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlunoTest {
	String curso;
	

	@Test
	public void testAlunoStringIntStringSexoEnumTituloEnumStringCurso() {
		curso = "computação";
	}

	@Test
	public void testAluno() {
		curso = "computação";
		
	}
	@Test
	public void testGetCurso() {
		 curso = "computacao";
			
		assertEquals("computacao", curso);
	}

	@Test
	public void testSetCurso() {
		 curso = "computacao";
			
		assertEquals("computacao", curso);
	}

}
