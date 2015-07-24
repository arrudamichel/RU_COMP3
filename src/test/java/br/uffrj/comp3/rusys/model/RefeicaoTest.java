package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefeicaoTest {

	Refeicao ref;

	@Test
	public void testRefeicaoOk() throws Exception {
		ref = new Refeicao(1, "Refeicao", TipoRefeicaoEnum.ALMOCO);
	}
	
	@Test(expected = Exception.class)
	public void testRefeicaoFail() throws Exception {
		ref = new Refeicao(1, null, TipoRefeicaoEnum.ALMOCO);
	}

	@Test
	public void testSetDescricaoOk() throws Exception {
		String esperado = "arroz";
		ref = new Refeicao(1, "Refeicao", TipoRefeicaoEnum.ALMOCO);
		ref.setDescricao("arroz");

		assertEquals(esperado, ref.getDescricao());
	}
	
	@Test(expected = Exception.class)
	public void testSetDescricaoFail() throws Exception {
		ref = new Refeicao(1, "Refeicao", TipoRefeicaoEnum.ALMOCO);
		ref.setDescricao(null);
	}
}