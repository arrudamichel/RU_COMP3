package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/CadastrarTicket")
public class CadTicket extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					//cadastrar(request, response);
					break;
				default:
					request.getRequestDispatcher("CadastrarTicket").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("WEB-INF/CadTicket.jsp").forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
