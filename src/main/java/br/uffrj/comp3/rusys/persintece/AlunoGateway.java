package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AlunoGateway
{
	private Connection conn;

	public AlunoGateway(Connection conn)
	{
		this.conn = conn;
	}

	public boolean inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "INSERT INTO \"aluno\" VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}
			
			stmt.execute();
			
			return true;
         
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public ResultSet selecionarAlunos()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{
			String sql = "SELECT * " + "   FROM \"aluno\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public ResultSet selecionarAlunoPorId(int identificador)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT * " + "   FROM \"aluno\" " + "   WHERE \"consumidor_id\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, identificador);
			rs = stmt.executeQuery();

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public boolean excluirAluno(int id)
	{
		try
		{
			String sql = "DELETE FROM \"aluno\" " + "   WHERE \"consumidor_id\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			
			stmt.execute();

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean alterarAluno(ArrayList<Object> valores, int identificador)
	{
		try
		{
			String sql = "UPDATE \"aluno\" " + "SET \"consumidor_id\" = ?, " + "\"curso_id_curso\" = ? "
					+ "WHERE \"consumidor_id\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.setInt(3, identificador);

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