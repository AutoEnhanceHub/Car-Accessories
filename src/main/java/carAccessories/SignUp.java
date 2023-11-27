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

public static boolean emailValidator(String email) {
    String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
            "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}


}
