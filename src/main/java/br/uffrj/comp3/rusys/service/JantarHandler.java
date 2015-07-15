package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Jantar;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.JantarGateway;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class JantarHandler
{
	public static void cadastrarJantar(RefeicaoVO refeicaoVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		int id = RefeicaoHandler.cadastrarRefeicao(refeicaoVO);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		JantarGateway jantarGW = new JantarGateway(conn);

		if (!jantarGW.inserir(id))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
	}
	
	public static Jantar recuperarJantar(int idjat) throws Exception
	{	

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway ag = new RefeicaoGateway(conn);
		ResultSet rsJantar = ag.selecionarRefeicaoPorId(idjat);

		Jantar jantar = null;
		
		while (rsJantar.next())
		{
			int id = rsJantar.getInt(1);
			String opcaoVeg = rsJantar.getString(2);
			String descricao = rsJantar.getString(3);
			
			jantar = new Jantar(id, descricao);
			jantar.setOpcaoVeg(opcaoVeg);
		}
		
		conn.close();
		
		return jantar;
	}

	public static Collection<Jantar> recuperarJantars(RefeicaoVO refeicaoVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		ArrayList<Jantar> jantars = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway ag = new RefeicaoGateway(conn);
		ResultSet rsJantar = ag.selecionarRefeicoes();

		while (rsJantar.next())
		{
			int id = rsJantar.getInt(1);
			String opcaoVeg = rsJantar.getString(2);
			String descricao = rsJantar.getString(3);
			
			Jantar jantar = new Jantar(id, descricao);
			jantar.setOpcaoVeg(opcaoVeg);
		}
		
		conn.close();
		
		return jantars;
	}

	public static void excluirJantar(Refeicao refeicao) throws Exception
	{
		RefeicaoHandler.excluirRefeicao(refeicao);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		JantarGateway cg = new JantarGateway(conn);

		if (!cg.excluirJantar(refeicao.getId()))
			throw new Exception("falha.ao.cadastrar.refeicao");
		
		conn.close();
	}
}
