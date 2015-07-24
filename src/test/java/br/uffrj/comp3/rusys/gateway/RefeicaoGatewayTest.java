package br.uffrj.comp3.rusys.gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;



public class RefeicaoGatewayTest {

	public static void main(String[] args) {
		
//		Refeicao refeicao = new Refeicao(1,"arroz"){};
//		refeicao.setDescricao("Refeicao de Teste");
//		refeicao.setOpcaoVeg("Vag de Test");
//		refeicao.setTurno(TurnoEnum.TARDE);
//		
//		
//		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
//		
//		TurnoGateway turnoGateway = new TurnoGateway(conn);
//
//		ResultSet rs = turnoGateway.selecionarTurnoPorNome(refeicao.getTurno().toString());
//
//		int turnoId = -1;
//		try {
//			rs.next();
//			turnoId = rs.getInt(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

/*		ArrayList<Object> valores = new ArrayList<>(Arrays.asList(refeicao.getDescricao(), refeicao.getOpcaoVeg(), turnoId));
				// \"descricao\", \"opcaoVegetariana\", \"Turno_idTurno\"
		if (refeicaoGateway.inserir(valores))
			System.out.println(valores + " inseridos");
		else
			System.out.println("Erro ao inserir refeicao");*/
		
//		System.out.println("SELECT");
//
//    	//rs = refeicaoGateway.selecionarRefeicaoPorId(id);
//		rs = refeicaoGateway.selecionarRefeicoes();
//		try {
//			while (rs.next()) {
//				int id = rs.getInt("idRefeicao");
//				String desc = rs.getString("descricao");
//		    	String opcao = rs.getString("opcaoVegetariana");
//		    	int turno_id = rs.getInt("Turno_idTurno");
//		    	System.out.println(id + " " + desc + " " + opcao + " " + turno_id);
//			}
//	    	
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

}
