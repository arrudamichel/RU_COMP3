package br.ufrrj.comp3.gateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public ConnectionFactory() {
		// TODO Auto-generated constructor stub
	}

	public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:~/git/RU_COMP3/ru", "sa", "sa");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
