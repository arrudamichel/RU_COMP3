package br.uffrj.comp3.rusys.service;

import java.sql.Connection;

import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.model.vo.TicketVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.util.Constantes;

public class TicketHandler {
	
	public static void cadastrarTicket(TicketVO ticketVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
	}

}
