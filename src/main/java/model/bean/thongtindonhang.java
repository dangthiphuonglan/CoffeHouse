package model.bean;

import java.sql.Date;

public class thongtindonhang {
	private int id;
	private Date ngaymua;
	private String madonhang;
	private int id_nguoimua;
	private String diachi;
	private int trangthai;
	private String tenkhachhang;
	private String sdt;
	private String emai;
	
	
	
	
	public thongtindonhang(int id, Date ngaymua, String madonhang, int id_nguoimua, String diachi, int trangthai,
			String tenkhachhang, String sdt, String emai) {
		super();
		this.id = id;
		this.ngaymua = ngaymua;
		this.madonhang = madonhang;
		this.id_nguoimua = id_nguoimua;
		this.diachi = diachi;
		this.trangthai = trangthai;
		this.tenkhachhang = tenkhachhang;
		this.sdt = sdt;
		this.emai = emai;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Date getNgaymua() {
		return ngaymua;
	}




	public void setNgaymua(Date ngaymua) {
		this.ngaymua = ngaymua;
	}




	public String getMadonhang() {
		return madonhang;
	}




	public void setMadonhang(String madonhang) {
		this.madonhang = madonhang;
	}




	public int getId_nguoimua() {
		return id_nguoimua;
	}




	public void setId_nguoimua(int id_nguoimua) {
		this.id_nguoimua = id_nguoimua;
	}




	public String getDiachi() {
		return diachi;
	}




	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}




	public int getTrangthai() {
		return trangthai;
	}




	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}




	public String getTenkhachhang() {
		return tenkhachhang;
	}




	public void setTenkhachhang(String tenkhachhang) {
		this.tenkhachhang = tenkhachhang;
	}




	public String getSdt() {
		return sdt;
	}




	public void setSdt(String sdt) {
		this.sdt = sdt;
	}




	public String getEmai() {
		return emai;
	}




	public void setEmai(String emai) {
		this.emai = emai;
	}




	public thongtindonhang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
