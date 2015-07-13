package br.uffrj.comp3.rusys.persintece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
	public static Connection getConnection(String path, String user, String pass)
	{
		try
		{
			return DriverManager.getConnection(path, user, pass);
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
}
