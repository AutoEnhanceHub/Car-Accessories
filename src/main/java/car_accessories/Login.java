package car_accessories;

import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.logging.*;
public class Login {
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    private static final String ADMIN_ROLE = "Admin";

    protected List<User> users = new ArrayList<>();
    private int roles;
    private boolean isLogged;
    private Mailing m;
    private int verificationCode;
    private User u;
    private boolean validEmail;
    private int userIndex;

    public Login(User u) {
        this.u = u;
        initializeLogger();

        initializeDefaultUsers();
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

    private void initializeDefaultUsers() {
        User u1 = new User("ibrahim.sadi.asad@gmail.com", "123456", ADMIN_ROLE);
        User u2 = new User("ibrahimeceasad@gmail.com", "654321", "Customer");
        User u3 = new User("i.a.s.assad33@gmail.com", "987654", "Installer");
        User u4 = new User("abdallahdaher785@gmail.com", "abdallah@123", ADMIN_ROLE);

        users.addAll(List.of(u1, u2, u3, u4));
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
            LOGGER.info("Not a valid Email");
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
        String type = users.get(userIndex).getType();
        roles = type.equalsIgnoreCase(ADMIN_ROLE) ? 0 : type.equalsIgnoreCase("Customer") ? 1 :
                type.equalsIgnoreCase("Installer") ? 2 : -1;
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
