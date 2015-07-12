package br.ufrrj.comp3.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RefeicaoGateway {
	private Connection conn;

	public RefeicaoGateway(Connection conn) {
		this.conn = conn;
	}

	public boolean inserir(ArrayList<Object> valores) {
		// SELECT * FROM "refeicao" idRefeicao descricao opcaoVegetariana
		// Turno_idTurno
		try {
			String sql = "insert into \"refeicao\" (\"descricao\", \"opcaoVegetariana\", \"Turno_idTurno\") values (?,?,?)";
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

	public ResultSet selecionarRefeicoes() {
		ResultSet rs = null;
		Statement stat;

		try {
			String sql = "SELECT * FROM \"refeicao\"";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	public ResultSet selecionarRefeicaoPorId(int id) {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM \"refeicao\" WHERE \"idRefeicao\" = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return rs;
	}

	public boolean excluirRefeicao(int id) {
		try {
			String sql = "DELETE FROM \"refeicao\" WHERE \"idRefeicao\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean alterarRefeicao(ArrayList<Object> valores, int id) {
		try {
			// String sql = "insert into \"refeicao\" (\"idRefeicao\", \"descricao\", \"opcaoVegetariana\", \"Turno_idTurno\") values (?,?,?,?)";
			String sql = "UPDATE \"refeicao\" " + "SET " + "\"descricao\" = ?, " + "\"opcaoVegetariana\" = ?, "
					+ "\"Turno_idTurno\" = ? " + "WHERE \"idRefeicao\" = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			// preenche os valores
			for (int i = 1; i <= valores.size(); i++) {
				if (valores.get(i - 1).getClass().equals(String.class))
					stmt.setString(i, (String) valores.get(i - 1));

				if (valores.get(i - 1).getClass().equals(Integer.class))
					stmt.setInt(i, (Integer) valores.get(i - 1));
			}
			stmt.setInt(4, id);
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
