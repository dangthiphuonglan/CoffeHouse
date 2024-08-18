package model.bean;

public class categories {
	private int id;
	private String name;
	private String picture;
	private String detail;
	public categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public categories(int id, String name, String picture, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.detail = detail;
	}
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
