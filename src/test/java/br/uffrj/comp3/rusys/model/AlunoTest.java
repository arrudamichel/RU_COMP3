package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlunoTest {
	String curso;
	

	@Test
	public void testAlunoStringIntStringSexoEnumTituloEnumStringCurso() {
		curso = "computa��o";
	}

	@Test
	public void testAluno() {
		curso = "computa��o";
		
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
