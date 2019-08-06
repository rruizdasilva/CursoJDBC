package br.com.caelum.jdbc;

import java.sql.*;

public class TesteInsercao {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = ConnectionPool.getConnection()){
            connection.setAutoCommit(false);
            String sql = "insert into Produto (nome, descricao) values (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                adiciona("Notebook'i5", "Notebook de ultima geracao", statement);
                adiciona("TV LCD", "32 polegadas", statement);
                adiciona("Blueray","Full HDMI", statement);
                connection.commit();
            } catch (Exception ex){
                ex.printStackTrace();
                connection.rollback();
                System.out.println("Rollback efetuado");
            }
        }
    }

    public static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
        if(nome.equals("Blueray")){
            throw new IllegalArgumentException("Problema ocorrido");
        }
        statement.setString(1, nome);
        statement.setString(2, descricao);
        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println(id + " gerado");
        }

        resultSet.close();
    }
}
