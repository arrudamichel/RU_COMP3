package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefeicaoTest {
	
	Refeicao re;
	

	@Test
	public void testRefeicao() throws Exception {
		re= new Refeicao(1,"arroz"){};
		
	}
	
	@Test(expected = Exception.class)
	public void testRefeicaoDescricao() throws Exception {
		re= new Refeicao(1,null){};
		
	}

	@Test
	public void testGetDescricao() throws Exception {
		String esperado = "arroz";
		re= new Refeicao(1,"arroz"){};
		
		assertEquals(esperado,re.getDescricao());
	}

	@Test
	public void testSetDescricao() throws Exception {
		String esperado = "arroz";
		re= new Refeicao(1,"feijao"){};
		re.setDescricao("arroz");
		
		assertEquals(esperado,re.getDescricao());
	}

	@Test
	public void testGetOpcaoVeg() throws Exception {
		String esperado = "arroz";
		re= new Refeicao(1,"feijao"){};
		re.setOpcaoVeg("arroz");
		
		assertEquals(esperado,re.getOpcaoVeg());
	}

	@Test
	public void testSetOpcaoVeg() throws Exception {
		String esperado = "arroz";
		re= new Refeicao(1,"feijao"){};
		re.setOpcaoVeg("arroz");
		
		assertEquals(esperado,re.getOpcaoVeg());
	}
	

	@Test
	public void testGetTurno() throws Exception {
		
		re= new Refeicao(1,"arroz"){};
		re.setTurno(TurnoEnum.MANHA);
		
		assertEquals(TurnoEnum.MANHA,re.getTurno());
	}
	

	@Test
	public void testSetTurno() throws Exception {
		re= new Refeicao(1,"arroz"){};
		re.setTurno(TurnoEnum.MANHA);
		
		assertEquals(TurnoEnum.MANHA,re.getTurno());
	}

	

	@Test
	public void testGetIdentificador() throws Exception {
		re= new Refeicao(1,"arroz"){};
		
		
		
		
		assertEquals(1,re.getIdentificador());
	}

}
