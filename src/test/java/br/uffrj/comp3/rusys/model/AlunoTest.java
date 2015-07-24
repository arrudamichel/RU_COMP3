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
		al = new Aluno(1, "Jessica", 2011785144, 2011, cu);
		
	}

	@Test
	public void testGetCurso() throws Exception {
		String esperado = "CC";
		de = new Departamento(1, "Depto", "DCC");
		cu = new Curso(1, "computacao", "CC", de);
		al = new Aluno(1, "Jessica", 2011785144, 2011, cu);
		
		assertEquals(esperado, al.getCurso().getSigla());
	}

	@Test
	public void testSetCurso() throws Exception {
		String esperado = "CC";
		de = new Departamento(1, "Depto", "DCC");
		Curso cu2 = new Curso(1, "computacao", "CC", de);		
		cu = new Curso(1, "computacao", "CC", de);
		al = new Aluno(1, "Jessica", 2011785144, 2011, cu);
		al.setCurso(cu2);
		
		assertEquals(esperado, al.getCurso().getSigla());
	}
	
	@Test
	public void testaAlunoOk() throws Exception
	{
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, 2010, curso);
	}
	
	@Test(expected = Exception.class)
	public void testaAlunoFail() throws Exception
	{
		Aluno aluno = new Aluno(1, "aluno", 123, 2010, null);
	}
	
	@Test
	public void testaSetterOk() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, 2010, curso);
		
		assertEquals(aluno.getCurso(), curso);
	}
	
	@Test(expected = Exception.class)
	public void testaSetterFail() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, 2010, curso);
		
		aluno.setCurso(null);
	}

}
