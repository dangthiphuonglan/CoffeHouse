package model.bean;

public class user {
	private int Id;
	private String name;
	private String email;
	private String pass;
	private String forget;
	private boolean active;
	private int role;
	
	
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public user(int id, String name, String email, String pass, String forget, boolean active, int role) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.forget = forget;
		this.active = active;
		this.role = role;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getForget() {
		return forget;
	}
	public void setForget(String forget) {
		this.forget = forget;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int isRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
}
