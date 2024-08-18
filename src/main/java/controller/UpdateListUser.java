package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.user;
import model.dao.UserDAO;
import utils.ReadExcelNhanVien;
import utils.WriteExcelNhanVien;


public class UpdateListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static Random rand = new Random();
    
    public UpdateListUser() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String excelFilePath = "C:/demo/Nhanvien.xlsx";
		ReadExcelNhanVien readFile = new ReadExcelNhanVien();
		List<user> listNV = readFile.readExcel(excelFilePath);
		
		ArrayList<user> listNVDB = userDAO.getListEmployee(1);
		String forgetPass = RegisterController.randomAlphaNumeric(8);
		String pass = "123456";
		for(int i = 0; i<listNV.size();i++) {
			if(listNV.get(i)!=null) {
				boolean ktr = false;
				for(user item1 : listNVDB) {
					if(!listNV.get(i).getEmail().equals(item1.getEmail())) {
						ktr = true;
						
					}else {
						ktr = false;
						break;
					}
				}
				if(ktr) {
					listNV.get(i).setActive(false);
					listNV.get(i).setRole(1);
					listNV.get(i).setPass(pass);
					listNV.get(i).setForget(forgetPass);
					if(userDAO.addItem(listNV.get(i)) > 0) {
					}
					
				}
			}else {
				continue;
			}
		}
		
		ArrayList<user> listNVDBud = userDAO.getListEmployee(1);
		for(user item1 : listNVDBud) {
			boolean ktr = false;
			
			for(int i = 0; i<=listNV.size()-1;i++) {
				
				if(!item1.getEmail().equals(listNV.get(i).getEmail())) {
					ktr = true;
				}else {
					ktr = false;
					break;
				}
			}
			if(ktr) {
				userDAO.delItem(item1);
				
			}
		}
		
		ArrayList<user> listNVDBnew = userDAO.getListEmployee(1);
		WriteExcelNhanVien excelNhanVien = new WriteExcelNhanVien();
		excelNhanVien.writeExcel(listNVDBnew, excelFilePath);
		
		response.sendRedirect(request.getContextPath() + "/info?msg=1");
		return;
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
