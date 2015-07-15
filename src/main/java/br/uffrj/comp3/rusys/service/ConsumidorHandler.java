package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class ConsumidorHandler
{
	public static int cadastrarConsumidor(ConsumidorVO consumidorVO) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway consumidorGW = new ConsumidorGateway(conn);
	
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(consumidorVO.getMatricula(), consumidorVO.getNome(),
				consumidorVO.getAnoDeIngresso(), consumidorVO.getSexo(), consumidorVO.getTitulo(), consumidorVO.getCpf()));
	
		ResultSet rs = consumidorGW.inserir(valores);
		
		if (rs==null)
			throw new Exception("falha.ao.cadastrar.consumidor");
		
		conn.close();
		
		return rs.getFetchSize();
	}

	public static void excluirConsumidor(Consumidor consumidor) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway cg = new ConsumidorGateway(conn);

		if (!cg.desativarConsumidor(consumidor.getId()))
			throw new Exception("falha.ao.excluir.consumidor");
		
		conn.close();
	}
}
