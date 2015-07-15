package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.vo.TicketVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
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
		Ticket ticket = new Ticket();
	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		TicketGateway ticketGateway = new TicketGateway(conn);
		
		ResultSet rs = ticketGateway.selecionarTicketPorId(id);
		
		rs.next();
		
		ConsumidorGateway consuGateway = new ConsumidorGateway(conn);
		
		int consuId = consuGateway.selecionarConsumidorPorMatricula(id);
		
		
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
