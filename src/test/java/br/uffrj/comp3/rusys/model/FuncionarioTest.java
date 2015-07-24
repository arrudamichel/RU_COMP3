package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FuncionarioTest {

	private Funcionario func;
	private Curso curso;
	private Departamento dept;
	
	@Test
	public void testFuncionarioOk() throws Exception {
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, "Funcionario", 100, 2010, dept);
	}
	
	@Test(expected = Exception.class)
	public void testFuncionarioDeptNull() throws Exception {
		func = new Funcionario(1, "Funcionario", 100, 2010, null);
	}
	
	@Test
	public void testSetDepartamentoOk() throws Exception {
		String esperado = "Depto";
		Departamento de2 = new Departamento(2,"Depto","DCC");
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, "Jessica", 2011785144, 2011, dept);
		func.setDepartamento(de2);
		assertEquals(esperado,func.getDepartamento().getNome());
	}
	
	@Test(expected = Exception.class)
	public void testSetDepartamentoNull() throws Exception {
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, "Jessica", 2011785144, 2011, dept);
		func.setDepartamento(null);
	}

}
