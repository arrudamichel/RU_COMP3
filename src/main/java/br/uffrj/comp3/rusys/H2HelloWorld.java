package br.uffrj.comp3.rusys;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufrrj.comp3.gateway.AlunoGateway;
import br.ufrrj.comp3.gateway.ConnectionFactory;
import br.ufrrj.comp3.gateway.ConsumidorGateway;
import br.ufrrj.comp3.gateway.CursoGateway;
import br.ufrrj.comp3.gateway.DepartamentoGateway;
import br.ufrrj.comp3.gateway.FuncionarioGateway;

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
    	valores.add("Sistemas de Informaï¿½ï¿½o");
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
    	
    	/*ArrayList<Object> valores = new ArrayList<Object>();
    	valores.add(2);
    	valores.add("Carne seca com farofa");
    	valores.add("Quixe de capim santo");
    	valores.add(3);
    	
    	RefeicaoGateway gateway = new RefeicaoGateway(conn);
    	
    	// inserir
    	if (gateway.inserir(valores))
    		System.out.println("Inseriu");
    	else
    		System.out.println("Deu ruim");*/
    	
    	//  (\"idRefeicao\", \"descricao\", \"opcaoVegetariana\", \"Turno_idTurno\")
/*    	ResultSet rs = gateway.selecionarCursos();
    	

    	rs.next();
    	int id = rs.getInt("idRefeicao");
    	String desc = rs.getString("descricao");
    	String opcao = rs.getString("opcaoVegetariana");
    	int turno_id = rs.getInt("Turno_idTurno");
    	System.out.println(id + " " + desc + " " + opcao + " " + turno_id);*/
    	    	
    /*	int id = 2;
    	ResultSet rs = gateway.selecionarRefeicaoPorId(id);
    	rs.next();
    	String desc = rs.getString("descricao");
    	String opcao = rs.getString("opcaoVegetariana");
    	int turno_id = rs.getInt("Turno_idTurno");
    	System.out.println(id + " " + desc + " " + opcao + " " + turno_id);
    	
    	if (gateway.excluirRefeicao(id))
    		System.out.println("Deletou");
    	else
    		System.out.println("Deu ruim");
    	
    	id = 1;
    	
    	if (gateway.alterarRefeicao(valores, id))
    		System.out.println("Atualizou");
    	else
    		System.out.println("Deu ruim");*/
    	

    	conn.close();
    }
    
    public void testeCurso() throws SQLException{
    	Connection conn = ConnectionFactory.getConnection("jdbc:h2:~/workspace/RU_COMP3/RU", "sa", "sa");
		
		//Insere
    	CursoGateway cg = new CursoGateway();
    	/*ArrayList<Object> valores = new ArrayList<Object>();
    	valores.add(2);
    	valores.add("Sistemas de Informação");
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
    	
    	
    	ArrayList<Object> valores = new ArrayList<Object>();
    	valores.add(1);
    	valores.add("Sistemas de Informação");
    	valores.add("SI");
    	valores.add(1);
    	
    	int id = 1;
    	cg.alterarCurso(conn, valores, id);
    	
    	ResultSet rs = cg.selecionarCursos(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
    	
    	conn.close();
    }

    public static void testeDepartamento() throws SQLException{
    	Connection conn = ConnectionFactory.getConnection("jdbc:h2:~/workspace/RU_COMP3/RU", "sa", "sa");
		DepartamentoGateway dp = new DepartamentoGateway();
		
    	System.out.println("Insere ----");
		//Insere    	
    	ArrayList<Object> valores = new ArrayList<Object>();
    	valores.add(2);
    	valores.add("Departamento de tecnologias e linguagens");
    	valores.add("DTL");
    	dp.inserir(conn, valores);
    	
    	//Seleciona todos
    	System.out.println("Seleciona tudo ----");
    	ResultSet rs = dp.selecionarDepartamentos(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
		
    	System.out.println("Seleciona id ----");
    	//Selecionar por id
    	int identificador = 1;
		rs = dp.selecionarDepartamentoPorId(conn, identificador);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
    	
    	System.out.println("Excluir ID ----");
    	//Excluir por id
    	identificador = 2;
    	dp.excluirDepartamento(conn, identificador);
    	
    	
    	ArrayList<Object> valores2 = new ArrayList<Object>();
    	valores2.add(1);
    	valores2.add("Departamento de Liguas");
    	valores2.add("DL");
    	
    	int id = 1;
    	dp.alterarDepartamento(conn, valores2, id);
    	
    	System.out.println("Seleciona depois exclusão ----");
    	rs = dp.selecionarDepartamentos(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
    	
    	conn.close();
    }
    
    public static void testeAluno() throws SQLException{
    	Connection conn = ConnectionFactory.getConnection("jdbc:h2:~/workspace/RU_COMP3/RU", "sa", "sa");
		AlunoGateway ag = new AlunoGateway();
		ArrayList<Object> valores = new ArrayList<Object>();
		
    	/*System.out.println("Insere ----");
		//Insere    	
    	valores.add(2);
    	valores.add(2);    	
    	ag.inserir(conn, valores);*/
    	
    	//Seleciona todos
    	System.out.println("Seleciona tudo ----");
    	ResultSet rs = ag.selecionarAlunos(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("consumidor_matricula")+";"+rs.getString("curso_idcurso"));
        }
		
    	System.out.println("Seleciona matricula ----");
    	//Selecionar por id
    	int matricula = 1;
		rs = ag.selecionarAlunoPorMatricula(conn, matricula);
    	while (rs.next()) {
    		System.out.println(rs.getString("consumidor_matricula")+";"+rs.getString("curso_idcurso"));
        }    	    	
    	
    	System.out.println("Altera matricula ----");
    	ArrayList<Object> valores5 = new ArrayList<Object>();
    	valores5.add(1);
    	valores5.add(2);    	
    	matricula = 1;
    	ag.alterarAluno(conn, valores5, matricula);
    	
    	/*System.out.println("Excluir ID ----");
    	//Excluir por id
    	ArrayList<Object> valores4 = new ArrayList<Object>();
    	valores4.add(2);
    	valores4.add(2);
    	ag.excluirAluno(conn, valores4);*/
    	
    	System.out.println("Seleciona depois exclusão ----");
    	rs = ag.selecionarAlunos(conn);
    	while (rs.next()) {
    		System.out.println(rs.getString("consumidor_matricula")+";"+rs.getString("curso_idcurso"));
        }
    	
    	conn.close();
    }
    
    public static void testeConsumidor() throws SQLException{
    	Connection conn = ConnectionFactory.getConnection("jdbc:h2:~/workspace/RU_COMP3/RU", "sa", "sa");
		ConsumidorGateway ag = new ConsumidorGateway();
		ArrayList<Object> valores = new ArrayList<Object>();
		
    	System.out.println("Insere ----");
		//Insere 
    	valores.add(11);
    	valores.add("Bugoaaaaaaaa");
    	valores.add("2010");
    	valores.add("M");
    	valores.add("Graduando");
    	valores.add("11112222211111");
    	ag.inserir(conn, valores);
    	
    	//Seleciona todos
    	System.out.println("Seleciona tudo ----");
    	ResultSet rs = ag.selecionarConsumidores(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
		
    	System.out.println("Seleciona matricula ----");
    	//Selecionar por id
    	int matricula = 11;
		rs = ag.selecionarConsumidorPorMatricula(conn, matricula);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }    	    	
    	
    	System.out.println("Altera matricula ----");
    	ArrayList<Object> valores5 = new ArrayList<Object>();    	    	
    	valores5.add("Bugo1222222");
    	valores5.add("2010");
    	valores5.add("M");
    	valores5.add("Graduando");
    	valores5.add("112233");
    	
    	matricula = 11;
    	ag.alterarConsumidor(conn, valores5, matricula);
    	
    	System.out.println("Seleciona depois alteração ----");
    	rs = ag.selecionarConsumidores(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
    	
    	System.out.println("Excluir ID ----");
    	//Excluir por id
    	matricula = 11;
    	ag.excluirConsumidor(conn, matricula);
    	
    	System.out.println("Seleciona depois exclusão ----");
    	rs = ag.selecionarConsumidores(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
    	
    	conn.close();
    }
    
    public static void testeFuncionario() throws SQLException{
    	Connection conn = ConnectionFactory.getConnection("jdbc:h2:~/workspace/RU_COMP3/RU", "sa", "sa");
		FuncionarioGateway ag = new FuncionarioGateway();
		ArrayList<Object> valores = new ArrayList<Object>();
		
    	System.out.println("Insere ----");
		//Insere    	
    	valores.add(2);
    	valores.add(2);    	
    	ag.inserir(conn, valores);
    	
    	//Seleciona todos
    	System.out.println("Seleciona tudo ----");
    	ResultSet rs = ag.selecionarFuncionarios(conn);
    	while (rs.next()) {
            System.out.println(rs.getString("consumidor_matricula")+";"+rs.getString("departamento_iddepartamento"));
        }
		
    	System.out.println("Seleciona matricula ----");
    	//Selecionar por id
    	int matricula = 1;
		rs = ag.selecionarFuncionarioPorMatricula(conn, matricula);
    	while (rs.next()) {
    		System.out.println(rs.getString("consumidor_matricula")+";"+rs.getString("departamento_iddepartamento"));
        }    	    	
    	
    	/*System.out.println("Altera matricula ----");
    	ArrayList<Object> valores5 = new ArrayList<Object>();
    	valores5.add(1);
    	valores5.add(2);    	
    	matricula = 1;
    	ag.alterarFuncionario(conn, valores5, matricula);*/
    	
    	System.out.println("Excluir ID ----");
    	//Excluir por id
    	ArrayList<Object> valores4 = new ArrayList<Object>();
    	valores4.add(2);
    	valores4.add(2);
    	ag.excluirFuncionario(conn, valores4);
    	
    	System.out.println("Seleciona depois exclusão ----");
    	rs = ag.selecionarFuncionarios(conn);
    	while (rs.next()) {
    		System.out.println(rs.getString("consumidor_matricula")+";"+rs.getString("departamento_iddepartamento"));
        }
    	
    	conn.close();
    }

}