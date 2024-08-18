package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.user;
import utils.DBConnectionUtil;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<user> getListUser() {
		ArrayList<user> list = new ArrayList<user>();
		conn = DBConnectionUtil.getConnection();
		String sql = "Select * From user";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("user_ID");
				String username = rs.getString("user_Name");
				String email = rs.getString("user_Email");
				String pass = rs.getString("user_Password");
				String forget = rs.getString("forget");
				boolean active = rs.getBoolean("active");
				
				int role = rs.getInt("role");
				user item = new user(id, username, email, pass, forget, active,role);
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
	
	public ArrayList<user> getListEmployee(int role) {
		ArrayList<user> list = new ArrayList<user>();
		conn = DBConnectionUtil.getConnection();
		String sql = "Select * From user where role = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, role);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("user_ID");
				String username = rs.getString("user_Name");
				String email = rs.getString("user_Email");
				String pass = rs.getString("user_Password");
				String forget = rs.getString("forget");
				boolean active = rs.getBoolean("active");
				

				user item = new user(id, username, email, pass, forget, active,role);
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

	public int addItem(user user) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO user (user_Name,user_Email,user_Password,forget,active,role) VALUES (?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPass());
			pst.setString(4, user.getForget());
			pst.setBoolean(5, user.isActive());
			pst.setInt(6, user.isRole());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pst!=null && conn!=null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}

	public user getUserById(int id) {
		user item = null;
		conn = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM user WHERE user_ID = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				int user_id = rs.getInt("user_ID");
				String name  = rs.getString("user_Name");
				String email  = rs.getString("user_Email");
				String pass  = rs.getString("user_Password");
				String forget  = rs.getString("forget");
				Boolean active = rs.getBoolean("active");
				int role = rs.getInt("role");
				item = new user(user_id, name, email, pass, forget, active,role);
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

	public int editItem(user item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE user SET user_Name = ?, user_Password = ? WHERE user_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPass());
			pst.setInt(3, item.getId());
			result = pst.executeUpdate();
			
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
		return result;
	}

	public int delItem(user item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		
		String sql = "DELETE FROM user WHERE user_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, item.getId());
			result = pst.executeUpdate();
			
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
		return result;
	}

	public user findUserByEmail(String email) {
		user item = null;
		conn = DBConnectionUtil.getConnection();
		String sql="SELECT * FROM user WHERE user_Email = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if(rs.next()) {
				int user_id = rs.getInt("user_ID");
				String name  = rs.getString("user_Name");
				String Email = rs.getString("user_Email");
				String pass  = rs.getString("user_Password");
				String forget  = rs.getString("forget");
				Boolean active = rs.getBoolean("active");
				int role = rs.getInt("role");
				item = new user(user_id, name, Email, pass, forget, active,role);
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
		return item;
	}

	public int updateItem(user item, String forgetPass) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE user SET forget=? WHERE user_ID=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, forgetPass);
			pst.setInt(2, item.getId());
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(pst!=null && conn!=null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return result;
		
	}

	public int updateActive(String email) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String sql = "UPDATE user SET active = 1 WHERE user_Email=? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(pst!=null && conn!=null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return result;
	}
	
	
}
