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
import br.uffrj.comp3.rusys.persintece.DepartamentoGateway;
import br.uffrj.comp3.rusys.persintece.FuncionarioGateway;
import br.uffrj.comp3.rusys.util.Constantes;

public class FuncionarioHandler
{

	public static void cadastrarFuncionario(ConsumidorVO consumidorVO) throws Exception
	{
		int id = ConsumidorHandler.cadastrarConsumidor(consumidorVO);

		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);	
		FuncionarioGateway ag = new FuncionarioGateway(conn);

		ArrayList<Object> valores2 = new ArrayList<Object>(
				Arrays.asList(id, consumidorVO.getDepartamento(), consumidorVO.getMatricula()));

		if (!ag.inserir(valores2))
			throw new Exception("falha.ao.cadastrar.aluno");

		conn.close();
	}
	
	public static Funcionario recuperarFuncionario(int idFuncionario) throws SQLException, Exception
	{
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		FuncionarioGateway funcionarioGW = new FuncionarioGateway(conn);

		/*ResultSet rsFuncionarios = funcionarioGW.selecionarFuncionarioPorMatricula(idFuncionario);

		Funcionario funcionario = null;
		
		while (rsFuncionarios.next())
		{
			int id = rsFuncionarios.getInt(1);
			int matricula = rsFuncionarios.getInt(2);
			int iddepartamento = rsFuncionarios.getInt(1);

			// seleciona departamento
			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsd = dg.selecionarDepartamentoPorId(iddepartamento);
			rsd.next();

			Departamento departamento = new Departamento(rsFuncionarios.getInt(4), rsd.getString(2), rsd.getString(3));

			// seleciona consumidor
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsc = cg.selecionarConsumidorPorMatricula(matricula);
			rsc.next();

			if (rsc.getInt(7) == 1)
			{
				funcionario = new Funcionario(id, rsFuncionarios.getString(2), matricula, rsFuncionarios.getString(2), departamento);

				funcionario.setCpf(rsFuncionarios.getString(2));
				funcionario.setSexo(SexoEnum.fromString(rsFuncionarios.getString(2)));
				funcionario.setTitulo(TituloEnum.fromString(rsFuncionarios.getString(2)));
			}
		}*/
		
		//return funcionario;
		return null;
	}
	public static Collection<Funcionario> recuperarFuncionarios(ConsumidorVO consumidorVO) throws Exception
	{
		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		FuncionarioGateway fg = new FuncionarioGateway(conn);
		ResultSet rsFuncionarios = fg.selecionarFuncionarios();

		while (rsFuncionarios.next())
		{
			int id = rsFuncionarios.getInt(1);
			int matricula = rsFuncionarios.getInt(2);
			int iddepartamento = rsFuncionarios.getInt(1);

			// seleciona departamento
			DepartamentoGateway dg = new DepartamentoGateway(conn);
			ResultSet rsd = dg.selecionarDepartamentoPorId(iddepartamento);
			rsd.next();

			Departamento departamento = new Departamento(rsFuncionarios.getInt(4), rsd.getString(2), rsd.getString(3));

			// seleciona consumidor
			ConsumidorGateway cg = new ConsumidorGateway(conn);
			ResultSet rsc = cg.selecionarConsumidorPorId(matricula);
			rsc.next();

			if (rsc.getInt(7) == 1)
			{
				Funcionario funcionario = new Funcionario(id, rsFuncionarios.getString(2), matricula, rsFuncionarios.getString(2), departamento);

				funcionario.setCpf(rsFuncionarios.getString(2));
				funcionario.setSexo(SexoEnum.fromString(rsFuncionarios.getString(2)));
				funcionario.setTitulo(TituloEnum.fromString(rsFuncionarios.getString(2)));

				funcionarios.add(funcionario);
			}

		}

		conn.close();

		return funcionarios;
	}

	public static void excluirFuncionario(Consumidor consumidor) throws Exception
	{
		ConsumidorHandler.excluirConsumidor(consumidor);
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		ConsumidorGateway cg = new ConsumidorGateway(conn);

		if (!cg.desativarConsumidor(consumidor.getMatricula()))
		throw new Exception("falha.ao.excluir.funcionario");

		conn.close();
	}
}
