package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.user;
import model.dao.UserDAO;
import utils.AuthUtil;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static Random rand = new Random();
	
	UserDAO userDAO;
	
    public RegisterController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/dangky.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("Name");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		String forgetPass = RegisterController.randomAlphaNumeric(8);
		boolean active = false;
		int role = 2;
		ArrayList<user> listItems = userDAO.getListUser();
		
		for(user item : listItems) {
			if(email.equals(item.getEmail())) {
				RequestDispatcher rd = request.getRequestDispatcher("/dangky.jsp?err=1");
				rd.forward(request, response);
				return;
			}
		}
		
		user user = new user(0, username, email, password, forgetPass, active,role);
		
		if(userDAO.addItem(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
	}
	
	public static String randomAlphaNumeric(int numberOfCharactor) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfCharactor; i++) {
			int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
			char ch = ALPHA_NUMERIC.charAt(number);
			sb.append(ch);
		}
		return sb.toString();
	}
	 public static int randomNumber(int min, int max) {
	        return rand.nextInt((max - min) + 1) + min;
	    }
}
