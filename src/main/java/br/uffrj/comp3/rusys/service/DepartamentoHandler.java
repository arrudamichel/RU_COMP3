package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.util.Constantes;

public class DepartamentoHandler
{
	public static void cadastrarDepartamento(DepartamentoVO departamentoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

//		CursoGateway cursoGateway = new CursoGateway(conn);
//		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);
//
//		ResultSet rs = deptGateway.selecionarDepartamentoPorNome(departamentoVO.getNomeDepartamento());
//
//		int deptId = -1;
//
//		rs.next();
//		deptId = rs.getInt(1);
//
//		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(departamentoVO.getNome(), departamentoVO.getSigla(), rs.getInt(deptId)));
//
//		if (!cursoGateway.inserir(valores))
//			throw new Exception("falha.ao.cadastrar.departamento");

		conn.close();
	}
	
	public static void atualizarDepartamento(Departamento departamento)
	{
		
	}
	
	public static void excluirDepartamento(Departamento departamento)
	{
		
	}
	
	public static ArrayList<Departamento> recuperarDepartamentos(DepartamentoVO departamentoVO)
	{
		return null;
		
	}
}
