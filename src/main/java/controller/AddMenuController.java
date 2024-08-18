package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.menu;
import model.dao.CategoriesDAO;
import model.dao.MenuDAO;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class AddMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriesDAO categoriesDAO;
	private MenuDAO menuDAO;
	
    public AddMenuController() {
        super();
        categoriesDAO = new CategoriesDAO();
        menuDAO = new MenuDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response) == false) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/menu/addmenu.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response) == false) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		String cat = request.getParameter("categories");
		
		Part filePart = request.getPart("picture");
		
		//táº¡o thÆ° má»¥c lÆ°u áº£nh
		final String dirPathName = request.getServletContext().getRealPath("/file");
		File dirFile = new File(dirPathName);
		System.out.println(dirFile);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		String filename = FileUtil.getName(filePart);
		
		String picture = FileUtil.rename(filename);
		
		String filePathName = dirPathName + File.separator + picture;

		int price = Integer.parseInt(request.getParameter("price"));

		model.bean.categories itemcat = categoriesDAO.getIdByCatName(cat);
		
		menu item = new menu(0, name, detail, picture, price, itemcat.getId());
	
		if(menuDAO.additem(item) > 0) {
			if(!filename.isEmpty()) {
				filePart.write(filePathName);
			}	
			response.sendRedirect(request.getContextPath()+"/menu.jsp");
			return;
		}
	}

}
