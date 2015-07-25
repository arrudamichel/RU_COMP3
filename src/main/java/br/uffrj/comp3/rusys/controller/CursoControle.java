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


@WebServlet("/GerirCurso")
public class CursoControle extends HttpServlet
{
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
				{	case Constantes.ACAO_SALVAR:
						cadastrar(request, response);
//						response.sendRedirect("GerirCurso");
						break;
					case Constantes.ACAO_DELETAR:
						excluir(request, response);
//						response.sendRedirect("GerirCurso");
						break;
					case Constantes.ACAO_EDITAR:
						editar(request, response);
//						response.sendRedirect("GerirCurso");
						break;	
					default:
						request.getRequestDispatcher("/WEB-INF/ListCurso.jsp").forward(request, response);
				}
			} 
			else
			{
				request.getRequestDispatcher("/WEB-INF/ListCurso.jsp").forward(request, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("cursoId");
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		String dept = request.getParameter("departamento");

		if (nome.equals("") || sigla.equals(""))
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/AtualizarCurso.jsp").forward(request, response);
		} else {
		
			CursoVO curso = new CursoVO();
			
			curso.setId(Integer.parseInt(id));
			curso.setNome(nome);
			curso.setSigla(sigla);
			curso.setDepartamento(Integer.parseInt(dept));

		
			CursoHandler.atualizarCurso(curso);
				
			toListar(Constantes.SUCESSO, request, response);
		}
	}

	private void toListar(String msg, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		iniciaCampos(request, response);
		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/WEB-INF/ListCurso.jsp").forward(request, response);		
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
	{
		// TODO Auto-generated method stub
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		String dept = request.getParameter("departamento");
		
		if (nome.equals("") || sigla.equals(""))
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/CadCurso.jsp").forward(request, response);
		} else {
			CursoVO cursoVO = new CursoVO();
			
			cursoVO.setNome(nome);
			cursoVO.setSigla(sigla);
			cursoVO.setDepartamento(Integer.parseInt(dept));
	
			CursoHandler.cadastrarCurso(cursoVO);
				
			toListar(Constantes.SUCESSO, request, response);
		}
	}
	
	private void iniciaCampos(HttpServletRequest request, HttpServletResponse response) 
	{
		response.setContentType("text/html");

		//------------ RECUPERA DEPARTAMENTO ---------------
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
				
		Collection<Departamento> departamentos = null;
		try
		{
			departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
				
		request.setAttribute("departamentos", departamentos);

		//------------ RECUPERA CURSOS ---------------
		CursoVO cursoVO = new CursoVO();
				
		Collection<Curso> cursos = null;
		try
		{
			cursos = CursoHandler.recuperarCursos(cursoVO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
				
		request.setAttribute("cursos", cursos);

		//------------ RECUPERA CURSOS ---------------

		String cursoId = (String) request.getParameter("cursoId");

		if (cursoId!=null)
		{
			Curso curso = null;
			try
			{
				curso = CursoHandler.recuperarCurso(Integer.parseInt(cursoId));
			} 
			catch (NumberFormatException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			request.setAttribute("curso", curso);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		iniciaCampos(request, response);
		
		//------------ ACAO ---------------
		try
		{
			String acao = (String) request.getParameter("acao");		
	
			if (acao != null)
			{
				switch (acao)
				{	
					case Constantes.NOVO:
						request.getRequestDispatcher("/WEB-INF/CadCurso.jsp").forward(request, response);
						break;
					case Constantes.ACAO_EDITAR:					
						request.getRequestDispatcher("/WEB-INF/AtualizarCurso.jsp").forward(request, response);
						break;
					default:
						request.getRequestDispatcher("/WEB-INF/ListCurso.jsp").forward(request, response);
				}
			} 
			else
			{
				request.getRequestDispatcher("/WEB-INF/ListCurso.jsp").forward(request, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			toListar( Constantes.ERRO, request, response);
		}
	}
}