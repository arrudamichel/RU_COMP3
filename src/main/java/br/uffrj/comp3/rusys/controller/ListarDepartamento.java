package br.uffrj.comp3.rusys.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class ListarDepartamento
{
	public ListarDepartamento()
	{
	}

	public ArrayList<Departamento> listar()
	{
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway dg = new DepartamentoGateway(conn);

		ResultSet rs = dg.selecionarDepartamentos();
		try
		{
			while (rs.next())
			{
				Departamento departamento = new Departamento();
				departamento.setIdentificador(rs.getInt(1));
				departamento.setNome(rs.getString(2));
				departamento.setSigla(rs.getString(3));

				departamentos.add(departamento);
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departamentos;
	}
}
