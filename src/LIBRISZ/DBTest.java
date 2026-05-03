package LIBRISZ;

import java.sql.Connection;

public class DBTest {

    public static void main(String[] args) {

        System.out.println("POSTGRESQL CONNECTION TEST--------------------------");

        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Java is connected to the Libris database.");
        } else {
            System.out.println("Java failed to connect to the Libris database.");
        }
    }
}