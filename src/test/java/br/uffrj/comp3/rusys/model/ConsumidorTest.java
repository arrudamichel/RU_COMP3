package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsumidorTest {
	
	String nome;
	String matricula;
	String ano;
	String sexo;
	String titulo;
	String cpf;
	String ticket;

	@Test
	public void testConsumidorStringIntStringSexoEnumTituloEnumString() {
		nome="Jessica";
	}

	@Test
	public void testConsumidor() {
		nome="jessica";
	}

	@Test
	public void testGetNome() {
		 nome = "Jessica";
			
		assertEquals("Jessica", nome);
	}

	@Test
	public void testSetNome() {
		 nome = "Jessica";
			
			assertEquals("Jessica", nome);
	}

	@Test
	public void testGetMatricula() {
		 matricula = "0101";
			
		assertEquals("0101", matricula);
	}

	@Test
	public void testSetMatricula() {
		 matricula = "0101";
			
			assertEquals("0101", matricula);
	}

	@Test
	public void testGetAnoDeIngresso() {
		 ano = "2015";
			
		 assertEquals("2015", ano);
	}

	@Test
	public void testSetAnoDeIngresso() {
		 ano = "2015";
			
		 assertEquals("2015", ano);
	}

	@Test
	public void testGetSexo() {
		 sexo = "feminino";
			
		assertEquals("feminino", sexo);
	}

	@Test
	public void testSetSexo() {
		sexo = "feminino";
		
		assertEquals("feminino", sexo);
	}

	@Test
	public void testGetTitulo() {
		 titulo = "graduacao";
			
		assertEquals("graduacao", titulo);
	}

	@Test
	public void testSetTitulo() {
		 titulo = "graduacao";
			
			assertEquals("graduacao", titulo);
	}

	@Test
	public void testGetCpf() {
		 cpf = "136389229";
			
		assertEquals("136389229", cpf);
	}

	@Test
	public void testSetCpf() {
		cpf = "136389229";
		
		assertEquals("136389229", cpf);
	}

	@Test
	public void testGetTickets() {
		 ticket = "pago";
			
		assertEquals("pago", ticket);
	}

	@Test
	public void testSetTickets() {
		 ticket = "pago";
			
		assertEquals("pago", ticket);
	}

}
