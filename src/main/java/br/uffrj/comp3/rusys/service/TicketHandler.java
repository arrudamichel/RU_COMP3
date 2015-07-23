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

		int pago = 0;
		if(ticketVO.isPago())
			pago = 1;
		else
			pago = 2;
		
		//consumidor_id  	refeicao_id_refeicao  	preco  	pago
		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(ticketVO.getConsumidorId(), ticketVO.getRefeicao(), ticketVO.getValor(), pago));

		if (ticketGateway.inserir(valores)==null)
			throw new Exception("falha.ao.cadastrar.ticket");

		conn.close();

	}
	
	public static Collection<Ticket> recuperarTickets(TicketVO ticketVO) throws Exception
	{		
		ArrayList<Ticket> tickets = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TicketGateway ag = new TicketGateway(conn);
		ResultSet rsTickect = ag.selecionarTickets();
		
		Ticket ticket = null;
		
		while (rsTickect.next())
		{
					
			int consuId = rsTickect.getInt("consumidor_id");
			int refeicaoId = rsTickect.getInt("refeicao_id_refeicao");
			int id = rsTickect.getInt("ticket_id");
						
			// ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao")
			Consumidor consumidor = ConsumidorHandler.recuperarConsumidor(consuId);
			
			if(consumidor != null){
				Refeicao refeicao = RefeicaoHandler.recuperarRefeicao(refeicaoId);
		
				//public Ticket(int id, boolean pago,Consumidor consumidor, Refeicao refeicao)
				// ("consumidor_matricula", "refeicao_idRefeicao", "preco", "pago")
				
				boolean pago = rsTickect.getInt("pago") == 1 ? true : false;
				
				ticket = new Ticket(id, pago, consumidor, refeicao);			
				
				tickets.add(ticket);
			}
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
		
		int consuId = rs.getInt("consumidor_id");
		int refeicaoId = rs.getInt("refeicao_id_refeicao");
				
		// ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao")
		Consumidor consumidor = ConsumidorHandler.recuperarConsumidor(consuId);
		
		if(consumidor != null){
			Refeicao refeicao = RefeicaoHandler.recuperarRefeicao(refeicaoId);

			//public Ticket(int id, boolean pago,Consumidor consumidor, Refeicao refeicao)
			// ("consumidor_matricula", "refeicao_idRefeicao", "preco", "pago")
					
			boolean pago = rs.getInt("pago") == 1 ? true : false;
			
			Ticket ticket = new Ticket(id, pago, consumidor, refeicao);
			
			conn.close();

			return ticket;
		}
		
		return null;
	}
	
	public static void excluirTicket(Ticket ticket) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway cg = new TicketGateway(conn);

		if (!cg.excluirTicket(ticket.getId()))
			throw new Exception("falha.ao.excluir.Ticket");
		
		conn.close();
	}
	
	public static void atualizarTicket(TicketVO ticketVO) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway cg = new TicketGateway(conn);

		int pago = 0;
				
		if(ticketVO.isPago() == true)
			pago = 1;
		else if(ticketVO.isPago() == false)
			pago = 0;
				
		if (!cg.alterarTicket(pago, ticketVO.getId()))
			throw new Exception("falha.ao.alterar.Ticket");
		
		conn.close();
	}
}
