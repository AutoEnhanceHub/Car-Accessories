package car_accessories;

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

public static boolean emailValidator(String email) {
     if (email != null)
    {
        Pattern p = Pattern.compile("^[A-Za-z].*?@gmail\\.com$");
        Matcher m = p.matcher(email);
        return m.find();
    }
    return false;
}



}
