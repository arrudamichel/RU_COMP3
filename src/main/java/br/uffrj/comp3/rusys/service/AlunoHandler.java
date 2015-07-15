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
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		AlunoGateway alunoGW = new AlunoGateway(conn);

		ArrayList<Object> valores2 = new ArrayList<Object>(Arrays.asList(id,consumidorVO.getMatricula(), consumidorVO.getCurso()));

		if (!alunoGW.inserir(valores2))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
	}
	
	public static Aluno recuperarAluno(int idAluno) throws SQLException, Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		AlunoGateway alunoGW = new AlunoGateway(conn);

		ResultSet rsAluno = alunoGW.selecionarAlunoPorMatricula(idAluno);

		Aluno aluno = null;
		
		while (rsAluno.next())
		{
			int id = rsAluno.getInt(1);
			int matricula = rsAluno.getInt(2);
			int idcurso = rsAluno.getInt(3);

			// seleciona curso
			CursoGateway curg = new CursoGateway(conn);
			ResultSet rsCurso = curg.selecionarCursoPorId(idcurso);
			rsCurso.next();

			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsDepartamento = dg.selecionarDepartamentoPorId(rsCurso.getInt(4));
			rsDepartamento.next();

			Departamento departamento = new Departamento(rsCurso.getInt(5), rsDepartamento.getString(3), rsDepartamento.getString(4));

			Curso curso = new Curso(rsCurso.getInt(1), rsCurso.getString(2), rsCurso.getString(3), departamento);

			// seleciona consumidor
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsConsumidor = cg.selecionarConsumidorPorMatricula(matricula);
			rsConsumidor.next();

			// Verifica se consumidor esta ativo
			if (rsConsumidor.getInt(7) == 1)
			{
				aluno = new Aluno(id, rsAluno.getString(2), matricula, rsAluno.getString(2), curso);

				aluno.setCpf(rsAluno.getString(2));
				aluno.setSexo(SexoEnum.fromString(rsAluno.getString(2)));
				aluno.setTitulo(TituloEnum.fromString(rsAluno.getString(2)));
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
			int matricula = rsAluno.getInt(2);
			int idcurso = rsAluno.getInt(3);

			// seleciona curso
			CursoGateway curg = new CursoGateway(conn);
			ResultSet rsCurso = curg.selecionarCursoPorId(idcurso);
			rsCurso.next();

			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsDepartamento = dg.selecionarDepartamentoPorId(rsCurso.getInt(4));
			rsDepartamento.next();

			Departamento departamento = new Departamento(rsCurso.getInt(5), rsDepartamento.getString(3), rsDepartamento.getString(4));

			Curso curso = new Curso(rsCurso.getInt(1), rsCurso.getString(2), rsCurso.getString(3), departamento);

			// seleciona consumidor
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsConsumidor = cg.selecionarConsumidorPorMatricula(matricula);
			rsConsumidor.next();

			// Verifica se consumidor esta ativo
			if (rsConsumidor.getInt(7) == 1)
			{
				Aluno aluno = new Aluno(id, rsAluno.getString(2), matricula, rsAluno.getString(2), curso);

				aluno.setCpf(rsAluno.getString(2));
				aluno.setSexo(SexoEnum.fromString(rsAluno.getString(2)));
				aluno.setTitulo(TituloEnum.fromString(rsAluno.getString(2)));

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
}
