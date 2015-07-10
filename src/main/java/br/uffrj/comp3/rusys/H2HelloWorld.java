package br.uffrj.comp3.rusys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;

import org.h2.tools.DeleteDbFiles;

public class H2HelloWorld {

    /**
     * Called when ran from command line.
     *
     * @param args ignored
     */
    public static void main(String... args) throws Exception {
        // delete the database named 'test' in the user home directory
        //DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/git/RU_COMP3/RU","sa","sa");
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        // stat.execute("runscript from 'init.sql'");

        //stat.execute("create table TEST(id int primary key, name varchar(255))");
        stat.execute("insert into TEST values(3, 'Bugo3')");
        ResultSet rs;
        rs = stat.executeQuery("select * from TEST");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        stat.close();
        conn.close();
    }

}