package br.uffrj.comp3.rusys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/AtualizarDepartamento")
public class AtualizarDepartamento extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");

		String acao = (String) request.getParameter("acao");

		String departamentoId = (String) request.getParameter("departamentoId");

	
		Departamento departamento = null;
		try
		{
			departamento = DepartamentoHandler.recuperarDepartamento(Integer.parseInt(request.getParameter("departamentoId")));
		} catch (NumberFormatException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		request.setAttribute("departamento", departamento);

		if (acao != null)
		{
			switch (acao)
			{
			case Constantes.SALVAR:
				atualizar(departamentoId, request, response);
				break;
			default:
				try
				{
					request.getRequestDispatcher("/WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
				} catch (Exception e)
				{
					request.setAttribute("erro", "O curso informado nao existe");
					request.getRequestDispatcher("/WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
				}
				// request.getRequestDispatcher("ListarCursos").forward(request,
				// response);
			}
		} else
		{

		}
	}
	
	private void atualizar(String cursoId, HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("departamentoId");
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");

		Departamento departamento = null;
		try
		{
			departamento = DepartamentoHandler.recuperarDepartamento(Integer.parseInt(id));
			
			departamento.setNome(nome);
			departamento.setSigla(sigla);			
		} catch (NumberFormatException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			DepartamentoHandler.atualizarDepartamento(departamento);

			request.getRequestDispatcher("/WEB-INF/CadDepartamento.jsp").forward(request, response);
		} catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doPost(req, resp);
	}
}
