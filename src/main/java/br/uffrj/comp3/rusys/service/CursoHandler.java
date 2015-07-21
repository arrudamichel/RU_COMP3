package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
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

		ResultSet rsDepartamento = deptGateway.selecionarDepartamentoPorId(cursoVO.getDepartamento());

		int deptId = -1;
		
		rsDepartamento.next();
		deptId = rsDepartamento.getInt(1);
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(cursoVO.getNome(), cursoVO.getSigla(), deptId));

		if (cursoGateway.inserir(valores) == null)
			throw new Exception("falha.ao.cadastrar.curso");
		conn.close();
	}
	
	public static void atualizarCurso(Curso curso) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(curso.getNome(), curso.getSigla(), curso.getDepartamento().getIdentificador())); //TODO FALTA O DEPARTAMENTO

		if (!cg.alterarCurso(valores, curso.getId()))
			throw new Exception("falha.ao.atualizart.curso");
		
		conn.close();
	}
	
	public static void excluirCurso(Curso curso) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);

		if (!cg.excluirCurso(curso.getId()))
			throw new Exception("falha.ao.excluir.curso");
	}
	
	public static Curso recuperarCurso(int id) throws Exception
	{	
		Curso curso = null;
	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);
		ResultSet rsCurso = cg.selecionarCursoPorId(id);
		rsCurso.next();
		
		DepartamentoGateway dg = new DepartamentoGateway(conn);
		ResultSet rsDepartamento = dg.selecionarDepartamentoPorId(rsCurso.getInt(4));
		rsDepartamento.next();

		Departamento departamento = new Departamento(rsDepartamento.getInt(1), rsDepartamento.getString(2), rsDepartamento.getString(3));
		
		curso = new Curso(id, rsCurso.getString(2), rsCurso.getString(3), departamento);
	
		conn.close();

		return curso;	
	}
	
	public static ArrayList<Curso> recuperarCursos(CursoVO cursoVO) throws Exception
	{
		
		ArrayList<Curso> cursos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);

		ResultSet rsCursos = cg.selecionarCursos();
		
		while (rsCursos.next())
		{
			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsDepartamento = dg.selecionarDepartamentoPorId(rsCursos.getInt(4));
			rsDepartamento.next();

			Departamento departamento = new Departamento(rsDepartamento.getInt(1), rsDepartamento.getString(2), rsDepartamento.getString(3));
			
			Curso curso = new Curso(rsCursos.getInt(1), rsCursos.getString(2), rsCursos.getString(3), departamento);

			cursos.add(curso);
		}

		conn.close();
		
		return cursos;
	}
}
