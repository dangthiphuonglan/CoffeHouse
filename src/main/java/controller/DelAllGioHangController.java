package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.giohang;
import model.dao.GioHangDAO;

@WebServlet("/DelAllGioHangController")
public class DelAllGioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GioHangDAO gioHangDAO;
    public DelAllGioHangController() {
        super();
        gioHangDAO = new GioHangDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<giohang> list = gioHangDAO.getList();
		
		for(int i = 0; i<list.size();i++) {
				list.remove(i);
		}

		request.setAttribute("listad", list);
		RequestDispatcher rd = request.getRequestDispatcher("/order/listorder.jsp");
		rd.forward(request, response);
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
