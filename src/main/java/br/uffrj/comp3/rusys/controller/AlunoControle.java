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
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.AlunoHandler;
import br.uffrj.comp3.rusys.service.CursoHandler;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/GerirAluno")
public class AlunoControle extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		ConsumidorVO alunoVO = new ConsumidorVO();
		Collection<Aluno> alunos = null;
		try
		{
			alunos = AlunoHandler.recuperarAlunos(alunoVO);
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
		request.setAttribute("alunos", alunos);
		
		CursoVO cursoVO = new CursoVO();
		Collection<Curso> cursos = null;

		try
		{
			cursos = CursoHandler.recuperarCursos(cursoVO);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("cursos", cursos);

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.ACAO_SALVAR:
					cadastrar(request, response);
					response.sendRedirect("GerirAluno");
					break;
				case Constantes.NOVO:
					request.getRequestDispatcher("/WEB-INF/CadAluno.jsp").forward(request, response);
					break;
				case Constantes.ACAO_DELETAR:
					//	excluir(request, response);
					response.sendRedirect("GerirAluno");
					break;
				case Constantes.ACAO_EDITAR:
					editar(request, response);
					response.sendRedirect("GerirAluno");
					break;
				default:
					request.getRequestDispatcher("/WEB-INF/listarAlunos.jsp").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("/WEB-INF/listarAlunos.jsp").forward(request, response);
		}
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String matricula = request.getParameter("matricula");
		String nome = request.getParameter("nome");
		String anoIngresso = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String curso = request.getParameter("curso");

		ConsumidorVO aluno= new ConsumidorVO();
		//System.out.println(id);
		aluno.setId(Integer.parseInt(id));
		aluno.setMatricula(Integer.parseInt(matricula));
		aluno.setNome(nome);
		aluno.setAnoDeIngresso(anoIngresso);
		aluno.setSexo(sexo);
		aluno.setTitulo(titulo);
		System.out.println("CPF" + cpf);
		aluno.setCpf(cpf);
		aluno.setCurso(Integer.parseInt(curso));
		
		try
		{	
			AlunoHandler.atualizarAluno(aluno, Integer.parseInt(id));

			request.getRequestDispatcher("/WEB-INF/CadAluno.jsp").forward(request, response);
		} catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
		
	}

	private void excluir(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String idAluno = request.getParameter("id");

		Aluno aluno = null;
		try
		{
			aluno = AlunoHandler.recuperarAluno(Integer.parseInt(idAluno));
		} catch (NumberFormatException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			AlunoHandler.excluirAluno(aluno);
			//request.getRequestDispatcher("/WEB-INF/listarAlunos.jsp").forward(request, response);
			response.sendRedirect("GerirAluno");
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
		
	}

	private void cadastrar(HttpServletRequest request,HttpServletResponse response) {
		
		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String ano = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String curso = request.getParameter("curso");

		ConsumidorVO alunoVO = new ConsumidorVO();
		
		alunoVO.setNome(nome);
		alunoVO.setMatricula(Integer.parseInt(matricula));
		alunoVO.setAnoDeIngresso(ano);
		alunoVO.setSexo(sexo);
		alunoVO.setTitulo(titulo);
		alunoVO.setCpf(cpf);
		alunoVO.setCurso(Integer.parseInt(curso));
		
		try
		{
			AlunoHandler.cadastrarAluno(alunoVO);
		    
			//String redirect = response.encodeRedirectURL("/WEB-INF/listarAlunos.jsp");
			//response.sendRedirect(redirect);			
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		ConsumidorVO consumidorVO = new ConsumidorVO();
		
		Collection<Aluno> alunos = null;
		try
		{
			alunos = AlunoHandler.recuperarAlunos(consumidorVO);
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		req.setAttribute("alunos", alunos);

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
		
		req.setAttribute("cursos", cursos);

		//------------ RECUPERA CURSOS ---------------

		String id = (String) req.getParameter("id");

		if (id!=null)
		{	
			Aluno aluno = null;
			try
			{
				Aluno a = AlunoHandler.recuperarAluno(Integer.parseInt(id));
				System.out.println(a.getId());
				aluno = a;
			} catch (NumberFormatException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			req.setAttribute("aluno", aluno);
		}
		
		//------------ ACAO ---------------
		
		String acao = (String) req.getParameter("acao");
		//System.out.println("ACAO GET = "+acao );

		if (acao != null)
		{
			switch (acao)
			{	
				case Constantes.NOVO:
					req.getRequestDispatcher("/WEB-INF/CadAluno.jsp").forward(req, resp);
					break;
				case Constantes.ACAO_EDITAR:					
					req.getRequestDispatcher("/WEB-INF/AtualizarAluno.jsp").forward(req, resp);
					break;
				case Constantes.ACAO_DELETAR:	
					System.out.println("Acao deletar");
					try {
						excluir(req, resp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					req.getRequestDispatcher("/WEB-INF/listarAlunos.jsp").forward(req, resp);
			}
		} 
		else
		{
			req.getRequestDispatcher("/WEB-INF/listarAlunos.jsp").forward(req, resp);
		}
	}
}
