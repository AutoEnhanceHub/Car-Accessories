package car_accessories;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class MainClass {
    private static final String INVALID_INFORMATION_PLEASE_TRY_AGAIN = "Invalid information! Please try again.";
    private static final String STRING = "********************************************************************";
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

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
            public synchronized String format(java.util.logging.LogRecord rama) {
                return rama.getMessage() + "\n";
            }
        });
        LOGGER.addHandler(consoleHandler);

        Scanner scanner = new Scanner(System.in);

        LOGGER.info("TURBOTWEAK ACCESSORIES");

        int authen = -1;

        do {
            try {
                LOGGER.info("1-Sign-up \n2-Sign-in \n3-Exit");
                authen = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                LOGGER.warning("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            switch (authen) {
                case 1 -> signUp(scanner, signInApplication);
                case 2 -> signIn(scanner, signInApplication);
                case 3 -> System.exit(0);
                default -> LOGGER.info("Invalid choice! Please try again.");
            }
        } while (authen != 3);

        scanner.close();
    }

    private static void signUp(Scanner scanner, Application signInApplication) {
        LOGGER.info("Enter your email: ");
        String email = scanner.nextLine();

        LOGGER.info("Enter your password: ");
        String password = scanner.nextLine();

        LOGGER.info("Enter your type: ");
        String type = scanner.nextLine();

        if (signInApplication.login.emailValidator(email)) {
            signInApplication.login.addUser(new User(email, password, type));
            LOGGER.info("User Created Successfully");
        } else {
            LOGGER.info(INVALID_INFORMATION_PLEASE_TRY_AGAIN);
        }
    }

    private static void signIn(Scanner scanner, Application signInApplication) {
        LOGGER.info("Enter your email: ");
        String signInEmail = scanner.nextLine();

        LOGGER.info("Enter your password: ");
        String signInPassword = scanner.nextLine();

        signInApplication.login.setUser(new User(signInEmail, signInPassword, ""));
        signInApplication.setuser(signInEmail, signInPassword, "");

        if (signInApplication.login.login()) {
            int verificationCode = getVerificationCode(scanner);

            if (signInApplication.login.confirmLogin(verificationCode)) {
                signInApplication.login.setRoles();
                handleRoles(scanner, signInApplication, signInEmail, signInPassword);
            } else {
                LOGGER.info(INVALID_INFORMATION_PLEASE_TRY_AGAIN);
            }
        } else {
            LOGGER.info(INVALID_INFORMATION_PLEASE_TRY_AGAIN);
        }
    }


    private static void handleRoles(Scanner scanner, Application signInApplication, String signInEmail, String signInPassword) {
        int roles = signInApplication.login.getRoles();

        switch (roles) {
            case 0 -> {
                LOGGER.info("WELCOME TO TURBOTWEAK ACCESSORIE Admin");
                signInApplication.setuser(signInEmail, signInPassword, "Admin");
                adminDashboard(scanner, signInApplication);
            }
            case 1 -> {
                signInApplication.setuser(signInEmail, signInPassword, "Customer");
                LOGGER.info("WELCOME TO TURBOTWEAK ACCESSORIE Customer");
                handleCustomerOptions(scanner, signInApplication);
            }
            default -> {
                signInApplication.setuser(signInEmail, signInPassword, "Installer");
                LOGGER.info("WELCOME TO TURBOTWEAK ACCESSORIE Installer");
                handleInstallerOptions(scanner, signInApplication);
            }
        }
    }

    private static void handleCustomerOptions(Scanner scanner, Application signInApplication) {
        int select;

        while (true) {
            LOGGER.info("Choose an option:\n1. Review And Rate A Product\n2. Exit");
            select = scanner.nextInt();

            if (select == 1) {
                signInApplication.newrate();
            } else if (select == 2) {
                break;
            } else {
                LOGGER.info("Choose a right option\n");
            }
        }
    }

    private static void handleInstallerOptions(Scanner scanner, Application signInApplication) {
        int select;

        while (true) {
            LOGGER.info("Choose an option:\n1. Install a Product to request\n2. Exit");
            select = scanner.nextInt();

            if (select == 1) {
                signInApplication.installproduct();
            } else if (select == 2) {
                break;
            } else {
                LOGGER.info("Choose a right option\n");
            }
        }
    }


    private static int getVerificationCode(Scanner scanner) {
        LOGGER.info("Enter your verificationCode: ");
        return scanner.nextInt();
    }

    private static void adminDashboard(Scanner adminScanner, Application application) {
        int adminChoice = -1;

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
                case 1 -> showAllUsers(application);
                case 2 -> addUser(adminScanner, application);
                case 3 -> deleteUser(adminScanner, application);
                case 4 -> updateUser(adminScanner, application);
                case 5 -> mainMenu(application,adminScanner);
                case 6 -> signOut();
                default -> invalidChoice();
            }
        } while (adminChoice != 6);
    }

    private static void showAllUsers(Application application) {
        for (User user : application.login.users) {
            LOGGER.info("Email: " + user.getEmail());
            LOGGER.info("Password: " + user.getPassword());
            LOGGER.info("Type: " + user.getType());
            LOGGER.info("------------------------------------");
        }
        LOGGER.info(STRING);
    }

    private static void addUser(Scanner adminScanner, Application application) {
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
            LOGGER.info(INVALID_INFORMATION_PLEASE_TRY_AGAIN);
        }
        LOGGER.info(STRING);
    }

    private static void deleteUser(Scanner adminScanner, Application application) {
        LOGGER.info("Enter user email that needs to be deleted: ");
        String email = adminScanner.nextLine();
        LOGGER.info("Enter user password that needs to be deleted: ");
        String password = adminScanner.nextLine();
        LOGGER.info("Enter your password to confirm deletion: ");
        String adminPassword = adminScanner.nextLine();
        if (application.newUser.getPassword().equals(adminPassword)) {
            if (application.login.deleteUser(new User(email, password))) {
                LOGGER.info("User Deleted Successfully");
            } else {
                LOGGER.info("User Delete Failed");
            }
        } else {
            LOGGER.info("Your Password Invalid! Please Try Again!");
        }
        LOGGER.info(STRING);
    }

    private static void updateUser(Scanner adminScanner, Application application) {
        LOGGER.info("Enter user email that needs to be updated: ");
        String oldEmail = adminScanner.nextLine();
        LOGGER.info("Enter user password that needs to be updated: ");
        String oldPassword = adminScanner.nextLine();
        LOGGER.info("Enter your password to confirm updating: ");
        String adminPassword4 = adminScanner.nextLine();
        String oldType = "";
        if (application.newUser.getPassword().equals(adminPassword4)) {
            for (User s : application.login.users) {
                if (oldPassword.equals(s.getPassword()) && oldEmail.equalsIgnoreCase(s.getEmail())) {
                    oldType = s.getType();
                }
            }

            LOGGER.warning("If you want to update value just insert -1 ");
            LOGGER.info("Enter user new email that needs to be updated: ");
            String newEmail = adminScanner.nextLine();
            LOGGER.info("Enter user new password that needs to be updated: ");
            String newPassword = adminScanner.nextLine();
            LOGGER.info("Enter user new type that needs to be updated: ");
            String newType = adminScanner.nextLine();

            if (newPassword.equals("-1")) {
                newPassword = oldPassword;
            }
            if (newType.equals("-1")) {
                newType = oldType;
            }
            if (newEmail.equals("-1")) {
                newEmail = oldEmail;
            }

            if (application.login.updateUser(new User(oldEmail, oldPassword, oldType),
                    new User(newEmail, newPassword, newType))) {
                LOGGER.info("User Updating Successfully");
            } else {
                LOGGER.info("User Updating Failed");
            }
        } else {
            LOGGER.info("Your Password Invalid! Please Try Again!");
        }
        LOGGER.info(STRING);
    }

    private static void mainMenu(Application application,Scanner adminScanner) {
        LOGGER.info("""
            Choose an option:
            1. Add New Category
            2. Edit a Category
            3. Delete a Category
            4. Add New Product
            5. Edit a Product
            6. Delete a Product
            7. Get a Report
            8. Show average ratings and reviews
            9. show products
            10. Exit
                 """);
        int ans = adminScanner.nextInt();
        switch (ans) {
            case 1 -> application.newCatogry();
            case 2 -> application.editCategory();
            case 3 -> application.deleteCategory();
            case 4 -> application.newproduct();
            case 5 -> application.editproduct();
            case 6 -> application.deleteproduct();
            case 7 -> application.makereport();
            case 8 -> application.showreviews();
            case 10 -> LOGGER.info("Exit");
            case 9 -> application.showproducts();
            default -> LOGGER.info(INVALID_INFORMATION_PLEASE_TRY_AGAIN);
        }
        LOGGER.info(STRING);
    }

    private static void signOut() {
        LOGGER.info("Sign Out");
        LOGGER.info(STRING);
    }

    private static void invalidChoice() {
        LOGGER.info("Invalid choice! Please try again.");
        LOGGER.info(STRING);
    }

}
