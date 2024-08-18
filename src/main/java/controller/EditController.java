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

import model.bean.menu;
import model.dao.CategoriesDAO;
import model.dao.MenuDAO;
import utils.AuthUtil;
import utils.FileUtil;

@MultipartConfig
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MenuDAO menuDAO;
	private CategoriesDAO categoriesDAO;
    public EditController() {
        super();
       menuDAO = new MenuDAO();
       categoriesDAO = new CategoriesDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("menuid"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		menu item = menuDAO.getItem(id);
		request.setAttribute("itemedit", item);
		
		RequestDispatcher rd = request.getRequestDispatcher("/menu/editmenu.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("menuid"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		menu item = menuDAO.getItem(id);
		
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		Part filePart = request.getPart("picture");
		
		final String dirPathName = request.getServletContext().getRealPath("/file");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		//Láº¥y tÃªn file tá»« path
		String filename =FileUtil.getName(filePart);
		//Ä‘á»•i tÃªn file
		String picture = "";
		if(filename.isEmpty()) {
			picture = item.getPicture();
		} else {
			picture = FileUtil.rename(filename);
		}
		 
		//dÆ°á»�ng dáº«n file
		String filePathName = dirPathName + File.separator + picture;
		
		int price  = Integer.parseInt(request.getParameter("price"));
		
		String categories = request.getParameter("categories");
		
		model.bean.categories categories2 = categoriesDAO.getIdByCatName(categories);
		menu itemnew = new menu(id, name, detail, picture, price, categories2.getId());
		
		if(menuDAO.editItem(itemnew) > 0) {	
			if(!filename.isEmpty()) {
				//xÃ³a file cÅ©
				String oldfilePathName = dirPathName  + File.separator + item.getPicture();
				File oldFile = new File(oldfilePathName);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				//ghi file
				filePart.write(filePathName);		
			}
			request.setAttribute("msg", "1");
			RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
			rd.forward(request, response);
		}
	}


}
