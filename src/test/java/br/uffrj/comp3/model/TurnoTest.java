package br.uffrj.comp3.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.RefeicaoGateway;
import br.ufrrj.comp3.gateway.TurnoGateway;

public class TurnoTest {

	public static void main(String[] args) throws SQLException {

		for (Turno turno : Turno.values()) {
			System.out.println("<option value=\"" + turno.toString() + "\">" + turno.toString() + "<option>");
		}
		
		
		RefeicaoGateway gateway = new RefeicaoGateway(ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS));
		
		TurnoGateway turnoGateway = new TurnoGateway(ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS));
		
		ResultSet rs = turnoGateway.selecionarTurnoPorNome("TARDE");
		
		rs.next();
		
		System.out.println(rs.getInt(1));

	}

}
