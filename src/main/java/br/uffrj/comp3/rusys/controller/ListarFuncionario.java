package br.uffrj.comp3.rusys.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.service.FuncionarioHandler;
import br.uffrj.comp3.rusys.model.Funcionario;


public class ListarFuncionario extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ArrayList<Funcionario> listar()
	{
		try
		{
			return (ArrayList<Funcionario>) FuncionarioHandler.recuperarFuncionarios(new ConsumidorVO());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
