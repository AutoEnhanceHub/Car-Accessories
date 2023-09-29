package carAccessories;

import java.security.PublicKey;
import java.util.ArrayList;

public  class Application {

    boolean logged_in;

    public Application() {
        this.logged_in = false;
        categories=new ArrayList<Category>();
        categories.add(new Category("Interior"));
        categories.add(new Category("Exterior"));
        categories.add(new Category("Electronics"));

    }

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }
    public static ArrayList<Category> categories;
    public static String email,pass;
}
