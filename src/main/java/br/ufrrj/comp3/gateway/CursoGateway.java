package br.ufrrj.comp3.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CursoGateway {

	public CursoGateway() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean inserir(Connection conn, ArrayList<Object> valores){
		
		try{			
	      
	        String sql = "insert into \"curso\" (\"idcurso\", \"nome\", \"sigla\", \"departamento_iddepartamento\") values (?,?,?,?)";	
	        PreparedStatement stmt = conn.prepareStatement(sql);
	
	        // preenche os valores
	        for(int i = 1; i <= valores.size(); i++){
	        	if(valores.get(i-1).getClass().equals(String.class))
	        		stmt.setString(i, (String) valores.get(i-1));
	        	
	        	if(valores.get(i-1).getClass().equals(Integer.class))
	        		stmt.setInt(i, (Integer) valores.get(i-1));
			}
	        
	        stmt.execute();	        
		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;			
		}
		
        return true;
	}
	
	public ResultSet selecionarCursos(Connection conn){
		
		ResultSet rs = null;
		Statement stat; 
		
		try{
	      
	        String sql = "SELECT * FROM \"curso\"";
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql); 

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
        return rs;
	}
	
	public ResultSet selecionarCursoPorId(Connection conn, int identificador){
		
		ResultSet rs = null; 
		
		try{
	      
	        String sql = "SELECT * FROM \"curso\" WHERE \"idcurso\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, identificador);
	        rs = stmt.executeQuery();	        	        

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
        return rs;
	}
	
	public boolean excluirCurso(Connection conn, int identificador){
		
		try{
	      
	        String sql = "DELETE FROM \"curso\" WHERE \"idcurso\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, identificador);
	        stmt.execute();	        	        

		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
        return true;
	}
	
	public boolean alterarCurso(Connection conn, ArrayList<Object> valores, int identificador){
		
		try{			
		      
	        String sql = "UPDATE \"curso\" "
	        				+ "SET \"idcurso\" = ?, "
	        				+ 		"\"nome\" = ?, "
	        				+ 		"\"sigla\" = ?, "
	        				+ 		"\"departamento_iddepartamento\" = ? "
	        				+ "WHERE \"idcurso\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	
	        // preenche os valores
	        for(int i = 1; i <= valores.size(); i++){
	        	if(valores.get(i-1).getClass().equals(String.class))
	        		stmt.setString(i, (String) valores.get(i-1));
	        	
	        	if(valores.get(i-1).getClass().equals(Integer.class))
	        		stmt.setInt(i, (Integer) valores.get(i-1));
			}
	        
	        stmt.setInt(5, identificador);
	        
	        stmt.execute();	        
		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;			
		}
		
        return true;
	}
}		
