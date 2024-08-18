package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.chitietdonhang;
import utils.DBConnectionUtil;

public class ChiTietDonHangDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public int addItemChiTiet(chitietdonhang itemChiTiet) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "Insert into chitietdonhang(id_thongtindonhang,id_sanpham,soluong,dongia) values (?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, itemChiTiet.getId_thongtindonhang());
			pst.setInt(2, itemChiTiet.getId_sanpham());
			pst.setInt(3, itemChiTiet.getSoluong());
			pst.setInt(4, itemChiTiet.getDongia());
			
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
	
	public ArrayList<chitietdonhang> getListDH(){
		ArrayList<chitietdonhang> listdh = new ArrayList<chitietdonhang>();
		conn = DBConnectionUtil.getConnection();
		String sql = "select * from chitietdonhang";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt(1);
				int id_thongtindonhang = rs.getInt(2);
				int id_sanpham = rs.getInt(3);
				int soluong = rs.getInt(4);
				int dongia = rs.getInt(5);
				
				chitietdonhang item = new chitietdonhang(id, id_thongtindonhang, id_sanpham, soluong, dongia);
				
				listdh.add(item);
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
		return listdh;
	}
	
	public chitietdonhang getDHByIdDH(int id_DH){
		chitietdonhang dh = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "select * from chitietdonhang where id_thongtindonhang = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_DH);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				int id_thongtindonhang = rs.getInt(2);
				int id_sanpham = rs.getInt(3);
				int soluong = rs.getInt(4);
				int dongia = rs.getInt(5);
				
				dh = new chitietdonhang(id, id_thongtindonhang, id_sanpham, soluong, dongia);
				
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
		return dh;
	}
}
