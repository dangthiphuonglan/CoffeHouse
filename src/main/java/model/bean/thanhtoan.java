package model.bean;

public class thanhtoan {
	private int id_thanhtoan;
	private String tenphuongthuc;
	private String cachthanhtoan;
	public thanhtoan(int id_thanhtoan, String tenphuongthuc, String cachthanhtoan) {
		super();
		this.id_thanhtoan = id_thanhtoan;
		this.tenphuongthuc = tenphuongthuc;
		this.cachthanhtoan = cachthanhtoan;
	}
	public int getId_thanhtoan() {
		return id_thanhtoan;
	}
	public void setId_thanhtoan(int id_thanhtoan) {
		this.id_thanhtoan = id_thanhtoan;
	}
	public String getTenphuongthuc() {
		return tenphuongthuc;
	}
	public void setTenphuongthuc(String tenphuongthuc) {
		this.tenphuongthuc = tenphuongthuc;
	}
	public String getCachthanhtoan() {
		return cachthanhtoan;
	}
	public void setCachthanhtoan(String cachthanhtoan) {
		this.cachthanhtoan = cachthanhtoan;
	}
	public thanhtoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
