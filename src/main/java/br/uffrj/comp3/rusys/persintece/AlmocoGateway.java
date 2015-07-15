package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlmocoGateway {
	private Connection conn;

	public AlmocoGateway(Connection conn)
	{
		this.conn = conn;
	}

	public boolean inserir(int idRefeicao)
	{
		try
		{
			String sql = "INSERT INTO \"almoco\" VALUES (?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, idRefeicao);
			
			stmt.execute();
			
			return true;         
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public ResultSet selecionarAlmocos()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{
			String sql = "SELECT * FROM \"almoco\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public ResultSet selecionarAlmocoPorId(int idRefeicao)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT * FROM \"almoco\" WHERE \"refeicao_id_refeicao\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idRefeicao);
			rs = stmt.executeQuery();

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public boolean excluirAlmoco(int idRefeicao)
	{
		try
		{
			String sql = "DELETE FROM \"almoco\" WHERE \"refeicao_id_refeicao\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idRefeicao);
			stmt.execute();

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean alterarAlmoco(int idRefeicao)
	{
		try
		{
			String sql = "UPDATE \"almoco\" SET \"refeicao_id_refeicao\" = ? WHERE \"refeicao_id_refeicao\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);			

			stmt.setInt(1, idRefeicao);
			stmt.setInt(2, idRefeicao);

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
