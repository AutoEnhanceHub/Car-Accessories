package car_accessories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.logging.*;

public class Login {
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    private static final String ADMIN_ROLE = "Admin";
    private static final String CUSTOMER_ROLE = "Customer";
    private static final String INSTALLER_ROLE = "Installer";

    List<User> users = new ArrayList<>();
    int roles;
    boolean isLogged;
    Mailing m;
    int verificationCode;
    User u;
    boolean validEmail;
    int userIndex;

    Login(User u) {
        this.u = u;
        initializeLogger();

        User u1 = new User("ibrahim.sadi.asad@gmail.com", "123456", ADMIN_ROLE);
        User u2 = new User("ibrahimeceasad@gmail.com", "654321", CUSTOMER_ROLE);
        User u3 = new User("i.a.s.assad33@gmail.com", "987654", INSTALLER_ROLE);
        User u4 = new User("abdallahdaher785@gmail.com", "abdallah@123", ADMIN_ROLE);

        users.addAll(Arrays.asList(u1, u2, u3, u4));
        isLogged = false;
    }

    private void initializeLogger() {
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

    public boolean login() {
        if (emailValidator(u.getEmail())) {
            User foundUser = findUserByEmailAndPassword(u.getEmail(), u.getPassword());
            if (foundUser != null) {
                m = new Mailing(u.getEmail());
                setValidEmail(true);
                m.sendVerificationCode();
                userIndex = users.indexOf(foundUser);
                return true;
            }
        }
        setValidEmail(false);
        return false;
    }

    private User findUserByEmailAndPassword(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean emailValidator(String email) {
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    public void setValidEmail(boolean validEmail) {
        this.validEmail = validEmail;
    }

    public boolean confirmLogin(int verificationCode) {
        this.verificationCode = verificationCode;
        if (validEmail && m.verificationCode == this.verificationCode) {
            setLogged(true);
            return true;
        }
        return false;
    }

    public void setRoles() {
        String type=users.get(userIndex).getType();
        if (type.equalsIgnoreCase(admins)){
            roles=0;
        }
        else if (type.equalsIgnoreCase("Customer")){
            roles=1;
        }
         else if(type.equalsIgnoreCase("Installer")){
            roles=2;
        }
        else {
            roles=-1;
        }
    }

    public int getRoles() {
        return roles;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public boolean addUser(User u) {
        if (emailValidator(u.getEmail())) {
            users.add(u);
            return true;
        }
        LOGGER.info("Not a valid Email");
        return false;
    }

    public void setUser(User u) {
        this.u = u;
    }

    public boolean updateUser(User oldUser, User newUser) {
        User foundUser = findUserByEmailAndPassword(oldUser.getEmail(), oldUser.getPassword());
        boolean isUpdating = false;

        if (foundUser != null && emailValidator(newUser.getEmail())) {
            userIndex = users.indexOf(foundUser);
            users.get(userIndex).setType(newUser.getType());
            users.get(userIndex).setPassword(newUser.getPassword());
            users.get(userIndex).setEmail(newUser.getEmail());
            isUpdating = true;
        }
        return isUpdating;
    }

    public boolean deleteUser(User u) {
        for (User r : users) {
            if (r.getEmail().equals(u.getEmail()) && r.getPassword().equals(u.getPassword())) {
                users.remove(r);
                return true;
            }
        }
        return false;
    }
}
