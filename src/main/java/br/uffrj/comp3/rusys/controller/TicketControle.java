package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.model.vo.TicketVO;
import br.uffrj.comp3.rusys.service.ConsumidorHandler;
import br.uffrj.comp3.rusys.service.RefeicaoHandler;
import br.uffrj.comp3.rusys.service.TicketHandler;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/GerirTicket")
public class TicketControle extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		String idtick = (String) request.getParameter("idTicket");
		
		ArrayList<Ticket> tickets = null;
		try {
			tickets = (ArrayList<Ticket>) TicketHandler.recuperarTickets(new TicketVO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("tickets", tickets);
		
		ArrayList<Consumidor> consumidores = null;
		try {
			consumidores = (ArrayList<Consumidor>) ConsumidorHandler.recuperarConsumidor(new ConsumidorVO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("consumidores", consumidores);
		
		
		ArrayList<Refeicao> refeicoes = null;
		try {
			refeicoes = RefeicaoHandler.recuperarRefeicoes(new RefeicaoVO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("refeicoes", refeicoes);
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(acao);

		
		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.CANCELAR:					
					request.getRequestDispatcher("/WEB-INF/ListarTicket.jsp").forward(request, response);
				break;
				case Constantes.ACAO_SALVAR:
					cadastrar(request, response);
					response.sendRedirect("GerirTicket");
					break;
				case Constantes.ACAO_EDITAR:
					editar(request, response);
					break;
				default:
					request.getRequestDispatcher("/WEB-INF/ListarTicket.jsp").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("/WEB-INF/ListarTicket.jsp").forward(request, response);
		}	
	}

	private void editar(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		String pago = request.getParameter("pago");
		
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(pago +" - "+ id);
		TicketVO ticketVO = new TicketVO();
		ticketVO.setId(Integer.parseInt(id));
		
		if(Integer.parseInt(pago) == 1)
			TicketVO.setPago(true);
		else if (Integer.parseInt(pago) == 0)
			TicketVO.setPago(false);
		try
		{
			TicketHandler.atualizarTicket(ticketVO);

			response.sendRedirect("GerirTicket");
		} catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
		
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) {
		
//		String identificador = request.getParameter("id");
//
//		Refeicao refeicao = null;
//		try
//		{
//			refeicao = RefeicaoHandler.recuperarRefeicao(Integer.parseInt(identificador));
//		} catch (NumberFormatException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (Exception e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		try
//		{
//			RefeicaoHandler.excluirRefeicao(refeicao);
//		} 
//		catch (Exception e)
//		{
//			request.setAttribute("mensagem", Constantes.ERRO);
//		}
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		String descricao = request.getParameter("descricao");
//		String opcaoVeg = request.getParameter("opVeg");
//		String turno = request.getParameter("turno");
//
//		
//		RefeicaoVO refeicaoVO = new RefeicaoVO();
//		refeicaoVO.setDescricao(descricao);
//		refeicaoVO.setOpcaoVeg(opcaoVeg);
//		refeicaoVO.setTurno(TurnoEnum.fromString(turno));
//		
//		try
//		{
//			RefeicaoHandler.cadastrarRefeicao(refeicaoVO);
//		} 
//		catch (Exception e)
//		{
//			request.setAttribute("mensagem", Constantes.ERRO);
//		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Ticket> tickets = null;
		try {
			tickets = (ArrayList<Ticket>) TicketHandler.recuperarTickets(new TicketVO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("tickets", tickets);
		
		String idtick = (String) request.getParameter("id");
		Ticket ticket = null;
		try {
			ticket = TicketHandler.recuperarTicket(Integer.parseInt(idtick));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("ticket", ticket);
		
		String acao = (String) request.getParameter("acao");

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.ACAO_SALVAR:
					request.getRequestDispatcher("/WEB-INF/CadTicket.jsp").forward(request, response);
					break;
				case Constantes.ACAO_EDITAR:
					request.getRequestDispatcher("/WEB-INF/AtualizarTicket.jsp").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("/WEB-INF/ListarTicket.jsp").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("/WEB-INF/ListarTicket.jsp").forward(request, response);
		}
	}
}
