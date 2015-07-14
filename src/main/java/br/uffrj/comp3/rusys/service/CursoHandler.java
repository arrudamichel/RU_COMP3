package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class CursoHandler
{
	public static void cadastrarCurso(CursoVO cursoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		CursoGateway cursoGateway = new CursoGateway(conn);
		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);

		ResultSet rs = deptGateway.selecionarDepartamentoPorNome(cursoVO.getNomeDepartamento());

		int deptId = -1;

		rs.next();
		deptId = rs.getInt(1);

		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(cursoVO.getNome(), cursoVO.getSigla(), rs.getInt(deptId)));

		if (!cursoGateway.inserir(valores))
			throw new Exception("falha.ao.cadastrar.curso");

		conn.close();
	}
	
	public static void atualizarCurso(Curso curso) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);

		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(curso.getNome(), curso.getSigla(), curso.getDepartamento().getIdentificador()));

		if (!cg.alterarCurso(valores, curso.getIdentificador()))
			throw new Exception("falha.ao.atualizart.curso");
	}
	
	public static void excluirCurso(Curso curso) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);

		if (!cg.excluirCurso(curso.getIdentificador()))
			throw new Exception("falha.ao.excluir.curso");
	}
	
	public static Curso recuperarCurso(int id) throws Exception
	{	
		Curso curso = new Curso();
	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);
		
		ResultSet rs = cg.selecionarCursoPorId(id);
		
		if (rs.getFetchSize() != 1)
		{
			curso.setIdentificador(rs.getInt(1));
			curso.setNome(rs.getString(2));
			curso.setSigla(rs.getString(3));

			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsd = dg.selecionarDepartamentoPorId(rs.getInt(4));
			rsd.next();

			Departamento departamento = new Departamento();
			departamento.setIdentificador(rsd.getInt(1));
			departamento.setNome(rsd.getString(2));
			departamento.setSigla(rsd.getString(3));

			curso.setDepartamento(departamento);
		}
		else
		{
			throw new Exception("curso.não.existe");
		}

		conn.close();

		return curso;	
	}
	
	public static ArrayList<Curso> recuperarCursos(CursoVO cursoVO) throws SQLException
	{
		ArrayList<Curso> cursos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);

		ResultSet rs = cg.selecionarCursos();

	
		while (rs.next())
		{
			Curso curso = new Curso();
			curso.setIdentificador(rs.getInt(1));
			curso.setNome(rs.getString(2));
			curso.setSigla(rs.getString(3));

			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsd = dg.selecionarDepartamentoPorId(rs.getInt(4));
			rsd.next();

			Departamento departamento = new Departamento();
			departamento.setIdentificador(rsd.getInt(1));
			departamento.setNome(rsd.getString(2));
			departamento.setSigla(rsd.getString(3));

			curso.setDepartamento(departamento);

			cursos.add(curso);
		}

		conn.close();
		

		return cursos;
		
	}
}
