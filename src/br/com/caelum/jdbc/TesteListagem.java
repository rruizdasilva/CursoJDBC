package br.com.caelum.jdbc;

import java.sql.*;

public class TesteListagem {

    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionPool().getConnection();
        Statement statement = connection.createStatement();
        boolean resultado = statement.execute("insert into produto (nome, descricao) values ('Notebook', 'Notebook i5')");
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
