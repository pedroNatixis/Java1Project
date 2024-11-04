package Models;

import java.time.LocalDate;

public class Client {
	   private int id;
	    private String name;
	    private int phone;
	    private String address;
	    private int nif;
	    private LocalDate dateOfBirth;
	    private int userId;

	    // Constructor
	    public Client(int id, String name, int phone, String address, int nif, LocalDate dateOfBirth, int userId) {
	        this.id = id;
	        this.name = name;
	        this.phone = phone;
	        this.address = address;
	        this.nif = nif;
	        this.dateOfBirth = dateOfBirth;
	        this.userId = userId;
	    }

	    // Getters and Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getPhone() {
	        return phone;
	    }
	    

	    public void setPhone(int phone) {
	        this.phone = phone;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public int getNif() {
	        return nif;
	    }

	    public void setNif(int nif) {
	        this.nif = nif;
	    }

	    public LocalDate getDateOfBirth() {
	        return dateOfBirth;
	    }

	    public void setDateOfBirth(LocalDate dateOfBirth) {
	        this.dateOfBirth = dateOfBirth;
	    }
	    
	    public int getUserId() {
	        return userId;
	    }
	    
	    public void setUserId(int userId) {
	        this.userId = userId;
	    }
	    
	    
	
		
	
}
