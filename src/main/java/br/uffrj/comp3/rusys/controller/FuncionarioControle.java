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
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.service.FuncionarioHandler;
import br.uffrj.comp3.rusys.util.Constantes;


@WebServlet("/GerirFuncionario")
public class FuncionarioControle extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		iniciaCampor(request, response);
		
		String acao = (String) request.getParameter("acao");
		try
		{
			if (acao != null)
			{
				switch (acao)
				{
					case Constantes.ACAO_SALVAR:
						cadastrar(request, response);
//						response.sendRedirect("GerirFuncionario");
						break;
					case Constantes.ACAO_EDITAR:
						editar(request, response);
//						response.sendRedirect("GerirFuncionario");
						break;
					case Constantes.ACAO_DELETAR:
						//excluir(request, response);
//						response.sendRedirect("GerirFuncionario");
						break;
					default:
						request.getRequestDispatcher("/WEB-INF/listarFuncionarios.jsp").forward(request, response);
				}
			} 
			else
			{
				request.getRequestDispatcher("WEB-INF/CadFuncionario.jsp").forward(request, response);
			}
		
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, request, response);
		}
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String idFuncionario = request.getParameter("id");

		Funcionario funcionario = null;
		
		funcionario = FuncionarioHandler.recuperarFuncionario(Integer.parseInt(idFuncionario));	

		FuncionarioHandler.excluirFuncionario(funcionario);
			
		toListar(Constantes.SUCESSO, request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		
		String id = request.getParameter("id");
		String matricula = request.getParameter("matricula");
		String nome = request.getParameter("nome");
		String anoIngresso = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String departamento = request.getParameter("departamento");
		
		if (nome.equals("") || matricula.equals("") || anoIngresso.equals(""))
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/AtualizarFuncionario.jsp").forward(request, response);
		} else 
		{
			ConsumidorVO funcionario= new ConsumidorVO();
			funcionario.setId(Integer.parseInt(id));
			funcionario.setMatricula(Integer.parseInt(matricula));
			funcionario.setNome(nome);
			funcionario.setAnoDeIngresso(Integer.parseInt(anoIngresso));
			funcionario.setSexo(sexo);
			funcionario.setTitulo(titulo);
			funcionario.setCpf(cpf);
			funcionario.setCurso(Integer.parseInt(departamento));
			
			FuncionarioHandler.atualizarFuncionario(funcionario, Integer.parseInt(id));
				
			toListar(Constantes.SUCESSO, request, response);
		}		
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String ano = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String departamento = request.getParameter("departamento");
		
		if (nome.equals("") || matricula.equals("") || ano.equals(""))
		{
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("/WEB-INF/CadFuncionario.jsp").forward(request, response);
		} else {
			ConsumidorVO funcionarioVO = new ConsumidorVO();
			
			funcionarioVO.setNome(nome);
			funcionarioVO.setMatricula(Integer.parseInt(matricula));
			funcionarioVO.setAnoDeIngresso(Integer.parseInt(ano));
			funcionarioVO.setSexo(sexo);
			funcionarioVO.setTitulo(titulo);
			funcionarioVO.setCpf(cpf);
			funcionarioVO.setDepartamento(Integer.parseInt(departamento));
			
			FuncionarioHandler.cadastrarFuncionario(funcionarioVO);
				
			toListar(Constantes.SUCESSO, request, response);
		}				
	}

	private void toListar(String msg, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		iniciaCampor(request, response);
		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/WEB-INF/listarFuncionarios.jsp").forward(request, response);		
	}

	public ArrayList<Departamento> listaDepartamentos() throws SQLException, Exception
	{
		return DepartamentoHandler.recuperarDepartamentos(new DepartamentoVO());
	}
	
	private void iniciaCampor(HttpServletRequest req, HttpServletResponse resp) 
	{
		resp.setContentType("text/html");
		
		String id = req.getParameter("id");
		
		if(id!=null && id.length() > 0)
		{
			try 
			{
				Funcionario funcionario = FuncionarioHandler.recuperarFuncionario(Integer.parseInt(id));

				req.setAttribute("funcionario", funcionario);
			} 
			catch (NumberFormatException e) 
			{
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO_NUM);
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO_SQL);
				e.printStackTrace();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO);
				e.printStackTrace();
			}
		}
		
		ConsumidorVO consumidorVO = new ConsumidorVO();
		
		Collection<Funcionario> funcionarios = null;
		try
		{
			funcionarios = FuncionarioHandler.recuperarFuncionarios(consumidorVO);
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("funcionarios", funcionarios);
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		Collection<Departamento> departamentos = null;
		try
		{
			departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("departamentos", departamentos);

	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		iniciaCampor(req, resp);
		try
		{
			String acao = req.getParameter("acao");
			
			if(acao == null)
			{
				//req.setAttribute("funcionarios", new FuncionarioHandler());
				req.getRequestDispatcher("WEB-INF/listarFuncionarios.jsp").forward(req, resp);
			}
			else if (acao.equals(Constantes.NOVO))
			{
				req.getRequestDispatcher("WEB-INF/CadFuncionario.jsp").forward(req, resp);
			}
			else if (acao.equals(Constantes.ACAO_EDITAR))
			{
				req.getRequestDispatcher("WEB-INF/AtualizarFuncionario.jsp").forward(req, resp);
			}
			else if (acao.equals(Constantes.ACAO_DELETAR))
			{
				excluir(req, resp);
//				resp.sendRedirect("GerirFuncionario");
			}
		
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			toListar(Constantes.ERRO, req, resp);
		}
	}
}
