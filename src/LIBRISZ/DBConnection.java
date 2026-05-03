package LIBRISZ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/libris";
    private static final String USER = "postgres";
    private static final String PASSWORD = "munchkinz2005";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Failed to connect to PostgreSQL.");
            e.printStackTrace();
            return null;
        }
    }
}