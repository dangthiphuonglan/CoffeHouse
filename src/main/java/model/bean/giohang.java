package model.bean;

public class giohang {
	private int id_giohang;
	private int id_sanpham;
	private int soluong;
	private boolean tinhtrang;
	private int id_user;
	
	
	
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public int getId_giohang() {
		return id_giohang;
	}
	public void setId_giohang(int id_giohang) {
		this.id_giohang = id_giohang;
	}
	public int getId_sanpham() {
		return id_sanpham;
	}
	public void setId_sanpham(int id_sanpham) {
		this.id_sanpham = id_sanpham;
	}
	public giohang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public giohang(int id_giohang, int id_sanpham) {
		super();
		this.id_giohang = id_giohang;
		this.id_sanpham = id_sanpham;
	}
	public boolean isTinhtrang() {
		return tinhtrang;
	}
	public void setTinhtrang(boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public giohang(int id_giohang, int id_sanpham, boolean tinhtrang, int id_user) {
		super();
		this.id_giohang = id_giohang;
		this.id_sanpham = id_sanpham;
		this.tinhtrang = tinhtrang;
		this.id_user = id_user;
	}
	public giohang(int id_giohang, int id_sanpham, int soluong, boolean tinhtrang, int id_user) {
		super();
		this.id_giohang = id_giohang;
		this.id_sanpham = id_sanpham;
		this.soluong = soluong;
		this.tinhtrang = tinhtrang;
		this.id_user = id_user;
	}
	

}
