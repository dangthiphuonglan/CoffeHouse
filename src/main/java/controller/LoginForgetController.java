package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.user;
import model.dao.UserDAO;

public class LoginForgetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO;
    public LoginForgetController() {
        super();
        userDAO = new UserDAO();
    }
    
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static Random rand = new Random();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("Email");
		String code = request.getParameter("Code");
		
		String forgetPass = RegisterController.randomAlphaNumeric(8);
		
		ArrayList<user> list = userDAO.getListUser();
		HttpSession session = request.getSession();
		for(user item : list) {
			if(email.equals(item.getEmail()) && code.equals(item.getForget())) {
				int result = userDAO.updateItem(item, forgetPass);
				System.out.println(result);
				session.setAttribute("user", item);
				response.sendRedirect(request.getContextPath()+"/index");
				return;
			}
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
