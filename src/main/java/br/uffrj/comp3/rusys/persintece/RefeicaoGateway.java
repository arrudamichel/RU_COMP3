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
	
	/***
	 * "id" INT NOT NULL AUTO_INCREMENT,
  	*"descricao" VARCHAR(45) NULL,
  	*"opcaoVegetariana" VARCHAR(45) NULL,  
  	*"turno" VARCHAR(45) NOT NULL,
  	*"tipo" VARCHAR(45) NULL,
  	*"situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	public ResultSet inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "insert into refeicao (descricao, opcaoVegetariana, turno, tipo, situacao) values (?,?,?,?,1)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1) instanceof String)
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1) instanceof Integer)
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
			String sql = "SELECT * FROM refeicao where situacao=1";
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
			String sql = "SELECT * FROM refeicao WHERE id = ? and situacao=1";
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
	
	/***
	 * "id" INT NOT NULL AUTO_INCREMENT,
  	*"descricao" VARCHAR(45) NULL,
  	*"opcaoVegetariana" VARCHAR(45) NULL,  
  	*"turno" VARCHAR(45) NOT NULL,
  	*"tipo" VARCHAR(45) NULL,
  	*"situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	
	public ResultSet selecionarRefeicaoPorTipo(String tipo)
	{
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM refeicao WHERE tipo = ? and situacao=1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,tipo);
			rs = stmt.executeQuery();
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}
	
	/***
	 * "id" INT NOT NULL AUTO_INCREMENT,
  	*"descricao" VARCHAR(45) NULL,
  	*"opcaoVegetariana" VARCHAR(45) NULL,  
  	*"turno" VARCHAR(45) NOT NULL,
  	*"tipo" VARCHAR(45) NULL,
  	*"situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	public ResultSet selecionarRefeicaoPorTurno(String turno)
	{
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM refeicao WHERE turno = ? and situacao=1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,turno);
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
			String sql = "UPDATE refeicao SET situacao = ? WHERE id = ?";

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
	
	/***
	 * "id" INT NOT NULL AUTO_INCREMENT,
  	*"descricao" VARCHAR(45) NULL,
  	*"opcaoVegetariana" VARCHAR(45) NULL,  
	 * @param valores
	 * @return
	 */

	public boolean alterarRefeicao(ArrayList<Object> valores, int id)
	{
		try
		{
			String sql = "UPDATE refeicao " + "SET " + "descricao = ?, " + "opcaoVegetariana = ? "
					+ "WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1) instanceof String)
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
