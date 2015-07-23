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
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.persintece.AlunoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class AlunoHandler
{
	public static void cadastrarAluno(ConsumidorVO consumidorVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		int id = ConsumidorHandler.cadastrarConsumidor(consumidorVO);
		System.out.println(id);
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		AlunoGateway alunoGW = new AlunoGateway(conn);
		
		ArrayList<Object> valores2 = new ArrayList<Object>(Arrays.asList(id,consumidorVO.getCurso()));
		
		if (!alunoGW.inserir(valores2))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
	}
	
	public static Aluno recuperarAluno(int idAluno) throws SQLException, Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		AlunoGateway alunoGW = new AlunoGateway(conn);

		ResultSet rsAluno = alunoGW.selecionarAlunoPorId(idAluno);

		Aluno aluno = null;
		
		while (rsAluno.next())
		{
			int id = rsAluno.getInt(1);
			//int matricula = rsAluno.getInt(2);
			int idcurso = rsAluno.getInt("curso_id_curso");

			// seleciona curso
			CursoGateway curg = new CursoGateway(conn);
			ResultSet rsCurso = curg.selecionarCursoPorId(idcurso);
			rsCurso.next();
			
			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsDepartamento = dg.selecionarDepartamentoPorId(rsCurso.getInt(4));
			rsDepartamento.next();

			Departamento departamento = new Departamento(rsCurso.getInt("departamento_id_departamento"), rsDepartamento.getString("nome"), rsDepartamento.getString("sigla"));

			Curso curso = new Curso(rsCurso.getInt("id_curso"), rsCurso.getString("nome"), rsCurso.getString("sigla"), departamento);

			// seleciona consumidor
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsConsumidor = cg.selecionarConsumidorPorId(id);
			rsConsumidor.next();

			// Verifica se consumidor esta ativo
			if (rsConsumidor.getInt("situacao") == 1)
			{
				aluno = new Aluno(id, rsConsumidor.getString("nome"), rsConsumidor.getInt("matricula"), rsConsumidor.getString("ano_ingresso"), curso);

				aluno.setCpf(rsConsumidor.getString("cpf"));
				aluno.setSexo(SexoEnum.fromString(rsConsumidor.getString("sexo")));
				aluno.setTitulo(TituloEnum.fromString(rsConsumidor.getString("titulo")));
			}
		}
		
		return aluno;
	
	}

	public static Collection<Aluno> recuperarAlunos(ConsumidorVO consumidorVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		ArrayList<Aluno> alunos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		AlunoGateway ag = new AlunoGateway(conn);
		ResultSet rsAluno = ag.selecionarAlunos();

		while (rsAluno.next())
		{
			int id = rsAluno.getInt(1);
			//int matricula = rsAluno.getInt(1);
			int idcurso = rsAluno.getInt(2);

			// seleciona curso
			CursoGateway curg = new CursoGateway(conn);
			ResultSet rsCurso = curg.selecionarCursoPorId(idcurso);
			rsCurso.next();

			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsDepartamento = dg.selecionarDepartamentoPorId(rsCurso.getInt(4));
			rsDepartamento.next();
			//System.out.println("AlunoHandler.RecuperarAlunos . get5 "+ rsCurso.getInt(5) + "rsDepartamento.getString(3)" + rsDepartamento.getString(3) + "rsDepartamento.getString(4)" + rsDepartamento.getString(4));
			Departamento departamento = new Departamento(rsCurso.getInt("departamento_id_departamento"), rsDepartamento.getString("nome"), rsDepartamento.getString("sigla"));

			Curso curso = new Curso(rsCurso.getInt("id_curso"), rsCurso.getString("nome"), rsCurso.getString("sigla"), departamento);

			// seleciona consumidor
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsConsumidor = cg.selecionarConsumidorPorId(id);
			rsConsumidor.next();

			// Verifica se consumidor esta ativo
			if (rsConsumidor.getInt("situacao") == 1)
			{
				Aluno aluno = new Aluno(id, rsConsumidor.getString("nome"), rsConsumidor.getInt("matricula"), rsConsumidor.getString("ano_ingresso"), curso);

				aluno.setCpf(rsConsumidor.getString("cpf"));
				aluno.setSexo(SexoEnum.fromString(rsConsumidor.getString("sexo")));
				aluno.setTitulo(TituloEnum.fromString(rsConsumidor.getString("titulo")));

				alunos.add(aluno);
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
			throw new Exception("falha.ao.cadastrar.refeicao");
		
		conn.close();
	}
	
	public static void atualizarAluno(ConsumidorVO consumidorVO, int id) throws Exception{

		ConsumidorHandler.atualizarConsumidor(consumidorVO);

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		AlunoGateway alunoGW = new AlunoGateway(conn);

		ArrayList<Object> valores2 = new ArrayList<Object>(Arrays.asList(consumidorVO.getCurso()));
		
		alunoGW.alterarAluno(valores2, id);

		conn.close();
	}
}
