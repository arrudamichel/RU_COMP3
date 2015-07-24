package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.Ticket;

public class TicketTest {
	private Consumidor aluno;
	private Consumidor funcionario;
	private Departamento departamento;	
	private Refeicao refNoite;
	private Refeicao refManha;
	private Refeicao refTarde;
	private Curso curso;

	
	@Before
	public void setUp() throws Exception {
		departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		funcionario = new Funcionario(1, "Funcionario", 123, "2010", departamento);
		curso = new Curso(1, "Ciencia da Computacao","CCOMP", departamento);
		aluno = new Aluno(2, "Aluno", 124, "2011", curso);
		refNoite = new Refeicao(1, "refNoite", TipoRefeicaoEnum.JANTAR);
		refNoite.setOpcaoVeg("Salada");
		refTarde = new Refeicao(2, "refTarde", TipoRefeicaoEnum.ALMOCO);
		refTarde.setOpcaoVeg("Salada");
		refManha = new Refeicao(3, "refManha", TipoRefeicaoEnum.DEJEJUM);
		refManha.setOpcaoVeg("Salada");
	}

	@Test
	public void testPrecoDoAlunoManha() throws Exception {
		Ticket ticketAluno = new Ticket(1, true, aluno, refManha);
		
		Float expected = new Float(0.5);
		
		Float actual = ticketAluno.getValor();
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPrecoDoAlunoTarde() throws Exception {
		Ticket ticketAluno = new Ticket(1, true, aluno, refTarde);
		
		Float expected = new Float(1.0);
		
		Float actual = ticketAluno.getValor();
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPrecoDoAlunoNoite() throws Exception {
		Ticket ticketAluno = new Ticket(1, true, aluno, refNoite);
		
		Float expected = new Float(1.0);
		
		Float actual = ticketAluno.getValor();
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPrecoDoFuncionarioTarde() throws Exception {
		Ticket ticketFuncionario = new Ticket(1, true, funcionario, refTarde);
		
		Float expected = new Float(6.0);
		
		Float actual = ticketFuncionario.getValor();
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPrecoDoFuncionarioNoite() throws Exception {
		Ticket ticketFuncionario = new Ticket(1, true, funcionario, refNoite);
		
		Float expected = new Float(6.0);
		
		Float actual = ticketFuncionario.getValor();
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testPrecoDoFuncionarioManha() throws Exception {
		Ticket ticketFuncionario = new Ticket(1, true, funcionario, refManha);
		
		Float expected = new Float(3.0);
		
		Float actual = ticketFuncionario.getValor();
		
		assertEquals(actual, expected);
	}

}
