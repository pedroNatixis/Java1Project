package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String query = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        Connection conn = null;
        
        try {
            conn = dbContext.getNewConnection(); // Supondo que este método exista no DbContext
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, user.UserName);
            ps.setString(2, user.PassWord); 
            ps.setString(3, user.Email);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true se o utilizador foi adicionado com sucesso
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na base de dados: " + e.getMessage());
            e.printStackTrace(); // Para depuração, evite em produção
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
