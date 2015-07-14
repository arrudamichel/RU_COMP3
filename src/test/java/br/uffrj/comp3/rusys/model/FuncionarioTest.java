package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FuncionarioTest {

	Funcionario fu;
	Curso cu;
	Departamento de;
	
	public void testFuncionarioStringIntStringSexoEnumTituloEnumStringDepartamento() {
		String esperado = "Jessica";
		fu = new Funcionario("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", de );
		assertEquals(esperado,fu.getNome());
	}

	@Test
	public void testFuncionario() {
		de = new Departamento("Depto de Computacao","DCC");
		fu = new Funcionario("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", de );
	}

	@Test
	public void testGetDepartamento() {
		String esperado = "DCC";
		de = new Departamento("Depto de Computacao","DCC");
		fu = new Funcionario("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", de );
		assertEquals(esperado,fu.getDepartamento().getSigla());
	}

	@Test
	public void testSetDepartamento() {
		String esperado = "Depto";
		Departamento de2 = new Departamento("Depto","DCC");
		fu = new Funcionario("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910", de );
		fu.setDepartamento(de2);
		assertEquals(esperado,fu.getDepartamento().getNome());
	}

}
