package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.RefeicaoImpl;
import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.interfaces.Refeicao;
import br.uffrj.comp3.rusys.model.vo.TicketVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.TicketGateway;
import br.uffrj.comp3.rusys.service.exceptions.AlterarNoBancoExeception;
import br.uffrj.comp3.rusys.service.exceptions.ExcluirDoBancoException;
import br.uffrj.comp3.rusys.service.exceptions.InsercaoNoBancoException;
import br.uffrj.comp3.rusys.util.Constantes;

public class TicketHandler {

	public static void cadastrarTicket(TicketVO ticketVO) throws Exception 
	{
		Consumidor consumidor = ConsumidorHandler.recuperarConsumidor(ticketVO.getConsumidorId());	
		Refeicao refeicao = RefeicaoHandler.recuperarRefeicao(ticketVO.getRefeicao());
		Ticket ticket = new Ticket(ticketVO.getId(), ticketVO.isPago(), consumidor, refeicao);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TicketGateway ticketGateway = new TicketGateway(conn);
		
		int pago = 0;
		if (ticket.isPago())
			pago = 1;
		else
			pago = 0;
		
		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(ticket.getConsumidor().getId(), ticket.getRefeicao().getId(), ticket.getValor(), pago));

		if (ticketGateway.inserir(valores)==null)
			throw new InsercaoNoBancoException();

		conn.close();

	}
	
	public static Collection<Ticket> recuperarTickets(TicketVO ticketVO) throws Exception
	{		
		ArrayList<Ticket> tickets = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TicketGateway ag = new TicketGateway(conn);
		ResultSet rsTickect = null;
		
		if (ticketVO.getId() != null) 
		{
			rsTickect = ag.selecionarTicketPorId(ticketVO.getId());
		} 
		else
		{
			rsTickect = ag.selecionarTickets();
		}
		
		Ticket ticket = null;
		
		while (rsTickect.next())
		{
			int id = rsTickect.getInt("id");
			
			int consuId = rsTickect.getInt("consumidor_fk");
			int refeicaoId = rsTickect.getInt("refeicao_fk");
						
			// ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao")
			Consumidor consumidor = ConsumidorHandler.recuperarConsumidor(consuId);
			
			if(consumidor != null)
			{
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
		TicketVO ticketVO = new TicketVO();
		ticketVO.setId(id);
		
		Collection<Ticket> tickets = recuperarTickets(ticketVO);
		
		if (tickets != null && tickets.size()==1) 
		{
			return tickets.iterator().next();
		}
		
		return null;
	}
	
	public static void excluirTicket(Ticket ticket) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway cg = new TicketGateway(conn);

		if (!cg.excluirTicket(ticket.getId()))
			throw new ExcluirDoBancoException();
		
		conn.close();
	}
	
	public static void atualizarTicket(TicketVO ticketVO) throws Exception
	{	
		Ticket ticket = recuperarTicket(ticketVO.getId());
		
		if (ticketVO.isPago()!=null)
		{
			ticket.setPago(ticketVO.isPago());
		}
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway cg = new TicketGateway(conn);

		if (!cg.alterarTicket(new ArrayList<Object>(Arrays.asList(ticket.isPago())), ticket.getId()))
			throw new AlterarNoBancoExeception();
		
		conn.close();
	}
}
