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
import br.uffrj.comp3.rusys.persintece.TurnoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class RefeicaoHandler {

	public static void cadastrarRefeicao(RefeicaoVO refeicaoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		TurnoGateway turnoGateway = new TurnoGateway(conn);

		ResultSet rs = turnoGateway.selecionarTurnoPorNome(refeicaoVO.getTurno().toString());

		int turnoId = -1;
		try
		{
			rs.next();
			turnoId = rs.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(refeicaoVO.getDescricao(), 
				refeicaoVO.getOpcaoVeg(), turnoId));

		if (!refeicaoGateway.inserir(valores))
		throw new Exception("falha.ao.cadastrar.refeicao");
		
		conn.close();

	}
	
	public static void atualizarRefeicao(RefeicaoVO refeicaoVO)
	{
		
	}
	
	public static void excluirRefeicao(RefeicaoVO refeicaoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);
		
		if (!rg.desativarRefeicao(refeicaoVO.getIdentificador()));
			throw new Exception("falha.ao.cadastrar.refeicao");
	}
	
	public static ArrayList<Refeicao> recuperarRefeicoes(RefeicaoVO refeicaoVO) throws Exception
	{
		ArrayList<Refeicao> refeicoes = new ArrayList<>();

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);

		ResultSet rs = rg.selecionarRefeicoes();

		while (rs.next())
		{
			Refeicao refeicao = new Refeicao();
			refeicao.setIdentificador(rs.getInt(1));
			refeicao.setDescricao(rs.getString(2));
			refeicao.setOpcaoVeg(rs.getString(3));

			TurnoGateway tg = new TurnoGateway(conn);
			
			ResultSet rst = tg.selecionarTurnoPorId(rs.getInt(4));
			rst.next();

			// Verifica se e ativo ou nao
			if (rs.getInt(5) == 1)
			{
				refeicao.setTurno(TurnoEnum.valueOf(rst.getString(2)));

				refeicoes.add(refeicao);
			}
		}

		conn.close();
		
		return refeicoes;
	}
}
