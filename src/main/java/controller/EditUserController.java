package controller;

import java.io.IOException;
import java.util.List;

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
import utils.WriteExcelNhanVien;

public class EditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private WriteExcelNhanVien writeExcelNhanVien;
    public EditUserController() {
        super();
        userDAO = new UserDAO();
        writeExcelNhanVien = new WriteExcelNhanVien();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		user item = userDAO.getUserById(id);
		
		request.setAttribute("userEdit", item);
		
		RequestDispatcher rd = request.getRequestDispatcher("/info/editInfo.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String excelFilePath = "C:/demo/Nhanvien.xlsx";
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			System.out.println("Lỗi!!!");
		}
		
		user item = userDAO.getUserById(id);
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");

		
		item.setName(name);

		item.setPass(pass);

		
		
		if(userDAO.editItem(item) > 0) {
			List<user> listNV = userDAO.getListEmployee(1);
			writeExcelNhanVien.writeExcel(listNV, excelFilePath);
			response.sendRedirect(request.getContextPath()+"/info");
		}else {
			System.out.println("Lỗi");
		}
		
	}
	
}
