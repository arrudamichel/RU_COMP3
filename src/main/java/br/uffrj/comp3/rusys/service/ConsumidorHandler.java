package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.model.vo.TicketVO;
import br.uffrj.comp3.rusys.persintece.AlunoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.persintece.TicketGateway;
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
		rs.next();
		if (rs==null)
			throw new Exception("falha.ao.cadastrar.consumidor");
		int id = rs.getInt(1);
		conn.close();
		
		return id;
	}
	
	public static void atualizarConsumidor(ConsumidorVO consumidorVO) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway consumidorGW = new ConsumidorGateway(conn);
		System.out.println("CPF"+consumidorVO.getCpf());
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(consumidorVO.getNome(),
				consumidorVO.getAnoDeIngresso(), consumidorVO.getSexo(), consumidorVO.getTitulo()));
		
		consumidorGW.alterarConsumidor(valores, consumidorVO.getMatricula());
		System.out.println("AquiConsumidor");

		conn.close();

	}
	
	public static Collection<Consumidor> recuperarConsumidor(ConsumidorVO consumidorVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		ArrayList<Consumidor> consumidores = new ArrayList<>();

		
		ArrayList<Aluno> alunos = (ArrayList<Aluno>) AlunoHandler.recuperarAlunos(consumidorVO);
		
		
		ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) FuncionarioHandler.recuperarFuncionarios(consumidorVO);
		
		consumidores.addAll(alunos);
		consumidores.addAll(funcionarios);
		
		return consumidores;
	}

	
	public static Consumidor recuperarConsumidor(int idConsumidor) throws SQLException, Exception
	{
		Consumidor consumidor = null;
		
		Aluno aluno = AlunoHandler.recuperarAluno(idConsumidor);		
		
		if (aluno!=null) 
		{
			consumidor = aluno;
			return consumidor;
		}
		
		Funcionario funcionario = FuncionarioHandler.recuperarFuncionario(idConsumidor);
		
		if (funcionario!=null) 
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
	
	public static Consumidor recuperarConsumidorPorMatricula(int matricula) throws SQLException, Exception
	{		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway cg = new ConsumidorGateway(conn);

		ResultSet rsConsumidor = cg.selecionarConsumidorPorMatricula(matricula);
		
		Consumidor consumidor = null;
		while(rsConsumidor.next()){
			int consumidor_id = rsConsumidor.getInt("consumidor_id");
			
			Aluno aluno = AlunoHandler.recuperarAluno(consumidor_id);		
			
			if (aluno!=null) 
			{
				consumidor = aluno;
				return consumidor;
			}
			
			Funcionario funcionario = FuncionarioHandler.recuperarFuncionario(consumidor_id);
			
			if (funcionario!=null) 
			{
				consumidor = funcionario;
			}					
		}
		
		conn.close();
		
		return consumidor;
	}
}
