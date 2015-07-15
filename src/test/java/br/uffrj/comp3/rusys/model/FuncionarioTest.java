package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FuncionarioTest {

	Funcionario fu;
	Curso cu;
	Departamento de;
	
	public void testFuncionarioStringIntStringSexoEnumTituloEnumStringDepartamento() throws Exception {
		String esperado = "Jessica";
		de = new Departamento(1, "Depto", "DCC");
		fu = new Funcionario(1, "Jessica", 2011785144, "2011", de);
		assertEquals(esperado,fu.getNome());
	}

	@Test
	public void testFuncionario() throws Exception {
		de = new Departamento(1, "Ccomp", "DCC");
		fu = new Funcionario(1, "Jessica", 2011785144, "2011", de);
	}

	@Test
	public void testGetDepartamento() throws Exception {
		String esperado = "Jessica";
		de = new Departamento(1, "Depto", "DCC");
		fu = new Funcionario(1, "Jessica", 2011785144, "2011", de);
		assertEquals(esperado,fu.getNome());
	}

	@Test
	public void testSetDepartamento() throws Exception {
		String esperado = "Depto";
		Departamento de2 = new Departamento(2,"Depto","DCC");
		de = new Departamento(1, "Ccomp", "DCC");
		fu = new Funcionario(1, "Jessica", 2011785144, "2011", de);
		fu.setDepartamento(de2);
		assertEquals(esperado,fu.getDepartamento().getNome());
	}

}
