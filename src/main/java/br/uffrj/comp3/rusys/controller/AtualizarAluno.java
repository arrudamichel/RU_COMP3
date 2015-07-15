package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.service.AlunoHandler;
import br.uffrj.comp3.rusys.service.CursoHandler;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/AtualizarAluno")
public class AtualizarAluno extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		String matricula = (String) request.getParameter("matricula");
		
		Aluno aluno = AlunoHandler.recuperarAluno(matricula);
		
		request.setAttribute("aluno", aluno);
		
		CursoVO cursoVO = new CursoVO();
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
					atualizar(matricula,request, response);
					break;
				default:
					try
					{						
						request.getRequestDispatcher("/WEB-INF/AtualizarAluno.jsp").forward(request, response);
					} 
					catch (Exception e)
					{
						request.setAttribute("erro", "O curso informado nao existe");
						request.getRequestDispatcher("/WEB-INF/AtualizarAluno.jsp").forward(request, response);
					}
					//request.getRequestDispatcher("ListarCursos").forward(request, response);			
			}
		} 
		else
		{
			
		}
	}
	
	private void atualizar(String cursoId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String id = request.getParameter("cursoId");
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		String dept = request.getParameter("departamento");
		
		Curso curso = new Curso();
		
		curso.setIdentificador(Integer.parseInt(id));
		curso.setNome(nome);
		curso.setSigla(sigla);
		curso.setDepartamento(DepartamentoHandler.recuperarDepartamento(Integer.parseInt(dept)));
		
		try
		{
			CursoHandler.atualizarCurso(curso);
		    
			request.getRequestDispatcher("/WEB-INF/CadCurso.jsp").forward(request, response);			
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