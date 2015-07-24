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

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Curso;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.AlunoHandler;
import br.uffrj.comp3.rusys.service.CursoHandler;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;
import br.uffrj.comp3.rusys.service.FuncionarioHandler;
import br.uffrj.comp3.rusys.util.Constantes;


@WebServlet("/GerirFuncionario")
public class FuncionarioControle extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		
		String acao = (String) request.getParameter("acao");
		
		ConsumidorVO funcionarioVO = new ConsumidorVO();
		
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
				case Constantes.ACAO_SALVAR:
					cadastrar(request, response);
					//response.sendRedirect("GerirFuncionario");
					break;
				case Constantes.ACAO_EDITAR:
					editar(request, response);
					response.sendRedirect("GerirFuncionario");
					break;
				case Constantes.ACAO_DELETAR:
					//excluir(request, response);
					response.sendRedirect("GerirFuncionario");
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
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String idFuncionario = request.getParameter("id");

		Funcionario funcionario = null;
		try{
			funcionario = FuncionarioHandler.recuperarFuncionario(Integer.parseInt(idFuncionario));
		} catch (NumberFormatException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1){
			e1.printStackTrace();
		}
		
		try{
			FuncionarioHandler.excluirFuncionario(funcionario);
			request.setAttribute("mensagem", Constantes.SUCESSO);
			request.getRequestDispatcher("WEB-INF/listarFuncionarios.jsp").forward(request, response);
		} 
		catch (Exception e){
			request.setAttribute("mensagem", Constantes.ERRO);
			request.getRequestDispatcher("WEB-INF/listaFuncionarios.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String matricula = request.getParameter("matricula");
		String nome = request.getParameter("nome");
		String anoIngresso = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String departamento = request.getParameter("departamento");
		
		if(nome.equals("")|| matricula.equals("") || anoIngresso.equals("") || departamento.equals("")){
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("WEB-INF/listarFuncionario.jsp").forward(request, response);
		}else{

			ConsumidorVO funcionario= new ConsumidorVO();
			funcionario.setId(Integer.parseInt(id));
			funcionario.setMatricula(Integer.parseInt(matricula));
			funcionario.setNome(nome);
			funcionario.setAnoDeIngresso(anoIngresso);
			funcionario.setSexo(sexo);
			funcionario.setTitulo(titulo);
			System.out.println("CPF" + cpf);
			funcionario.setCpf(cpf);
			funcionario.setCurso(Integer.parseInt(departamento));
		
			try{	
				FuncionarioHandler.atualizarFuncionario(funcionario, Integer.parseInt(id));
				request.setAttribute("mensagem", Constantes.SUCESSO);
				request.getRequestDispatcher("WEB-INF/CadFuncionario.jsp").forward(request, response);
			} catch (Exception e){
				request.setAttribute("mensagem", Constantes.ERRO);
				request.getRequestDispatcher("WEB-INF/listarFuncionarios.jsp").forward(request, response);
			}
		}

	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String ano = request.getParameter("anoIngresso");
		String sexo = request.getParameter("sexo");
		String titulo = request.getParameter("titulo");
		String cpf = request.getParameter("cpf");
		String departamento = request.getParameter("departamento");
		if(nome.equals("")|| matricula.equals("") || ano.equals("") || departamento.equals("")){
			request.setAttribute("mensagem", Constantes.ERRO_VAZIO);
			request.getRequestDispatcher("WEB-INF/CadFuncionario.jsp").forward(request, response);
		}else{
			ConsumidorVO funcionarioVO = new ConsumidorVO();
			
			funcionarioVO.setNome(nome);
			funcionarioVO.setMatricula(Integer.parseInt(matricula));
			funcionarioVO.setAnoDeIngresso(ano);
			funcionarioVO.setSexo(sexo);
			funcionarioVO.setTitulo(titulo);
			funcionarioVO.setCpf(cpf);
			funcionarioVO.setDepartamento(Integer.parseInt(departamento));
			
			try{
				FuncionarioHandler.cadastrarFuncionario(funcionarioVO);
				request.setAttribute("mensagem", Constantes.SUCESSO);
				request.getRequestDispatcher("WEB-INF/listarFuncionarios.jsp").forward(request, response);
			} 
			catch (Exception e){
				request.setAttribute("mensagem", Constantes.ERRO);
			}
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String acao = req.getParameter("acao");
		String id = req.getParameter("id");
		if(id!=null){
			try {
				Funcionario funcionario = FuncionarioHandler.recuperarFuncionario(Integer.parseInt(id));

				req.setAttribute("funcionario", funcionario);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO_NUM);
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO_SQL);
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				req.setAttribute("mensagem", Constantes.ERRO);
				e.printStackTrace();
			}
		}
		ConsumidorVO consumidorVO = new ConsumidorVO();
		
		Collection<Funcionario> funcionarios = null;
		try{
			funcionarios = FuncionarioHandler.recuperarFuncionarios(consumidorVO);
		} catch (SQLException e1){
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("funcionarios", funcionarios);
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		Collection<Departamento> departamentos = null;
		try{
			departamentos = DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		req.setAttribute("departamentos", departamentos);

		if(acao == null){
			//req.setAttribute("funcionarios", new FuncionarioHandler());
			req.getRequestDispatcher("WEB-INF/listarFuncionarios.jsp").forward(req, resp);
		}else if (acao.equals(Constantes.NOVO)){
			req.getRequestDispatcher("WEB-INF/CadFuncionario.jsp").forward(req, resp);
		}else if (acao.equals(Constantes.ACAO_EDITAR)){
			req.getRequestDispatcher("WEB-INF/AtualizarFuncionario.jsp").forward(req, resp);
		}else if (acao.equals(Constantes.ACAO_DELETAR)){
			try {
				excluir(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
