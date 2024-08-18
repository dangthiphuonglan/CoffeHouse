package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.user;
import model.dao.UserDAO;
import utils.AuthUtil;
import utils.WriteExcelNhanVien;

public class DelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO;
    WriteExcelNhanVien writeExcelNhanVien;
    public DelUserController() {
        super();
        userDAO = new UserDAO();
        writeExcelNhanVien = new WriteExcelNhanVien();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String excelFilePath = "C:/demo/Nhanvien.xlsx";
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			System.out.println("Lỗi");
		}
		user item = userDAO.getUserById(id);
		
		if(userDAO.delItem(item) > 0) {
			List<user> listNV = userDAO.getListEmployee(1);
			writeExcelNhanVien.writeExcel(listNV, excelFilePath);
			response.sendRedirect(request.getContextPath()+"/info");
		}else {
			System.out.println("lỗi");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
