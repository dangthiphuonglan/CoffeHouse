package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.giohang;
import utils.DBConnectionUtil;

public class GioHangDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public int addItem(giohang itemGioHang) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "Insert into giohang(id_sanpham,soluong,tinhtrang,id_user) values (?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, itemGioHang.getId_sanpham());
			pst.setInt(2, itemGioHang.getSoluong());
			pst.setBoolean(3, false);
			pst.setInt(4, itemGioHang.getId_user());
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(pst!=null&& conn!=null) {
				try {
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
	
	public ArrayList<giohang> getList(){
		ArrayList<giohang> listgh = new ArrayList<giohang>();
		conn = DBConnectionUtil.getConnection();
		String sql = "select * from giohang";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id_giohang = rs.getInt(1);
				int id_sanpham = rs.getInt(2);
				int soluong = rs.getInt(3);
				boolean tinhtrang = rs.getBoolean(4);
				int id_user = rs.getInt(5);
				giohang itemGh = new giohang(id_giohang, id_sanpham,soluong,tinhtrang,id_user);
				listgh.add(itemGh);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs!= null && st!=null&& conn!=null) {
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
		
		return listgh;
	}

	public int delItem(int id_sanpham) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "delete from giohang where id_sanpham = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_sanpham);
			
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if( pst!=null&& conn!=null) {
				try {
					
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

	public int updateSP(giohang slnew) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "update giohang set soluong = ? where id_giohang = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, slnew.getSoluong());
			pst.setInt(2, slnew.getId_giohang());
			
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if( pst!=null&& conn!=null) {
				try {
					
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
