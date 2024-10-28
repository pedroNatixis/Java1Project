package Models;

public class User {
	public int Id;
	public String UserName;
	public String PassWord;
	public String Email;

    public User(int _id, String _userName, String _passWord, String _email) {
        this.Id = _id;
        this.UserName = _userName;
        this.PassWord = _passWord;
    	this.Email = _email;
    }
    
    public User(String _userName, String _passWord, String _email) {
        this.UserName = _userName;
        this.PassWord = _passWord;
    	this.Email = _email;
    }
    
}
