package car_accessories;

import java.security.SecureRandom;
import java.util.*;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailing {

    String to;
    String from;
    SecureRandom random;
    int verificationCode;
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());


    Mailing(String to){
        this.to = to;
        from="accessoriescar378@gmail.com";
        random = new SecureRandom();
        verificationCode = 10000 + random.nextInt(90000);

        try {
            LOGGER.setUseParentHandlers(false);

            Handler[] handlers = LOGGER.getHandlers();
            for (Handler handler : handlers) {
                LOGGER.removeHandler(handler);
            }

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(java.util.logging.LogRecord logRecord) {
                    return logRecord.getMessage() + "\n";
                }
            });

            LOGGER.addHandler(consoleHandler);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred during logger configuration", e);
        }

    }



    private void sending(String subject, String text){
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
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        }
        catch (MessagingException ppp) {
            LOGGER.log(Level.SEVERE, "An error occurred in the messaging system: " + ppp.getMessage(), ppp);
        }

    }

    public void sendVerificationCode(){
      String subject = "This is your verification code for car-accessories";
      String text = "Your code is "+verificationCode +"\n"+"Please don't share this code with anyone";
     sending(subject,text);
      
    }

    public void sendEmail(String subject,String text) {
       sending(subject,text);
    }
}
