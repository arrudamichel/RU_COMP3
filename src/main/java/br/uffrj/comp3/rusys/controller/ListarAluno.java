package br.uffrj.comp3.rusys.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.persintece.AlunoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.util.Constantes;
import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;


public class ListarAluno
{
	public ListarAluno()
	{
	}

	public ArrayList<Aluno> listar()
	{
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
				if (rs.getInt(7) == 1)
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
}
