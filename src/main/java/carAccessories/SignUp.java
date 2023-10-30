package carAccessories;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp {
    User newUser;
    Login l;
    public SignUp(User newUser,Login l) {
        this.newUser = newUser;
        this.l=l;
    }

    public boolean createAccount(){
        if(emailValidator(newUser.getEmail())) {
            l.users.add(newUser);
            return true;
        }
        return false;
    }

    public static boolean emailValidator(String Email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }

}
