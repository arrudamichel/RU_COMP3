package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.vo.AlunoVO;
import br.uffrj.comp3.rusys.persintece.AlunoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class AlunoHandler {
	
	public static void cadastrarAluno(AlunoVO alunoVO) throws Exception {
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway cg = new ConsumidorGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(alunoVO.getMatricula(), alunoVO.getNome(), 
																		alunoVO.getAnoDeIngresso(), 
																		alunoVO.getSexo(), alunoVO.getTitulo(),
																		alunoVO.getCpf()));
		
		if (!cg.inserir(valores))
			throw new Exception("falha.ao.cadastrar.consumidor");
				
		AlunoGateway ag = new AlunoGateway(conn);

		ArrayList<Object> valores2 = new ArrayList<Object>(Arrays.asList(alunoVO.getMatricula(), alunoVO.getCurso()));

		if (!ag.inserir(valores2))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
		
	}

	public static Collection<Aluno> recuperarAlunos(AlunoVO alunoVO) {
		ArrayList<Aluno> alunos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		AlunoGateway ag = new AlunoGateway(conn);
		ResultSet rs = ag.selecionarAlunos();

		try
		{
			while (rs.next())
			{
				int matricula = rs.getInt(1);
				int idcurso = rs.getInt(2);

				// seleciona curso
				CursoGateway curg = new CursoGateway(conn);
				ResultSet rscur = curg.selecionarCursoPorId(idcurso);
				rscur.next();

				Curso curso = new Curso();
				curso.setIdentificador(rscur.getInt(1));
				curso.setNome(rscur.getString(2));
				curso.setSigla(rscur.getString(3));

				DepartamentoGateway dg = new DepartamentoGateway(conn);
				ResultSet rsd = dg.selecionarDepartamentoPorId(rscur.getInt(4));
				rsd.next();

				Departamento departamento = new Departamento();
				departamento.setIdentificador(rsd.getInt(1));
				departamento.setNome(rsd.getString(2));
				departamento.setSigla(rsd.getString(3));

				curso.setDepartamento(departamento);

				// seleciona consumidor
				ConsumidorGateway cg = new ConsumidorGateway(conn);
				ResultSet rsc = cg.selecionarConsumidorPorMatricula(matricula);
				rsc.next();

				// Verifica se consumidor esta ativo
				if (rsc.getInt(7) == 1)
				{
//					TODO:CPF
					
					Aluno aluno = new Aluno(rsc.getString(2), matricula, rsc.getString(3),SexoEnum.valueOf(rsc.getString(4)), TituloEnum.valueOf(rsc.getString(5)), rsc.getString(6),curso);

					alunos.add(aluno);
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alunos;
	}

	public static void excluirAluno(AlunoVO alunoVO) throws Exception {

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway cg = new ConsumidorGateway(conn);
		
		if (!cg.desativarConsumidor(alunoVO.getMatricula()));
			throw new Exception("falha.ao.cadastrar.refeicao");
			
	}

	public static Aluno recuperarAluno(String matricula) {
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		AlunoGateway ag = new AlunoGateway(conn);
		ResultSet rs = ag.selecionarAlunoPorMatricula(Integer.parseInt(matricula));

		try
		{
			while (rs.next())
			{			
				int idcurso = rs.getInt(2);

				// seleciona curso
				CursoGateway curg = new CursoGateway(conn);
				ResultSet rscur = curg.selecionarCursoPorId(idcurso);
				rscur.next();

				Curso curso = new Curso();
				curso.setIdentificador(rscur.getInt(1));
				curso.setNome(rscur.getString(2));
				curso.setSigla(rscur.getString(3));

				DepartamentoGateway dg = new DepartamentoGateway(conn);
				ResultSet rsd = dg.selecionarDepartamentoPorId(rscur.getInt(4));
				rsd.next();

				Departamento departamento = new Departamento();
				departamento.setIdentificador(rsd.getInt(1));
				departamento.setNome(rsd.getString(2));
				departamento.setSigla(rsd.getString(3));

				curso.setDepartamento(departamento);

				// seleciona consumidor
				ConsumidorGateway cg = new ConsumidorGateway(conn);
				ResultSet rsc = cg.selecionarConsumidorPorMatricula(Integer.parseInt(matricula));
				rsc.next();

					
				Aluno aluno = new Aluno(rsc.getString(2), Integer.parseInt(matricula), rsc.getString(3),SexoEnum.valueOf(rsc.getString(4)), TituloEnum.valueOf(rsc.getString(5)), rsc.getString(6), curso);
				return aluno;

			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
