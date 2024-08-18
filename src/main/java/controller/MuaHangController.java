package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.chitietdonhang;
import model.bean.menu;
import model.bean.thanhtoan;
import model.bean.thongtindonhang;
import model.bean.user;
import model.dao.ChiTietDonHangDAO;
import model.dao.GioHangDAO;
import model.dao.MenuDAO;
import model.dao.ThongTinDonHangDAO;
import utils.WriteExcelHoaDon;

public class MuaHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private MenuDAO menuDAO;
    private ThongTinDonHangDAO thongTinDonHangDAO;
    private ChiTietDonHangDAO chiTietDonHangDAO;
    private GioHangDAO gioHangDAO;
    private static Random rand = new Random();
    public MuaHangController() {
        super();
        menuDAO = new MenuDAO();
        thongTinDonHangDAO = new ThongTinDonHangDAO();
        chiTietDonHangDAO = new ChiTietDonHangDAO();
        gioHangDAO = new GioHangDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idsp = 0;
		try {
			idsp = Integer.parseInt(request.getParameter("idsp"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		menu itemDH = menuDAO.getItem(idsp);
		
		request.setAttribute("itemDH", itemDH);
		RequestDispatcher rd = request.getRequestDispatcher("/public/dathang.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String[] tensp = request.getParameterValues("name");
		
		int[] chiphi = new int[1000];
		for(int i = 0; i<tensp.length;i++) {
			menu it = menuDAO.getItem(tensp[i]);
			chiphi[i] = it.getPrice();
		}
		String[] soluong = request.getParameterValues("soluong");

		HttpSession httpSession = request.getSession();
        @SuppressWarnings("unchecked")
		ArrayList<menu> listspMua = ( ArrayList<menu>) httpSession.getAttribute("listspMua");
        
        int i = 0;
		for(menu itsp : listspMua) {
			long millis=System.currentTimeMillis();
			java.sql.Date ngaymua = new java.sql.Date(millis);
			String madh = MuaHangController.randomAlphaNumeric(5);
			HttpSession session = request.getSession();
			user itemLogin = (user) session.getAttribute("user");
			
			int id_nguoimua = 0;
			if(itemLogin != null) {
				id_nguoimua = itemLogin.getId();
			}
			
			String diachi = request.getParameter("dc");
			int trangthaihoatdong = 0;
			String tenkh = request.getParameter("tenkh");
			String sdt =  request.getParameter("sdt");
			String email =  request.getParameter("email");
			
			

			int sl = Integer.parseInt(soluong[i]);
			int cp = chiphi[i];
			int dongia = sl*cp;
			
			thongtindonhang itemdonhang = new thongtindonhang(0, ngaymua, madh, id_nguoimua, diachi, trangthaihoatdong, tenkh, sdt, email);
			
			if(thongTinDonHangDAO.addItemDH(itemdonhang) > 0) {
				thongtindonhang itemnew = thongTinDonHangDAO.getItemnew();
				chitietdonhang itemChiTiet = new chitietdonhang(0, itemnew.getId(), itsp.getId(), sl, dongia);
				if(chiTietDonHangDAO.addItemChiTiet(itemChiTiet) > 0) {
					String excelFilePath = "C:/demo/dathang.xlsx";
					WriteExcelHoaDon example = new WriteExcelHoaDon();
					ArrayList<chitietdonhang> listdh = chiTietDonHangDAO.getListDH();
					example.writeExcel(listdh, excelFilePath);
					if(gioHangDAO.delItem(itsp.getId())>0){
						i++;
						System.out.println("Xóa thành công");
						
					}
				}
			}else {
				System.out.println("Thêm ko dc");
				i++;
			}
			
		}
		
		response.sendRedirect(request.getContextPath()+"/order/list?msg=1");
		return;
        
        
		
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
