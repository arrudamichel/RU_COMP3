package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RefeicaoGateway
{
	private Connection conn;

	public RefeicaoGateway(Connection conn)
	{
		this.conn = conn;
	}

	public ResultSet inserir(ArrayList<Object> valores)
	{
		// SELECT * FROM "refeicao" idRefeicao descricao opcaoVegetariana
		// Turno_idTurno
		try
		{
			String sql = "insert into \"refeicao\" (\"descricao\", \"opcaoVegetariana\", \"turno\", \"situacao\") values (?,?,?,1)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}
			stmt.execute();
			
			return stmt.getGeneratedKeys();
         
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ResultSet selecionarRefeicoes()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{
			String sql = "SELECT * FROM \"refeicao\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return rs;
	}

	public ResultSet selecionarRefeicaoPorId(int id)
	{
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM \"refeicao\" WHERE \"id_refeicao\" = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public boolean desativarRefeicao(int id)
	{
		try
		{
			String sql = "UPDATE \"refeicao\" SET \"situacao\" = ? WHERE \"id_refeicao\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setInt(2, id);
			stmt.execute();

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean alterarRefeicao(ArrayList<Object> valores, int id)
	{
		try
		{
			String sql = "UPDATE \"refeicao\" " + "SET " + "\"descricao\" = ?, " + "\"opcaoVegetariana\" = ? "
					+ "WHERE \"id_refeicao\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));
			}
			stmt.setInt(3, id);
			stmt.execute();
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
