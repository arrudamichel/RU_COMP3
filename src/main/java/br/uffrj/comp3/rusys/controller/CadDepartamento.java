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



@WebServlet("/CadastrarDepartamento")
public class CadDepartamento extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
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

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					cadastrar(request, response);
					break;
				case Constantes.ACAO_EDITAR:
					request.getRequestDispatcher("AtualizarDepartamento").forward(request, response);
					break;
				case Constantes.ACAO_LISTAR:
					request.getRequestDispatcher("/WEB-INF/CadDepartamento.jsp").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarDepartamento").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("/WEB-INF/CadDepartamento.jsp").forward(request, response);
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");

		DepartamentoVO dptoVO = new DepartamentoVO();
		dptoVO.setNome(nome);
		dptoVO.setSigla(sigla);	
		
		try
		{
			DepartamentoHandler.cadastrarDepartamento(dptoVO);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
	}

	public ArrayList<Departamento> listaDepartamentos()
	{
		try
		{
			return DepartamentoHandler.recuperarDepartamentos(new DepartamentoVO());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
