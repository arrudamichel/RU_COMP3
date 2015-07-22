package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CursoGateway
{
	private Connection conn;

	public CursoGateway(Connection conn)
	{
		this.conn = conn;
	}

	public ResultSet inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "INSERT INTO \"curso\" (\"nome\", \"sigla\", \"departamento_id_departamento\") values (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			 
			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
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

	public ResultSet selecionarCursos()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{

			String sql = "SELECT \"id_curso\", \"nome\", \"sigla\", \"departamento_id_departamento\" FROM \"curso\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public ResultSet selecionarCursoPorId(int identificador)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT \"id_curso\", \"nome\", \"sigla\", \"departamento_id_departamento\" FROM \"curso\" WHERE \"id_curso\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, identificador);
			rs = stmt.executeQuery();

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public boolean excluirCurso(int identificador)
	{
		try
		{
			String sql = "DELETE FROM \"curso\" WHERE \"id_curso\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, identificador);
			stmt.execute();

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean alterarCurso(ArrayList<Object> valores, int identificador)
	{
		try
		{
			String sql = "UPDATE \"curso\" " + "SET " + "\"nome\" = ?, " + "\"sigla\" = ?, "
					+ "\"departamento_id_departamento\" = ? " + "WHERE \"id_curso\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.setInt(4, identificador);

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
