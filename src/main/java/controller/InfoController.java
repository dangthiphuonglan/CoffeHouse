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

import model.bean.user;
import model.dao.UserDAO;
import utils.AuthUtil;
public class InfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO;
    public InfoController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		user userLogin  = (user)session.getAttribute("user");
		
		String msg = "";
		if(request.getParameter("msg") != null) {
			msg = (String)request.getParameter("msg");
		}
		if(userLogin.isRole() == 0) {
			ArrayList<user> listuser = userDAO.getListEmployee(1);
			listuser.add(userLogin);
			request.setAttribute("userList", listuser);
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/info.jsp");
			rd.forward(request, response);
			return;
		} else {
			if(userLogin.isRole() == 1) {
				ArrayList<user> listuser = userDAO.getListEmployee(2);
				listuser.add(userLogin);
				request.setAttribute("userList", listuser);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("/info.jsp");
				rd.forward(request, response);
				return;
			}else {
				request.setAttribute("userLogin", userLogin);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("/info.jsp");
				rd.forward(request, response);
				return;
			}
			
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String timkiem = request.getParameter("searchinfo");
		String key = ".*" + timkiem + ".*";
		
		ArrayList<user> list  = new ArrayList<user>();
		
		HttpSession session = request.getSession();
		user userLogin  = (user)session.getAttribute("user");
		if(userLogin.isRole() == 0) {
			list = userDAO.getListEmployee(1);
		}else {
			if(userLogin.isRole() == 1) {
				list = userDAO.getListEmployee(2);
			}else {
				list = userDAO.getListEmployee(3);
			}
		}
		
		ArrayList<user> listtk = new ArrayList<user>();
		
		for(user item : list) {
			if(item.getName().toLowerCase().matches(key.toLowerCase())) {
				listtk.add(item);
			}
		}
		
		request.setAttribute("listtk", listtk);
		RequestDispatcher rd = request.getRequestDispatcher("/info.jsp");
		rd.forward(request, response);
	}

}
