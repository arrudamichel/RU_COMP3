package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.util.ArrayUtil;

import br.uffrj.comp3.rusys.model.Almoco;
import br.uffrj.comp3.rusys.model.Dejejum;
import br.uffrj.comp3.rusys.model.Jantar;
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.persintece.AlmocoGateway;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.JantarGateway;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class RefeicaoHandler
{
	public static int cadastrarRefeicao(RefeicaoVO refeicaoVO) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		ArrayList<Object> valores = new ArrayList<Object>(
				Arrays.asList(refeicaoVO.getDescricao(), refeicaoVO.getOpcaoVeg(), refeicaoVO.getTurno().toString()));
		
		ResultSet rs = refeicaoGateway.inserir(valores);
		
		if (rs==null)
			throw new Exception("falha.ao.cadastrar.refeicao");

		conn.close();
		
		return rs.getFetchSize();
	}
	
	public static Refeicao recuperarRefeicao(int ifref) throws Exception
	{	
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		AlmocoGateway ag = new AlmocoGateway(conn);
		ResultSet rsAlmoco = ag.selecionarAlmocoPorId(ifref);
		
		JantarGateway jg = new JantarGateway(conn);
		ResultSet rsJantar = jg.selecionarJantarPorId(ifref);
		
		JantarGateway dg = new JantarGateway(conn);
		ResultSet rsDejejum = dg.selecionarJantarPorId(ifref);

		Refeicao refeicao = null;
		
		if (rsAlmoco.next())
		{
			int id = rsAlmoco.getInt(1);
			String opcaoVeg = rsAlmoco.getString(2);
			String descricao = rsAlmoco.getString(3);
			
			refeicao = new Almoco(id, descricao);
			refeicao.setOpcaoVeg(opcaoVeg);
		} 
		else if (rsJantar.next())
		{
			int id = rsJantar.getInt(1);
			String opcaoVeg = rsJantar.getString(2);
			String descricao = rsJantar.getString(3);
			
			refeicao = new Jantar(id, descricao);
			refeicao.setOpcaoVeg(opcaoVeg);
		}
		else if (rsDejejum.next())
		{
			int id = rsDejejum.getInt(1);
			String opcaoVeg = rsDejejum.getString(2);
			String descricao = rsDejejum.getString(3);
			
			refeicao = new Dejejum(id, descricao);
			refeicao.setOpcaoVeg(opcaoVeg);
		}

		conn.close();
		
		return refeicao;
	}

	public static void atualizarRefeicao(RefeicaoVO refeicaoVO)
	{

	}

	public static void excluirRefeicao(Refeicao refeicao) throws Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);

		if (!rg.desativarRefeicao(refeicao.getIdentificador()))
			throw new Exception("falha.ao.cadastrar.refeicao");
		
		conn.close();
	}

	public static ArrayList<Refeicao> recuperarRefeicoes(RefeicaoVO refeicaoVO) throws Exception
	{
		ArrayList<Refeicao> refeicoes = new ArrayList<>();

//		TODO
		refeicoes.addAll(JantarHandler.recuperarJantars(refeicaoVO));
		refeicoes.addAll(AlmocoHandler.recuperarAlmocos(refeicaoVO));
		refeicoes.addAll(DejejumHandler.recuperarDejejums(refeicaoVO));

		return refeicoes;
	}
}
