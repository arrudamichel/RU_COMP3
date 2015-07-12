package br.ufrrj.comp3.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AlunoGateway {

	public AlunoGateway() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean inserir(Connection conn, ArrayList<Object> valores){
		
		try{			
	      
	        String sql = "INSERT INTO \"aluno\" VALUES (?,?)";	
	        PreparedStatement stmt = conn.prepareStatement(sql);

	        // preenche os valores
	        for(int i = 1; i <= valores.size(); i++){
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
	
	public ResultSet selecionarAlunos(Connection conn){
		
		ResultSet rs = null;
		Statement stat; 
		
		try{
	      
	        String sql = "SELECT * "
	        		+ "   FROM \"aluno\"";
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql); 

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
        return rs;
	}
	
	public ResultSet selecionarAlunoPorMatricula(Connection conn, int matricula){
		
		ResultSet rs = null; 
		
		try{
	      
	        String sql = "SELECT * "
	        		+ "   FROM \"aluno\" "
	        		+ "   WHERE \"consumidor_matricula\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, matricula);
	        rs = stmt.executeQuery();	        	        

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
        return rs;
	}
	
	public boolean excluirAluno(Connection conn, ArrayList<Object> valores){
		
		try{
	      
	        String sql = "DELETE FROM \"aluno\" "
	        		+ "   WHERE \"consumidor_matricula\" = ?"
	        		+ "       AND \"curso_idcurso\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        // preenche os valores
	        for(int i = 1; i <= valores.size(); i++){	        	
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
	
	public boolean alterarAluno(Connection conn, ArrayList<Object> valores, int matricula){
		
		try{			
		      
	        String sql = "UPDATE \"aluno\" "
	        			+ "SET \"consumidor_matricula\" = ?, "
	        			+ 		"\"curso_idcurso\" = ? "	        			 		
	        			+ "WHERE \"consumidor_matricula\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	
	        // preenche os valores
	        for(int i = 1; i <= valores.size(); i++){
	        	if(valores.get(i-1).getClass().equals(Integer.class))
	        		stmt.setInt(i, (Integer) valores.get(i-1));
			}
	        
	        stmt.setInt(3, matricula);
	        
	        stmt.execute();	        
		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;			
		}
		
        return true;
	}
}