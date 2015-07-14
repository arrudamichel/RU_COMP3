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
		
		System.out.println("oiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
	}
	
	public static void atualizarCurso(Curso curso)
	{
		
	}
	
	public static void excluirCurso(Curso curso)
	{
		
	}
	
	public static ArrayList<Curso> recuperarCursos(CursoVO cursoVO)
	{
		return null;
		
	}
}
