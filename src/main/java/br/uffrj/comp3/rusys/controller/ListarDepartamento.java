package br.uffrj.comp3.rusys.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;

public class ListarDepartamento extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ArrayList<Departamento> listar()
	{
		try
		{
			return DepartamentoHandler.recuperarDepartamentos(new DepartamentoVO());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
