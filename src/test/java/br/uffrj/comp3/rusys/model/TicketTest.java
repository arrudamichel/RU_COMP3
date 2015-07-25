package br.uffrj.comp3.rusys.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import br.uffrj.comp3.rusys.service.exceptions.*;

public class TicketTest {

	private Consumidor aluno;
	private Consumidor funcionario;
	private Curso curso;
	private Departamento departamento;
	private Refeicao refNoite;
	private Refeicao refManha;
	private Refeicao refTarde;

	@Before
	public void setUp() throws Exception {
		departamento = new Departamento(1, "Departamento de Ciencia da Computacao", "DCC");
		funcionario = new Funcionario(1, "Funcionario", 123, 2010, departamento);
		curso = new Curso(1, "Ciencia da Computacao", "CCOMP", departamento);
		aluno = new Aluno(2, "Aluno", 124, 2011, curso);
		refNoite = new Refeicao(1, "refNoite", TipoRefeicaoEnum.JANTAR);
		refTarde = new Refeicao(2, "refTarde", TipoRefeicaoEnum.ALMOCO);
		refManha = new Refeicao(3, "refManha", TipoRefeicaoEnum.DEJEJUM);
	}

	@Test
	public void testaTicketAlunoOk() throws Exception {
		Ticket ticket = new Ticket(1, true, aluno, refNoite);
	}

	@Test
	public void testaTicketFuncionarioOk() throws Exception {
		Ticket ticket = new Ticket(1, true, funcionario, refNoite);
	}

	@Test(expected = ConsumidorNull.class)
	public void testaTicketConsumidorNull() throws Exception {
		Ticket ticket = new Ticket(1, true, null, refNoite);
	}

	@Test(expected = RefeicaoNull.class)
	public void testaTicketRefeicaoNull() throws Exception {
		Ticket ticket = new Ticket(1, true, aluno, null);
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