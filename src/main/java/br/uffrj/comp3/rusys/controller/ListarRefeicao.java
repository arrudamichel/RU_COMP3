package br.uffrj.comp3.rusys.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.service.RefeicaoHandler;
import br.uffrj.comp3.rusys.model.Refeicao;

public class ListarRefeicao extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ArrayList<Refeicao> listar()
	{

		try
		{
			return RefeicaoHandler.recuperarRefeicoes(new RefeicaoVO());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
