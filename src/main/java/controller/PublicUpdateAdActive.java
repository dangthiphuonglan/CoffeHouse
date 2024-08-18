package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.thongtindonhang;
import model.dao.ThongTinDonHangDAO;

public class PublicUpdateAdActive extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ThongTinDonHangDAO thongTinDonHangDAO;
    public PublicUpdateAdActive() {
        super();
        thongTinDonHangDAO = new ThongTinDonHangDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int iddh = 0;
		try {
			iddh = Integer.parseInt(request.getParameter("iddh"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		thongtindonhang itemUpdate = thongTinDonHangDAO.getItemById(iddh);
		
		itemUpdate.setTrangthai(1);
		
		if(thongTinDonHangDAO.updateItem(itemUpdate) > 0) {
			response.sendRedirect(request.getContextPath() + "/order/list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
