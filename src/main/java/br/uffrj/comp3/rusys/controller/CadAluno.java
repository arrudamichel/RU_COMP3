package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CadastrarAluno")
public class CadAluno extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		request.setAttribute("mensagem", "erro");
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/CadAlunos.jsp");
		rd.forward(request, response);
	}
}
