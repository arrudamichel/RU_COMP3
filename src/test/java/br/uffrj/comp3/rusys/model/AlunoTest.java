package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlunoTest {
	Aluno al;
	Curso cu;
	Departamento de;
	

	@Test
	public void testAlunoStringIntStringSexoEnumTituloEnumStringCurso() {
		 String esperado = "Jessica";
		 al = new Aluno("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", cu );
		 
	
		assertEquals(esperado, al.getNome());
	}

	@Test
	public void testAluno() {
		 de = new Departamento("Depto de Computacao","DCC");
		 cu = new Curso("Computacao", "CC", de);
		 al = new Aluno("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", cu );
		
	}
	@Test
	public void testGetCurso() {
		 String esperado = "Computacao";
		 cu = new Curso("Computacao", "CC", de);
		 al = new Aluno("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", cu );
		 
	
		assertEquals(esperado, al.getCurso().getNome());
	}

	@Test
	public void testSetCurso() {
		 String esperado = "Computacao2";
		 Curso cu2 = new Curso("Computacao2", "CC", de);
		 al = new Aluno("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", cu );
		 al.setCurso(cu2);
	
		assertEquals(esperado, al.getCurso().getNome());
	}

}
