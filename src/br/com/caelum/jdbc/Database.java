package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9555/LojaVirtual", "SA", "");
    }
}
