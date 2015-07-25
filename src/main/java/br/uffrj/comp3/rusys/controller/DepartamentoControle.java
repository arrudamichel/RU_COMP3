package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.util.Constantes;



@WebServlet("/GerirDepartamento")
public class DepartamentoControle extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		iniciaCampos(request, response);
		try
		{
			String acao = (String) request.getParameter("acao");
	
			if (acao != null)
			{
				switch (acao)
				{
					case Constantes.ACAO_DELETAR:
						excluir(request, response);
//						response.sendRedirect("GerirDepartamento");
						break;
					case Constantes.ACAO_EDITAR:
						editar(request, response);
//						response.sendRedirect("GerirDepartamento");
						break;
					case Constantes.ACAO_SALVAR:
						cadastrar(request, response);
//						response.sendRedirect("GerirDepartamento");
						break;
					default:
						request.getRequestDispatcher("/WEB-INF/ListDepartamento.jsp").forward(request, response);
				}
			} 
			else
			{
				request.getRequestDispatcher("/WEB-INF/ListDepartamento.jsp").forward(request, response);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
	{
		// TODO Auto-generated method stub
		
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("departamentoId");
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");

		if (nome.equals("") || sigla.equals(""))
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
		} else {
			DepartamentoVO departamento = new DepartamentoVO();
		
			departamento.setId(Integer.parseInt(id));
			departamento.setNome(nome);
			departamento.setSigla(sigla);			
			
			DepartamentoHandler.atualizarDepartamento(departamento);
				
			toListar(Constantes.SUCESSO, request, response);
		}
	}
	
	private void toListar(String msg, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		iniciaCampos(request, response);
		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/WEB-INF/ListDepartamento.jsp").forward(request, response);
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");

		if (nome.equals("") || sigla.equals(""))
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
		} else {
			DepartamentoVO dptoVO = new DepartamentoVO();
			dptoVO.setNome(nome);
			dptoVO.setSigla(sigla);	
			
			DepartamentoHandler.cadastrarDepartamento(dptoVO);
				
			toListar(Constantes.SUCESSO, request, response);
		}		
	}

	public ArrayList<Departamento> listaDepartamentos() throws SQLException, Exception
	{
		return DepartamentoHandler.recuperarDepartamentos(new DepartamentoVO());
	}
	
	private void iniciaCampos(HttpServletRequest request, HttpServletResponse response) 
	{
		response.setContentType("text/html");
		
		String departamentoId = request.getParameter("departamentoId");

		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		Collection<Departamento> departamentos = null;
		try
		{
			departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block	
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("departamentos", departamentos);
		
		//-------------------- Departamento ---------------
		
		if (departamentoId != null)
		{
			Departamento departamento = null;
			try
			{
				departamento = DepartamentoHandler.recuperarDepartamento(Integer.parseInt(departamentoId));
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
		}	
	}
	
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
						request.getRequestDispatcher("/WEB-INF/CadDepartamento.jsp").forward(request, response);
						break;
					case Constantes.ACAO_EDITAR:					
						request.getRequestDispatcher("/WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
						break;
					default:
						request.getRequestDispatcher("/WEB-INF/ListDepartamento.jsp").forward(request, response);
				}
			} 
			else
			{
				request.getRequestDispatcher("/WEB-INF/ListDepartamento.jsp").forward(request, response);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}
	}
}
