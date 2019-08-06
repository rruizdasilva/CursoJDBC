package br.com.caelum.jdbc;

import org.hsqldb.jdbc.JDBCPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private final DataSource datasource;

    ConnectionPool(){
        JDBCPool pool = new JDBCPool();
        pool.setURL("jdbc:hsqldb:hsql://localhost:9555/LojaVirtual");
        pool.setUser("SA");
        pool.setPassword("");
        this.datasource = pool;
    }

    public Connection getConnection() throws SQLException {
        // return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9555/LojaVirtual", "SA", "");
        Connection connection = datasource.getConnection();
        return connection;
    }
}
