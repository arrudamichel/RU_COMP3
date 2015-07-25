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
import br.uffrj.comp3.rusys.service.exceptions.AlterarNoBancoExeception;
import br.uffrj.comp3.rusys.service.exceptions.ExcluirDoBancoException;
import br.uffrj.comp3.rusys.service.exceptions.InsercaoNoBancoException;
import br.uffrj.comp3.rusys.util.Constantes;

public class DepartamentoHandler
{
	public static void cadastrarDepartamento(DepartamentoVO departamentoVO) throws Exception
	{
		if(!isSILGAunica(departamentoVO.getSigla()))
		{
			throw new Exception("DepartamentoHandler.cadastrarDepartamento.sigla.informado.ja.cadastrada");
		}
		
		Departamento departamento = new Departamento(departamentoVO.getId(), departamentoVO.getNome(), departamentoVO.getSigla());
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(departamento.getNome(), departamento.getSigla()));

		if (deptGateway.inserir(valores)==null)
			throw new InsercaoNoBancoException(); //"DepartamentoHandler.cadastrarDepartamento"

		conn.close();
	}
	
	public static void atualizarDepartamento(DepartamentoVO departamentoVO) throws Exception
	{
		Departamento departamento = recuperarDepartamento(departamentoVO.getId());
		
		if (departamentoVO.getNome() != null)
		{
			departamento.setNome(departamentoVO.getNome());
		}
		
		if (departamentoVO.getSigla() != null)
		{
			departamento.setSigla(departamentoVO.getSigla());
		}
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway dg = new DepartamentoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(departamento.getNome(), departamento.getSigla())); 

		if (!dg.alterarDepartamento(valores, departamento.getId()))
			throw new AlterarNoBancoExeception();
		
		conn.close();
	}
	
	public static boolean isSILGAunica(String sigla) throws Exception
	{
		DepartamentoVO departamentoVO = new DepartamentoVO();
		departamentoVO.setSigla(sigla);
		
		ArrayList<Departamento> departamentos = (ArrayList<Departamento>) DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		
		return departamentos==null || departamentos.isEmpty();
	}
	
	public static void excluirDepartamento(Departamento departamento) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway cg = new DepartamentoGateway(conn);

		if (!cg.excluirDepartamento(departamento.getId()))
			throw new ExcluirDoBancoException();
		
		conn.close();
	}
	
	public static ArrayList<Departamento> recuperarDepartamentos(DepartamentoVO departamentoVO) throws SQLException, Exception
	{
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DepartamentoGateway dg = new DepartamentoGateway(conn);

		ResultSet rs = null;

		if (departamentoVO.getSigla()!=null)
		{
			rs = dg.selecionarDepartamentoPorSigla(departamentoVO.getSigla());			
		} else 
		{
			rs = dg.selecionarDepartamentos();
		}
		
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
