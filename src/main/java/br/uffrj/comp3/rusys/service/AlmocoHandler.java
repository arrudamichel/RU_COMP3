package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Almoco;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.persintece.AlmocoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class AlmocoHandler
{
	public static void cadastrarAlmoco(RefeicaoVO refeicaoVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		int id = RefeicaoHandler.cadastrarRefeicao(refeicaoVO);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		AlmocoGateway almocoGW = new AlmocoGateway(conn);

		if (!almocoGW.inserir(id))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
	}
	
	public static Almoco recuperarAlmoco(int almocoid) throws Exception
	{	

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway ag = new RefeicaoGateway(conn);
		ResultSet rsAlmoco = ag.selecionarRefeicaoPorId(almocoid);

		Almoco almoco = null;
		
		while (rsAlmoco.next())
		{
			int id = rsAlmoco.getInt(1);
			String opcaoVeg = rsAlmoco.getString(2);
			String descricao = rsAlmoco.getString(3);
			
			almoco = new Almoco(id, descricao);
			almoco.setOpcaoVeg(opcaoVeg);
		}
		
		conn.close();
		
		return almoco;
	}

	public static Collection<Almoco> recuperarAlmocos(RefeicaoVO refeicaoVO) throws Exception
	{
//		Aluno aluno = new Aluno(id, nome, matricula, anoDeIngresso, curso);
		
		ArrayList<Almoco> almocos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway ag = new RefeicaoGateway(conn);
		ResultSet rsAlmoco = ag.selecionarRefeicoes();

		while (rsAlmoco.next())
		{
			int id = rsAlmoco.getInt(1);
			String opcaoVeg = rsAlmoco.getString(2);
			String descricao = rsAlmoco.getString(3);
			
			Almoco almoco = new Almoco(id, descricao);
			almoco.setOpcaoVeg(opcaoVeg);
			almocos.add(almoco);
		}
		
		conn.close();
		
		return almocos;
	}

	public static void excluirAlmoco(Refeicao refeicao) throws Exception
	{
		RefeicaoHandler.excluirRefeicao(refeicao);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		AlmocoGateway cg = new AlmocoGateway(conn);

		if (!cg.excluirAlmoco(refeicao.getId()))
			throw new Exception("falha.ao.cadastrar.refeicao");
		
		conn.close();
	}
}
