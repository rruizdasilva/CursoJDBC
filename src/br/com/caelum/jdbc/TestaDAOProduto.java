package br.com.caelum.jdbc;

import br.com.caelum.jdbc.DAO.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaDAOProduto {

    public static void main(String[] args) throws SQLException {
        Produto mesa = new Produto("Mesa Vermelha", "Mesa com 4 pés");

        try(Connection con = new ConnectionPool().getConnection()){
            ProdutosDAO dao = new ProdutosDAO(con);
            dao.salva(mesa);

            List<Produto> produtos = dao.lista();
            for (Produto produto:produtos){
                System.out.println("Existe o produto: " + produto);
            }
        }
    }
}
