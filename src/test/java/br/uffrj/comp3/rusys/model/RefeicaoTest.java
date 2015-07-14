package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefeicaoTest {
	
	Refeicao re;
	

	@Test
	public void testRefeicao() {
		re= new Refeicao();
		
	}

	@Test
	public void testGetDescricao() {
		String esperado = "arroz";
		re= new Refeicao();
		re.setDescricao("arroz");
		
		assertEquals(esperado,re.getDescricao());
	}

	@Test
	public void testSetDescricao() {
		String esperado = "arroz";
		re= new Refeicao();
		re.setDescricao("arroz");
		
		assertEquals(esperado,re.getDescricao());
	}

	@Test
	public void testGetOpcaoVeg() {
		String esperado = "arroz";
		re= new Refeicao();
		re.setOpcaoVeg("arroz");
		
		assertEquals(esperado,re.getOpcaoVeg());
	}

	@Test
	public void testSetOpcaoVeg() {
		String esperado = "arroz";
		re= new Refeicao();
		re.setOpcaoVeg("arroz");
		
		assertEquals(esperado,re.getOpcaoVeg());
	}
	

	@Test
	public void testGetTurno() {
		
		re= new Refeicao();
		re.setTurno(TurnoEnum.MANHA);
		
		assertEquals(TurnoEnum.MANHA,re.getTurno());
	}
	

	@Test
	public void testSetTurno() {
		re= new Refeicao();
		re.setTurno(TurnoEnum.MANHA);
		
		assertEquals(TurnoEnum.MANHA,re.getTurno());
	}

	

	@Test
	public void testGetIdentificador() {
		re= new Refeicao();
		re.setIdentificador(1);
		
		assertEquals(1,re.getIdentificador());
	}

	@Test
	public void testSetIdentificador() {
		re= new Refeicao();
		re.setIdentificador(1);
		
		assertEquals(1,re.getIdentificador());
	}

}
