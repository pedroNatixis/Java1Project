package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import Models.User;

public class DbUser {

    private final DbContext dbContext;

    public DbUser(DbContext dbContext) {
        this.dbContext = dbContext;
    }
    
    public boolean userEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        Connection conn = null;
        
        try {
            conn = dbContext.getNewConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão apenas se não estiver null
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public boolean userUserNameExists(String username) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ?";
        Connection conn = null;
        try {
            conn = dbContext.getNewConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão apenas se não estiver null
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    
    public boolean addUser(User user) {
        String userQuery = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        String clientQuery = "INSERT INTO client (userId, name, phone, address, nif, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = dbContext.getNewConnection(); // Obter nova conexão
            conn.setAutoCommit(false); // Inicia uma transação

            // Primeiro, cria o utilizador e obtém o userId
            PreparedStatement userPs = conn.prepareStatement(userQuery, java.sql.Statement.RETURN_GENERATED_KEYS);
            userPs.setString(1, user.UserName);
            userPs.setString(2, user.PassWord);
            userPs.setString(3, user.Email);
            
            int userRowsAffected = userPs.executeUpdate();

            // Obter o ID do utilizador criado
            ResultSet generatedKeys = userPs.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1); // Obter o ID do utilizador
            }

            // Agora, cria o cliente com user_id associado e parâmetros vazios
            PreparedStatement clientPs = conn.prepareStatement(clientQuery);
            clientPs.setInt(1, userId); // Associa o user_id ao cliente
            clientPs.setString(2, null); // Parâmetros vazios
            clientPs.setString(3, null);
            clientPs.setString(4, null);
            clientPs.setString(5, null);
            clientPs.setDate(6, null); // ou java.sql.Date.valueOf(LocalDate.now()) se preferires

            int clientRowsAffected = clientPs.executeUpdate();

            // Confirma a transação se ambos os inserts forem bem-sucedidos
            conn.commit();

            return userRowsAffected > 0 && clientRowsAffected > 0; // Retorna true se ambos foram adicionados com sucesso
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na base de dados: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback(); // Desfaz as alterações se ocorrer um erro
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace(); // Para depuração
            return false; // Retorna false em caso de erro
        } finally {
            // Fecha a conexão apenas se não estiver null
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public boolean authenticateUser(String username, String password) {
        String query = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";
        Connection conn = null;

        try {
            // Obtém uma nova conexão
            conn = dbContext.getNewConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password); // Considere usar hashing para senhas

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se a contagem for maior que 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar a conexão no bloco finally
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return false; // Retorna false se não encontrar o utilizador
    }
    

}
