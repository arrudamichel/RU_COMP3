package br.uffrj.comp3.gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.model.Constantes;
import br.uffrj.comp3.model.Refeicao;
import br.uffrj.comp3.model.Turno;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.RefeicaoGateway;
import br.ufrrj.comp3.gateway.TurnoGateway;

public class RefeicaoGatewayTest {

	public static void main(String[] args) {
		
		Refeicao refeicao = new Refeicao();
		refeicao.setDescricao("Refeicao de Teste");
		refeicao.setOpcaoVeg("Vag de Test");
		refeicao.setTurno(Turno.valueOf("TARDE"));
		
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		TurnoGateway turnoGateway = new TurnoGateway(conn);

		ResultSet rs = turnoGateway.selecionarTurnoPorNome(refeicao.getTurno().toString());

		int turnoId = -1;
		try {
			rs.next();
			turnoId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

/*		ArrayList<Object> valores = new ArrayList<>(Arrays.asList(refeicao.getDescricao(), refeicao.getOpcaoVeg(), turnoId));
				// \"descricao\", \"opcaoVegetariana\", \"Turno_idTurno\"
		if (refeicaoGateway.inserir(valores))
			System.out.println(valores + " inseridos");
		else
			System.out.println("Erro ao inserir refeicao");*/
		
		System.out.println("SELECT");

    	//rs = refeicaoGateway.selecionarRefeicaoPorId(id);
		rs = refeicaoGateway.selecionarCursos();
		try {
			while (rs.next()) {
				int id = rs.getInt("idRefeicao");
				String desc = rs.getString("descricao");
		    	String opcao = rs.getString("opcaoVegetariana");
		    	int turno_id = rs.getInt("Turno_idTurno");
		    	System.out.println(id + " " + desc + " " + opcao + " " + turno_id);
			}
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}
