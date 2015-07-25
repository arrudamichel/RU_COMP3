package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.interfaces.Refeicao;
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
//		String idtick = (String) request.getParameter("idTicket");
		
		String turno = (String) request.getParameter("turno");
		
		String acao = (String) request.getParameter("acao");
		
		try
		{
			if (acao != null)
			{
				switch (acao)
				{
					case Constantes.CANCELAR:					
						request.getRequestDispatcher("/WEB-INF/ListarTicket.jsp").forward(request, response);
					break;
					case Constantes.ACAO_SALVAR:
						cadastrar(request, response);
						
						break;
					case Constantes.ACAO_EDITAR:
						editar(request, response);
						break;
					case Constantes.ACAO_PROXIMO:
						
						String matricula = (String) request.getParameter("matricula");
						
						if(matricula.equals("") || turno.equals("")){
							request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
							request.getRequestDispatcher("/WEB-INF/CadTicket.jsp").forward(request, response);
						} else {															
							
							Consumidor consumidor = null;
							
							consumidor = ConsumidorHandler.recuperarConsumidorPorMatricula(Integer.parseInt(matricula));
							
							if(consumidor == null){
								request.setAttribute("mensagem", Constantes.ERRO_MAT);
								request.getRequestDispatcher("/WEB-INF/CadTicket.jsp").forward(request, response);
							} else {
							
								ArrayList<Refeicao> refeicoesTurno = null;
								
								refeicoesTurno = RefeicaoHandler.recuperarRefeicaoPorTurno(turno);
											
								request.setAttribute("refeicoesTurno", refeicoesTurno);	
		
								TicketVO ticket = new TicketVO();
								ticket.setConsumidorId(consumidor.getId());
								
								if (consumidor instanceof Aluno)
								{		
									ticket.setValor(Constantes.mapaTurnoConsumidor_PRECO.get(refeicoesTurno.iterator().next().getTurno().toString() + Aluno.class));
								}
								else if (consumidor instanceof Funcionario)
								{
									ticket.setValor(Constantes.mapaTurnoConsumidor_PRECO.get(refeicoesTurno.iterator().next().getTurno().toString() + Funcionario.class));
								}
											
								request.setAttribute("ticket", ticket);
									
								request.getRequestDispatcher("/WEB-INF/CadTicketSalva.jsp").forward(request, response);
							}
						}
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
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("id");
		String pago = request.getParameter("pago");
				
		TicketVO ticketVO = new TicketVO();
		ticketVO.setId(Integer.parseInt(id));
		
		if(Integer.parseInt(pago) == 1){			
			ticketVO.setPago(true);
		} else if (Integer.parseInt(pago) == 0) 
		{
			ticketVO.setPago(false);
		}

		TicketHandler.atualizarTicket(ticketVO);
			
		toListar(Constantes.SUCESSO, request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String consumidorId = request.getParameter("consumidor_id");
		String refeicaoId = request.getParameter("refeicao");
		String pago = request.getParameter("pago");
		String valor = request.getParameter("valor");
		
		TicketVO ticketVO = new TicketVO();
		ticketVO.setConsumidorId(Integer.parseInt(consumidorId));
		ticketVO.setPago(Boolean.parseBoolean(pago));
		ticketVO.setRefeicao(Integer.parseInt(refeicaoId));
		ticketVO.setValor(Float.parseFloat(valor));


		TicketHandler.cadastrarTicket(ticketVO);
			
		toListar(Constantes.SUCESSO, request, response);
	}
	
	private void toListar(String msg, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		iniciaCampos(request, response);
		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/WEB-INF/ListarTicket.jsp").forward(request, response);			
	}

	private void iniciaCampos(HttpServletRequest request, HttpServletResponse response) 
	{
		response.setContentType("text/html");
		
		ArrayList<Ticket> tickets = null;
		try 
		{
			tickets = (ArrayList<Ticket>) TicketHandler.recuperarTickets(new TicketVO());
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		request.setAttribute("tickets", tickets);
		
		ArrayList<Consumidor> consumidores = null;
		try 
		{
			consumidores = (ArrayList<Consumidor>) ConsumidorHandler.recuperarConsumidor(new ConsumidorVO());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("consumidores", consumidores);
				
		ArrayList<Refeicao> refeicoes = null;
		try {
			refeicoes = RefeicaoHandler.recuperarRefeicoes(new RefeicaoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("refeicoes", refeicoes);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		iniciaCampos(request, response);
		
		try
		{
			String acao = (String) request.getParameter("acao");
	
			if (acao != null)
			{
				switch (acao)
				{
					case Constantes.NOVO:
						request.getRequestDispatcher("/WEB-INF/CadTicket.jsp").forward(request, response);
						break;
					case Constantes.ACAO_EDITAR:
						
						String idtick = (String) request.getParameter("id");
						
						Ticket ticket = null;
			
						ticket = TicketHandler.recuperarTicket(Integer.parseInt(idtick));
					
						request.setAttribute("ticket", ticket);
						
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
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}
	}
}
