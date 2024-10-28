package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Person;

public class DbPerson {
    private final DbContext dbContext;

    public DbPerson(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    public List<Person> getPersons() {
    	List<Person> persons = new ArrayList<>();
    	
    	 String query = "SELECT * FROM pessoa";

    	 try (Connection conn = dbContext.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("nome");
                    int age = rs.getInt("idade");
                    String email = rs.getString("email");
                    persons.add(new Person(id, name, age, email));
                }
            } catch (SQLException e) {
                System.out.println("Erro ao obter dados da base de dados.");
                e.printStackTrace();
            }
    	
    	return persons;
    }
    
    
    public void addPerson(Person person) {
        String query = "INSERT INTO pessoa (nome, idade, email) VALUES (?, ?, ?)";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setString(1, person.Name);
            ps.setString(2, String.valueOf(person.getAge()));
            ps.setString(3, person.Email);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pessoa adicionada com sucesso.");
            } else {
                System.out.println("Erro ao adicionar a pessoa.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na base de dados.");
            e.printStackTrace();
        }
    }
    
    public void updatePerson(Person person) {
        String query = "UPDATE `pessoa` SET `nome` = ?, `idade` = ?, `email` = ? WHERE `id` = ?";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
       
            ps.setString(1, person.Name);
            ps.setString(2, String.valueOf(person.getAge()));
            ps.setString(3, person.Email);        
            ps.setString(4 , String.valueOf(person.Id));
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pessoa atualizada com sucesso.");
            } else {
                System.out.println("Erro ao atualizar a pessoa.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados na base de dados.");
            e.printStackTrace();
        }
    }
    
    
    
    public void deletePersonById(int id) {
        String query = "DELETE FROM pessoa WHERE id = ?";
        
        try (Connection conn = dbContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao eliminar a pessoa.");
            e.printStackTrace();
        }
    }
    
    
}
