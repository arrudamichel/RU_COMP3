package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class DepartamentoHandler
{
	public static void cadastrarDepartamento(DepartamentoVO departamentoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(departamentoVO.getNome(), 
																		departamentoVO.getSigla()));

		if (!deptGateway.inserir(valores))
			throw new Exception("falha.ao.cadastrar.departamento");

		conn.close();
	}
	
	public static void atualizarDepartamento(Departamento departamento)
	{
//		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
//		DepartamentoGateway dg = new DepartamentoGateway(conn);
		
	
	}
	
	public static void excluirDepartamento(Departamento departamento)
	{
		
	}
	
	public static ArrayList<Departamento> recuperarDepartamentos(DepartamentoVO departamentoVO)
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

	public static Departamento recuperarDepartamento(int departametnoId)
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway dg = new DepartamentoGateway(conn);

		ResultSet rs = dg.selecionarDepartamentoPorId(departametnoId);
		Departamento departamento = new Departamento();
		try
		{
			while (rs.next())
			{
				departamento.setIdentificador(rs.getInt(1));
				departamento.setNome(rs.getString(2));
				departamento.setSigla(rs.getString(3));
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departamento;
	}
}
