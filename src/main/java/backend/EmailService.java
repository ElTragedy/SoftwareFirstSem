package backend;

import com.sun.mail.util.MailLogger;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    private String fromEmail;
    private String password;
    private String username;
    private Properties props;
    public EmailService(String fromEmail, String password) {
        this.fromEmail = fromEmail;
        this.password = password;

        // set properties
        props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(String toEmail) {
        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        fromEmail, password);
            }
        });
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("This is the email subject");
            message.setText("This is the email body");

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String to = "";
        String from = "";
        String password = "";

        EmailService emailService = new EmailService(from, password);

        emailService.send(to);

    }
}

