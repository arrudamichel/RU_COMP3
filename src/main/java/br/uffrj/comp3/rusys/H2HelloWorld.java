package br.uffrj.comp3.rusys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.h2.tools.DeleteDbFiles;

import br.uffrj.comp3.model.Refeicao;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.CursoGateway;
import br.ufrrj.comp3.gateway.RefeicaoGateway;

public class H2HelloWorld {

    /**
     * Called when ran from command line.
     *
     * @param args ignored
     */
    public static void main(String... args) throws Exception {
        // delete the database named 'test' in the user home directory
        /*DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        // stat.execute("runscript from 'init.sql'");

        stat.execute("create table test(id int primary key, name varchar(255))");
        stat.execute("insert into test values(1, 'Hello')");
        ResultSet rs;
        rs = stat.executeQuery("select * from test");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        stat.close();
        conn.close();*/
		Connection conn = ConnectionFactory.getConnection("jdbc:h2:~/workspace/RU_COMP3/RU", "sa", "sa");
		
		//Insere
    //	CursoGateway cg = new CursoGateway();
    	/*ArrayList<Object> valores = new ArrayList<Object>();
    	valores.add(2);
    	valores.add("Sistemas de Informa��o");
    	valores.add("SI");
    	valores.add(1);
    	
    	cg.inserir(conn, valores);*/
    	
    	//Seleciona todos
    	/*ResultSet rs = cg.selecionarCursos(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }*/
		
    	//Selecionar por id
    	/*int identificador = 1;
		ResultSet rs = cg.selecionarCursoPorId(conn, identificador);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }*/
    	
    	/*//Excluir por id
    	int identificador = 2;
    	cg.excluirCurso(conn, identificador);*/
    	
    	
/*    	ArrayList<Object> valores = new ArrayList<Object>();
    	valores.add(1);
    	valores.add("Sistemas de Informacao");
    	valores.add("SI");
    	valores.add(1);
    	
    	int id = 1;
    	cg.alterarCurso(conn, valores, id);
    	
    	ResultSet rs = cg.selecionarCursos(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }*/
    	
    	ArrayList<Object> valores = new ArrayList<Object>();
    	valores.add(1);
    	valores.add("Carne moida com arroz");
    	valores.add("Alface sem graca");
    	valores.add(1);
    	
    	RefeicaoGateway gateway = new RefeicaoGateway(conn);
    	
    	// inserir
    	if (gateway.inserir(valores))
    		System.out.println("Inseriu");
    	else
    		System.out.println("Deu ruim");
    	
    	
    	
    	
    	conn.close();
    }

}