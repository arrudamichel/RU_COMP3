package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TicketGateway {
	private Connection conn;

	public TicketGateway(Connection conn)
	{
		this.conn = conn;
	}

	public boolean inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "INSERT INTO \"ticket\" (\"consumidor_matricula\", \"refeicao_idRefeicao\", \"preco\", \"pago\") values (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.execute();
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	public ResultSet selecionarTickets()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{

			String sql = "SELECT * FROM \"ticket\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}
	
	public ResultSet selecionarTicketPorId(int identificador)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT * FROM \"ticket\" WHERE \"consumidor_matricula\" = ?";

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
	
	public boolean excluirTicket(int identificador)
	{
		try
		{
			String sql = "DELETE FROM \"ticket\" WHERE \"consumidor_matricula\" = ?";

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
	
	/*	CREATE TABLE IF NOT EXISTS "ticket" (
	  "consumidor_matricula" INT NOT NULL,
	  "refeicao_idRefeicao" INT NOT NULL,
	  "preco" DECIMAL(10,2) NOT NULL,
	  "pago" TINYINT(1) NOT NULL,*/

	public boolean alterarTicket(ArrayList<Object> valores, int identificador)
	{
		try
		{
			String sql = "UPDATE \"ticket\" " + "SET " + "\"consumidor_matricula\" = ?, " + "\"refeicao_idRefeicao\" = ?, "
					+ "\"preco\" = ? " + "\"pago\" = ?"  + "WHERE \"consumidor_matricula\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.setInt(5, identificador);

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
