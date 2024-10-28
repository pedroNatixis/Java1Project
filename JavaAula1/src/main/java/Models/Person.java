package Models;

public class Person {

	public int Id;
	public String Name;
	public int Age;
	public String Email;

    public Person(int _id, String _name, int _age, String _email) {
        this.Id = _id;
        this.Name = _name;
        this.Age = _age;
    	this.Email = _email;
    }
    
    public Person(String _name, int _age, String _email) {
        this.Name = _name;
        this.Age = _age;
    	this.Email = _email;
    }
    
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public String getEmail() {
        return Email;
    }
}
