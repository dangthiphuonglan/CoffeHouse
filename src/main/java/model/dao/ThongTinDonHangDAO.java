package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.thongtindonhang;
import utils.DBConnectionUtil;

public class ThongTinDonHangDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public int addItemDH(thongtindonhang itemdonhang) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "insert into thongtindonhang(ngaymua,madonhang,id_nguoimua,diachigiaohang,trangthaihoatdong,tenkhachhang,sdt,email)"
				+ " values(?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setDate(1, itemdonhang.getNgaymua());
			pst.setString(2, itemdonhang.getMadonhang());
			pst.setInt(3, itemdonhang.getId_nguoimua());
			pst.setString(4, itemdonhang.getDiachi());
			pst.setInt(5, itemdonhang.getTrangthai());
			pst.setString(6, itemdonhang.getTenkhachhang());
			pst.setString(7, itemdonhang.getSdt());
			pst.setString(8, itemdonhang.getEmai());
			
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}

	public thongtindonhang getItemnew() {
		thongtindonhang item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM `thongtindonhang` WHERE id_thongtindonhang = (SELECT MAX(id_thongtindonhang) from thongtindonhang) ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int id = rs.getInt(1);
				Date ngaymua = rs.getDate(2);
				String madh = rs.getString(3);
				int id_nguoimua = rs.getInt(4);
				String diachi = rs.getString(5);
				int trangthai = rs.getInt(6);
				String tenkh = rs.getString(7);
				String sdt = rs.getString(8);
				String email = rs.getString(9);

				
				item = new thongtindonhang(id, ngaymua, madh, id_nguoimua,tenkh, trangthai, diachi, sdt, email);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return item;
	}
	
	public ArrayList<thongtindonhang> getItemByIdKH (int idkh){
		ArrayList<thongtindonhang> list = new ArrayList<thongtindonhang>();
		conn = DBConnectionUtil.getConnection();
		String sql = "select * from thongtindonhang where id_nguoimua = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idkh);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Date ngaymua = rs.getDate(2);
				String madh = rs.getString(3);
				int id_nguoimua = rs.getInt(4);
				String diachi = rs.getString(5);
				int trangthai = rs.getInt(6);
				String tenkh = rs.getString(7);
				String sdt = rs.getString(8);
				String email = rs.getString(9);
				
				thongtindonhang item = new thongtindonhang(id, ngaymua, madh, id_nguoimua,tenkh, trangthai, diachi, sdt, email);
				list.add(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return list;
	}
	
	public thongtindonhang getItemById (int idtt){
		thongtindonhang item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "select * from thongtindonhang where id_thongtindonhang = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idtt);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt(1);
				Date ngaymua = rs.getDate(2);
				String madh = rs.getString(3);
				int id_nguoimua = rs.getInt(4);
				String diachi = rs.getString(5);
				int trangthai = rs.getInt(6);
				String tenkh = rs.getString(7);
				String sdt = rs.getString(8);
				String email = rs.getString(9);
				
				item = new thongtindonhang(id, ngaymua, madh, id_nguoimua,tenkh, trangthai, diachi, sdt, email);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return item;
	}

	public int updateItem(thongtindonhang itemUpdate) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "update thongtindonhang set trangthaihoatdong = ? where id_thongtindonhang = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, itemUpdate.getTrangthai());
			pst.setInt(2, itemUpdate.getId());
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}
}
