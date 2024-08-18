package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.menu;
import utils.DBConnectionUtil;

public class MenuDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<menu> getListMenu(int cid) {
		ArrayList<menu> listMenu = new ArrayList<menu>();
		conn = DBConnectionUtil.getConnection();
		String sql = "Select * FROM product WHERE c_id = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String detail = rs.getString(3);
				String picture = rs.getString(4);
				int price = rs.getInt(5);
				menu item = new menu(id, name, detail, picture, price, cid);
				listMenu.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return listMenu;
	}
	
	public ArrayList<menu> getListMenu() {
		ArrayList<menu> listMenu = new ArrayList<menu>();
		conn = DBConnectionUtil.getConnection();
		String sql = "Select * FROM product";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String detail = rs.getString(3);
				String picture = rs.getString(4);
				int price = rs.getInt(5);
				int cid = rs.getInt(6);
				menu item = new menu(id, name, detail, picture, price, cid);
				listMenu.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return listMenu;
	}

	public int additem(menu item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO product(name,detail,picture,price,c_id) VALUES (?,?,?,?,?) ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getDetail());
			pst.setString(3, item.getPicture());
			pst.setInt(4, item.getPrice());
			pst.setInt(5, item.getC_id());
			
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

	public menu getItem(int id) {
		menu item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "Select * From product WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString(2);
				String detail = rs.getString(3);
				String picture = rs.getString(4);
				int price = rs.getInt(5);
				int c_id = rs.getInt(6);
				item = new menu(id, name, detail, picture, price, c_id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return item;
	}

	public int editItem(menu item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE product SET name=? , detail = ?, picture = ?, price = ?, c_id = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getDetail());
			pst.setString(3, item.getPicture());
			pst.setInt(4, item.getPrice());
			pst.setInt(5, item.getC_id());
			pst.setInt(6, item.getId());
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public int delItem(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM product WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public menu getItem(String string) {
		menu item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "Select * From product WHERE name = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, string);
			rs = pst.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String detail = rs.getString(3);
				String picture = rs.getString(4);
				int price = rs.getInt(5);
				int c_id = rs.getInt(6);
				item = new menu(id, name, detail, picture, price, c_id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(rs!=null && pst!=null && conn!=null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return item;
	}

	

}
