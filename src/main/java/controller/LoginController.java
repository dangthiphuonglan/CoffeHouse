package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.user;
import model.dao.UserDAO;
import utils.AuthUtil;
import utils.CookiesUtil;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int dem = 0;
	UserDAO userDAO;
    public LoginController() {
        super();
        userDAO = new UserDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/login1.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		if(AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		
		String email = request.getParameter("Email");
		String pass = request.getParameter("Password");
		
		ArrayList<user> listUser = userDAO.getListUser();
		HttpSession session = request.getSession();
		
		for(user item : listUser) {		
			if(email.equals(item.getEmail()) && item.isActive() == true) {
				response.sendRedirect(request.getContextPath() + "/login?err=2");
				return;
			}else {
				if(email.equals(item.getEmail()) && pass.equals(item.getPass())) {
					session.setAttribute("user", item);
					
					response.sendRedirect(request.getContextPath()+"/index");
					return;
				}else {
					if(email.equals(item.getEmail())) {
						dem++;
						if(dem == 3) {
							if(userDAO.updateActive(email) > 0) {
								response.sendRedirect(request.getContextPath() + "/login?err=2");
								return;
							}
						}
					}
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/login?err=1");
		return;
		
			
	}

}
