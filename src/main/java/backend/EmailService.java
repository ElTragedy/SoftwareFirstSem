package backend;

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
    private Properties props;
    public EmailService(String fromEmail, String password) {
        this.fromEmail = fromEmail;
        this.password = password;

        // set properties
        props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(String toEmail, String subject, String body) {
        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        fromEmail, password);
            }
        });
        session.setDebug(false);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String to = System.getenv("EMAIL");

        EmailService emailService = new EmailService(System.getenv("EMAIL"), System.getenv("PASSWORD"));

        emailService.send(to, "Subject", "Body");

    }
}

