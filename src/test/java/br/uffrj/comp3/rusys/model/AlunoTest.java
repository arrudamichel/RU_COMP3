package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.RefeicaoImpl;
import br.uffrj.comp3.rusys.service.exceptions.*;


public class AlunoTest {
	
	@Test
	public void testaAlunoOk() throws Exception
	{
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, 2010, curso);
	}
	
	@Test(expected = CursoNull.class)
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
	
	@Test(expected = CursoNull.class)
	public void testaSetterFail() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "aluno", 123, 2010, curso);
		
		aluno.setCurso(null);
	}
	
	@Test(expected = NomeNull.class)
	public void testAlunoNomeNull() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Consumidor aluno = new Aluno(1, null, 123, 2010, curso);
	}
	
	@Test(expected = AnoDeIngressoNull.class)
	public void testAlunoAnoDeIngressoNull() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Consumidor aluno = new Aluno(1, "nome do aluno", 123, null, curso);
	}
	
	@Test
	public void testAlunoCPFOK() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "nome do aluno", 123, 2010, curso);
		String expected = "11111111111";
		aluno.setCpf("11111111111");
		
		assertEquals(expected, aluno.getCpf());
	}
	
	@Test(expected = Exception.class)
	public void testAlunoCPFNotOK() throws Exception {
		Departamento departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		Curso curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		Aluno aluno = new Aluno(1, "nome do aluno", 123, 2010, curso);
		aluno.setCpf("11111111p11");
	}
	
}