package pe.edu.upeu.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    private static SQLiteConnection instance;
    private Connection connection;

    private static final String DB_NAME = "cliente_db";
    private static final String DB_PATH = "data/" + DB_NAME;
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    // Constructor privado (Singleton)
    private SQLiteConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);

            // Activar claves foráneas en SQLite
            connection.createStatement().execute("PRAGMA foreign_keys = ON;");

            System.out.println("Conexión a SQLite establecida.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a SQLite: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para obtener la instancia única
    public static synchronized SQLiteConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteConnection();
        }
        return instance;
    }

    // Obtener la conexión
    public Connection getConnection() {
        return connection;
    }

    // Cerrar conexión (opcional)
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}