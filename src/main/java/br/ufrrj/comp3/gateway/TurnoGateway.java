package br.ufrrj.comp3.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TurnoGateway {
	private Connection conn;

	public TurnoGateway(Connection conn) {
		this.conn = conn;
	}

	public ResultSet selecionarTurnos() {
		ResultSet rs = null;
		Statement stat;

		try {
			String sql = "SELECT * FROM \"turno\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	public ResultSet selecionarTurnoPorId(int id) {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM \"turno\" WHERE \"idTurno\" = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}
	
	public ResultSet selecionarTurnoPorNome(String turno) {
		ResultSet rs = null;
		try {
			String sql = "SELECT \"idTurno\" FROM \"turno\" WHERE \"descricao\" = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, turno);
			rs = stmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}



}
