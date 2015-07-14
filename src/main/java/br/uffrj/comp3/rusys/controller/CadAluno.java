package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.vo.AlunoVO;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.service.AlunoHandler;
import br.uffrj.comp3.rusys.service.CursoHandler;
import br.uffrj.comp3.rusys.service.RefeicaoHandler;
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
		
		AlunoVO alunoVO = new AlunoVO();
		Collection<Aluno> alunos = AlunoHandler.recuperarAlunos(alunoVO);				
		request.setAttribute("alunos", alunos);
		
		CursoVO cursoVO = new CursoVO();
		Collection<Curso> cursos = CursoHandler.recuperarCursos(cursoVO);
		request.setAttribute("cursos", cursos);

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					cadastrar(request, response);
					break;
				case Constantes.EXCLUIR:
					excluir(request, response);
					break;
				case Constantes.EDITAR:
					editar(request, response);
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
	
	private void editar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void excluir(HttpServletRequest request,
			HttpServletResponse response) {
		
		String matricula = request.getParameter("matricula");

		AlunoVO alunoVO = new AlunoVO();
		alunoVO.setMatricula(Integer.parseInt(matricula));
		
		try
		{
			AlunoHandler.excluirAluno(alunoVO);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
		
	}

	private void cadastrar(HttpServletRequest request,
			HttpServletResponse response) {
		
		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String ano = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String curso = request.getParameter("curso");
		
		AlunoVO alunoVO = new AlunoVO();
		
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
		    
			String redirect = response.encodeRedirectURL("WEB-INF/CadCurso.jsp");
			response.sendRedirect(redirect);			
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
