package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;
import br.uffrj.comp3.rusys.service.exceptions.*;

public class CursoTest {

	private Departamento dept;
	private Curso curso;

	@Test
	public void testCursoOk() throws Exception {
		dept = new Departamento(1, "Depto", "DCC");
		curso = new Curso(1, "computacao", "CC", dept);
	}

	@Test(expected = SiglaNull.class)
	public void testCursoSemSigla() throws Exception {
		dept = new Departamento(1, "Depto", "DCC");
		curso = new Curso(1, "computacao", null, dept);
	}

	@Test(expected = DepartamentoNull.class)
	public void testCursoSemDepartamento() throws Exception {
		curso = new Curso(1, "computacao", "CC", null);
	}

	@Test(expected = SiglaNull.class)
	public void testSetSiglaNull() throws Exception {
		dept = new Departamento(1, "Depto", "DCC");
		curso = new Curso(1, "computacao", "CC", dept);
		curso.setSigla(null);

	}

	@Test
	public void testSetSigla() throws Exception {
		String esperado = "CComp";
		dept = new Departamento(1, "Depto", "DCC");
		curso = new Curso(1, "computacao", "CC", dept);
		curso.setSigla("CComp");

		assertEquals(esperado, curso.getSigla());
	}
	
	@Test(expected = DepartamentoNull.class)
	public void testSetDepartamentoNull() throws Exception {
		dept = new Departamento(1, "Depto", "DCC");
		curso = new Curso(1, "computacao", "CC", dept);
		curso.setDepartamento(null);
	}

	@Test
	public void testSetDepartamentoOk() throws Exception {
		Departamento de2 = new Departamento(2, "Depto de Computacao", "DCC1");
		dept = new Departamento(1, "Depto", "DCC");
		curso = new Curso(1, "computacao", "CC", dept);
		curso.setDepartamento(de2);
	}
}