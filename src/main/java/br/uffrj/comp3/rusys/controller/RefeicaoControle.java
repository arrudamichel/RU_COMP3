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
import br.uffrj.comp3.rusys.model.Refeicao;
import br.uffrj.comp3.rusys.model.vo.RefeicaoVO;
import br.uffrj.comp3.rusys.service.RefeicaoHandler;
import br.uffrj.comp3.rusys.util.Constantes;

@WebServlet("/GerirRefeicao")
public class RefeicaoControle extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		RefeicaoVO refeicaoVO = new RefeicaoVO();
		
		Collection<Refeicao> refeicoes = null;
		try
		{
			refeicoes = RefeicaoHandler.recuperarRefeicoes(refeicaoVO);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("refeicoes", refeicoes);
		
		
		Collection<TurnoEnum> turnos = (Collection<TurnoEnum>) new ArrayList<TurnoEnum>(Arrays.asList(TurnoEnum.values()));
		
		request.setAttribute("turnos", turnos);
		
		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					cadastrar(request, response);
					break;
				case Constantes.EXCLUIR:
					excluir(request, response);
					break;
				case Constantes.EDITAR:
					editar(request, response);
					break;
				default:
					request.getRequestDispatcher("GerirRefeicao").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("/WEB-INF/CadRefeicao.jsp").forward(request, response);
		}	
	}

	private void editar(HttpServletRequest request, HttpServletResponse response)
	{
		// TODO Auto-generated method stub
		
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) {
		
		String identificador = request.getParameter("id");

		Refeicao refeicao = null;
		try
		{
			refeicao = RefeicaoHandler.recuperarRefeicao(Integer.parseInt(identificador));
		} catch (NumberFormatException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			RefeicaoHandler.excluirRefeicao(refeicao);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String descricao = request.getParameter("descricao");
		String opcaoVeg = request.getParameter("opVeg");
		String turno = request.getParameter("turno");

		
		RefeicaoVO refeicaoVO = new RefeicaoVO();
		refeicaoVO.setDescricao(descricao);
		refeicaoVO.setOpcaoVeg(opcaoVeg);
		refeicaoVO.setTurno(TurnoEnum.fromString(turno));
		
		try
		{
			RefeicaoHandler.cadastrarRefeicao(refeicaoVO);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}