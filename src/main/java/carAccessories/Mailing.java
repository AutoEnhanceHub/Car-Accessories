package carAccessories;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailing {

    String to;
    String from;
    Random random;
    int verificationCode;

    Mailing(String to){
        this.to = to;
        from="accessoriescar378@gmail.com";
        random = new Random();
        verificationCode = 10000 + random.nextInt(90000);
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public Random getRandom() {
        return random;
    }

    public void sendVerificationCode(){
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
                @Override
                protected  PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication("accessoriescar378@gmail.com","zxrl yldn nguy xhny");
                }
            });
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,false));
            message.setSubject("This is your verification code for car-accessories");
            message.setText("Your code is "+verificationCode);
            message.setText("Please don't share this code with anyone");
            Transport.send(message);
        }
        catch (MessagingException m){
            m.printStackTrace();
        }
    }
    public void resetPassword(){

        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
                @Override
                protected  PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication("accessoriescar378@gmail.com","zxrl yldn nguy xhny");
                }
            });
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,false));
            message.setSubject("This is your code to reset new password");
            message.setText("Your code is "+verificationCode);
            message.setText("Please don't share this code with anyone");
            Transport.send(message);
        }
        catch (MessagingException m){
            m.printStackTrace();
        }

    }
    public static void sendEmail(String senderEmail, String senderPassword, String recipientEmail, String subject, String messageText) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "your-smtp-server.com"); // Replace with your SMTP server address
        props.put("mail.smtp.port", "587"); // Replace with your SMTP server port
        props.put("mail.smtp.auth", "true"); // Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS - This line here

        // Create a Session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (AuthenticationFailedException e) {
            System.err.println("Authentication failed. Check your email address and password.");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
