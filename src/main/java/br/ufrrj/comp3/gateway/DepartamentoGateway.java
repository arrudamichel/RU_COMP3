package br.ufrrj.comp3.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartamentoGateway {

	private Connection conn;

	public DepartamentoGateway(Connection conn) {
		this.conn = conn;
	}
	
	public boolean inserir(ArrayList<Object> valores){
		
		try{			
	      
	        String sql = "INSERT INTO \"departamento\" ( \"nome\", \"sigla\") values (?,?)";	
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
	
	public ResultSet selecionarDepartamentos(){
		
		ResultSet rs = null;
		Statement stat; 
		
		try{
	      
	        String sql = "SELECT * FROM \"departamento\"";
	        stat = conn.createStatement();
	        rs = stat.executeQuery(sql); 

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
        return rs;
	}
	
	public ResultSet selecionarDepartamentoPorId(int identificador){
		
		ResultSet rs = null; 
		
		try{
	      
	        String sql = "SELECT * FROM \"departamento\" WHERE \"iddepartamento\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, identificador);
	        rs = stmt.executeQuery();	        	        

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
        return rs;
	}
	
	public boolean excluirDepartamento(int identificador){
		
		try{
	      
	        String sql = "DELETE FROM \"departamento\" WHERE \"iddepartamento\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, identificador);
	        stmt.execute();	        	        

		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
        return true;
	}
	
	public boolean alterarDepartamento(ArrayList<Object> valores, int identificador){
		
		try{			
		      
	        String sql = "UPDATE \"departamento\" "
	        				+ "SET \"iddepartamento\" = ?, "
	        				+ 		"\"nome\" = ?, "
	        				+ 		"\"sigla\" = ? "	        				
	        				+ "WHERE \"iddepartamento\" = ?";
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	
	        // preenche os valores
	        for(int i = 1; i <= valores.size(); i++){
	        	if(valores.get(i-1).getClass().equals(String.class))
	        		stmt.setString(i, (String) valores.get(i-1));
	        	
	        	if(valores.get(i-1).getClass().equals(Integer.class))
	        		stmt.setInt(i, (Integer) valores.get(i-1));
			}
	        
	        stmt.setInt(4, identificador);
	        
	        stmt.execute();	        
		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;			
		}
		
        return true;
	}
}
