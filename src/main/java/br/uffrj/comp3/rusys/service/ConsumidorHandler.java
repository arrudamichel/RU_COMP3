package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.service.exceptions.AlterarNoBancoExeception;
import br.uffrj.comp3.rusys.service.exceptions.CpfAlreadyExistsException;
import br.uffrj.comp3.rusys.service.exceptions.ExcluirDoBancoException;
import br.uffrj.comp3.rusys.service.exceptions.InsercaoNoBancoException;
import br.uffrj.comp3.rusys.util.Constantes;

public class ConsumidorHandler
{
	public static int cadastrarConsumidor(ConsumidorVO consumidorVO) throws Exception
	{	
		if(!isCPFunico(consumidorVO.getCpf()))
		{
			throw new CpfAlreadyExistsException(consumidorVO.getCpf());
		}
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway consumidorGW = new ConsumidorGateway(conn);
	
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(consumidorVO.getMatricula(), consumidorVO.getNome(),
				consumidorVO.getAnoDeIngresso(), consumidorVO.getSexo(), consumidorVO.getTitulo(), consumidorVO.getCpf()));

		ResultSet rs = consumidorGW.inserir(valores);
		
		if (rs==null)
			throw new InsercaoNoBancoException();
		
		rs.next();
		
		int id = rs.getInt(1);
		conn.close();
		
		return id;
	}
	
	public static void atualizarConsumidor(ConsumidorVO consumidorVO) throws Exception
	{	
		Consumidor consumidor = recuperarConsumidor(consumidorVO.getId());
		
		if (consumidorVO.getNome() != null)
		{
			consumidor.setNome(consumidorVO.getNome());
		}
		if (consumidorVO.getMatricula() != null)
		{
			consumidor.setMatricula(consumidorVO.getMatricula());
		}
		if (consumidorVO.getAnoDeIngresso() != null)
		{
			consumidor.setAnoDeIngresso(consumidorVO.getAnoDeIngresso());
		}
		if (consumidorVO.getSexo() != null)
		{
			consumidor.setSexo(SexoEnum.fromString(consumidorVO.getSexo()));
		}
		if (consumidorVO.getTitulo() != null)
		{
			consumidor.setTitulo(TituloEnum.fromString(consumidorVO.getTitulo()));
		}
		if (consumidorVO.getCpf() != null)
		{
			consumidor.setCpf(consumidorVO.getCpf());
		}	
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway consumidorGW = new ConsumidorGateway(conn);
		
		System.out.println("CPF"+consumidor.getCpf());
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(consumidor.getNome(),
				consumidor.getAnoDeIngresso(), consumidor.getSexo().toString(), consumidor.getTitulo().toString(), consumidor.getCpf()));
		
		if (!consumidorGW.alterarConsumidor(valores, consumidor.getId())) 
		{
			throw new AlterarNoBancoExeception();
		}

		conn.close();
	}
	
	public static boolean isCPFunico(String cpf) throws Exception
	{
		ConsumidorVO consumidorVO = new ConsumidorVO();
		consumidorVO.setCpf(cpf);
		
		ArrayList<ConsumidorVO> consumidores = (ArrayList<ConsumidorVO>) ConsumidorHandler.recuperarConsumidorVOs(consumidorVO);
		
		return consumidores==null || consumidores.isEmpty();
	}
	
	public static Collection<Consumidor> recuperarConsumidor(ConsumidorVO consumidorVO) throws Exception
	{
		ArrayList<Consumidor> consumidores = new ArrayList<>();

		ArrayList<Aluno> alunos = (ArrayList<Aluno>) AlunoHandler.recuperarAlunos(consumidorVO);
		
		ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) FuncionarioHandler.recuperarFuncionarios(consumidorVO);
		
		consumidores.addAll(alunos);
		consumidores.addAll(funcionarios);
		
		return consumidores;
	}
	
	public static Collection<ConsumidorVO> recuperarConsumidorVOs(ConsumidorVO consumidorVO) throws Exception
	{
		ArrayList<ConsumidorVO> consumidorVOs = new ArrayList<>();
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway consumidorGW = new ConsumidorGateway(conn);
		
		ResultSet rsConsumidores = null;
		
		if (consumidorVO.getId()!=null)
		{
			rsConsumidores = consumidorGW.selecionarConsumidorPorId(consumidorVO.getId());
		} 
		else if (consumidorVO.getCpf()!=null)
		{
			rsConsumidores = consumidorGW.selecionarConsumidorPorCpf(consumidorVO.getCpf());
		}
		else if (consumidorVO.getMatricula()!=null)
		{
			rsConsumidores = consumidorGW.selecionarConsumidorPorMatricula(consumidorVO.getMatricula());
		}
		else
		{
			rsConsumidores = consumidorGW.selecionarConsumidores();
		}
		
		ConsumidorVO consumidorVOretorno = null;
		
		while (rsConsumidores.next())
		{	
			consumidorVOretorno =  new ConsumidorVO();
			
			consumidorVOretorno.setId(rsConsumidores.getInt("id")); //TODO: completar. por enquanto nao foi preciso
			
			consumidorVOs.add(consumidorVOretorno);
		}

		return consumidorVOs;
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
			throw new ExcluirDoBancoException();
		
		conn.close();
	}
	
	public static Consumidor recuperarConsumidorPorMatricula(int matricula) throws SQLException, Exception
	{		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway cg = new ConsumidorGateway(conn);

		ResultSet rsConsumidor = cg.selecionarConsumidorPorMatricula(matricula);
		
		Consumidor consumidor = null;
		while(rsConsumidor.next()){
			int consumidor_id = rsConsumidor.getInt("id");
			
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
