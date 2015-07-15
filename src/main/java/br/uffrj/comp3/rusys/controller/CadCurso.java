package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.CursoHandler;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.util.Constantes;


@WebServlet("/CadastrarCurso")
public class CadCurso extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		
//		departamentoVO.set(); campos de consulta
		
		Collection<Departamento> departamentos = null;
		try
		{
			departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		request.setAttribute("departamentos", departamentos);
		
		
		CursoVO cursoVO = new CursoVO();
		
//		departamentoVO.set(); campos de consulta
		
		Collection<Curso> cursos = null;
		try
		{
			cursos = CursoHandler.recuperarCursos(cursoVO);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("cursos", cursos);

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					cadastrar(request, response);
					break;
				case Constantes.ACAO_EDITAR:
					request.getRequestDispatcher("AtualizarCurso").forward(request, response);
					break;
					
				default:
					request.getRequestDispatcher("ListarCursos").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("/WEB-INF/CadCurso.jsp").forward(request, response);
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		String dept = request.getParameter("departamento");
				
		CursoVO cursoVO = new CursoVO();
		
		cursoVO.setNome(nome);
		cursoVO.setSigla(sigla);
		cursoVO.setNomeDepartamento(dept);
		
		try
		{
			CursoHandler.cadastrarCurso(cursoVO);
		    
			String redirect = response.encodeRedirectURL("/WEB-INF/CadCurso.jsp");
			response.sendRedirect(redirect);			
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}