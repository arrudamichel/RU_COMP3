package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
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
	
	public static Ticket recuperarTicket(int id) throws Exception
	{	
/*		CREATE TABLE IF NOT EXISTS "ticket" (
				  "ticket_id" INT NOT NULL AUTO_INCREMENT,
				  "consumidor_matricula" INT NOT NULL,
				  "refeicao_idRefeicao" INT NOT NULL,
				  "preco" DECIMAL(10,2) NOT NULL,
				  "pago" TINYINT(1) NOT NULL,*/
		
		
		Ticket ticket = new Ticket();
	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway ticketGateway = new TicketGateway(conn);
		
		ResultSet rs = ticketGateway.selecionarTicketPorId(id);
		
		rs.next();
		int consuId = rs.getInt(2);
		
		ConsumidorGateway consuGateway = new ConsumidorGateway(conn);
		
		
		AlunoGateway alunoGateway = new AlunoGateway(conn);
		FuncionarioGateway funcGateway = new FuncionarioGateway(conn);
		
		ResultSet func = funcGateway.selecionarFuncionarioPorMatricula(consuId);
		ResultSet alu = alunoGateway.selecionarAlunoPorMatricula(consuId);
		
		
		
		ticket.setConsumidor(consumidor);
		
		t.setIdentificador(rs.getInt(1));
		curso.setNome(rs.getString(2));
		curso.setSigla(rs.getString(3));

		DepartamentoGateway dg = new DepartamentoGateway(conn);
		ResultSet rsd = dg.selecionarDepartamentoPorId(rs.getInt(4));
		rsd.next();

		Departamento departamento = new Departamento();
		departamento.setIdentificador(rsd.getInt(1));
		departamento.setNome(rsd.getString(2));
		departamento.setSigla(rsd.getString(3));

		curso.setDepartamento(departamento);
	
		conn.close();

		return curso;	
	}
}
