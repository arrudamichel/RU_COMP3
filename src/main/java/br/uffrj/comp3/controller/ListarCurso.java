package br.uffrj.comp3.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.model.Constantes;
import br.uffrj.comp3.model.Curso;
import br.uffrj.comp3.model.Departamento;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.CursoGateway;
import br.ufrrj.comp3.gateway.DepartamentoGateway;

public class ListarCurso {

	public ListarCurso() {
	}

	public ArrayList<Curso> listar(){
		ArrayList<Curso> cursos = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		CursoGateway cg = new CursoGateway(conn);
		
		ResultSet rs = cg.selecionarCursos();
		
		try {
			while(rs.next()){
				Curso curso = new Curso();
				curso.setIdentificador(rs.getInt(1));
				curso.setNome(rs.getString(2));
				curso.setSigla(rs.getString(3));
				
				DepartamentoGateway dg = new DepartamentoGateway(conn);
				ResultSet rsd = dg.selecionarDepartamentoPorId(rs.getInt(4));			
				rsd.next();
				
				Departamento departamento = new Departamento();
				departamento.setIdentificador(rsd.getInt(1));
				departamento.setNome(rsd.getString(2));
				departamento.setSigla(rsd.getString(3));

				curso.setDepartamento(departamento);
				
				cursos.add(curso);
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cursos;
	}
}
