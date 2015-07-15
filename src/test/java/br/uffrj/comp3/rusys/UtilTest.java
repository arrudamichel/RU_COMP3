package br.uffrj.comp3.rusys;

import static org.junit.Assert.*;

import org.junit.Test;

import br.uffrj.comp3.rusys.util.Util;

public class UtilTest {

	@Test
	public void testValidaCpfOK() {
		String cpf = "11111111111";
		
		boolean expected = true;
		
		boolean actual = Util.valida(cpf);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testValidaCpfInvalidLength() {
		String cpf = "1111111111";
		
		boolean expected = false;
		
		boolean actual = Util.valida(cpf);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testValidaCpfInvalidNumber() {
		String cpf = "11111111112";
		
		boolean expected = false;
		
		boolean actual = Util.valida(cpf);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testValidaCpfInvalidChar() {
		String cpf = "1111111111p";
		
		boolean expected = false;
		
		boolean actual = Util.valida(cpf);
		
		assertEquals(expected, actual);
	}

}
