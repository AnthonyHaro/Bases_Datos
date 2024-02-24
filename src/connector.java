import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connector {
    private static final String dbhost = "jdbc:mysql://u6wngoycjcnhcfdi:8SUrkbxGIVSBnLeZBdbP@b5b73wsjwsxfndlstuxv-mysql.services.clever-cloud.com:3306/b5b73wsjwsxfndlstuxv";
    private static final String dbuser = "u6wngoycjcnhcfdi";
    private static final String dbpassword = "8SUrkbxGIVSBnLeZBdbP";
    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            // Registro del driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexión
            conexion = DriverManager.getConnection(dbhost, dbuser, dbpassword);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}
