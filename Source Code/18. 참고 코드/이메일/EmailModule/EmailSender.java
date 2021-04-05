package EmailModule;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;

public class EmailSender {
    public static void sendHTMLEmail(String smtphost, String smtpport,
            final String serverhostaccount, final String serverhostpwd, String[] toAddressList,
            String subject, String htmlcode) throws AddressException,
            MessagingException {
    	
        // Get system properties
        Properties properties = System.getProperties();
   
        // Setup mail server
        properties.put("mail.smtp.host", smtphost);
        properties.put("mail.smtp.port", smtpport);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // auth check
        Authenticator auth = new Authenticator(){
      	  public PasswordAuthentication getPasswordAuthentication(){
      		  return new PasswordAuthentication(serverhostaccount, serverhostpwd);
      	  }
        };
        
        // Get the auth Session object.
        Session session = Session.getInstance(properties, auth);
   
        try{
           // Create a default MimeMessage object.
           MimeMessage mimeMessage = new MimeMessage(session);
           // Set From: header field of the header.
           mimeMessage.setFrom(new InternetAddress(serverhostaccount));
           // Set To: header field of the header.
           if(toAddressList != null)
           {
	           InternetAddress[] toAddresses = new InternetAddress[toAddressList.length];	// 수신자 리스트
	           for(int i = 0 ; i < toAddressList.length; i ++)
	           {
	           	toAddresses[i] = new InternetAddress(toAddressList[i]);
	           }
	           mimeMessage.addRecipients(Message.RecipientType.TO, toAddresses);
	           // Set Subject: header field
	           mimeMessage.setSubject(subject);
	
	           // Send the actual HTML message, as big as you like
	           mimeMessage.setContent(htmlcode,"text/html; charset=utf-8");
	           // Send message
	           Transport.send(mimeMessage);
           }
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
    }
}
