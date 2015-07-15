package br.uffrj.comp3.rusys.controller;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.service.AlunoHandler;
import br.uffrj.comp3.rusys.model.Aluno;

@WebServlet("/ListarAlunos")
public class ListarAluno extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ArrayList<Aluno> listar() 
	{
		try
		{
			return (ArrayList<Aluno>) AlunoHandler.recuperarAlunos(new ConsumidorVO());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
