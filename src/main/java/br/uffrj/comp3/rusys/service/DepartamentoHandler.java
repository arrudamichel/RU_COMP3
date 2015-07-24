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
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(departamentoVO.getNome(), departamentoVO.getSigla()));

		if (deptGateway.inserir(valores)==null)
			throw new Exception("falha.ao.cadastrar.departamento");

		conn.close();
	}
	
	public static void atualizarDepartamento(Departamento departamento) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway dg = new DepartamentoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(departamento.getNome(), departamento.getSigla())); 

		if (!dg.alterarDepartamento(valores, departamento.getId()))
			throw new Exception("falha.ao.atualizar.departamento");
		
		conn.close();
	}
	
	public static void excluirDepartamento(Departamento departamento) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway cg = new DepartamentoGateway(conn);

		if (!cg.excluirDepartamento(departamento.getId()))
			throw new Exception("falha.ao.excluir.departamento");
		
		conn.close();
	}
	
	public static ArrayList<Departamento> recuperarDepartamentos(DepartamentoVO departamentoVO) throws SQLException, Exception
	{
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway dg = new DepartamentoGateway(conn);

		ResultSet rs = dg.selecionarDepartamentos();
	
		while (rs.next())
		{
			Departamento departamento = new Departamento(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla"));

			departamentos.add(departamento);
		}
		
		conn.close();

		return departamentos;
	}

	public static Departamento recuperarDepartamento(int departametnoId) throws SQLException, Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway dg = new DepartamentoGateway(conn);

		ResultSet rs = dg.selecionarDepartamentoPorId(departametnoId);
		
		Departamento departamento = null;

		while (rs.next())
		{
			departamento = new Departamento(rs.getInt("id"), rs.getString("nome"), rs.getString("sigla"));
		}
		
		conn.close();
		
		return departamento;
	}
}
