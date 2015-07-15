package br.uffrj.comp3.rusys.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.service.CursoHandler;

@WebServlet("/ListarCursos")
public class ListarCurso extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ArrayList<Curso> listar()
	{
		try
		{
			return CursoHandler.recuperarCursos(new CursoVO());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
