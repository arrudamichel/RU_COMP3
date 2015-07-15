package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Dejejum;
import br.uffrj.comp3.rusys.model.Jantar;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.DejejumGateway;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class DejejumHandler
{
	public static void cadastrarJantar(RefeicaoVO refeicaoVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		int id = RefeicaoHandler.cadastrarRefeicao(refeicaoVO);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		DejejumGateway deJejumGW = new DejejumGateway(conn);

		if (!deJejumGW.inserir(id))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
	}
	
	public static Dejejum recuperarDejejum(int iddej) throws Exception
	{	

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway ag = new RefeicaoGateway(conn);
		ResultSet rsAlmoco = ag.selecionarRefeicaoPorId(iddej);

		Dejejum dejejum = null;
		
		while (rsAlmoco.next())
		{
			int id = rsAlmoco.getInt(1);
			String opcaoVeg = rsAlmoco.getString(2);
			String descricao = rsAlmoco.getString(3);
			
			dejejum = new Dejejum(id, descricao);
			dejejum.setOpcaoVeg(opcaoVeg);
		}
		
		conn.close();
		
		return dejejum;
	}

	public static Collection<Dejejum> recuperarDejejums(RefeicaoVO refeicaoVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		ArrayList<Dejejum> dejejums = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway ag = new RefeicaoGateway(conn);
		ResultSet rsDejejum = ag.selecionarRefeicoes();

		while (rsDejejum.next())
		{
			int id = rsDejejum.getInt(1);
			String opcaoVeg = rsDejejum.getString(2);
			String descricao = rsDejejum.getString(3);
			
			Dejejum desejum = new Dejejum(id, descricao);
			desejum.setOpcaoVeg(opcaoVeg);
			dejejums.add(desejum);
		}
		
		conn.close();
		
		return dejejums;
	}

	public static void excluirJantar(Refeicao refeicao) throws Exception
	{
		RefeicaoHandler.excluirRefeicao(refeicao);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		DejejumGateway cg = new DejejumGateway(conn);

		if (!cg.excluirDejejum(refeicao.getId()))
			throw new Exception("falha.ao.cadastrar.refeicao");
		
		conn.close();
	}
}
