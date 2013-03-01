package test.dto;

public class User {
 
	private String usename;
	private String password;
	private String name;
	
	
	
	public String getUsename() {
		return usename;
	}



	public void setUsename(String usename) {
		this.usename = usename;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "User []";
	}
	
	
	
}
