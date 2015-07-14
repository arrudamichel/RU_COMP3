package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/CadastrarAluno")
public class CadAluno extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		/*DepartamentoVO departamentoVO = new DepartamentoVO();
				
		Collection<Departamento> departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		
		request.setAttribute("departamentos", departamentos);*/

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					//cadastrar(request, response);
					break;
				default:
					request.getRequestDispatcher("CadastrarAluno").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("WEB-INF/CadAlunos.jsp").forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
