package br.uffrj.comp3.rusys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.model.vo.FuncionarioVO;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.service.FuncionarioHandler;
import br.uffrj.comp3.rusys.util.Constantes;


@WebServlet("/CadastrarFuncionario")
public class CadFuncionario extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		FuncionarioVO funcionarioVO = new FuncionarioVO();
		
		Collection<Funcionario> funcionarios = null;
		try
		{
			funcionarios = FuncionarioHandler.recuperarFuncionarios(funcionarioVO);
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		request.setAttribute("funcionarios", funcionarios);
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		Collection<Departamento> departamentos = null;
		try
		{
			departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("departamentos", departamentos);

		if (acao != null)
		{
			switch (acao)
			{
				case Constantes.SALVAR:
					cadastrar(request, response);
					break;
				case Constantes.EDITAR:
					editar(request, response);
					break;
				case Constantes.EXCLUIR:
					excluir(request, response);
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
	
	private void excluir(HttpServletRequest request,
			HttpServletResponse response) {
		
		String matricula = request.getParameter("matricula");

		FuncionarioVO funcionarioVO = new FuncionarioVO();
		funcionarioVO.setMatricula(Integer.parseInt(matricula));
		
		try
		{
			FuncionarioHandler.excluirFuncionario(funcionarioVO);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
		
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String ano = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String departamento = request.getParameter("departamento");
		
		FuncionarioVO funcionarioVO = new FuncionarioVO();
		
		funcionarioVO.setNome(nome);
		funcionarioVO.setMatricula(Integer.parseInt(matricula));
		funcionarioVO.setAnoDeIngresso(ano);
		funcionarioVO.setSexo(sexo);
		funcionarioVO.setTitulo(titulo);
		funcionarioVO.setCpf(cpf);
		funcionarioVO.setDepartamento(Integer.parseInt(departamento));
		
		try
		{
			FuncionarioHandler.cadastrarDepartamento(funcionarioVO);
		} 
		catch (Exception e)
		{
			request.setAttribute("mensagem", Constantes.ERRO);
		}
	}

	public ArrayList<Departamento> listaDepartamentos()
	{
		try
		{
			return DepartamentoHandler.recuperarDepartamentos(new DepartamentoVO());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
