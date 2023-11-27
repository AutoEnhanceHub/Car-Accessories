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

    private void sending(String Subject, String Text){

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
            message.setSubject(Subject);
            message.setText(Text);
            Transport.send(message);
        }
        catch (MessagingException m){
            m.printStackTrace();
        }

        
    }

    public void sendVerificationCode(){
      String Subject = "This is your verification code for car-accessories";
      String Text = "Your code is "+verificationCode +"\n"+"Please don't share this code with anyone";
     sending(Subject,Text);
      
    }
    public void resetPassword(){
        String Subject = "This is your code to reset new password";
        String Text = "Your code is "+verificationCode +"\n"+"Please don't share this code with anyone";
        sending(Subject,Text);

    }
    public void sendEmail(String subject,String Text) {
       sending(Subject,Text);
    }


}
