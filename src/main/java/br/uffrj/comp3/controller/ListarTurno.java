package br.uffrj.comp3.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.uffrj.comp3.model.Constantes;
import br.uffrj.comp3.model.Turno;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.TurnoGateway;

public class ListarTurno {

	public ListarTurno() {
	}
	
	public ArrayList<Turno> listar(){
		
		ArrayList<Turno> turnos = new ArrayList<>();		
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		try {
			TurnoGateway tg = new TurnoGateway(conn);
			ResultSet rs = tg.selecionarTurnos();
			
			while(rs.next()){				
				turnos.add(Turno.valueOf(rs.getString(2)));				
			}
			
			return turnos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}
