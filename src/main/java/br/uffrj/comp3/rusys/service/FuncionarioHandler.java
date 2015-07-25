package br.uffrj.comp3.rusys.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import br.uffrj.comp3.rusys.model.Consumidor;
import br.uffrj.comp3.rusys.model.Departamento;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.SexoEnum;
import br.uffrj.comp3.rusys.model.TituloEnum;
import br.uffrj.comp3.rusys.model.vo.ConsumidorVO;
import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.ConsumidorGateway;
import br.uffrj.comp3.rusys.persintece.FuncionarioGateway;
import br.uffrj.comp3.rusys.service.exceptions.AlterarNoBancoExeception;
import br.uffrj.comp3.rusys.service.exceptions.ExcluirDoBancoException;
import br.uffrj.comp3.rusys.service.exceptions.InsercaoNoBancoException;
import br.uffrj.comp3.rusys.util.Constantes;

public class FuncionarioHandler
{

	public static void cadastrarFuncionario(ConsumidorVO consumidorVO) throws Exception
	{
		Departamento departamento = DepartamentoHandler.recuperarDepartamento(consumidorVO.getDepartamento());
		Funcionario funcionario = new Funcionario(consumidorVO.getId(), consumidorVO.getNome(), consumidorVO.getMatricula(), consumidorVO.getAnoDeIngresso(), departamento);
		
		int id = ConsumidorHandler.cadastrarConsumidor(consumidorVO);

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		FuncionarioGateway ag = new FuncionarioGateway(conn);

		ArrayList<Object> valores2 = new ArrayList<Object>(
				Arrays.asList( id,funcionario.getDepartamento().getId()));

		if (!ag.inserir(valores2))
			throw new InsercaoNoBancoException();

		conn.close();
	}
	
	public static Funcionario recuperarFuncionario(int id) throws SQLException, Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		FuncionarioGateway funcionarioGW = new FuncionarioGateway(conn);

		ResultSet rsFuncionarios = funcionarioGW.selecionarFuncionarioPorId(id);
		
		Funcionario funcionario = null;
		
		if(rsFuncionarios.next())
		{	
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsConsumidores = cg.selecionarConsumidorPorId(id);
			
			int iddepartamento = rsFuncionarios.getInt("departamento_fk");

			Departamento departamento = DepartamentoHandler.recuperarDepartamento(iddepartamento);
			
			while (rsConsumidores.next())
			{
				if (rsConsumidores.getInt("situacao") == 1)
				{
					funcionario = new Funcionario(id, rsConsumidores.getString("nome"), rsConsumidores.getInt("matricula"), rsConsumidores.getInt("ano_ingresso"), departamento);
	
					funcionario.setCpf(rsConsumidores.getString("cpf"));
					funcionario.setSexo(SexoEnum.fromString(rsConsumidores.getString("sexo")));
					funcionario.setTitulo(TituloEnum.fromString(rsConsumidores.getString("titulo")));
				}
			}
		}
		
		return funcionario;
	}
	
	public static Collection<Funcionario> recuperarFuncionarios(ConsumidorVO consumidorVO) throws Exception
	{
		ArrayList<ConsumidorVO> consumidorVOs = (ArrayList<ConsumidorVO>) ConsumidorHandler.recuperarConsumidorVOs(consumidorVO);
		
		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		FuncionarioGateway funcionarioGW = new FuncionarioGateway(conn);
		
		Funcionario funcionario = null;
		
		for (ConsumidorVO consVO: consumidorVOs)
		{	
			ResultSet rsFuncionarios = funcionarioGW.selecionarFuncionarioPorId(consVO.getId());
			
			if(rsFuncionarios.next())
			{
				ConsumidorGateway cg = new ConsumidorGateway(conn);
				ResultSet rsConsumidores = cg.selecionarConsumidorPorId(rsFuncionarios.getInt("id"));

				int iddepartamento = rsFuncionarios.getInt("departamento_fk");
		
				Departamento departamento = DepartamentoHandler.recuperarDepartamento(iddepartamento);
					
				while (rsConsumidores.next())
				{
					if (rsConsumidores.getInt("situacao") == 1)
					{
						funcionario = new Funcionario(rsFuncionarios.getInt("id"), rsConsumidores.getString("nome"), rsConsumidores.getInt("matricula"), rsConsumidores.getInt("ano_ingresso"), departamento);
			
						funcionario.setCpf(rsConsumidores.getString("cpf"));
						funcionario.setSexo(SexoEnum.fromString(rsConsumidores.getString("sexo")));
						funcionario.setTitulo(TituloEnum.fromString(rsConsumidores.getString("titulo")));
							
						funcionarios.add(funcionario);
					}
				}
				
			}
		}

		return funcionarios;
	}

	public static void excluirFuncionario(Consumidor consumidor) throws Exception
	{
		ConsumidorHandler.excluirConsumidor(consumidor);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway cg = new ConsumidorGateway(conn);

		if (!cg.desativarConsumidor(consumidor.getId()))
			throw new ExcluirDoBancoException();

		conn.close();
	}

	public static void atualizarFuncionario(ConsumidorVO consumidorVO, int idDepartamento) throws Exception
	{
		Funcionario funcionario = recuperarFuncionario(consumidorVO.getId());
		
		if (consumidorVO.getDepartamento()!=null) 
		{	
			Departamento departamento = DepartamentoHandler.recuperarDepartamento(idDepartamento);
			funcionario.setDepartamento(departamento);
		}
		
		ConsumidorHandler.atualizarConsumidor(consumidorVO);

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		FuncionarioGateway funcionarioGW = new FuncionarioGateway(conn);

		ArrayList<Object> valores2 = new ArrayList<Object>(Arrays.asList(funcionario.getDepartamento().getId()));
		
		if (!funcionarioGW.alterarFuncionario(valores2, idDepartamento))
			throw new AlterarNoBancoExeception();

		conn.close();
	}

}
