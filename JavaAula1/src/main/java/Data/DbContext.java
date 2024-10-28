package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {
    private static final String URL = "jdbc:mysql://localhost:3306/java-aula1";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    // Instâncias das classes de acesso às tabelas
    private DbPerson dbPerson;
    private DbUser dbUser;

    // Construtor público para permitir novas instâncias
    public DbContext() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // Inicializar as classes de acesso às tabelas
            this.dbPerson = new DbPerson(this);
            this.dbUser = new DbUser(this);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    // Método para returnar uma nova conexão
    public Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    
    // Método para obter a conexão
    public Connection getConnection() {
        return this.connection;
    }

    // Método para fechar a conexão
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para obter as instâncias das classes específicas
    public DbPerson getDbPerson() {
        return dbPerson;
    }
    
    public DbUser getDbUser() {
        return dbUser;
    }

}
