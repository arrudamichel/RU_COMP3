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

	/***
	 * "id" INT NOT NULL,
  	*"curso_fk" INT NOT NULL,
	 * @param valores
	 * @return
	 */
	public boolean inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "INSERT INTO \"aluno\" VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1) instanceof Integer)
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

	/***
	 * "id" INT NOT NULL,
  	*"curso_fk" INT NOT NULL,
	 * @param valores
	 * @return
	 */
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

	/***
	 * "id" INT NOT NULL,
  	*"curso_fk" INT NOT NULL,
	 * @param valores
	 * @return
	 */
	public ResultSet selecionarAlunoPorId(int id)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT \"id\", \"curso_fk\"  " + "   FROM \"aluno\" " + "   WHERE \"id\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
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
			String sql = "DELETE FROM \"aluno\" " + "   WHERE \"id\" = ?";

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

	/***
	 * "id" INT NOT NULL,
  	*"curso_fk" INT NOT NULL,
	 * @param valores
	 * @return
	 */
	public boolean alterarAluno(ArrayList<Object> valores, int id)
	{
		try
		{
			String sql = "UPDATE \"aluno\" " + "SET \"curso_fk\" = ? " + "WHERE \"id\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1) instanceof Integer)
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

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
}