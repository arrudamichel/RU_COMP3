package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.service.CursoHandler;
import br.uffrj.comp3.rusys.util.Constantes;


@WebServlet("/CadastrarCurso")
public class CadCurso extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acaoCriar");

		if (acao != null && acao.equals(Constantes.SALVAR))
		{
			cadastrar(request, response);
		} 
		else
		{
			request.getRequestDispatcher("WEB-INF/CadCurso.jsp").forward(request, response);
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