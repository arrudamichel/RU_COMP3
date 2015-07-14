package br.uffrj.comp3.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.persintece.TurnoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class TurnoTest {

	public static void main(String[] args) throws SQLException {

		for (TurnoEnum turno : TurnoEnum.values()) {
			System.out.println("<option value=\"" + turno.toString() + "\">" + turno.toString() + "<option>");
		}
		
		
		RefeicaoGateway gateway = new RefeicaoGateway(ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS));
		
		TurnoGateway turnoGateway = new TurnoGateway(ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS));
		
		ResultSet rs = turnoGateway.selecionarTurnoPorNome("TARDE");
		
		rs.next();
		
		System.out.println(rs.getInt(1));

	}

}
