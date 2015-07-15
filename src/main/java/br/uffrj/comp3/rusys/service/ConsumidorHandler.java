package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.persintece.AlunoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
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
	
	public static Consumidor recuperarConsumidor(int idConsumidor) throws SQLException, Exception
	{
		Consumidor consumidor = null;
		
		Aluno aluno = AlunoHandler.recuperarAluno(idConsumidor);
		
		Funcionario funcionario = FuncionarioHandler.recuperarFuncionario(idConsumidor);

		if (aluno!=null) 
		{
			consumidor = aluno;
		} 
		else if (funcionario!=null) 
		{
			consumidor = funcionario;
		}
		
		return consumidor;
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
