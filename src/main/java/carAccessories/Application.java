package carAccessories;

import java.util.ArrayList;

public  class Application {


    User user;
    Login login;

    public Application() {
        user=new User("ibrahim.sadi.asad@gmail.com","123456","Admin");
        login=new Login(user);
    }


}
