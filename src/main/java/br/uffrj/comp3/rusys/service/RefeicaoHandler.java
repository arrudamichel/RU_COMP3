package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class RefeicaoHandler
{
	public static int cadastrarRefeicao(RefeicaoVO refeicaoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
	
		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(refeicaoVO.getDescricao(), refeicaoVO.getOpcaoVeg(), refeicaoVO.getTurno().toString()));
		
		ResultSet rs = refeicaoGateway.inserir(valores);
		
		rs.next();
		int id = 0;
		id = rs.getInt(1);
		
		conn.close();
		
		return id;
	}
	
	public static Refeicao recuperarRefeicao(int id) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway gatewayRefeicao = new RefeicaoGateway(conn);
		ResultSet rsRefeicao = gatewayRefeicao.selecionarRefeicaoPorId(id);
		
		rsRefeicao.next();
		
		Refeicao refeicao = new Refeicao(rsRefeicao.getInt("id_refeicao"),rsRefeicao.getString("descricao"),rsRefeicao.getString("opcaoVegetariana"),TurnoEnum.fromString(rsRefeicao.getString("turno")));
		
		conn.close();
		
		return refeicao;
	}

	public static void atualizarRefeicao(RefeicaoVO refeicaoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(refeicaoVO.getDescricao(), refeicaoVO.getOpcaoVeg()));
		
		if(!rg.alterarRefeicao(valores, refeicaoVO.getId()))
			throw new Exception("falha.ao.atualizar.refeicao");
		
		conn.close();	
	}

	public static void excluirRefeicao(Refeicao refeicao) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);

		if (!rg.desativarRefeicao(refeicao.getIdentificador()))
			throw new Exception("falha.ao.cadastrar.refeicao");
		
		conn.close();
	}

	public static ArrayList<Refeicao> recuperarRefeicoes(RefeicaoVO refeicaoVO) throws Exception
	{
		ArrayList<Refeicao> refeicoes = new ArrayList<>();

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway gatewayRefeicao = new RefeicaoGateway(conn);
		ResultSet rsRefeicao = gatewayRefeicao.selecionarRefeicoes();
		
		while(rsRefeicao.next()){
			
			if(rsRefeicao.getInt("situacao") == 1){
				Refeicao refeicao = new Refeicao(rsRefeicao.getInt("id_refeicao"),rsRefeicao.getString("descricao"),rsRefeicao.getString("opcaoVegetariana"),TurnoEnum.fromString(rsRefeicao.getString("turno")));
				
				refeicoes.add(refeicao);
			}			
		}
		
		conn.close();
		
		return refeicoes;
	}

	public static ArrayList<Refeicao> recuperarRefeicaoPorTurno(String turno) throws SQLException, Exception {
		ArrayList<Refeicao> refeicoes = new ArrayList<>();

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway gatewayRefeicao = new RefeicaoGateway(conn);
		ResultSet rsRefeicao = gatewayRefeicao.selecionarRefeicoes();
		
		while(rsRefeicao.next()){
			if(rsRefeicao.getInt("situacao") == 1 && TurnoEnum.fromString(turno) == TurnoEnum.fromString(rsRefeicao.getString("turno"))){
				Refeicao refeicao = new Refeicao(rsRefeicao.getInt("id_refeicao"),rsRefeicao.getString("descricao"),rsRefeicao.getString("opcaoVegetariana"),TurnoEnum.fromString(rsRefeicao.getString("turno")));
				
				refeicoes.add(refeicao);
			}			
		}
		
		conn.close();
		
		return refeicoes;		
	}
}
