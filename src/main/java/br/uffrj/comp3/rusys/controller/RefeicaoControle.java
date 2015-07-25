package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.model.interfaces.Refeicao;
import br.uffrj.comp3.rusys.model.TipoRefeicaoEnum;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.service.RefeicaoHandler;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/GerirRefeicao")
public class RefeicaoControle extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		iniciaCampos(request, response);
		
		try
		{
			String acao = (String) request.getParameter("acao");
			
			if (acao != null)
			{
				switch (acao)
				{
					case Constantes.ACAO_SALVAR:
						cadastrar(request, response);
						break;
					case Constantes.ACAO_EDITAR:
						editar(request, response);					
						break;
					default:
						request.getRequestDispatcher("/WEB-INF/ListRefeicao.jsp").forward(request, response);
				}
			} 
			else
			{
				request.getRequestDispatcher("/WEB-INF/ListRefeicao.jsp").forward(request, response);
			}	
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}	
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String descricao = request.getParameter("descricao");
		String opVeg = request.getParameter("opVeg");
		
		if(id ==null || descricao==null || opVeg==null)
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/ListRefeicao.jsp").forward(request, response);//request.getRequestDispatcher("GerirRefeicao.jsp").forward(request, response);
		}
		
		RefeicaoVO refeicaoVO = new RefeicaoVO();
		refeicaoVO.setId(Integer.parseInt(id));
		refeicaoVO.setDescricao(descricao);
		refeicaoVO.setOpcaoVeg(opVeg);
	
		if (RefeicaoHandler.atualizarRefeicao(refeicaoVO)) 
		{
			toListar(Constantes.SUCESSO, request, response);
		}
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String descricao =(String) request.getParameter("descricao");
		String opcaoVeg =(String) request.getParameter("opVeg");
		String turno =(String) request.getParameter("turno");
		String tipo =(String) request.getParameter("tipo");
		
		if (descricao.equals("") || opcaoVeg.equals("") || tipo.equals(""))
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/CadRefeicao.jsp").forward(request, response);
		}
		else
		{
			RefeicaoVO refeicaoVO = new RefeicaoVO();
			
			refeicaoVO.setDescricao(descricao);
			refeicaoVO.setOpcaoVeg(opcaoVeg);
			refeicaoVO.setTurno(TurnoEnum.fromString(turno));
			refeicaoVO.setTipo(TipoRefeicaoEnum.fromString(tipo));
	
			int id = RefeicaoHandler.cadastrarRefeicao(refeicaoVO);
				
			if (id != 0)
			{
				toListar(Constantes.SUCESSO, request, response);
			}
		}
	}
	
	private void iniciaCampos(HttpServletRequest request, HttpServletResponse response) 
	{
		response.setContentType("text/html");

		//------------ REFEICOES ---------------
		RefeicaoVO refeicaoVO = new RefeicaoVO();
				
		Collection<Refeicao> refeicoes = null;
		try
		{
			refeicoes = RefeicaoHandler.recuperarRefeicoes(refeicaoVO);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
			e.printStackTrace();
		}
		request.setAttribute("refeicoes", refeicoes);
				
		//------------ TURNOS ---------------		
				
		Collection<TurnoEnum> turnos = (Collection<TurnoEnum>) new ArrayList<TurnoEnum>(Arrays.asList(TurnoEnum.values()));
				
		request.setAttribute("turnos", turnos);
		//------------ TURNOS ---------------
			
		
		//------------ TIPOS ---------------		
		
		Collection<TipoRefeicaoEnum> tipos = (Collection<TipoRefeicaoEnum>) new ArrayList<TipoRefeicaoEnum>(Arrays.asList(TipoRefeicaoEnum.values()));
						
		request.setAttribute("tipos", tipos);
		//------------ TIPOS ---------------

		String identificador = request.getParameter("id");
		
		//------------ REFEICAO ---------------
						
		Refeicao refeicao = null;
		
		if(identificador != null && identificador.length() > 0)
		{
			try
			{
				refeicao = RefeicaoHandler.recuperarRefeicao(Integer.parseInt(identificador));
			} 
			catch (NumberFormatException e1)
			{
				request.setAttribute("mensagem", Constantes.ERRO_NUM);
				e1.printStackTrace();
			}
			catch (Exception e1)
			{
				request.setAttribute("mensagem", Constantes.ERRO);
				e1.printStackTrace();
			}
		}
		
		request.setAttribute("refeicao", refeicao);
	}
	
	private void toListar(String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		iniciaCampos(request, response);
		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/WEB-INF/ListRefeicao.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		iniciaCampos(request, response);

		//------------ ACAO ---------------
		try
		{
			String acao = (String) request.getParameter("acao");
	
			if (acao != null){
				switch (acao){	
					case Constantes.NOVO:
						request.getRequestDispatcher("/WEB-INF/CadRefeicao.jsp").forward(request, response);
						break;
					case Constantes.ACAO_EDITAR:
						request.getRequestDispatcher("/WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
						break;
					default:
						request.getRequestDispatcher("/WEB-INF/ListRefeicao.jsp").forward(request, response);
				}
			} 
			else
			{
				request.getRequestDispatcher("/WEB-INF/ListRefeicao.jsp").forward(request, response);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}	
	}
}