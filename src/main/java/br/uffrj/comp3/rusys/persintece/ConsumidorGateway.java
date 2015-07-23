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

	public ResultSet inserir(ArrayList<Object> valores){
		try{
			String sql = "INSERT INTO \"consumidor\" (\"matricula\", \"nome\", \"ano_ingresso\", "
					+ "								  \"sexo\", \"titulo\", \"cpf\", \"situacao\") "
					+ "VALUES (?,?,?,?,?,?,1)";
			PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++){
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.execute();
			
			return stmt.getGeneratedKeys();
         
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public ResultSet selecionarConsumidores(){
		ResultSet rs = null;
		Statement stat;

		try{

			String sql = "SELECT \"id_consumidor\",\"matricula\", \"nome\", \"ano_ingresso\", "
								+ "\"sexo\", \"titulo\", \"cpf\", \"situacao\" " 
						+"FROM \"consumidor\" where \"situacao\"=1";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public ResultSet selecionarConsumidorPorId(int id)
	{
		ResultSet rs = null;

		try
		{

			String sql = "SELECT \"consumidor_id\", \"matricula\", \"nome\", \"ano_ingresso\", "
						+ 		"\"sexo\", \"titulo\", \"cpf\", \"situacao\" " 
						+ "FROM \"consumidor\" " + "   WHERE \"consumidor_id\" = ?";

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

	public boolean desativarConsumidor(int matricula)
	{
		try
		{
			String sql = "UPDATE \"consumidor\" " + "SET \"situacao\" = ? " + "WHERE \"matricula\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setInt(2, matricula);
			stmt.execute();

		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public void alterarConsumidor(ArrayList<Object> valores, int matricula) throws Exception
	{
		try
		{
			String sql = "UPDATE \"consumidor\" " 
						+"SET \"nome\" = ?, \"ano_ingresso\" = ?, \"sexo\" = ?, "
						+	" \"titulo\" = ? "
						+"WHERE \"matricula\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++)
			{
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}

			stmt.setInt(5, matricula);

			stmt.execute();
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new Exception("falha.ao.cadastrar.consumidor");

		}
	}
}
