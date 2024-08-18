package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.menu;
import model.dao.MenuDAO;
import utils.AuthUtil;



public class DelMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;
    public DelMenuController() {
        super();
        menuDAO= new MenuDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("menuid"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?err=1");
			return;
		}
		
		menu item = menuDAO.getItem(id);
		if(item == null) {
			
		}
		
		if(menuDAO.delItem(id) > 0) {
			//xÃ³a áº£nh
			final String dirPathName = request.getServletContext().getRealPath("/file");
			String filePathName = dirPathName + File.separator + item.getPicture();
			//System.out.println(filePathName);
			File file = new File(filePathName);
			
			if(file.exists()) {
				file.delete();
			}
			request.setAttribute("msg", "2");
			RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
			rd.forward(request, response);
			return;
		}else {
			request.setAttribute("err", "Xóa thất bại");
			RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
