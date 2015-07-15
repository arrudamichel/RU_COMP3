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

		Collection<Departamento> departamentos = null;
		try
		{
			departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		} catch (SQLException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		request.setAttribute("departamentos", departamentos);

		Curso curso = null;
		try
		{
			curso = CursoHandler.recuperarCurso(Integer.parseInt(request.getParameter("cursoId")));
		} catch (NumberFormatException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		request.setAttribute("curso", curso);

		if (acao != null)
		{
			switch (acao)
			{
			case Constantes.SALVAR:
				atualizar(cursoId, request, response);
				break;
			default:
				try
				{
					request.getRequestDispatcher("/WEB-INF/AtualizarCurso.jsp").forward(request, response);
				} catch (Exception e)
				{
					request.setAttribute("erro", "O curso informado nao existe");
					request.getRequestDispatcher("/WEB-INF/AtualizarCurso.jsp").forward(request, response);
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
		String id = request.getParameter("cursoId");
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		String dept = request.getParameter("departamento");

		System.out.println(id + " " + nome + " " + sigla + " " +  dept);
		Curso curso = null;
		try
		{
			curso = CursoHandler.recuperarCurso(Integer.parseInt(id));
			
			curso.setNome(nome);
			curso.setSigla(sigla);
			curso.setDepartamento(DepartamentoHandler.recuperarDepartamento(Integer.parseInt(dept)));
			
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
			CursoHandler.atualizarCurso(curso);

			request.getRequestDispatcher("/WEB-INF/CadCurso.jsp").forward(request, response);
		} catch (Exception e)
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
