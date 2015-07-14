package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.util.Constantes;


@WebServlet("/CadastrarFuncionario")
public class CadFuncionario extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		/*DepartamentoVO departamentoVO = new DepartamentoVO();
				
		Collection<Departamento> departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		
		request.setAttribute("departamentos", departamentos);*/

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					cadastrar(request, response);
					break;
				default:
					request.getRequestDispatcher("CadastrarFuncionario").forward(request, response);
			}
		} 
		else
		{
			request.getRequestDispatcher("WEB-INF/CadFuncionario.jsp").forward(request, response);
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/*String nome = request.getParameter("nome");
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
		}*/
	}

	public ArrayList<Departamento> listaDepartamentos()
	{

		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		/*Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);

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
		}*/

		return departamentos;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
