package model.bean;

public class menu {
	private int id;
	private String name;
	private String detail;
	private String picture;
	private int price;
	private int c_id;
	public menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public menu(int id, String name, String detail, String picture, int price, int c_id) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.picture = picture;
		this.price = price;
		this.c_id = c_id;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	
	
}
