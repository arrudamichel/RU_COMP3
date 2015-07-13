package br.uffrj.comp3.rusys.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.TurnoGateway;
import br.uffrj.comp3.rusys.util.Constantes;


public class ListarTurno
{

	public ListarTurno()
	{
	}

	public ArrayList<TurnoEnum> listar()
	{
		ArrayList<TurnoEnum> turnos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		try
		{
			TurnoGateway tg = new TurnoGateway(conn);
			ResultSet rs = tg.selecionarTurnos();

			while (rs.next())
			{
				turnos.add(TurnoEnum.valueOf(rs.getString(2)));
			}

			return turnos;
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
