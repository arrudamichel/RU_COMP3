package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlunoTest {
	Aluno al;
	Curso cu;
	Departamento de;

	@Test
	public void testAluno() throws Exception {
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		al = new Aluno(1, "Jessica", 2011785144, "2011", cu);
		
	}

	@Test
	public void testGetCurso() throws Exception {
		String esperado = "CC";
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		al = new Aluno(1, "Jessica", 2011785144, "2011", cu);
		
		assertEquals(esperado, al.getCurso().getSigla());
	}

	@Test
	public void testSetCurso() throws Exception {
		String esperado = "CC";
		Curso cu2 = new Curso(1, "computacao", "CC", de);
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		al = new Aluno(1, "Jessica", 2011785144, "2011", cu);
		al.setCurso(cu2);
		
		assertEquals(esperado, al.getCurso().getSigla());
	}

}
