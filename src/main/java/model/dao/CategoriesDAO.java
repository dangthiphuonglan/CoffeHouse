package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.categories;
import utils.DBConnectionUtil;

public class CategoriesDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<categories> getCategories(){
		ArrayList<categories> list = new ArrayList<categories>();
		conn = DBConnectionUtil.getConnection();
		String sql = "Select * from categories";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String picture = rs.getString(3);
				String detail = rs.getString(4);
				
				categories item = new categories(id, name, picture, detail);
				list.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && st!=null && conn!=null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return list;
	}

	public categories getIdByCatName(String cat) {
		categories item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE categories.name=?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat);
			rs = pst.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				item = new categories(id, null,null,null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && st!=null && conn!=null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		
		return item;
	}

	public int additem(categories item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO categories(name,picture,detail) VALUES (?,?,?) ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPicture());
			pst.setString(3, item.getDetail());
			
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
