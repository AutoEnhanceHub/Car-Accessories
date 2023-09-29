package carAccessories;

public  class Application {

    boolean logged_in;

    public Application() {
        this.logged_in = false;
    }

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }
}
