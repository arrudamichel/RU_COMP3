package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsumidorTest {
	
	Consumidor con;

	@Test
	public void testConsumidorStringIntStringSexoEnumTituloEnumString() {
		 String esperado = "Jessica";
		 con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
	 
	
		assertEquals(esperado, con.getNome());
	}

	@Test
	public void testConsumidor() {
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
	}

	@Test
	public void testGetNome() {
		String esperado = "Jessica";
		 con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
	 
	
		assertEquals(esperado, con.getNome());
	}

	@Test
	public void testSetNome() {
		String esperado = "Julia";
		 con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
	 con.setNome("Julia");
	
		assertEquals(esperado, con.getNome());
	}

	@Test
	public void testGetMatricula() {
		int esperado = 2011785144;
		 con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		 
	
		assertEquals(esperado, con.getMatricula());
	}

	@Test
	public void testSetMatricula() {
		int esperado = 2011780123;
		 con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		 con.setMatricula(2011780123);
	
		assertEquals(esperado, con.getMatricula());
	}

	@Test
	public void testGetAnoDeIngresso() {
		String esperado = "2011";
		 con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		
		assertEquals(esperado, con.getAnoDeIngresso());
	}

	@Test
	public void testSetAnoDeIngresso() {
		String esperado = "2012";
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		con.setAnoDeIngresso("2012");
		
		assertEquals(esperado, con.getAnoDeIngresso());
	}

	@Test
	public void testGetSexo() {
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		
			
		assertEquals(SexoEnum.F, con.getSexo());
	}

	@Test
	public void testSetSexo() {
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		con.setSexo(SexoEnum.M);
		
		assertEquals(SexoEnum.M, con.getSexo());
	}

	@Test
	public void testGetTitulo() {
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		
			
		assertEquals(TituloEnum.ESPECIALIZACAO, con.getTitulo());
	}

	@Test
	public void testSetTitulo() {
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		con.setTitulo(TituloEnum.MESTRADO);
			
		assertEquals(TituloEnum.MESTRADO, con.getTitulo());
	}

	@Test
	public void testGetCpf() {
		String esperado = "12345678910";
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		
			
		assertEquals(esperado, con.getCpf());
	}

	@Test
	public void testSetCpf() {
		String esperado = "12345678911";
		con = new Consumidor("Jessica", 2011785144, "2011", SexoEnum.F, TituloEnum.ESPECIALIZACAO , "12345678910"){};
		con.setCpf("12345678911");
			
		assertEquals(esperado, con.getCpf());
	}



}
