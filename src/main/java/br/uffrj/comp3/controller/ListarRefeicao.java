package br.uffrj.comp3.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.model.Constantes;
import br.uffrj.comp3.model.Refeicao;
import br.uffrj.comp3.model.Turno;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.RefeicaoGateway;
import br.ufrrj.comp3.gateway.TurnoGateway;

public class ListarRefeicao {

	public ListarRefeicao() {				
	}
	
	public ArrayList<Refeicao> listar(){
		
		ArrayList<Refeicao> refeicoes = new ArrayList<>();
		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		RefeicaoGateway rg = new RefeicaoGateway(conn);
		
		ResultSet rs = rg.selecionarRefeicoes();
		
		try {
			while(rs.next()){
				Refeicao refeicao = new Refeicao();
				refeicao.setIdentificador(rs.getInt(1));
				refeicao.setDescricao(rs.getString(2));
				refeicao.setOpcaoVeg(rs.getString(3));			
				
				TurnoGateway tg = new TurnoGateway(conn);
				
				ResultSet rst = tg.selecionarTurnoPorId(rs.getInt(4));
				rst.next();
							
				//Verifica se e ativo ou nao
				if(rs.getInt(5) == 1){
					refeicao.setTurno(Turno.valueOf(rst.getString(2)));
					
					refeicoes.add(refeicao);
				}				
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return refeicoes;		
	}
}
