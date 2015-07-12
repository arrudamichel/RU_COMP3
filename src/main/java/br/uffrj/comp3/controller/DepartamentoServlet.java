package br.uffrj.comp3.controller;

import java.io.IOException;
import java.sql.Connection;
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
import br.uffrj.comp3.model.Departamento;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.DepartamentoGateway;

@WebServlet("/Departamento")
public class DepartamentoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");

		Departamento departamento = new Departamento();
		departamento.setNome(nome);
		departamento.setSigla(sigla);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		DepartamentoGateway departamentoGateway = new DepartamentoGateway(conn);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList(departamento.getNome(), departamento.getSigla()));

		if (departamentoGateway.inserir(valores))
			request.setAttribute("mensagem", Constantes.SUCESSO);
		else
			request.setAttribute("mensagem", Constantes.ERRO);
		
		request.setAttribute("departamento", departamento);
		RequestDispatcher rd = request.getRequestDispatcher("CadDepartamento.jsp");
		rd.forward(request, response);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
