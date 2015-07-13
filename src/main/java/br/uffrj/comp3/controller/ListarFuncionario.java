package br.uffrj.comp3.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.model.CPF;
import br.uffrj.comp3.model.Constantes;
import br.uffrj.comp3.model.Departamento;
import br.uffrj.comp3.model.Funcionario;
import br.uffrj.comp3.model.Sexo;
import br.uffrj.comp3.model.Titulo;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.ConsumidorGateway;
import br.ufrrj.comp3.gateway.DepartamentoGateway;
import br.ufrrj.comp3.gateway.FuncionarioGateway;

public class ListarFuncionario {

	public ListarFuncionario() {
	}

	public ArrayList<Funcionario> listar(){
		
		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);		
		FuncionarioGateway fg = new FuncionarioGateway(conn);
		ResultSet rs = fg.selecionarFuncionarios();
		
		try {
			while(rs.next()){
				int matricula = rs.getInt(2);
				int iddepartamento = rs.getInt(1);
				
				//seleciona departamento				
				DepartamentoGateway dg = new DepartamentoGateway(conn);
				ResultSet rsd = dg.selecionarDepartamentoPorId(iddepartamento);			
				rsd.next();
				
				Departamento departamento = new Departamento();
				departamento.setIdentificador(rsd.getInt(1));
				departamento.setNome(rsd.getString(2));
				departamento.setSigla(rsd.getString(3));
								
				//seleciona consumidor				
				ConsumidorGateway cg = new ConsumidorGateway(conn);
				ResultSet rsc = cg.selecionarConsumidorPorMatricula(matricula);
				rsc.next();											
				
				if(rs.getInt(7) == 1){

					Funcionario funcionario = new Funcionario(rsc.getString(2), matricula, rsc.getString(3), Sexo.valueOf(rsc.getString(4)) , Titulo.valueOf(rsc.getString(5)), new CPF(rsc.getString(6)), departamento);
				
					funcionarios.add(funcionario);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return funcionarios;		
	}
}
