package car_accessories;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class MainClass {
    private static final String CATEGORY ="the Category ";
    private static final String ADMIN_STRING ="Admin";
    private static final String CUSTOMER_STRING ="Customer";
    private static final String EXIT_STRING =". Exit";
    private static final String INSTALLER_STRING ="Installer";
    private static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    private static final String NEXT_TIME ="Enter a valid value in the next time\n";
    private static final String TABS ="     ";
    public static boolean comparePasswords(String inputPassword, String hashedPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(inputPassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                hexHash.append(String.format("%02x", b));
            }

            return hexHash.toString().equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {


            return false;
        }
    }
    private static final String INVALID_INFORMATION_PLEASE_TRY_AGAIN = "Invalid information! Please try again.";
    private static final String STRING = "********************************************************************";
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    static {

        System.setProperty("mail.debug", "false");


        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(Level.OFF);
            }
        }
    }

    public static void main(String[] arg) {

        Application signInApplication = new Application();
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
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(consoleHandler);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred during logger configuration", e);
        }


        Scanner scanner = new Scanner(System.in);

        LOGGER.info("TURBOTWEAK ACCESSORIES");

        int authen = -1;

        do {
            try {
                LOGGER.info("1-Sign-up \n2-Sign-in \n3-Exit");
                authen = scanner.nextInt();
                scanner.nextLine();
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
        String welcomeMsg="WELCOME TO TURBOTWEAK ACCESSORIE";
        switch (roles) {
            case 0 -> {
                LOGGER.info(welcomeMsg);
                signInApplication.setuser(signInEmail, signInPassword, ADMIN_STRING);
                adminDashboard(scanner, signInApplication);
            }
            case 1 -> {
                signInApplication.setuser(signInEmail, signInPassword, CUSTOMER_STRING);
                LOGGER.info(welcomeMsg);
                handleCustomerOptions(scanner, signInApplication);
            }
            default -> {
                signInApplication.setuser(signInEmail, signInPassword, INSTALLER_STRING);
                LOGGER.info(welcomeMsg);
                handleInstallerOptions(scanner, signInApplication);
            }
        }
    }

    private static void handleCustomerOptions(Scanner scanner, Application signInApplication) {
        int select;
        String options = """
        Choose an option:
        1. Order a product
        2. Review And Rate A Product
        3. Show all products
        4. Send a request
        5. Exit
        """;

        while (true) {
            LOGGER.info(options);
            select = scanner.nextInt();

            if (select == 1) {
                orderproduct(signInApplication);
            } else if (select == 2) {
                newrate(signInApplication);
            }
            else if(select ==3){
                showallproducts(signInApplication);
            } else if (select ==4) {
                makerequest(signInApplication);
            } else if(select ==5){
                break;
            }else {
                LOGGER.info("Choose a right option\n");
            }
        }
    }

    private static void handleInstallerOptions(Scanner scanner, Application signInApplication) {
        int select;

        while (true) {
            LOGGER.info("Choose an option:\n1. Install a Product\n2. Exit");
            select = scanner.nextInt();

            if (select == 1) {
                addnewpro(signInApplication);
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
    private static final String NO_CHANGE = "-1";
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
            boolean scan=comparePasswords(NO_CHANGE,newPassword);

            if (scan)  {
                newPassword = oldPassword;
            }
            if (NO_CHANGE.equals(newType)) {
                newType = oldType;
            }
            if (NO_CHANGE.equals(newEmail)) {
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

    private static void mainMenu(Application application, Scanner adminScanner) {
        int ans;
        do {
            try {
                LOGGER.info("""
                    Choose an option:
                    1. Add New Category
                    2. Edit a Category
                    3. Delete a Category
                    4. Edit a Product
                    5. Delete a Product
                    6. Get a Report
                    7. Show average ratings and reviews
                    8. Show products
                    9. Exit
                    """);

                ans = adminScanner.nextInt();

                switch (ans) {
                    case 1 -> addnewcat(application);
                    case 2 -> editcat(application);
                    case 3 -> deletecat(application);
                    case 4 -> editpro(application);
                    case 5 -> deletepro(application);
                    case 6 -> newreport(application);
                    case 7 -> getreviews(application);
                    case 8 -> showallproducts(application);
                    case 9 -> LOGGER.info("Exit");
                    default -> invalidChoice();

                }

                LOGGER.info(STRING);
                adminScanner.nextLine();
            } catch (InputMismatchException e) {
                LOGGER.warning("Invalid input. Please enter a valid option.");
                adminScanner.nextLine();
                ans = -1;
            }
        } while (ans != 9);
    }


    private static void signOut() {
        LOGGER.info("Sign Out");
        LOGGER.info(STRING);
    }

    private static void invalidChoice() {
        LOGGER.info("Invalid choice! Please try again.");
        LOGGER.info(STRING);
    }
    private static void addnewcat(Application application) {
        try {
            if (application.newUser.type.equals(ADMIN_STRING)) {
                LOGGER.info("What is the name of the Category?");
                String m = application.scanner.nextLine();

                if (application.foundc(m)) {
                    String ygy1 = CATEGORY + m + " already exists";
                    LOGGER.info(ygy1);
                } else {
                    addnewCategoryConfirmation(m, application);
                }
            } else {
                LOGGER.info("Only admins can add Categories");
            }
        } catch (Exception e) {
            LOGGER.info("An error occurred while adding a new category. Please try again.");
        }
    }
    private static void editcat(Application application) {
        try {
            int i;
            if (application.newUser.type.equals(ADMIN_STRING)) {
                if (Application.categories.isEmpty()) {
                    LOGGER.info("There is no categories in the system");
                } else {
                    StringBuilder f = new StringBuilder();
                    for (i = 0; i < Application.categories.size(); i++) {
                        f.append(i + 1).append(". ").append(Application.categories.get(i).name).append("\n");
                    }
                    String exitOption = (i + 1) + EXIT_STRING;
                    f.append(exitOption);

                    editCategoryHelper(application, i, f);
                }
            } else {
                LOGGER.info("Only admins can Edit Categories\n");
            }
        } catch (Exception e) {
            LOGGER.info("An error occurred while editing a category. Please try again.");
        }
    }

    private static void editCategoryHelper(Application application, int i, StringBuilder f) {
        try {
            String ygy1 = "Choose a Category\n" + f;
            LOGGER.info(ygy1);

            int select = application.scanner.nextInt();
            application.scanner.nextLine();
            if (select < 1) {
                LOGGER.info(INVALID_INPUT_MESSAGE);
            } else if (select == (i + 1)) {
                mainMenu(application, application.scanner);
            } else if (select > (i + 1)) {
                LOGGER.info(INVALID_INPUT_MESSAGE);
            } else {
                select--;
                LOGGER.info("What is the new name of the Category?");
                String rename = application.scanner.nextLine();
                if (application.foundc(rename)) {
                    LOGGER.info("This new name is for another Category");
                } else {
                    boolean response = false;
                    LOGGER.info("Are you sure you want to continue?\n1. yes");
                    int answer = application.scanner.nextInt();
                    application.scanner.nextLine();
                    if (answer == 1) response = true;

                    if (response) {
                        application.edtcatogry(Application.categories.get(select).name, rename);
                        LOGGER.info("The Name is edited successfully\n");
                    } else {
                        LOGGER.info("the Category is not Edited\n");
                    }
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.info(INVALID_INPUT_MESSAGE);
        }
    }
    private static void deletepro(Application application) {
        try {
            if (!application.newUser.type.equals(ADMIN_STRING)) {
                LOGGER.info("Only admins can delete products\n");
                return;
            }

            String ygy1 = "Choose a Category to delete a product\n" + showallcatogries();
            LOGGER.info(ygy1);

            int cselect = application.scanner.nextInt();
            application.scanner.nextLine();
            cselect--;
            if (cselect == i) {
                mainMenu(application, application.scanner);
            } else if (Application.categories.get(cselect).products.isEmpty()) {
                LOGGER.info("There is no products to remove\n");
                return;
            }
            String catname = Application.categories.get(cselect).name;
            String ygy4 = "Choose a product to delete\n" + getallproducts(catname, application);
            LOGGER.info(ygy4);
            String exitOption = (j + 2) + EXIT_STRING;
            LOGGER.info(exitOption);
            int pselect = application.scanner.nextInt();
            application.scanner.nextLine();
            pselect--;

            if (pselect == (j + 1)) {
                deletepro(application);
            } else if (pselect > (j + 1)) {
                LOGGER.info(INVALID_INPUT_MESSAGE);
                deletepro(application);
            } else {
                Application.categories.get(cselect).products.remove(pselect);
                LOGGER.info("The product has been deleted");
            }
        } catch (Exception e) {
            LOGGER.info(INVALID_INPUT_MESSAGE);
        }
    }
    private static void addnewpro(Application application) {
        try {
            if (!application.newUser.type.equals(INSTALLER_STRING)) {
                LOGGER.info("Only Installers can add products\n");
                return;
            }

            String ygy1 = "Choose a Category to add a new product\n" + showallcatogries();
            LOGGER.info(ygy1);

            int select = application.scanner.nextInt();
            application.scanner.nextLine();
            select--;
            String catname = Application.categories.get(select).name;
            LOGGER.info("What is the name of the new product?\n");
            String pname = application.scanner.nextLine();
            LOGGER.info("What is the quantity of the new product?\n");

            int quantity = application.scanner.nextInt();
            application.scanner.nextLine();
            if (quantity < 1) {
                throw new InputMismatchException();
            }
            if (application.foundp(catname, pname)) {
                application.addnewproduct(catname, pname, quantity, 0, 0);
                String ygy2 = "The quantity is added to the exist product " + pname + "\n";
                LOGGER.info(ygy2);
                return;
            }
            LOGGER.info("what is the price of this new product?\n");

            int price = application.scanner.nextInt();
            application.scanner.nextLine();
            if (price < 1) {
                LOGGER.info(INVALID_INPUT_MESSAGE);
                return;
            }
            LOGGER.info("Which year this product will be expired?\n");

            int year = application.scanner.nextInt();
            application.scanner.nextLine();
            if (year <= LocalDate.now().getYear()) {
                LOGGER.info(INVALID_INPUT_MESSAGE);
                return;
            }
            application.addnewproduct(catname, pname, quantity, price, year);
            LOGGER.info("The product is added Successfully\n");

        } catch (Exception e) {
            LOGGER.info(INVALID_INPUT_MESSAGE);
        }
    }

    private static void editpro(Application application) {
        try {
            if (!application.newUser.type.equals(ADMIN_STRING)) {
                LOGGER.info("Only admins can edit products\n");
                return;
            }

            String ygy1 = "Choose a Category to edit\n" + showallcatogries();
            LOGGER.info(ygy1);

            int cselect = application.scanner.nextInt();
            application.scanner.nextLine();
            cselect--;
            if (Application.categories.get(cselect).products.isEmpty()) {
                LOGGER.info("There is no products to edit\n");
                return;
            }
            String catname = Application.categories.get(cselect).name;
            String ygy3 = "Choose a product to edit\n" + getallproducts(catname, application);
            LOGGER.info(ygy3);

            int pselect = application.scanner.nextInt();
            application.scanner.nextLine();
            pselect--;
            String old = Application.categories.get(cselect).products.get(pselect).name;
            String ygy2 = "What is the new name of the product " + old + "?\n";
            LOGGER.info(ygy2);
            String newname = application.scanner.nextLine();

            for (int i = 0; i < Application.categories.get(cselect).products.size(); i++) {
                if (i == pselect) continue;
                if (newname.equals(Application.categories.get(cselect).products.get(i).name)) {
                    LOGGER.info(INVALID_INPUT_MESSAGE);
                    return;
                }
            }
            String ygy4 = "What is the new price of the product " + old + "?\n";
            LOGGER.info(ygy4);

            int newprice = application.scanner.nextInt();
            application.scanner.nextLine();
            if (newprice < 1) {
                LOGGER.info(INVALID_INPUT_MESSAGE);
                return;
            }
            if (newname.isEmpty()) {
                application.editproduct(catname, old, old, newprice);
            } else {
                application.editproduct(catname, old, newname, newprice);
            }
            LOGGER.info("The product is updated successfully\n");
        } catch (Exception e) {
            LOGGER.info(INVALID_INPUT_MESSAGE);
        }
    }

    public static class InvalidOptionException extends Exception {
        public InvalidOptionException() {
            super("Invalid option selected.");
        }
    }

    private static void newreport(Application application) {
        if (application.newUser.getType().equals(ADMIN_STRING)) {
            try {
                LOGGER.info("What is the name of the file?");
                String file = application.scanner.next();

                LOGGER.info("""
                        Choose a report
                        1. Sales report
                        2. Product rates report
                        3. Category products report
                        4. Rates and reviews report
                        5. Exit
                        """);

                int c = application.scanner.nextInt();
                application.scanner.nextLine();


                switch (c) {
                    case 1 -> application.report("Sales", file);
                    case 2 -> application.report("Product rates", file);
                    case 3 -> application.report("Category products", file);
                    case 4 -> application.report("rates and reviews", file);
                    case 5 -> mainMenu(application,application.scanner);
                    default -> invalidChoice();
                }
            } catch (Exception e) {
                LOGGER.info(NEXT_TIME);
            }
        }

    }
    private static void getreviews(Application application) {
        try {
            if (!application.newUser.type.equals(ADMIN_STRING)) {
                LOGGER.info("Only Admins can get information\n");
                return;
            }

            String ygy1 = "Choose a Category to get information about a product\n" + showallcatogries();
            LOGGER.info(ygy1);

            int cselect = application.scanner.nextInt();
            application.scanner.nextLine();
            cselect--;
            if (Application.categories.get(cselect).products.isEmpty()) {
                LOGGER.info("There are no products to get information about\n");
                return;
            }

            String catname = Application.categories.get(cselect).name;
            String ygy2 = "Choose a product to get information about\n" + getallproducts(catname, application);
            LOGGER.info(ygy2);

            int pselect = application.scanner.nextInt();
            application.scanner.nextLine();
            pselect--;
            String pname = Application.categories.get(cselect).products.get(pselect).name;

            String message = reviews(catname, pname, application) + "\n";
            if (message.isEmpty()) {
                LOGGER.info("The chosen product doesn't have any ratings or reviews\n");
                return;
            }

            LOGGER.info(message);
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }
    private static void showallproducts(Application application) {
        try {
            String ygh = "Choose a Category to see its products\n" + showallcatogries();

            LOGGER.info(ygh);

            int x = application.scanner.nextInt();
            application.scanner.nextLine();
            x--;

            String pop = getallproducts(Application.categories.get(x).name, application);
            LOGGER.info(pop);

        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }
    private static void deletecat(Application application) {
        try {
            if (!application.newUser.type.equals(ADMIN_STRING)) {
                LOGGER.info("Only admins can delete Categories\n");
                return;
            }

            if (Application.categories.isEmpty()) {
                LOGGER.info("There are no categories in the system\n");
            } else {
                StringBuilder f = new StringBuilder();
                int i;
                for (i = 0; i < Application.categories.size(); i++) {
                    f.append(i + 1).append(". ").append(Application.categories.get(i).name).append("\n");
                }

                String exitOption = (i + 1) + EXIT_STRING;
                f.append(exitOption);

                int select = chooseAndDeleteCategory(application, f.toString());

                if (select < 1 || select > (Application.categories.size() + 1)) {
                    LOGGER.info(INVALID_INPUT_MESSAGE);
                } else if (select == (Application.categories.size() + 1)) {
                    mainMenu(application, application.scanner);
                } else {
                    select--;

                    int response = 9;
                    while (true) {
                        LOGGER.info("Are you sure you want to continue?\n1. yes / 2. no\n");
                        int answer = application.scanner.nextInt();
                        application.scanner.nextLine();
                        if (answer == 1 || answer == 2) {
                            response = answer;
                            break;
                        } else {
                            LOGGER.info(INVALID_INPUT_MESSAGE);
                        }
                    }
                    if (response == 1) {
                        application.dltcat(Application.categories.get(select).name);
                        LOGGER.info("The Category is deleted\n");
                    } else {
                        LOGGER.info("The Category is not deleted\n");
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }

    private static int chooseAndDeleteCategory(Application application, String categoryList) {
        try {
            String categoryLists = "Choose a Category\n" + categoryList;
            LOGGER.info(categoryLists);
            int select = application.scanner.nextInt();
            application.scanner.nextLine();
            return select;
        } catch (NumberFormatException e) {
            LOGGER.info(INVALID_INPUT_MESSAGE);
            return -1; // Indicate an invalid input
        }
    }


    static int i;
    private static String showallcatogries() {
        try {
            StringBuilder f = new StringBuilder();
            int i;

            for (i = 0; i < Application.categories.size(); i++) {
                f.append(i + 1).append(". ").append(Application.categories.get(i).name).append("\n");
            }

            String exitOption = (i + 1) + EXIT_STRING;
            f.append(exitOption);

            return f.toString();
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
            return "";
        }
    }
    private static void newrate(Application application) {
        try {
            if (!application.newUser.type.equals(CUSTOMER_STRING)) {
                LOGGER.info("Only customers can rate and review\n");
                return;
            }

            String ygy1 = "Choose a Category to rate and review a product\n" + showallcatogries();
            LOGGER.info(ygy1);

            int cselect = application.scanner.nextInt();
            application.scanner.nextLine();
            cselect--;
            if (Application.categories.get(cselect).products.isEmpty()) {
                LOGGER.info("There are no products to rate or review in this Category\n");
                return;
            }

            String catname = Application.categories.get(cselect).name;
            String ygy2 = "Choose a product to rate and review\n" + getallproducts(catname, application);
            LOGGER.info(ygy2);

            int pselect = application.scanner.nextInt();
            application.scanner.nextLine();
            pselect--;
            String pname = Application.categories.get(cselect).products.get(pselect).name;

            LOGGER.info("How much is the adding rate? 1-5");

            int rate = application.scanner.nextInt();
            application.scanner.nextLine();
            if (rate < 1 || rate > 5) {
                throw new InvalidOptionException();
            }

            LOGGER.info("Write a new review");
            String review = application.scanner.nextLine();
            if (!review.isEmpty()) {
                application.rateReview(catname, pname, rate, review);
                LOGGER.info("The new rate is added, and the new review is added\n");
            } else {
                LOGGER.info(NEXT_TIME);
            }
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }

    private static void orderproduct(Application application) {
        try {
            if (!application.newUser.type.equals(CUSTOMER_STRING)) {
                LOGGER.info("Only customers can make an installation request\n");
                return;
            }

            String ygy1 = "Choose a Category to request a product\n" + showallcatogries();
            LOGGER.info(ygy1);

            int cselect = application.scanner.nextInt();
            application.scanner.nextLine();
            cselect--;
            if (Application.categories.get(cselect).products.isEmpty()) {
                LOGGER.info("There are no products to request\n");
                return;
            }

            String catname = mydup(cselect);
            String ygy5 = mydup2(catname, application);
            LOGGER.info(ygy5);

            int pselect = application.scanner.nextInt();
            application.scanner.nextLine();
            pselect--;
            String pname = Application.categories.get(cselect).products.get(pselect).name;

            if (Application.categories.get(cselect).products.get(pselect).quantity == 0) {
                LOGGER.info("The product is not enough to buy!\n");
                return;
            }

            LOGGER.info("Select the quantity");

            int qu = application.scanner.nextInt();
            application.scanner.nextLine();
            LOGGER.info("What is the car name?");
            String car = application.scanner.nextLine();

            if (application.installrequest(catname, pname, qu, car)) {
                int fee = qu * Application.categories.get(cselect).products.get(pselect).price;
                String ygy9 = "The request will be installed successfully\n" +
                        "Your FEE is " + qu * Application.categories.get(cselect).products.get(pselect).price + "\n";
                LOGGER.info(ygy9);

                if (Application.categories.get(cselect).products.get(pselect).quantity == 0) {
                    Application.categories.get(cselect).products.remove(pselect);
                }

                final SecureRandom random = new SecureRandom();
                int rValue = random.nextInt(5) + 1;
                LocalDate ship = LocalDate.now().plusDays(rValue);
                String message = "Your order has been received and is currently being processed. " +
                        " The order is going to be shipped after." + ship +
                        ". Thank you for shopping with us!\n" +
                        "Best regards,";
                Application.sales.add(new Sales(catname, pname, fee, qu, LocalDate.now(), ship, car));

                String recipientEmail = application.newUser.getEmail();
                String subject = "Installation Request";

                Mailing m1 = new Mailing(recipientEmail);
                m1.sendEmail(subject, message);
            }
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }

    static int j;
    private static String getallproducts(String catname, Application application) {
        try {
            StringBuilder f = new StringBuilder();
            if (application.foundc(catname)) {
                if (Application.categories.get(Application.indexes[0]).products.isEmpty()) {
                    return "There are no products";
                }
                f.append("#. name     quantity     price     rate\n");
                for (int j = 0; j < Application.categories.get(Application.indexes[0]).products.size(); j++) {
                    int c = j + 1;
                    if (j == Application.categories.get(Application.indexes[0]).products.size() - 1) {
                        f.append(c).append(". ").append(Application.categories.get(Application.indexes[0]).products.get(j).name).append(TABS).append(Application.categories.get(Application.indexes[0]).products.get(j).quantity).append(TABS).append(Application.categories.get(Application.indexes[0]).products.get(j).price).append(TABS).append(Application.categories.get(Application.indexes[0]).products.get(j).rateAvg);
                        break;
                    }
                    f.append(c).append(". ").append(Application.categories.get(Application.indexes[0]).products.get(j).name).append(TABS).append(Application.categories.get(Application.indexes[0]).products.get(j).quantity).append(TABS).append(Application.categories.get(Application.indexes[0]).products.get(j).price).append(TABS).append(Application.categories.get(Application.indexes[0]).products.get(j).rateAvg).append("\n");
                }
            } else {
                f.append("The Category is empty");
            }

            return f.toString();
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
            return "";
        }
    }

    private static void makerequest(Application application) {
        try {
            if (!application.newUser.type.equals(CUSTOMER_STRING)) {
                LOGGER.info("Only customers can make an installation request\n");
                return;
            }

            String ygy1 = "Choose a Category to request a product\n" + showallcatogries();
            LOGGER.info(ygy1);

            int cselect = application.scanner.nextInt();
            application.scanner.nextLine();
            cselect--;

            String catname = Application.categories.get(cselect).name;
            String ygy5 = "Choose a product to request\n" + getallproducts(catname, application);
            LOGGER.info(ygy5);

            int pselect = application.scanner.nextInt();
            application.scanner.nextLine();
            pselect--;
            String pname = Application.categories.get(cselect).products.get(pselect).name;

            LOGGER.info("Select the quantity");
            int qu = application.scanner.nextInt();
            application.scanner.nextLine();

            LOGGER.info("What is the car name?");
            String car = application.scanner.nextLine();

            char t = 2;
            String message = "Please provide me this request as possible \n" + t +
                    catname + "," + pname + "," + qu + "," + car;

            String recipientEmail = application.newUser.getEmail();
            String subject = "Customer Request";

            Mailing m1 = new Mailing(recipientEmail);
            m1.sendEmail(subject, message);
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }

    private static void addnewCategoryConfirmation(String categoryName, Application application) {
        try {
            if (application.newUser.type.equals(ADMIN_STRING)) {
                String m = categoryName;
                if (application.foundc(m)) {
                    String ygy1 = CATEGORY + m + " already exists";
                    LOGGER.info(ygy1);
                } else {
                    boolean response = false;

                    LOGGER.info("Are you sure you want to continue?\n1. yes ");
                    int answer = application.scanner.nextInt();
                    application.scanner.nextLine();

                    if (answer == 1) response = true;

                    if (response) {
                        String ygy1 = "You added the Category " + m;
                        LOGGER.info(ygy1);
                        application.addcat(m);
                    }
                }
            } else {
                LOGGER.info("Only admins can add Categories");
            }
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }


    private static String reviews(String catname, String pname, Application application) {
        try {
            if (application.foundp(catname, pname)) {
                StringBuilder f = new StringBuilder();
                int c;
                for (int i = 0; i < Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).reviews.size(); i++) {
                    c = i + 1;
                    f.append("Rate number ").append(c).append(" :").append(Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).rates.get(i));
                    f.append("\nReview number ").append(c).append(" :").append(Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).reviews.get(i)).append("\n\n\n");
                }
                f.append(" The Average Rate is: ").append(Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).rateAvg);
                return f.toString();
            }
            return "";
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
            return "";
        }
    }

    private static String mydup(int cselect) {
        try {
            return Application.categories.get(cselect).name;
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
            return "";
        }
    }

    private static String mydup2(String catname, Application application) {
        try {
            return "Choose a product to request\n" + getallproducts(catname, application);
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
            return "";
        }
    }


}


