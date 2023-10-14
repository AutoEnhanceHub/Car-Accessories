package carAccessories;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
    ArrayList<User>users;
    int roles;
    boolean isLogged;
    Mailing m;
    int verificationCode;
    User u;
    boolean validEmail;
    Login(User u){
        this.u=u;
        users=new ArrayList<User>();
        User u1=new User("ibrahim.sadi.asad@gmail.com","123456","Admin");
        User u2=new User("ibrahimeceasad@gmail.com","654321","Customer");
        User u3=new User("i.a.s.assad33@gmail.com","987654","Installer");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        isLogged=false;
    }
    public boolean login() {

        if (emailValidator(u.getEmail())) {

            for (User s : users) {
                if (u.getPassword().equals(s.getPassword()) && u.getEmail().equalsIgnoreCase(s.getEmail())) {
                    m = new Mailing(u.getEmail());
                    setValidEmail(true);
                    return true;
                }
            }
        }
            setValidEmail(false);
            return false;
    }

    public  boolean emailValidator(String Email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }

    public void setValidEmail(boolean validEmail) {
        this.validEmail = validEmail;
    }

    public void confirmLogin(int verificationCode){
        this.verificationCode=verificationCode;
        if(validEmail){
        if(m.verificationCode==this.verificationCode){
            setLogged(true);
            role(u);
        }
        }
    }
    public void role(User u){
        String type=u.getType();
        if (type.equalsIgnoreCase("Admin")){
            roles=0;
        }
        if(type.equalsIgnoreCase("Customer")){
            roles=1;
        }
        if(type.equalsIgnoreCase("Installer")){
            roles=2;
        }
        else {
            roles=-1;
        }
    }

    public int getRoles() {
        return roles;
    }

    public User getU() {
        return u;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public boolean isLogged() {
        return isLogged;
    }
    public void resetPassword(String newPassword){
        m=new Mailing(u.getEmail());
        if(verificationCode== m.getVerificationCode()){
            u.setPassword(newPassword);
        }
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean addUser(User u){
        if(emailValidator(u.getEmail())){
            users.add(u);
            return true;
        }
        return false;
    }

    public boolean updateUser(User oldUser,User newUser){
        boolean isUpdating=false;
        if(emailValidator(newUser.getEmail())){
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setType(newUser.getType());
            isUpdating=true;
        }
        return isUpdating;
    }

    public boolean deleteUser(User u){
     for(User r : users){
         if(r.getEmail().equals(u.getEmail())&&r.getPassword().equals(u.getPassword())){
             users.remove(r);
             return true;
         }
     }
     return false;
    }
}
