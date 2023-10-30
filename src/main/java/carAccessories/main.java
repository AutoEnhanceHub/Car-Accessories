package carAccessories;

import java.util.Scanner;
import java.util.logging.*;

public class main {
    private static final Logger LOGGER = Logger.getLogger(main.class.getName());

    public static void main(String[] arg) {
        LOGGER.setUseParentHandlers(false);

        Handler[] handlers = LOGGER.getHandlers();
        for (Handler handler : handlers) {
            LOGGER.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(java.util.logging.LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        LOGGER.addHandler(consoleHandler);

        Scanner scanner = new Scanner(System.in);

        LOGGER.info("TURBOTWEAK ACCESSORIES");

        int Authen;

        do {
            LOGGER.info("1-Sign-up \n2-Sign-in \n3-Exit");
            Authen = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (Authen) {
                case 1:
                    LOGGER.info("Enter your email: ");
                    String email = scanner.nextLine();

                    LOGGER.info("Enter your password: ");
                    String password = scanner.nextLine();

                    LOGGER.info("Enter your type: ");
                    String type = scanner.nextLine();

                    User user = new User(email, password, type);
                    Application application = new Application(user);
                    application.SignUp();
                    if (application.signUp.createAccount()) {
                        application.login.users.add(user);
                        LOGGER.info("User Created Successfully");
                    } else {
                        LOGGER.info("Invalid information! Please try again.");
                    }
                    break;

                case 2:
                    LOGGER.info("Enter your email: ");
                    String signInEmail = scanner.nextLine();

                    LOGGER.info("Enter your password: ");
                    String signInPassword = scanner.nextLine();

                    Application signInApplication = new Application(signInEmail, signInPassword);

                    if (signInApplication.login.login()) {
                        LOGGER.info("Enter your verificationCode: ");
                        int verificationCode = scanner.nextInt();
                        if (signInApplication.login.confirmLogin(verificationCode)) {
                            LOGGER.info("WELCOME TO TURBOTWEAK ACCESSORIES");
                            signInApplication.login.setRoles();

                            if (signInApplication.login.getRoles()==0) {
                                adminDashboard(scanner,signInApplication);

                            }
                            else{
                                LOGGER.info("Main menu");
                                //here you can call your method to main dashboard
                            }
                        } else {
                            LOGGER.info("Invalid information! Please try again.");
                        }
                    } else {
                        LOGGER.info("Invalid information! Please try again.");
                    }
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    LOGGER.info("Invalid choice! Please try again.");
            }
        } while (Authen != 3);




        scanner.close();
    }

    private static void adminDashboard(Scanner adminScanner,Application application) {
        int adminChoice;

        do {
            LOGGER.info("Admin Dashboard");
            LOGGER.info("1-Show all Users\n2-Add User\n3-Delete User\n4-Update User\n5-Sign out");
            adminChoice = adminScanner.nextInt();
            adminScanner.nextLine();

            switch (adminChoice) {
                case 1:
                    for (User user : application.login.users) {
                        LOGGER.info("Email: " + user.getEmail());
                        LOGGER.info("Password: " + user.getPassword());
                        LOGGER.info("Type: " + user.getType());
                        LOGGER.info("------------------------------------");
                    }
                    LOGGER.info("********************************************************************");

                    break;
                case 2:
                    LOGGER.info("Enter user email: ");
                    String email1 = adminScanner.nextLine();

                    LOGGER.info("Enter user password: ");
                    String password1 = adminScanner.nextLine();

                    LOGGER.info("Enter user type: ");
                    String type = adminScanner.nextLine();

                    User user = new User(email1, password1, type);
                    if (application.login.addUser(user)) {
                        LOGGER.info("User Added Successfully");
                    } else {
                        LOGGER.info("Invalid information! Please try again.");
                    }
                    LOGGER.info("********************************************************************");
                    break;
                case 3:
                    LOGGER.info("Enter user email that need to Delete: ");
                    String email = adminScanner.nextLine();
                    LOGGER.info("Enter user password that need to Delete: ");
                    String password = adminScanner.nextLine();
                    LOGGER.info("Enter your password to confirm deletion: ");
                    String adminPassword = adminScanner.nextLine();

                    if(application.user.getPassword().equals(adminPassword)){
                        if(application.login.deleteUser(new User(email,password))){
                            LOGGER.info("User Deleted Successfully");
                        }
                        else {
                            LOGGER.info("User Delete Failed");
                        }
                    }
                    else {
                        LOGGER.info("Your PassWord Invalid! Please Try Again!");
                    }

                    LOGGER.info("********************************************************************");

                    break;
                case 4:
                    LOGGER.info("Enter user email that need to Update: ");
                    String oldEmail = adminScanner.nextLine();
                    LOGGER.info("Enter user password that need to Update: ");
                    String oldPassword = adminScanner.nextLine();
                    LOGGER.info("Enter your password to confirm Updating: ");
                    String adminPassword4 = adminScanner.nextLine();
                    String oldType="";

                    if(application.user.getPassword().equals(adminPassword4)) {
                        for (User s : application.login.users) {
                            if (oldPassword.equals(s.getPassword()) && oldEmail.equalsIgnoreCase(s.getEmail())) {
                                int userIndex = application.login.users.indexOf(s);
                                 oldType = s.getType();
                            }
                        }
                        LOGGER.warning("If you wont to Update value just insert -1 ");
                        LOGGER.info("Enter user new email that need to Update: ");
                        String newEmail = adminScanner.nextLine();
                        LOGGER.info("Enter user new password that need to Update: ");
                        String newPassword = adminScanner.nextLine();
                        LOGGER.info("Enter user new type that need to Update: ");
                        String newType = adminScanner.nextLine();

                        if(newPassword.equals("-1")){
                            newPassword=oldPassword;
                        }
                        if(newType.equals("-1")){
                            newType=oldType;
                        }
                        if(newEmail.equals("-1")){
                            newEmail=oldEmail;
                        }

                        if(application.login.updateUser(new User(oldEmail,oldPassword,oldType),new User(newEmail,newPassword,newType))){
                            LOGGER.info("User Updating Successfully");
                        }
                        else {
                            LOGGER.info("User Updating Failed");
                        }
                    }
                    else{
                            LOGGER.info("Your PassWord Invalid! Please Try Again!");
                        }
                    LOGGER.info("********************************************************************");
                    break;

                case 5:
                    LOGGER.info("Sign Out");
                    LOGGER.info("********************************************************************");
                    break;
                default:
                    LOGGER.info("Invalid choice! Please try again.");
                    LOGGER.info("********************************************************************");
            }
        } while (adminChoice != 5);
    }
}
