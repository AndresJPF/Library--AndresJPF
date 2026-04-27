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
            e.printStackTrace();
            System.out.println("Error de conexión: " + e);

        }

        return conn;

    }
}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionDB {
//
//    // Librería de MySQL
//    public String driver = "com.mysql.jdbc.Driver";
//
//    // Nombre de la base de datos
//    public String database = "library";
//
//    // Host
//    public String hostname = "localhost";
//
//    // Puerto
//    public String port = "3306";
//
//    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
//    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
//
//    // Nombre de usuario
//    public String username = "root";
//
//    // Clave de usuario
//    public String password = "123456789";
//
//    public Connection connect() {
//        Connection conn = null;
//
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//
//        return conn;
//    }
//
//}