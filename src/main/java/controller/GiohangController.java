package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.giohang;
import model.bean.menu;
import model.bean.user;
import model.dao.GioHangDAO;
import model.dao.MenuDAO;

public class GiohangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO; 
    private GioHangDAO gioHangDAO;
    public GiohangController() {
        super();
        menuDAO = new MenuDAO();
        gioHangDAO = new GioHangDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idsp = 0;
		try {
			idsp = Integer.parseInt(request.getParameter("idsp"));
		} catch (NumberFormatException f) {
			// TODO: handle exception
		}
		menu itemSp = menuDAO.getItem(idsp);
		int soluong = 1;
		ArrayList<giohang> listGH = gioHangDAO.getList();
		for(giohang itemGh : listGH) {
			if(itemGh.getId_sanpham() == idsp) {
				int slnew = soluong + itemGh.getSoluong();
				itemGh.setSoluong(slnew);
				if(gioHangDAO.updateSP(itemGh) > 0) {
					response.sendRedirect(request.getContextPath() + "/order/list");;
					return;
				}else {
					System.out.println("Thêm lỗi");
				}
			}
		}
		boolean tinhtrang = false;
		HttpSession session = request.getSession();
		user itemLogin = (user)session.getAttribute("user");
		giohang itemGioHang = new giohang(0, idsp,soluong,tinhtrang,itemLogin.getId());
		
		if(gioHangDAO.addItem(itemGioHang) > 0) {
			response.sendRedirect(request.getContextPath() + "/order/list");;
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
