package controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Email;
import model.bean.user;
import model.dao.UserDAO;
import utils.EmailUtil;

public class ForgetPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO;
    public ForgetPassController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/info/forget.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("Email");
		user user = userDAO.findUserByEmail(email);
		if(user!=null) {
		      HttpSession sHttpSession = request.getSession();     
		      if(email=="") {
		    	  return;
		      }
		      else {
		    	  String to = email;
		    	  Properties properties = new Properties();
		    	  properties.setProperty("mail.smtp.auth","true");
		    	  properties.setProperty("mail.smtp.starttls.enable", "true");
		    	  properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		    	  properties.setProperty("mail.smtp.port", "587");
		    	  Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
		    		  protected PasswordAuthentication getPasswordAuthentication() {
		    			  String username="tranphunguyenle30@gmail.com";
		    			  String password="lgrtvmtbinofaqds";
		    			  return new PasswordAuthentication(username, password);	    			  
		    		  }  
		    	  });
		    	 try {
		    		 MimeMessage message = new MimeMessage(session);
			    	  message.setFrom(new InternetAddress(email));
					  message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
					  message.setSubject("Forget Password");
					  message.setText("Code:" + user.getForget());
					  //send mess
					  Transport.send(message);
					  System.out.println("send succesfull!!!");
					  request.setAttribute("msg", "Nhập mã!");
						RequestDispatcher rd = request.getRequestDispatcher("/info/LoginForget.jsp");
						rd.forward(request, response);
						System.out.println("OK");
						return;
					 
				} catch (Exception e) {
					// TODO: handle exception
				}
	   	 
		      }
		}else {
			response.sendRedirect(request.getContextPath() +"/forget?err=1");
			return;
		}
	}

}
