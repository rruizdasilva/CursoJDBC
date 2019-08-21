package br.com.caelum.jdbc.DAO;

import br.com.caelum.jdbc.ConnectionPool;
import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    private final Connection con;

    public ProdutosDAO(Connection con) {
        this.con = con;
    }

    public void salva(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (NOME,DESCRICAO) VALUES(?,?)";
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.execute();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    produto.setId(id);
                }
            }
        }
    }

    public List<Produto> lista() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.execute();
            transformaResultSetEmPRodutos(stmt, produtos);
        }
        return produtos;
    }

    public List<Produto> busca(Categoria categoria) throws SQLException {
        System.out.println("Executando uma query");
        List<Produto> produtos = new ArrayList<>();
        String sql = "select * from Produto where categoria_id = ?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, categoria.getId());
            stmt.execute();
            transformaResultSetEmPRodutos(stmt, produtos);
        }
        return produtos;
    }

    private void transformaResultSetEmPRodutos(PreparedStatement stmt, List<Produto> produtos) throws SQLException {
        try (ResultSet rs = stmt.getResultSet()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                Produto produto = new Produto(nome, descricao);
                produto.setId(id);
                produtos.add(produto);
            }
        }
    }
}
