package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
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



@WebServlet("/AtualizarCurso")
public class AtualizarCurso extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		String cursoId = (String) request.getParameter("cursoId");
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		Collection<Departamento> departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		
		request.setAttribute("departamentos", departamentos);
		
		Curso curso = null;
		
		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					atualizar(cursoId,request, response);
					break;
				default:
					request.getRequestDispatcher("ListarCursos").forward(request, response);			
			}
		} 
		else
		{
			try
			{
				curso = CursoHandler.recuperarCurso(Integer.parseInt(cursoId));
				request.setAttribute("curso", curso);
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request, response);
			} 
			catch (Exception e)
			{
				request.setAttribute("erro", "O curso informado nao existe");
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request, response);
			}
		}
	}
	
	private void atualizar(String cursoId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		String dept = request.getParameter("departamento");
		
		Curso curso = null;
		
		try
		{
			curso = CursoHandler.recuperarCurso(Integer.parseInt(cursoId));
		} 
		catch (Exception e1)
		{
			// TODO tratar erros
			e1.printStackTrace();
		}
		
		curso.setNome(nome);
		curso.setSigla(sigla);
		curso.setDepartamento(DepartamentoHandler.recuperarDepartamento(Integer.parseInt(dept)));
		
		try
		{
			CursoHandler.atualizarCurso(curso);
		    
			String redirect = response.encodeRedirectURL("ListarCursos");
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
