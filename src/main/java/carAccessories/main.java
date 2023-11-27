package carAccessories;

import javax.mail.Session;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.*;

public class main {
    private static final String nojom="********************************************************************";
    private static final Logger LOGGER = Logger.getLogger(main.class.getName());

    static {
        // Disable JavaMail debug logs
        System.setProperty("mail.debug", "false");

        // Suppress console logging for the root logger
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(Level.OFF);
            }
        }
    }


    public static void main(String[] arg) {

        Application signInApplication = new Application();
        LOGGER.setUseParentHandlers(false);

        Handler[] handlers = LOGGER.getHandlers();
        for (Handler handler : handlers) {
            LOGGER.removeHandler(handler);

        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
public synchronized String format(java.util.logging.LogRecord record) {
    return record.getMessage() + "\n";
}
        });
        LOGGER.addHandler(consoleHandler);

        Scanner scanner = new Scanner(System.in);

        LOGGER.info("TURBOTWEAK ACCESSORIES");

        int Authen=-1;

        do {


            try {
                LOGGER.info("1-Sign-up \n2-Sign-in \n3-Exit");
                Authen = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                LOGGER.warning("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            switch (Authen) {
                case 1:
                    LOGGER.info("Enter your email: ");
                    String email = scanner.nextLine();

                    LOGGER.info("Enter your password: ");
                    String password = scanner.nextLine();

                    LOGGER.info("Enter your type: ");
                    String type = scanner.nextLine();
                    if (signInApplication.login.emailValidator(email)) {
                        signInApplication.login.addUser(new User(email,password,type));
                        LOGGER.info("User Created Successfully");
                    } else {
                        LOGGER.info("Invalid information! Please try again.");
                    }
                    break;

                case 2:
                    LOGGER.info("Enter your email: ");
                    String signInEmail = scanner.nextLine();

                    LOGGER.info("Enter your password: ");
                   String  signInPassword = scanner.nextLine();

signInApplication.login.setUser(new User(signInEmail,signInPassword,""));
                    signInApplication.setuser(signInEmail,signInPassword,"");
                    if (signInApplication.login.login()) {
                        LOGGER.info("Enter your verificationCode: ");
                        int verificationCode = scanner.nextInt();
                        if (signInApplication.login.confirmLogin(verificationCode)) {

                            signInApplication.login.setRoles();

                            if (signInApplication.login.getRoles()==0) {
                                LOGGER.info("WELCOME TO TURBOTWEAK ACCESSORIE Admin");
                                signInApplication.setuser(signInEmail,signInPassword,"Admin");
                                adminDashboard(scanner,signInApplication);

                            }
                            else if (signInApplication.login.getRoles()==1){
                                signInApplication.setuser(signInEmail,signInPassword,"Customer");
                                LOGGER.info("WELCOME TO TURBOTWEAK ACCESSORIE Customer");

                            int select;
                            while (true){
                                LOGGER.info("Choose an option:\n1.Review And Rate A Product\n2.exit");
                                select= scanner.nextInt();
                                if(select==1){
                                    signInApplication.newrate();
                                } else if (select==2) {
                                    break;
                                }else {
                                    LOGGER.info("Choose a right option\n");
                                }
                            }

                            }
                            else { signInApplication.setuser(signInEmail,signInPassword,"Installer");
                                LOGGER.info("WELCOME TO TURBOTWEAK ACCESSORIE Installer");

                                int select;
                                while (true){ LOGGER.info("Choose an option:\n1.Install a Product to request\n2.exit");
                                    select= scanner.nextInt();
                                    if(select==1){
                                        signInApplication.installproduct();
                                    } else if (select==2) {
                                        break;
                                    }else {
                                        LOGGER.info("Choose a right option\n");
                                    }
                                }
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
        int adminChoice=-1;

        do {

            try {
                LOGGER.info("Admin Dashboard");
                LOGGER.info("1-Show all Users\n2-Add User\n3-Delete User\n4-Update User\n5-Main Menu\n6-Sign out");
                adminChoice = adminScanner.nextInt();
                adminScanner.nextLine();
            } catch (InputMismatchException e) {
                LOGGER.warning("Invalid input. Please enter a valid integer.");
                adminScanner.nextLine();
                continue;
            }

            switch (adminChoice) {
                case 1:
                    for (User user : application.login.users) {
                        LOGGER.info("Email: " + user.getEmail());
                        LOGGER.info("Password: " + user.getPassword());
                        LOGGER.info("Type: " + user.getType());
                        LOGGER.info("------------------------------------");
                    }
                    LOGGER.info(nojom);

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
                    LOGGER.info(nojom);
                    break;
                case 3:
                    LOGGER.info("Enter user email that need to Delete: ");
                    String email = adminScanner.nextLine();
                    LOGGER.info("Enter user password that need to Delete: ");
                    String password = adminScanner.nextLine();
                    LOGGER.info("Enter your password to confirm deletion: ");
                    String adminPassword = adminScanner.nextLine();

                    if(application.newUser.getPassword().equals(adminPassword)){
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

                    LOGGER.info(nojom);

                    break;
                case 4:
                    LOGGER.info("Enter user email that need to Update: ");
                    String oldEmail = adminScanner.nextLine();
                    LOGGER.info("Enter user password that need to Update: ");
                    String oldPassword = adminScanner.nextLine();
                    LOGGER.info("Enter your password to confirm Updating: ");
                    String adminPassword4 = adminScanner.nextLine();
                    String oldType="";

                    if(application.newUser.getPassword().equals(adminPassword4)) {
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
            newPassword = oldPassword;
        }
        if(newType.equals("-1")){
            newType = oldType;
        }
        if(newEmail.equals("-1")){
            newEmail = oldEmail;
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
                    LOGGER.info(nojom);
                    break;
                case 5:
                    String y1y="Choose an option:\n1.Add New Category\n2.Edit a Category\n3.Delete a Category\n4.Add New Product\n5.Edit a Product\n6.Delete a Product\n7.Get a Report\n8.Show average ratings and reviews\n9.exit";
                    LOGGER.info(y1y);
                    int ans=adminScanner.nextInt();
                    switch (ans){
                        case 1:application.newCatogry();break;
                        case 2:application.editCategory();break;
                        case 3:application.deleteCategory();break;
                        case 4:application.newproduct();break;
                        case 5:application.editproduct();break;
                        case 6:application.deleteproduct();break;
                        case 7:application.makereport();break;
                        case 8:application.showreviews();break;
                        case 9:LOGGER.info("Invalid input");break;
                        default:break;
                    }
                    LOGGER.info(nojom);
                    break;
                case 6:
                    LOGGER.info("Sign Out");
                    LOGGER.info(nojom);
                    break;
                default:
                    LOGGER.info("Invalid choice! Please try again.");
                    LOGGER.info(nojom);
            }
        } while (adminChoice != 6);
    }
}
