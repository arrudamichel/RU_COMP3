package br.uffrj.comp3.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.uffrj.comp3.rusys.model.Dejejum;
import br.uffrj.comp3.rusys.model.Refeicao;

public class RefeicaoTest {
	
	@Test
	public void testaConstrutorOk() throws Exception {
		Refeicao dejejum = new Dejejum(1, "dejejum");
	}
	
	@Test(expected = Exception.class)
	public void testaConstrutorFail() throws Exception {
		Refeicao dejejum = new Dejejum(1, null);
	}
	
	@Test
	public void testaSetterOk() throws Exception {
		Refeicao dejejum = new Dejejum(1, "dejejum");
		dejejum.setDescricao("dejejumNovo");
		
		assertEquals(dejejum.getDescricao(), "dejejumNovo");
	}
	
	@Test(expected = Exception.class)
	public void testaSetterFail() throws Exception {
		Refeicao dejejum = new Dejejum(1, null);
		dejejum.setDescricao(null);
	}

}
