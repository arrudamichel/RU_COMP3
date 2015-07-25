package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;
import br.uffrj.comp3.rusys.service.exceptions.*;


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
	
	@Test(expected = DepartamentoNull.class)
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
	
	@Test(expected = DepartamentoNull.class)
	public void testSetDepartamentoNull() throws Exception {
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, "Jessica", 2011785144, 2011, dept);
		func.setDepartamento(null);
	}
	
	@Test(expected = NomeNull.class)
	public void testFuncionarioNomeNull() throws Exception {
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, null, 2011785144, 2011, dept);
	}
	
	@Test(expected = AnoDeIngressoNull.class)
	public void testAlunoAnoDeIngressoNull() throws Exception {
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, "funcionario", 2011785144, null, dept);
	}
	
	@Test
	public void testFuncionarioCPFOK() throws Exception {
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, "funcionario", 2011785144, 2010, dept);
		String expected = "11111111111";
		func.setCpf("11111111111");
		
		assertEquals(expected, func.getCpf());
	}
	
	@Test(expected = InvalidCpfException.class)
	public void testFuncionarioCPFNotOK() throws Exception {
		dept = new Departamento(1, "Ccomp", "DCC");
		func = new Funcionario(1, "funcionario", 2011785144, 2010, dept);
		func.setCpf("111111pp111");
	}

}