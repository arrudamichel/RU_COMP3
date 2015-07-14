package br.uffrj.comp3.rusys.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.persintece.TurnoGateway;
import br.uffrj.comp3.rusys.util.Constantes;
import br.uffrj.comp3.rusys.model.Refeicao;

public class ListarRefeicao
{

	public ListarRefeicao()
	{
	}

	public ArrayList<Refeicao> listar()
	{
		ArrayList<Refeicao> refeicoes = new ArrayList<>();

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);

		ResultSet rs = rg.selecionarRefeicoes();

		try
		{
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
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return refeicoes;
	}
}
