package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.service.CursoHandler;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.util.Constantes;



@WebServlet("/CadastrarDepartamento")
public class CadDepartamento extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
				
		Collection<Departamento> departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		
		request.setAttribute("departamentos", departamentos);

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					cadastrar(request, response);
					break;
				default:
					request.getRequestDispatcher("ListarCursos").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("WEB-INF/CadDepartamento.jsp").forward(request, response);
		}
		
		
		/*String nome = request.getParameter("nome");
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
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/CadDepartamento.jsp");
		rd.forward(request, response);

		try
		{
			conn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}*/
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");

		DepartamentoVO dptoVO = new DepartamentoVO();
		dptoVO.setNome(nome);
		dptoVO.setSigla(sigla);	
		
		try
		{
			DepartamentoHandler.cadastrarDepartamento(dptoVO);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
	}

	public ArrayList<Departamento> listaDepartamentos()
	{

		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

		DepartamentoGateway deptGateway = new DepartamentoGateway(conn);
		
		// iddepartamento nome sigla
		ResultSet rs = deptGateway.selecionarDepartamentos();
		try
		{
			while (rs.next())
			{
				Departamento departamento = new Departamento();
				departamento.setIdentificador(rs.getInt("iddepartamento"));
				departamento.setNome(rs.getString("nome"));
				departamento.setSigla(rs.getString("sigla"));

				departamentos.add(departamento);
			}
			conn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return departamentos;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
