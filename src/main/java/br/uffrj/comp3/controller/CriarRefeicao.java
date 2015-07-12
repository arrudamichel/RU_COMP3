package br.uffrj.comp3.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import br.uffrj.comp3.model.Constantes;
import br.uffrj.comp3.model.Refeicao;
import br.uffrj.comp3.model.Turno;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.RefeicaoGateway;
import br.ufrrj.comp3.gateway.TurnoGateway;

@WebServlet("/Refeicao")
public class CriarRefeicao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String descricao = request.getParameter("descricao");
		String opVeg = request.getParameter("opVeg");
		String turno = request.getParameter("turno");

		Refeicao refeicao = new Refeicao();
		refeicao.setDescricao(descricao);
		refeicao.setOpcaoVeg(opVeg);
		refeicao.setTurno(Turno.valueOf(turno));
		request.setAttribute("refeicao", refeicao);

		// System.out.println(descricao + " " + opVeg + " " + turno);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		TurnoGateway turnoGateway = new TurnoGateway(conn);

		ResultSet rs = turnoGateway.selecionarTurnoPorNome(refeicao.getTurno().toString());

		int turnoId = -1;
		try {
			rs.next();
			turnoId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RefeicaoGateway refeicaoGateway = new RefeicaoGateway(conn);

		ArrayList<Object> valores = new ArrayList<>(Arrays.asList(refeicao.getDescricao(), refeicao.getOpcaoVeg(), turnoId));
				// \"descricao\", \"opcaoVegetariana\", \"Turno_idTurno\"
		if (refeicaoGateway.inserir(valores))
			System.out.println(valores + " inseridos");
		else
			System.out.println("Erro ao inserir refeicao");

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}