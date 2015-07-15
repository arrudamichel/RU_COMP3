package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsumidorTest {
	
	Consumidor con;

	@Test
	public void testConsumidorStringIntStringSexoEnumTituloEnumString() throws Exception {
		 String esperado = "Jessica";
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
	 
	
		assertEquals(esperado, con.getNome());
	}

	@Test
	public void testConsumidor() throws Exception {
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
	}

	@Test
	public void testGetNome() throws Exception {
		String esperado = "Jessica";
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
	
		assertEquals(esperado, con.getNome());
	}

	@Test
	public void testSetNome() throws Exception {
		String esperado = "Julia";
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){};
		 con.setNome("Julia");
	
		assertEquals(esperado, con.getNome());
	}

	@Test
	public void testGetMatricula() throws Exception {
		int esperado = 2011785144;
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
	
		assertEquals(esperado, con.getMatricula());
	}

	@Test
	public void testSetMatricula() throws Exception {
		int esperado = 2011780123;
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
		 con.setMatricula(2011780123);
			
		 assertEquals(esperado, con.getMatricula());
	
	}

	@Test
	public void testGetAnoDeIngresso() throws Exception {
		String esperado = "2011";
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
			
		
		assertEquals(esperado, con.getAnoDeIngresso());
	}

	@Test
	public void testSetAnoDeIngresso() throws Exception {
		String esperado = "2012";
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
		con.setAnoDeIngresso("2012");
		
		assertEquals(esperado, con.getAnoDeIngresso());
	}

	@Test
	public void testGetSexo() throws Exception {
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
		
		 con.setSexo(SexoEnum.fromString("Feminino"));
			
		assertEquals("Feminino", con.getSexo().toString());
	}

	@Test
	public void testSetSexo() throws Exception {
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
			
		 con.setSexo(SexoEnum.fromString("Feminino"));
			
		assertEquals("Feminino", con.getSexo().toString());
	}

	@Test
	public void testGetTitulo() throws Exception {
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
			
		 con.setTitulo(TituloEnum.fromString("Especializacao"));
			
		assertEquals("Especializacao", con.getTitulo().toString());;
	}

	@Test
	public void testSetTitulo() throws Exception {
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
			
		 con.setTitulo(TituloEnum.fromString("Especializacao"));
			
		assertEquals("Especializacao", con.getTitulo().toString());;
	}

	@Test
	public void testGetCpf() throws Exception {
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
			
		 con.setCpf("13638466710");
		 System.out.println("cpf" + con.getCpf());
			
		assertEquals("13638466710", con.getCpf());
	}

	@Test
	public void testSetCpf() throws Exception {
		 con = new Consumidor(1, "Jessica", 2011785144, "2011"){}; 
			
		 con.setCpf("13638466710");
			
		assertEquals("13638466710", con.getCpf());
	}



}
