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

import model.bean.menu;
import model.dao.MenuDAO;

public class PublicDatHangNhieuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;
    public PublicDatHangNhieuController() {
        super();
        menuDAO = new MenuDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] id =(request.getParameterValues("idsp"));
		String sl = request.getParameter("sl");
		ArrayList<menu> listspMua = new ArrayList<menu>();
		if(id == null) {
			request.setAttribute("err", 1);
	        RequestDispatcher rd = request.getRequestDispatcher("/order/listorder.jsp");
			rd.forward(request, response);
			return;
		}
        for (String s : id) {
            int idsp = Integer.parseInt(s);
            menu item = menuDAO.getItem(idsp);
            listspMua.add(item);
        }
        
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("listspMua", listspMua);
        
        request.setAttribute("listspMua", listspMua);
        request.setAttribute("sl", sl);
        RequestDispatcher rd = request.getRequestDispatcher("/order/index2.jsp");
		rd.forward(request, response);
	}

}
