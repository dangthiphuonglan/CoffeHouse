package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import model.bean.Email;

public class EmailUtil {
	public static void send(Email email) throws Exception {
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        
        Session session = Session.getInstance(props, new Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(email.getFrom(), email.getFrommPassword());
        	}
		});
        
       try {
    	   javax.mail.Message message =  new MimeMessage(session);
    	   
    	   message.setFrom(new InternetAddress(email.getFrom()));
    	   message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
    	   message.setSubject(email.getSubject());
    	   message.setContent(email.getContent(),"text/html ; charset=utf-8");
    	   
    	   Transport.send(message);
    	   
       }catch(Exception e) {
    	   e.printStackTrace();
    	   
    	   throw e;
       }
	}
}
