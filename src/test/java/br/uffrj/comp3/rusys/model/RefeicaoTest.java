package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;
import br.uffrj.comp3.rusys.service.exceptions.*;


import org.junit.Test;

public class RefeicaoTest {

	RefeicaoImpl ref;

	@Test
	public void testRefeicaoOk() throws Exception {
		ref = new RefeicaoImpl(1, "Refeicao", TipoRefeicaoEnum.ALMOCO);
	}
	
	@Test(expected = DescricaoNull.class)
	public void testRefeicaoFail() throws Exception {
		ref = new RefeicaoImpl(1, null, TipoRefeicaoEnum.ALMOCO);
	}
	
	@Test(expected = TipoNullOrVazio.class)
	public void testRefeicaoTipoFail() throws Exception {
		ref = new RefeicaoImpl(1, "Refeicao", null);
	}

	@Test
	public void testSetDescricaoOk() throws Exception {
		String esperado = "arroz";
		ref = new RefeicaoImpl(1, "Refeicao", TipoRefeicaoEnum.ALMOCO);
		ref.setDescricao("arroz");

		assertEquals(esperado, ref.getDescricao());
	}
	
	@Test(expected = DescricaoNull.class)
	public void testSetDescricaoFail() throws Exception {
		ref = new RefeicaoImpl(1, "Refeicao", TipoRefeicaoEnum.ALMOCO);
		ref.setDescricao(null);
	}
}