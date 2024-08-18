package model.bean;

public class chitietdonhang {
	private int id;
	private int id_thongtindonhang;
	private int id_sanpham;
	private int soluong;
	private int dongia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_thongtindonhang() {
		return id_thongtindonhang;
	}
	public void setId_thongtindonhang(int id_thongtindonhang) {
		this.id_thongtindonhang = id_thongtindonhang;
	}
	public int getId_sanpham() {
		return id_sanpham;
	}
	public void setId_sanpham(int id_sanpham) {
		this.id_sanpham = id_sanpham;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public int getDongia() {
		return dongia;
	}
	public void setDongia(int dongia) {
		this.dongia = dongia;
	}
	public chitietdonhang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public chitietdonhang(int id, int id_thongtindonhang, int id_sanpham, int soluong, int dongia) {
		super();
		this.id = id;
		this.id_thongtindonhang = id_thongtindonhang;
		this.id_sanpham = id_sanpham;
		this.soluong = soluong;
		this.dongia = dongia;
	}
	
	
}
