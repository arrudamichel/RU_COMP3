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

	public boolean inserir(ArrayList<Object> valores) {
		// idTurno  	descricao  
		// SELECT * FROM "turno"
		try {
			String sql = "insert into \"turno\" (\"idTurno\", \"descricao\") values (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++) {
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
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

	public boolean excluirTurno(int id) {
		try {
			String sql = "DELETE FROM \"turno\" WHERE \"idTurno\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean alterarTurno(ArrayList<Object> valores, int id) {
		try {
			String sql = "UPDATE \"turno\" " + "SET \"idTurno\" = ?, " + "\"descricao\" = ?" + "WHERE \"idTurno\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++) {
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}
			stmt.setInt(5, id);
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}


}
