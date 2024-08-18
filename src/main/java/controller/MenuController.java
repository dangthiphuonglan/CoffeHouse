package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.menu;
import model.dao.MenuDAO;
import utils.AuthUtil;

public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;
    public MenuController() {
        super();
        menuDAO = new MenuDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int cid = 0;
		if(
			request.getParameter("cid") != null
		) {
			try {
				cid = Integer.parseInt(request.getParameter("cid"));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		ArrayList<menu> listmenu = new ArrayList<menu>();
		
		if(cid > 0) {
			listmenu=menuDAO.getListMenu(cid);
		}
		
		int err = 0;
		if(listmenu != null && listmenu.size()>0) {
			err = 1;
		}else {
			err = 0;
		}
		
		request.setAttribute("cid", cid);
		request.setAttribute("err", err);
		request.setAttribute("listmenu", listmenu);
		RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String timkiem = request.getParameter("menuname");
		String key = ".*" + timkiem + ".*";
		ArrayList<menu> list = menuDAO.getListMenu();
		
		ArrayList<menu> listtk = new ArrayList<menu>();
		
		for(menu item: list) {
			if(item.getName().toLowerCase().matches(key.toLowerCase())) {
				listtk.add(item);
			}
		}
		
		request.setAttribute("listtk", listtk);
		RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
		rd.forward(request, response);

	}

}
