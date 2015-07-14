package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.RefeicaoGateway;
import br.uffrj.comp3.rusys.persintece.TurnoGateway;
import br.uffrj.comp3.rusys.util.Constantes;
import br.uffrj.comp3.rusys.model.Refeicao;

@WebServlet("/CadastrarRefeicao")
public class CadRefeicao extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();

		String descricao = request.getParameter("descricao");
		String opVeg = request.getParameter("opVeg");
		String turno = request.getParameter("turno");
		String id = request.getParameter("id");

		Refeicao refeicao = new Refeicao();
		refeicao.setDescricao(descricao);
		refeicao.setOpcaoVeg(opVeg);
		refeicao.setTurno(TurnoEnum.valueOf(turno));
		request.setAttribute("refeicao", refeicao);

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TurnoGateway turnoGateway = new TurnoGateway(conn);

		ResultSet rs = turnoGateway.selecionarTurnoPorNome(refeicao.getTurno().toString());

		int turnoId = -1;
		try
		{
			rs.next();
			turnoId = rs.getInt(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		if (id != null)
		{
			ArrayList<Object> valores = new ArrayList<Object>(
					Arrays.asList(refeicao.getDescricao(), refeicao.getOpcaoVeg()));
			if (refeicaoGateway.alterarRefeicao(valores, Integer.parseInt(id)))
				request.setAttribute("mensagem", Constantes.SUCESSO);
			else
				request.setAttribute("mensagem", Constantes.ERRO);
		} 
		else
		{
			ArrayList<Object> valores = new ArrayList<Object>(
					Arrays.asList(refeicao.getDescricao(), refeicao.getOpcaoVeg(), turnoId, 1));
			if (refeicaoGateway.inserir(valores))
				request.setAttribute("mensagem", Constantes.SUCESSO);
			else
				request.setAttribute("mensagem", Constantes.ERRO);
		}

		try
		{
			conn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/CadRefeicao.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String id = req.getParameter("id");
		Refeicao refeicao = new Refeicao();

		if (id != null)
		{
			Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
			ResultSet rs = new RefeicaoGateway(conn).selecionarRefeicaoPorId(Integer.parseInt(id));
			try
			{
				if (rs.next())
				{
					refeicao.setIdentificador(rs.getInt(1));
					refeicao.setDescricao(rs.getString(2));
					refeicao.setOpcaoVeg(rs.getString(3));

					TurnoGateway tg = new TurnoGateway(conn);

					ResultSet rst = tg.selecionarTurnoPorId(rs.getInt(4));

					// Verifica se e ativo ou nao
					if (rst.next())
						refeicao.setTurno(TurnoEnum.valueOf(rst.getString(2)));
				}
				conn.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		req.setAttribute("refeicao", refeicao);
		RequestDispatcher rd = req.getRequestDispatcher("CadRefeicao.jsp");
		rd.forward(req, resp);
	}
}