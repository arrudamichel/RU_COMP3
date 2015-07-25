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
		if(!isSILGAunica(cursoVO.getSigla()))
		{
			throw new Exception("CursoHandler.cadastrarCurso.sigla.informado.ja.cadastrada");
		}
		
		Departamento departamento = DepartamentoHandler.recuperarDepartamento(cursoVO.getDepartamento());
		Curso curso = new Curso(cursoVO.getId(), cursoVO.getNome(), cursoVO.getSigla(), departamento);
		
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		CursoGateway cursoGateway = new CursoGateway(conn);
		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);

		ResultSet rsDepartamento = deptGateway.selecionarDepartamentoPorId(curso.getDepartamento().getId());

		int deptId = -1;
		
		rsDepartamento.next();
		deptId = rsDepartamento.getInt(1);
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(curso.getNome(), curso.getSigla(), deptId));

		if (cursoGateway.inserir(valores) == null)
			throw new Exception("falha.ao.cadastrar.curso");
		
		conn.close();
	}
	
	public static void atualizarCurso(CursoVO cursoVO) throws Exception
	{
		Curso curso = recuperarCurso(cursoVO.getId());
		
		if (cursoVO.getNome() != null)
		{
			curso.setNome(cursoVO.getNome());
		}
		if (cursoVO.getSigla() != null)
		{
			curso.setSigla(cursoVO.getSigla());
		}
		if (cursoVO.getDepartamento() != null)
		{
			Departamento departamento = DepartamentoHandler.recuperarDepartamento(cursoVO.getDepartamento());		
			curso.setDepartamento(departamento);
		}
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(curso.getNome(), curso.getSigla(), curso.getDepartamento().getId()));

		if (!cg.alterarCurso(valores, curso.getId()))
			throw new Exception("falha.ao.atualizar.curso");
		
		conn.close();
	}
	
	public static boolean isSILGAunica(String sigla) throws Exception
	{
		CursoVO cursoVO = new CursoVO();
		cursoVO.setSigla(sigla);
		
		ArrayList<Curso> cursos = (ArrayList<Curso>) CursoHandler.recuperarCursos(cursoVO);
		
		return cursos==null || cursos.isEmpty();
	}
	
	public static void excluirCurso(Curso curso) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);

		if (!cg.excluirCurso(curso.getId()))
			throw new Exception("falha.ao.excluir.curso");
		
		conn.close();
	}
	
	public static Curso recuperarCurso(int id) throws Exception
	{	
		Curso curso = null;
	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);
		ResultSet rsCurso = cg.selecionarCursoPorId(id);
		
		rsCurso.next();
		
		int departamentoId = rsCurso.getInt("departamento_fk");
		String nome = rsCurso.getString("nome");
		String sigla = rsCurso.getString("sigla");
		
		conn.close();

		Departamento departamento = DepartamentoHandler.recuperarDepartamento(departamentoId);
		
		curso = new Curso(id, nome, sigla, departamento);
	
		return curso;	
	}
	
	public static ArrayList<Curso> recuperarCursos(CursoVO cursoVO) throws Exception
	{
		ArrayList<Curso> cursos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);

		ResultSet rsCursos = null;
		if(cursoVO.getSigla() != null){
			
			rsCursos = cg.selecionarCursoPorSigla(cursoVO.getSigla());
			
		} else {
			rsCursos = cg.selecionarCursos();
		}

		while (rsCursos.next())
		{
			Departamento departamento = DepartamentoHandler.recuperarDepartamento(rsCursos.getInt("departamento_fk"));
			
			Curso curso = new Curso(rsCursos.getInt("id"), rsCursos.getString("nome"), rsCursos.getString("sigla"), departamento);

			cursos.add(curso);
		}
		
		conn.close();
		
		return cursos;
	}
}
