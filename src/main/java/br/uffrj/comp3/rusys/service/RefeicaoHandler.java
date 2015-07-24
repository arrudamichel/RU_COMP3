package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.TipoRefeicaoEnum;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class RefeicaoHandler
{
	public static int cadastrarRefeicao(RefeicaoVO refeicaoVO) throws Exception
	{
		@SuppressWarnings("unused")
		Refeicao refeicao = new Refeicao(refeicaoVO.getId(), refeicaoVO.getDescricao(), refeicaoVO.getTipo());
		
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
	
		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(refeicaoVO.getDescricao(), refeicaoVO.getOpcaoVeg(), refeicaoVO.getTurno().toString(), refeicaoVO.getTipo().toString()));
		
		ResultSet rs = refeicaoGateway.inserir(valores);
		conn.close();

		if (rs==null)
			throw new Exception("falha.ao.cadastrar.refeicao");
		
		rs.next();
		int id = rs.getInt(0);
		
		return id;
	}
	
	public static Refeicao recuperarRefeicao(int id) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway rg = new RefeicaoGateway(conn);
		ResultSet rsRefeicao = rg.selecionarRefeicaoPorId(id);

		Refeicao refeicao = null;
		
		if (rsRefeicao.next())
		{
			String opcaoVeg = rsRefeicao.getString("opcaoVegetariana");
			String descricao = rsRefeicao.getString("descricao");
			TipoRefeicaoEnum tipo = TipoRefeicaoEnum.fromString(rsRefeicao.getString("tipo"));
			
			refeicao = new Refeicao(rsRefeicao.getInt("id"), descricao, tipo);
			refeicao.setOpcaoVeg(opcaoVeg);
		} 

		conn.close();
		
		return refeicao;
	}

	public static boolean atualizarRefeicao(RefeicaoVO refeicaoVO) throws Exception
	{
		@SuppressWarnings("unused")
		Refeicao refeicao = new Refeicao(refeicaoVO.getId(), refeicaoVO.getDescricao(), refeicaoVO.getTipo());
		
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(refeicaoVO.getDescricao(), refeicaoVO.getOpcaoVeg()));
		
		boolean rs = refeicaoGateway.alterarRefeicao(valores, refeicaoVO.getId());
		
		if (!rs)
			throw new Exception("falha.ao.cadastrar.refeicao");

		conn.close();
		
		return rs;
	}

	public static void excluirRefeicao(Refeicao refeicao) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);

		if (!rg.desativarRefeicao(refeicao.getId()))
			throw new Exception("falha.ao.excluir.refeicao");
		
		conn.close();
	}

	public static ArrayList<Refeicao> recuperarRefeicoes(RefeicaoVO refeicaoVO) throws Exception
	{	
		ArrayList<Refeicao> refeicoes = new ArrayList<>();

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway ag = new RefeicaoGateway(conn);
		ResultSet rsRefeicao = null;

		if (refeicaoVO.getTipo()!=null)
		{
			rsRefeicao = ag.selecionarRefeicaoPorTipo(refeicaoVO.getTipo().toString());
		}
		else if (refeicaoVO.getTurno()!=null)
		{
			rsRefeicao = ag.selecionarRefeicaoPorTurno(refeicaoVO.getTurno().toString());
		}
		else
		{
			rsRefeicao = ag.selecionarRefeicoes();
		}
		
		while (rsRefeicao.next())
		{
			int id = rsRefeicao.getInt("id");
			String opcaoVeg = rsRefeicao.getString("opcaoVegetariana");
			String descricao = rsRefeicao.getString("descricao");
			TipoRefeicaoEnum tipo = TipoRefeicaoEnum.fromString(rsRefeicao.getString("tipo"));
			
			Refeicao refeicao = new Refeicao(id, descricao, tipo);
			refeicao.setOpcaoVeg(opcaoVeg);
			
			refeicoes.add(refeicao);
		}
		
		conn.close();	

		return refeicoes;
	}

	public static ArrayList<Refeicao> recuperarRefeicaoPorTurno(String turno) throws SQLException, Exception 
	{
		RefeicaoVO refeicaoVO = new RefeicaoVO();	
		refeicaoVO.setTurno(TurnoEnum.fromString(turno));
		
		return recuperarRefeicoes(refeicaoVO);		
	}
}
