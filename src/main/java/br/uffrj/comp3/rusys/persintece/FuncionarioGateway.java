package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionarioGateway
{
	private Connection conn;

	public FuncionarioGateway(Connection conn)
	{
		this.conn = conn;
	}

	public boolean inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "INSERT INTO \"funcionario\" VALUES (?,?)";
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

	public ResultSet selecionarFuncionarios()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{
			String sql = "SELECT * " + "   FROM \"funcionario\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public ResultSet selecionarFuncionarioPorId(int identificador)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT * " + "   FROM \"funcionario\" " + "   WHERE \"consumidor_id\" = ?";

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

	public boolean excluirFuncionario(int identificador)
	{
		try
		{
			String sql = "DELETE FROM \"funcionario\" " + "   WHERE \"consumidor_id\" = ?";

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

	public boolean alterarFuncionario(ArrayList<Object> valores, int matricula)
	{
		try
		{
			String sql = "UPDATE \"funcionario\" " + "SET \"consumidor_id\" = ?, "
					+ "\"departamento_id_departamento\" = ? " + "WHERE \"consumidor_id\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.setInt(3, matricula);

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
