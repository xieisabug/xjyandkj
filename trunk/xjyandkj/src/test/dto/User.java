package test.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_user")
public class User {
 
	
	private int id;
	private String usename;
	private String password;
	private String name;
	
	
	
	public User() {
		super();
	}



	public User(int id, String usename, String password, String name) {
		super();
		this.id = id;
		this.usename = usename;
		this.password = password;
		this.name = name;
	}



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
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", usename=" + usename + "]";
	}


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
