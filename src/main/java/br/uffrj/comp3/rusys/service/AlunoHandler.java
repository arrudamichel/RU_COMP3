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
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.persintece.AlunoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class AlunoHandler
{
	public static void cadastrarAluno(ConsumidorVO consumidorVO) throws Exception
	{
		Curso curso =  CursoHandler.recuperarCurso(consumidorVO.getCurso());	
		@SuppressWarnings("unused")
		Aluno aluno = new Aluno(consumidorVO.getId(), consumidorVO.getNome(), consumidorVO.getMatricula(), consumidorVO.getAnoDeIngresso(), curso);
		
		
		int id = ConsumidorHandler.cadastrarConsumidor(consumidorVO);
		//System.out.println(id);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		AlunoGateway alunoGW = new AlunoGateway(conn);
		
		ArrayList<Object> valores2 = new ArrayList<Object>(Arrays.asList(id,consumidorVO.getCurso()));
		
		if (!alunoGW.inserir(valores2))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
	}
	
	public static Aluno recuperarAluno(int id) throws SQLException, Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		AlunoGateway alunoGW = new AlunoGateway(conn);

		ResultSet rsAlunos = alunoGW.selecionarAlunoPorId(id);
		
		Aluno aluno = null;
		
		if(rsAlunos.next())
		{	
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsConsumidores = cg.selecionarConsumidorPorId(id);
			
			int idCurso = rsAlunos.getInt("curso_fk");
			
			Curso curso = CursoHandler.recuperarCurso(idCurso);
			
			while (rsConsumidores.next())
			{
				if (rsConsumidores.getInt("situacao") == 1)
				{
					aluno = new Aluno(id, rsConsumidores.getString("nome"), rsConsumidores.getInt("matricula"), rsConsumidores.getInt("ano_ingresso"), curso);
	
					aluno.setCpf(rsConsumidores.getString("cpf"));
					aluno.setSexo(SexoEnum.fromString(rsConsumidores.getString("sexo")));
					aluno.setTitulo(TituloEnum.fromString(rsConsumidores.getString("titulo")));
				}
			}
		}
		
		conn.close();
		
		return aluno;
	}

	public static Collection<Aluno> recuperarAlunos(ConsumidorVO consumidorVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		ArrayList<ConsumidorVO> consumidorVOs = (ArrayList<ConsumidorVO>) ConsumidorHandler.recuperarConsumidorVOs(consumidorVO);
		
		ArrayList<Aluno> alunos = new ArrayList<>();
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		AlunoGateway alunoGW = new AlunoGateway(conn);
		
		Aluno aluno = null;
		
		for (ConsumidorVO consVO: consumidorVOs)
		{	
			ResultSet rsAlunos = alunoGW.selecionarAlunoPorId(consVO.getId());
	
			if (rsAlunos.next())
			{
				int idCurso = rsAlunos.getInt("curso_fk");	
				
				Curso curso = CursoHandler.recuperarCurso(idCurso);
				
				ConsumidorGateway cg = new ConsumidorGateway(conn);
				ResultSet rsConsumidores = cg.selecionarConsumidorPorId(rsAlunos.getInt("id"));
				
				while (rsConsumidores.next())
				{
					if (rsConsumidores.getInt("situacao") == 1)
					{
						aluno = new Aluno(rsConsumidores.getInt("id"), rsConsumidores.getString("nome"), rsConsumidores.getInt("matricula"), rsConsumidores.getInt("ano_ingresso"), curso);
						
						aluno.setCpf(rsConsumidores.getString("cpf"));
						aluno.setSexo(SexoEnum.fromString(rsConsumidores.getString("sexo")));
						aluno.setTitulo(TituloEnum.fromString(rsConsumidores.getString("titulo")));
						
						alunos.add(aluno);
					}
				}
			}
		}
		
		conn.close();
		
		return alunos;
	}

	public static void excluirAluno(Consumidor consumidor) throws Exception
	{
		ConsumidorHandler.excluirConsumidor(consumidor);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		AlunoGateway cg = new AlunoGateway(conn);

		if (!cg.excluirAluno(consumidor.getId()))
			throw new Exception("alunoHandler.falha.ao.excluir.aluno");
		
		conn.close();
	}
	
	public static void atualizarAluno(ConsumidorVO consumidorVO, int id) throws Exception
	{
		Curso curso =  CursoHandler.recuperarCurso(consumidorVO.getCurso());	
		@SuppressWarnings("unused")
		Aluno aluno = new Aluno(consumidorVO.getId(), consumidorVO.getNome(), consumidorVO.getMatricula(), consumidorVO.getAnoDeIngresso(), curso);
		
		
		ConsumidorHandler.atualizarConsumidor(consumidorVO);

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		AlunoGateway alunoGW = new AlunoGateway(conn);

		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(consumidorVO.getCurso()));
		
		if(!alunoGW.alterarAluno(valores, id))
		{
			throw new Exception("alunoHandler.erro.ao.atualizar.aluno");
		}
		conn.close();
	}
}
