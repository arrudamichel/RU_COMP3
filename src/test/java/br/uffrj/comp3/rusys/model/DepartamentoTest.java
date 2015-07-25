package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;
import br.uffrj.comp3.rusys.service.exceptions.*;

public class DepartamentoTest {

	private Departamento dept;
	

	@Test
	public void testDepartamentoOk() throws Exception {
		dept = new Departamento(1,"Depto", "DE");
	}

	@Test(expected = SiglaNull.class)
	public void testDepartamentoSiglaNull() throws Exception {
		dept = new Departamento(1,"Depto", null);
	}

	@Test
	public void testSetSiglaOk() throws Exception {
		String esperado = "DER";
		dept = new Departamento(1,"Depto", "DE");
		dept.setSigla("DER");
		

		assertEquals(esperado,dept.getSigla());
	}
	
	@Test(expected = SiglaNull.class)
	public void testSetSiglaNull() throws Exception {
		String esperado = "DER";
		dept = new Departamento(1,"Depto", "DE");
		dept.setSigla(null);
	}
}