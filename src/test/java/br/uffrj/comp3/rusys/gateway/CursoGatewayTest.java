package br.uffrj.comp3.rusys.gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import br.uffrj.comp3.rusys.persintece.ConnectionFactory;
import br.uffrj.comp3.rusys.persintece.CursoGateway;
import br.uffrj.comp3.rusys.util.Constantes;




public class CursoGatewayTest {

	public static void main(String[] args) {
		Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
		
		CursoGateway cursoGateway = new CursoGateway(conn);
// INSERT INTO "curso" ("nome","sigla","ativado","departamento_iddepartamento") VALUES ('Letras','LE', 0, 2);
		
		ArrayList<Object> valores = new ArrayList<Object>(Arrays.asList("Turismo", "TUR", 2));
		if (cursoGateway.inserir(valores)!=null)
			System.out.println(valores + " inseridos no teste");
		else
			System.out.println(valores + " deu ruim no teste");
			
			
		
		ResultSet rs = cursoGateway.selecionarCursos();
		try {
			while (rs.next()) {
				int id = rs.getInt("idcurso");
				String desc = rs.getString("nome");
		    	String opcao = rs.getString("sigla");
		    	int idDept = rs.getInt("departamento_iddepartamento");
		    	System.out.println(id + " " + desc + " " + opcao + " " + idDept);
			}
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		valores = new ArrayList<Object>(Arrays.asList("Administracao", "ADM", 2));
		if (cursoGateway.alterarCurso(valores, 5))
			System.out.println(valores + " alterados no teste");
		else
			System.out.println(valores + " deu ruim alterados no teste");
		
		
		 rs = cursoGateway.selecionarCursos();
			try {
				while (rs.next()) {
					int id = rs.getInt("idcurso");
					String desc = rs.getString("nome");
			    	String opcao = rs.getString("sigla");
			    	int idDept = rs.getInt("departamento_iddepartamento");
			    	System.out.println(id + " " + desc + " " + opcao + " " + idDept);
				}
		    	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
