package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.categories;
import model.bean.menu;
import model.dao.CategoriesDAO;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class AddCatConroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoriesDAO categoriesDAO;
    public AddCatConroller() {
        super();
        categoriesDAO = new CategoriesDAO();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/categories/addcat.jsp");
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
		
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");

		Part filePart = request.getPart("picture");
		
		final String dirPathName = request.getServletContext().getRealPath("/file");
		File dirFile = new File(dirPathName);
		System.out.println(dirFile);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}

		String filename = FileUtil.getName(filePart);

		String picture = FileUtil.rename(filename);

		String filePathName = dirPathName + File.separator + picture;
		
		categories item = new categories(0, name, picture, detail);
		
		if(categoriesDAO.additem(item) > 0) {
			if(!filename.isEmpty()) {
				filePart.write(filePathName);
			}	
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
	}

}
