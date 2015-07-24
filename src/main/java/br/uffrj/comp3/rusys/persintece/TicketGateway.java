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
	
	/***
	 * "id" INT NOT NULL AUTO_INCREMENT,
	 *"consumidor_fk" INT NOT NULL,
	 *"refeicao_fk" INT NOT NULL,
	 *"preco" DECIMAL(10,2) NOT NULL,
	 *"pago" TINYINT(1) NOT NULL,
	 * @param valores
	 * @param identificador
	 * @return
	 */

	public ResultSet inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "INSERT INTO ticket (consumidor_fk, refeicao_fk, preco, pago) values (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1) instanceof String)
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1) instanceof Integer)
					stmt.setInt(i, (Integer) valores.get(i - 1));
				
				if (valores.get(i - 1) instanceof Boolean)
					stmt.setInt(i, ((Boolean) valores.get(i - 1))? 1: 0);
				
				if (valores.get(i - 1) instanceof Float)
					stmt.setFloat(i, (Float) valores.get(i - 1));
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
	
	/***
	 * "id" INT NOT NULL AUTO_INCREMENT,
	 *"consumidor_fk" INT NOT NULL,
	 *"refeicao_fk" INT NOT NULL,
	 *"preco" DECIMAL(10,2) NOT NULL,
	 *"pago" TINYINT(1) NOT NULL,
	 * @param valores
	 * @param identificador
	 * @return
	 */
	
	public ResultSet selecionarTickets()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{
			String sql = "SELECT * FROM ticket";
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
			String sql = "SELECT * FROM ticket WHERE id = ?";

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
			String sql = "DELETE FROM ticket WHERE id = ?";

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
	
	/***
	 * "id" INT NOT NULL AUTO_INCREMENT,
	 *"consumidor_fk" INT NOT NULL,
	 *"refeicao_fk" INT NOT NULL,
	 *"pago" TINYINT(1) NOT NULL,
	 * @param valores
	 * @param identificador
	 * @return
	 */

	public boolean alterarTicket(ArrayList<Object> valores, int identificador)
	{
		try
		{
			String sql = "UPDATE ticket " + "SET " + "pago = ?"  + " WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1) instanceof String)
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1) instanceof Integer)
					stmt.setInt(i, (Integer) valores.get(i - 1));
				
				if (valores.get(i - 1) instanceof Boolean)
					stmt.setInt(i, ((Boolean) valores.get(i - 1))? 1 : 0);
			}

			stmt.setInt(2, identificador);

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
