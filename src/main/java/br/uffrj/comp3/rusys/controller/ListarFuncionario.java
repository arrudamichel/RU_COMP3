package br.uffrj.comp3.rusys.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.persintece.FuncionarioGateway;
import br.uffrj.comp3.rusys.util.Constantes;
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;


public class ListarFuncionario
{

	public ListarFuncionario()
	{
	}

	public ArrayList<Funcionario> listar()
	{
		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		FuncionarioGateway fg = new FuncionarioGateway(conn);
		ResultSet rs = fg.selecionarFuncionarios();

		try
		{
			while (rs.next())
			{
				int matricula = rs.getInt(2);
				int iddepartamento = rs.getInt(1);

				// seleciona departamento
				DepartamentoGateway dg = new DepartamentoGateway(conn);
				ResultSet rsd = dg.selecionarDepartamentoPorId(iddepartamento);
				rsd.next();

				Departamento departamento = new Departamento();
				departamento.setIdentificador(rsd.getInt(1));
				departamento.setNome(rsd.getString(2));
				departamento.setSigla(rsd.getString(3));

				// seleciona consumidor
				ConsumidorGateway cg = new ConsumidorGateway(conn);
				ResultSet rsc = cg.selecionarConsumidorPorMatricula(matricula);
				rsc.next();

				if (rs.getInt(7) == 1)
				{
//					TODO:CPF
					
					Funcionario funcionario = new Funcionario(rsc.getString(2), matricula, rsc.getString(3),SexoEnum.valueOf(rsc.getString(4)), TituloEnum.valueOf(rsc.getString(5)), rsc.getString(6),departamento);

					funcionarios.add(funcionario);
				}

			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return funcionarios;
	}
}
