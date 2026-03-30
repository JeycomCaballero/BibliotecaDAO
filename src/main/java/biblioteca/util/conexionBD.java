package biblioteca.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    static String url = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
    static String user = "root";
    static String password = "123456";

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Se conectó a la BD correctamente");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
        return con;
    }
}