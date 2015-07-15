package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.vo.TicketVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.persintece.TicketGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class TicketHandler {

	public static void cadastrarTicket(TicketVO ticketVO) throws Exception 
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TicketGateway ticketGateway = new TicketGateway(conn);
		ConsumidorGateway consumidorGateway = new ConsumidorGateway(conn);
		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		ResultSet rs = consumidorGateway.selecionarConsumidorPorMatricula(ticketVO.getMatricula());

		int consumId = -1;

		rs.next();
		consumId = rs.getInt(1);

		rs = refeicaoGateway.selecionarRefeicaoPorId(ticketVO.getRefeicao());

		int refeicaoId = -1;

		rs.next();
		refeicaoId = rs.getInt(1);

		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(consumId, refeicaoId, ticketVO.getValor(), ticketVO.isPago()));

		if (ticketGateway.inserir(valores)==null)
			throw new Exception("falha.ao.cadastrar.ticket");

		conn.close();

	}
	
	public static Collection<Ticket> recuperarTickets(TicketVO ticketVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		ArrayList<Ticket> tickets = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TicketGateway ag = new TicketGateway(conn);
		ResultSet rsTickect = ag.selecionarTickets();

		Ticket ticket = null;
		
		while (rsTickect.next())
		{
		
			int consuId = rsTickect.getInt(2);
			int refeicaoId = rsTickect.getInt(2);
			
			// ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao")
			Consumidor consumidor = ConsumidorHandler.recuperarConsumidor(consuId);
			
			Refeicao refeicao = RefeicaoHandler.recuperarRefeicao(refeicaoId);

			//public Ticket(int id, boolean pago,Consumidor consumidor, Refeicao refeicao)
			// ("consumidor_matricula", "refeicao_idRefeicao", "preco", "pago")
			
			boolean pago = rsTickect.getInt(4) == 1 ? true : false;
			
			ticket = new Ticket(consuId,pago , consumidor, refeicao);
			
			tickets.add(ticket);
		}
		
		conn.close();
		
		return tickets;
	}

	public static Ticket recuperarTicket(int id) throws Exception 
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		TicketGateway ticketGateway = new TicketGateway(conn);
		ResultSet rs = ticketGateway.selecionarTicketPorId(id);
		rs.next();
		int consuId = rs.getInt(2);
		int refeicaoId = rs.getInt(2);
		
		// ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao")
		Consumidor consumidor = ConsumidorHandler.recuperarConsumidor(consuId);
		
		Refeicao refeicao = RefeicaoHandler.recuperarRefeicao(refeicaoId);

		//public Ticket(int id, boolean pago,Consumidor consumidor, Refeicao refeicao)
		// ("consumidor_matricula", "refeicao_idRefeicao", "preco", "pago")
		
		boolean pago = rs.getInt(4) == 1 ? true : false;
		
		Ticket ticket = new Ticket(consuId,pago , consumidor, refeicao);
		
		conn.close();

		return ticket;
	}
	
	public static void excluirTicket(Ticket ticket) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway cg = new TicketGateway(conn);

		if (!cg.excluirTicket(ticket.getId()))
			throw new Exception("falha.ao.excluir.Ticket");
		
		conn.close();
	}
}
