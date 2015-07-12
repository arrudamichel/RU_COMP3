package br.uffrj.comp3.gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.uffrj.comp3.model.Constantes;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.DepartamentoGateway;

public class DepartamentoGatewayTest {

	public static void main(String[] args) {
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);
//  iddepartamento  	nome  	sigla  
		ResultSet rs = deptGateway.selecionarDepartamentos();
		try {
			while (rs.next()) {
				int id = rs.getInt("iddepartamento");
				String desc = rs.getString("nome");
		    	String opcao = rs.getString("sigla");
		    	System.out.println(id + " " + desc + " " + opcao + " ");
			}
	    	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
