package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado a BD");

        } catch (Exception e) {

            System.out.println("Error de conexión: " + e);

        }

        return conn;

    }
}

