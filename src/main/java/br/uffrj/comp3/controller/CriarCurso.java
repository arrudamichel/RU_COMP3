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
import br.uffrj.comp3.model.Curso;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.CursoGateway;
import br.ufrrj.comp3.gateway.DepartamentoGateway;
import br.ufrrj.comp3.gateway.RefeicaoGateway;
import br.ufrrj.comp3.gateway.TurnoGateway;
@WebServlet("/Curso")
public class CriarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		String dept = request.getParameter("departamento");

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		CursoGateway cursoGateway = new CursoGateway(conn);
		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);
		
		ResultSet rs = deptGateway.selecionarDepartamentoPorNome(dept);
		
		int deptId = -1;
		try {
			rs.next();
			deptId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(name, sigla, deptId));
		
		if (cursoGateway.inserir(valores))
			request.setAttribute("mensagem", Constantes.SUCESSO);
		else
			request.setAttribute("mensagem", Constantes.ERRO);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("CadCurso.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}