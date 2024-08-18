package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

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

import model.bean.Email;
import utils.AuthUtil;
import utils.EmailUtil;

public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String mes = request.getParameter("mes");
		
		final String fromEmail = "tranphunguyenle30@gmail.com";
        // Mat khai email cua ban
        final String password = "lgrtvmtbinofaqds";
     
        final String toEmail = "tranphunguyenle30@gmail.com";
        
        final String body ="mes: "+ mes + ", Email: " + email;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        javax.mail.Session session = javax.mail.Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
	        msg.addHeader("Content-Transfer-Encoding", "8bit");
	        msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
	        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
	        msg.setSubject(subject, "UTF-8");
	        msg.setText(body, "UTF-8");
	        msg.setSentDate(new Date());
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	        Transport.send(msg);
	        
	        response.sendRedirect(request.getContextPath() + "/contact?msg=1");
			return;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/contact?msg=1");
		return;
	}

}
