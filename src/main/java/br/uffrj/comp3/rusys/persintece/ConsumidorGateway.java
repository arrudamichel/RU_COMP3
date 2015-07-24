package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsumidorGateway{
	private Connection conn;

	public ConsumidorGateway(Connection conn){
		this.conn = conn;
	}

	/***
	 *  "id" INT NOT NULL AUTO_INCREMENT,
	 * "matricula" INT NOT NULL,
	 * "nome" VARCHAR(45) NOT NULL,
	 * "ano_ingresso" VARCHAR(45) NOT NULL,
	 * "sexo" VARCHAR(45) NOT NULL,
	 * "titulo" VARCHAR(45) NULL,
	 * "cpf" VARCHAR(45) NULL,
	 * "situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
		
	public ResultSet inserir(ArrayList<Object> valores)
	{
		try
		{
			String sql = "INSERT INTO \"consumidor\" (\"matricula\", \"nome\", \"ano_ingresso\", "
						+ "								  \"sexo\", \"titulo\", \"cpf\", \"situacao\") "
						+ "VALUES (?,?,?,?,?,?,1)";
			PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

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

	/***
	 *  "id" INT NOT NULL AUTO_INCREMENT,
	 * "matricula" INT NOT NULL,
	 * "nome" VARCHAR(45) NOT NULL,
	 * "ano_ingresso" VARCHAR(45) NOT NULL,
	 * "sexo" VARCHAR(45) NOT NULL,
	 * "titulo" VARCHAR(45) NULL,
	 * "cpf" VARCHAR(45) NULL,
	 * "situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	public ResultSet selecionarConsumidores()
	{
		ResultSet rs = null;
		Statement stat;

		try
		{
			String sql = "SELECT * " + "   FROM \"consumidor\" where \"situacao\"=1";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	/***
	 *  "id" INT NOT NULL AUTO_INCREMENT,
	 * "matricula" INT NOT NULL,
	 * "nome" VARCHAR(45) NOT NULL,
	 * "ano_ingresso" VARCHAR(45) NOT NULL,
	 * "sexo" VARCHAR(45) NOT NULL,
	 * "titulo" VARCHAR(45) NULL,
	 * "cpf" VARCHAR(45) NULL,
	 * "situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	public ResultSet selecionarConsumidorPorId(int id)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT * " + "   FROM \"consumidor\" " + "   WHERE \"id\" = ? and \"situacao\"=1";

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
	 *  "id" INT NOT NULL AUTO_INCREMENT,
	 * "matricula" INT NOT NULL,
	 * "nome" VARCHAR(45) NOT NULL,
	 * "ano_ingresso" VARCHAR(45) NOT NULL,
	 * "sexo" VARCHAR(45) NOT NULL,
	 * "titulo" VARCHAR(45) NULL,
	 * "cpf" VARCHAR(45) NULL,
	 * "situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	public boolean desativarConsumidor(int id)
	{
		try
		{
			String sql = "UPDATE \"consumidor\" " + "SET \"situacao\" = ? " + "WHERE \"id\" = ?";

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
	 *  "id" INT NOT NULL AUTO_INCREMENT,
	 * "nome" VARCHAR(45) NOT NULL,
	 * "ano_ingresso" VARCHAR(45) NOT NULL,
	 * "sexo" VARCHAR(45) NOT NULL,
	 * "titulo" VARCHAR(45) NULL,
	 * "cpf" VARCHAR(45) NULL,
	 * @param valores
	 * @return
	 */
	public boolean alterarConsumidor(ArrayList<Object> valores, int id)
	{
		try
		{
			String sql = "UPDATE \"consumidor\" " + "SET \"nome\" = ?, " + " \"ano_ingresso\" = ?, " + " \"sexo\" = ?, "
					+ " \"titulo\" = ?," + " \"cpf\" = ? " + "WHERE \"id\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1) instanceof String)
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1) instanceof Integer)
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.setInt(6, id);

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
	 *  "id" INT NOT NULL AUTO_INCREMENT,
	 * "matricula" INT NOT NULL,
	 * "nome" VARCHAR(45) NOT NULL,
	 * "ano_ingresso" VARCHAR(45) NOT NULL,
	 * "sexo" VARCHAR(45) NOT NULL,
	 * "titulo" VARCHAR(45) NULL,
	 * "cpf" VARCHAR(45) NULL,
	 * "situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	public ResultSet selecionarConsumidorPorCpf(String cpf)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT * " + "   FROM \"consumidor\" " + "   WHERE \"cpf\" = ? and \"situacao\"=1";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(0, cpf);
			rs = stmt.executeQuery();
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	/***
	 *  "id" INT NOT NULL AUTO_INCREMENT,
	 * "matricula" INT NOT NULL,
	 * "nome" VARCHAR(45) NOT NULL,
	 * "ano_ingresso" VARCHAR(45) NOT NULL,
	 * "sexo" VARCHAR(45) NOT NULL,
	 * "titulo" VARCHAR(45) NULL,
	 * "cpf" VARCHAR(45) NULL,
	 * "situacao" TINYINT(1) NOT NULL,
	 * @param valores
	 * @return
	 */
	public ResultSet selecionarConsumidorPorMatricula(int matricula)
	{
		ResultSet rs = null;

		try
		{
			String sql = "SELECT * " + "   FROM \"consumidor\" " + "   WHERE \"matricula\" = ? and \"situacao\"=1";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, matricula);
			rs = stmt.executeQuery();

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}
}
