package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.vo.TicketVO;
import br.uffrj.comp3.rusys.persintece.AlunoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.persintece.FuncionarioGateway;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.persintece.TicketGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class TicketHandler {

	public static void cadastrarTicket(TicketVO ticketVO) throws Exception {
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TicketGateway ticketGateway = new TicketGateway(conn);

		ConsumidorGateway consumidorGateway = new ConsumidorGateway(conn);

		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		ResultSet rs = consumidorGateway.selecionarConsumidorPorMatricula(ticketVO.getMatricula());

		int consumId = -1;

		rs.next();
		consumId = rs.getInt(1);

		rs = refeicaoGateway.selecionarRefeicaoPorId(ticketVO.getRefeicao());

		int refeicaoId = -1;

		rs.next();
		refeicaoId = rs.getInt(1);

		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(consumId, refeicaoId, ticketVO.getValor(), ticketVO.isPago()));

		if (!ticketGateway.inserir(valores))
			throw new Exception("falha.ao.cadastrar.ticket");

		conn.close();

	}

	public static Ticket recuperarTicket(int id) throws Exception {
		/*
		 * CREATE TABLE IF NOT EXISTS "ticket" ( "ticket_id" INT NOT NULL
		 * AUTO_INCREMENT, "consumidor_matricula" INT NOT NULL,
		 * "refeicao_idRefeicao" INT NOT NULL, "preco" DECIMAL(10,2) NOT NULL,
		 * "pago" TINYINT(1) NOT NULL,
		 */


		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway ticketGateway = new TicketGateway(conn);

		ResultSet rs = ticketGateway.selecionarTicketPorId(id);

		rs.next();
		int consuId = rs.getInt(2);

		ConsumidorGateway consuGateway = new ConsumidorGateway(conn);

		AlunoGateway alunoGateway = new AlunoGateway(conn);
		FuncionarioGateway funcGateway = new FuncionarioGateway(conn);

		ResultSet funcSet = funcGateway.selecionarFuncionarioPorMatricula(consuId);
		ResultSet aluSet = alunoGateway.selecionarAlunoPorMatricula(consuId);
		ResultSet consuSet = consuGateway.selecionarConsumidorPorMatricula(consuId);
		
		// ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao")
		Consumidor consumidor;
		if (funcSet.isBeforeFirst()) {
			consumidor = new Funcionario();		
		} else if (aluSet.isBeforeFirst()) {
			consumidor = new Aluno();
		} else {
			throw new Exception("consumidor.nao.econtrado");
		}
		
		consumidor.setMatricula(consuId);
		consumidor.setNome(consuSet.getString(2));
		consumidor.setAnoDeIngresso(consuSet.getString(3));
		consumidor.setSexo(consuSet.getString(4).equals("M") ? SexoEnum.M : SexoEnum.F);
		consumidor.setCpf(consuSet.getString(6));

		//public Ticket(int id, boolean pago,Consumidor consumidor, Refeicao refeicao)
		// ("consumidor_matricula", "refeicao_idRefeicao", "preco", "pago")
		Ticket ticket = new Ticket(id);
		ticket.setConsumidor(consumidor);
		ticket.setValor(rs.getFloat(3));
		ticket.setPago((rs.getInt(4) == 1) ? true : false);


		conn.close();

		return ticket;
	}
}
