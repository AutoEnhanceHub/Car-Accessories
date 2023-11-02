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
            session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,false));
            message.setSubject("This is your verification code for car-accessories");
            message.setText("Your code is "+verificationCode+ "\nPlease don't share this code with anyone");
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
    public void sendEmail(String subject,String message1) {
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
            message.setSubject(subject);
            message.setText(message1);
            Transport.send(message);
        }
        catch (MessagingException m){
            m.printStackTrace();
        }
    }


}
