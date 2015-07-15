package br.uffrj.comp3.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Dejejum;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Refeicao;

public class AlunoTest {
	
	@Test
	public void testaAlunoOk() throws Exception
	{
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, "2010", curso);
	}
	
	@Test(expected = Exception.class)
	public void testaAlunoFail() throws Exception
	{
		Aluno aluno = new Aluno(1, "aluno", 123, "2010", null);
	}
	
	@Test
	public void testaSetterOk() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, "2010", curso);
		
		assertEquals(aluno.getCurso(), curso);
	}
	
	@Test(expected = Exception.class)
	public void testaSetterFail() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, "2010", curso);
		
		aluno.setCurso(null);
	}
	
}
