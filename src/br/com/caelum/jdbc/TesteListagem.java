package br.com.caelum.jdbc;

import java.sql.*;

public class TesteListagem {

    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9555/LojaVirtual", "SA", "");
        Statement statement = connection.createStatement();
        boolean resultado = statement.execute("select * from Produto");
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");
            System.out.println(id);
            System.out.println(nome);
            System.out.println(descricao);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
