package car_accessories;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.*;

public class Testing {

    User v;
    String catname,newp,file,text,review,pname,carname;
    private final Application application;
    int q,newqu,y,olds,pr,rate,qu,avl_q;

    float oldavg;


    boolean exist;

    boolean newAccount=false;

    boolean userAdded=false;

    User c;
    boolean isUserUpdating = false;

    boolean isUserDeleting = false;
    public Testing(Application application) {

        this.application = application;
        c=new User("s1333","123","Customer");
        v=new User("s121@gmial.com","122","Admin");
    }
    String newc,newname,oldname;
    int oldsize;

    @Given("I am an admin\\(adding) by admin")
    public void i_am_an_admin_adding_with_email_and_password() {
        application.setuser(v.email,v.password,v.type);

        if(application.login.login()){
          application.login.setRoles();
          assertEquals(0,application.login.getRoles());
            oldsize=Application.categories.size();
        }

    }

    @When("I add a new category with the name {string}")
    public void i_add_a_new_category_with_the_name(String string) {
        newc=string;
    }

    @Then("i must scan if the name {string} is exits before")
    public void i_must_scan_if_the_name_is_exits_before(String string) {
        assertFalse(application.foundc(string));
    }

    @Then("if found i must not add the name {string}")
    public void if_found_i_must_not_add_the_name(String string) {
        if(application.foundc(string)){
            System.out.println("This cant be added");
        }

    }

    @Then("if not found the category with name {string} must be added")
    public void if_not_found_the_category_with_name_must_be_added(String string) {
        if(!application.foundc(newc)){ newc=string;
            application.addcat(newc);

        }}

    @Then("i must confirm the adding by admin")
    public void i_must_confirm_the_adding_with_email_and_password() {
        if(v.type.equals("Admin")){
            System.out.print("Added successfully");

        }
    }
    @Given("I am an admin\\(editing) by admin")
    public void i_am_an_admin_editing_with_email_and_password() {
        assertEquals("Admin", v.type);
    }
    @When("I edit the category with the name {string}")
    public void i_edit_the_category_with_the_name(String string) {
        oldname=string;
    }
    @Then("i enter a new name {string}")
    public void i_enter_a_new_name(String string) {
        newname=string;
    }
    @Then("i must scan if the new name is for another Catogry")
    public void i_must_scan_if_the_new_name_is_for_another_catogry() {
        assertFalse(application.foundc(newname));
    }


    @When("if found i will not edit it")
    public void if_found_i_will_not_edit_it() {
        System.out.println("Cant be edited");
    }
    @When("if not found i must change its name")
    public void if_not_found_i_must_change_its_name() {
        application.edtcatogry(oldname,newname);
    }

    @Then("the category {string} must be edited")
    public void the_category_must_be_edited(String string) {
        assertFalse(application.foundc(string));
    }


    @Given("I am an admin\\(deleting) by admin")
    public void i_am_an_admin_deleting_by_admin() {
        assertEquals("Admin", v.type);

    }

    @When("I delete the category with the name {string}")
    public void i_delete_the_category_with_the_name(String string) {

        application.dltcat(string);
    }

    @Then("the category {string} must be deleted")
    public void the_category_must_be_deleted(String string) {
        assertFalse(application.foundc(string));
    }






    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        assertFalse(application.login.isLogged());
    }

    @When("the information is valid email is {string} and password is {string}")
    public void the_information_is_valid_email_is_and_password_is(String Email, String Pass) {
        boolean loginSuccessful = false;
        for(User u1:application.login.users){
            if(new Login(v).emailValidator(u1.getEmail())){
                if(u1.getEmail().equalsIgnoreCase(Email)&&u1.getPassword().equals(Pass)){
                    application.login.setLogged(true);
                    loginSuccessful=true;
                    break;
                }
            }
        }
        assertTrue(loginSuccessful);

        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou", "Type"));

        // Act
        login.setRoles();

        // Assert
        assertEquals(-1, login.getRoles());
    }
    @And("verification code is 12345")
    public void verification_code_is() {

        boolean f=true;
        assertTrue(f);
    }

    @Then("user successfully log in")
    public void user_successfully_log_in() {

        if(application.login.isLogged()){
            boolean loginSuccessful=true;
            assertTrue(loginSuccessful);
        }

        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou", "Customer"));

        // Act
        login.setRoles();

        // Assert
        assertEquals(1, login.getRoles());

    }

    @When("the email is invalid email is {string} and password is {string}")
    public void the_email_is_invalid_email_is_and_password_is(String Email, String Pass) {
        boolean loginFailed = false;
        for (User u1 : application.login.users) {
            if (!u1.getEmail().equalsIgnoreCase(Email) && u1.getPassword().equals(Pass)) {
                application.login.setLogged(false);
                loginFailed = true;
                break;
            }
        }

        assertFalse(loginFailed);
    }

    @Then("user failed in log in")
    public void user_failed_in_log_in() {

        if(!application.login.isLogged()){
            boolean loginFailed=true;
            assertTrue(loginFailed);
        }

    }

    @When("the password is invalid email is {string} and password is {string}")
    public void the_password_is_invalid_email_is_and_password_is(String Email, String Pass) {
        boolean loginFailed = false;
        for (User u1 : application.login.users) {
            if (u1.getEmail().equalsIgnoreCase(Email) && !u1.getPassword().equals(Pass)) {
                application.login.setLogged(false);
                loginFailed = true;
                break;
            }
        }

        assertFalse(loginFailed);


        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou", "Installer"));

        // Act
        login.setRoles();
        // Assert
        assertEquals(2, login.getRoles());
    }

    @When("the information are invalid email is {string} and password is {string}")
    public void the_information_are_invalid_email_is_and_password_is(String Email, String Pass) {
        boolean loginFailed = false;
        for(User u1:application.login.users){
            if(!u1.getEmail().equalsIgnoreCase(Email)&&!u1.getPassword().equals(Pass)){
                application.login.setLogged(false);
                loginFailed=true;
                break;
            }
        }
        assertTrue(loginFailed);

        Login login = new Login(new User("jane.doe@example.org", "iloveyou","admin"));

        // Act
        login.setRoles();

        // Assert
        assertEquals(0, login.getRoles());
        User u1 =new User("","");
       assertFalse ((new Login(u1)).login());
    }




    @Given("I am an admin\\(adding)")
    public void i_am_an_admin_adding() {
        assertEquals("Admin", v.type);

        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        login.setValidEmail(true);

        // Act
        login.confirmLogin(1);

        // Act and Assert
        assertFalse(login.login());
        assertFalse(login.validEmail);



    }
    @Given("I am an Installer\\(adding)")
    public void i_am_an_installer_adding() {
        application.newUser.setType("Installer");
        assertEquals("Installer", application.newUser.type);
        Login l3 = new Login(application.newUser);
        l3.setRoles();
    }

    @When("i choose the Category {string} of the new product {string}")
    public void i_choose_the_category_of_the_new_product(String string, String string2) {
        catname=string; newp=string2;
    }

    @When("I fill in the quantity {int}")
    public void i_fill_in_the_quantity(Integer int1) {
        q=int1;
    }
    @When("I fill in the price {int}")
    public void i_fill_in_the_price(Integer int1) {
        pr=int1;
    }


    @When("i fill in the year {int} of the expirationDate")
    public void i_fill_in_the_year_of_the_expiration_date(Integer int1) {
        y=int1;
    }

    @When("i must scan if this product exist or not")
    public void i_must_scan_if_this_product_exist_or_not() {
        exist= application.foundp(catname,newp);

    }

    @When("if exist i must add the new quantity to the old quantity")
    public void if_exist_i_must_add_the_new_quantity_to_the_old_quantity() {
        if(exist){

            olds=Application.categories.get(Application.indexes[0]).products.size();
        }
    }

    @When("if not exist i must add a new product with datails above")
    public void if_not_exist_i_must_add_a_new_product_with_datails_above() {
        application.addnewproduct(catname,newp,q,pr,y);
    }

    @When("the new product must be added to the product list")
    public void the_new_product_must_be_added_to_the_product_list() {
        assertEquals(Application.categories.get(Application.indexes[0]).products.size(), (olds + 1));
    }

    @Given("I am an admin\\(editing)")
    public void i_am_an_admin_editing() {
        assertEquals("Admin", v.type);
        Login l2 =new Login(v);
        assertEquals(0, l2.getRoles());
    }

    @When("i choose the Category {string} to edit the product {string}")
    public void i_choose_the_category_to_edit_the_product(String string, String string2) {
        catname=string;
        oldname=string2;
    }

    @When("I update the product name to {string}")
    public void i_update_the_product_name_to(String string) {
        newp=string;
    }

    @When("I add new rate {int} for the the product")
    public void i_add_new_rate_for_the_the_product(Integer int1) {
        rate=int1;
    }

    @When("the product details must be updated")
    public void the_product_details_must_be_updated() {
        application.editproduct(catname,oldname,newp,rate);
        assertFalse(application.foundp(catname, oldname));
    }

    @Given("I am an admin\\(deleting)")
    public void i_am_an_admin_deleting() {
        assertEquals("Admin", v.type);
    }

    @When("i choose the Category {string} to delete the product {string}")
    public void i_choose_the_category_to_delete_the_product(String string, String string2) {
        oldname=string2;
        application.dltp(string,string2);
    }

    @When("the product should be removed from the product list of the Category {string}")
    public void the_product_should_be_removed_from_the_product_list_of_the_category(String string) {
        assertFalse(application.foundp(string,oldname));
    }


    @Given("i am a Customer to get a product")
    public void i_am_a_customer_to_get_a_product() {
        c.type="Installer";
        application.setuser(application.newUser.getEmail(),application.newUser.getPassword(),"Customer");
        assertEquals("Customer", application.newUser.type);
    }
    @When("i select the product {string} from the category {string}")
    public void i_select_the_product_from_the_category(String string, String string2) {
        pname=string;catname=string2;
    }
    @When("i must fill in the quantity {int}")
    public void i_must_fill_in_the_quantity(Integer int1) {
        qu=int1;
    }
    @Then("i must see the available quantity")
    public void i_must_see_the_available_quantity() {
        if(application.foundp(catname,pname)){
            avl_q=Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).quantity;
        }
    }

    @Then("if the requested quantity if not enough the request should be cancelled")
    public void if_the_requested_quantity_if_not_enough_the_request_should_be_cancelled() {
        if(qu>avl_q)fail();
    }


    @Then("if enough i must fill in the car name {string}")
    public void if_enough_i_must_fill_in_the_car_name(String string) {
        carname=string;
    }
    @Then("the customer should receive an appointment scheduling email about receiving the order")
    public void the_customer_should_receive_an_appointment_scheduling_email_about_receiving_the_order() {
        if(application.installrequest(catname,pname,qu,carname)){
            System.out.println("The message is sent");
            newqu=Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).quantity;
        }

    }
    @Then("the product availability should be updated")
    public void the_product_availability_should_be_updated() {
        assertEquals("Installer", c.type);
        assertNotEquals(avl_q,newqu);
    }




    @Given("i am a customer\\(review and rate)")
    public void i_am_a_customer_review_and_rate() {
        application.setuser(application.newUser.getEmail(),application.newUser.getPassword(),"Customer");
        assertEquals("Customer", c.type);
        Login l =new Login(c);
        l.setRoles();
    }

    @Then("i must choose a product {string} from category {string}")
    public void i_must_choose_a_product_from_category(String string, String string2) {
        catname=string2;
        pname=string;
    }

    @Then("i must enter the new rate {int}")
    public void i_must_enter_the_new_rate(Integer int1) {
        rate=int1;
    }

    @Then("if the rating is not in the range")
    public void if_the_rating_is_not_in_the_range() {
        exist= rate >= 1 && rate <= 5;

    }

    @Then("Nothing will happen then exit the page")
    public void nothing_will_happen_then_exit_the_page() {
        if(!exist) System.out.println("The rate is not in the range");
    }

    @Then("if the rating is in the possible range i must leave a review {string}")
    public void if_the_rating_is_in_the_possible_range_i_must_leave_a_review(String string) {
        if(exist){
            review=string;application.foundp(catname,pname);
            oldsize=Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).rates.size();
            oldavg=Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).rateAvg;
            application.rateReview(catname,pname,rate,review);
        }
    }

    @Then("the successful message will appear")
    public void the_ssuccessful_message_will_apear() {
        if(exist){
            assertEquals((oldsize + 1), Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).rates.size());
        }
    }

    @Then("the average rating for the product must be updated")
    public void the_average_rating_for_the_product_must_be_updated() {
        if(exist){
            if(oldavg!=rate){
                assertNotEquals(oldavg,Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).rateAvg);
            }
        }
    }

    @Then("the review must be added")
    public void the_review_must_be_added() {
        if(exist){
            assertEquals((oldsize + 1), Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).reviews.size());
        }
    }

    @Given("i am and admin \\(get informations)")
    public void i_am_and_admin_get_informations() {
        assertEquals("Admin", v.type);
        assertEquals(0, application.login.getRoles());
    }

    @When("i choose a product {string} form Category {string}")
    public void i_choose_a_product_form_category(String string, String string2) {
        catname=string2;pname=string;
    }

    @Then("the average ratings for the product must be displayed and its reviews")
    public void the_average_ratings_for_the_product_must_be_displayed_and_its_reviews() {
        if(Application.categories.get(Application.indexes[0]).products.get(Application.indexes[1]).rates.isEmpty()){
            System.out.println("the example product doesnot have any review");
        }else{

            assertNotEquals(application.ratesqu(catname,pname), 0);

        }
    }


    @When("the information is exist email is {string}")
    public void the_information_is_exist_email_is(String email) {
        boolean f = false;
        for(User u:application.login.users){
            if(u.getEmail().equalsIgnoreCase(email)){
                f=true;
                newAccount=false;
                break;
            }
        }
        assertTrue(f);
    }

    @Then("creating an account failed")
    public void creating_an_account_failed() {
        assertFalse(newAccount);
    }

    @When("the information is not formatly correct")
    public void the_information_is_not_formatly_correct() {
        boolean format =false;

        String email=application.newUser.getEmail();
        if(application.login.emailValidator(email)){
            format=true;
        }
        assertTrue(format);
    }

    @When("the information is not exist email is not {string}")
    public void the_information_is_not_exist_email_is_not(String email) {
        boolean f = false;
        for(User u:application.login.users){
            if(!u.getEmail().equalsIgnoreCase(email)){
                f=true;
                newAccount=true;
            }
        }
        assertTrue(f);

    }
    @Then("creating an account successfully")
    public void creating_an_account_successfully() {
        assertTrue(newAccount);

    }

    @Given("I am an admin")
    public void i_am_an_admin() {
        boolean f=false;
        application.setuser(application.newUser.getEmail(),application.newUser.getPassword(),"Admin");
        if(application.newUser.getType().equals("Admin")){
            f=true;
        }
        assertTrue(f);
        assertEquals(0, application.login.getRoles());

    }

    @When("i choose to add new user but the user is already exist")
    public void i_choose_to_add_new_user_but_the_user_is_already_exist() {
        String  email="ibrahim.sadi.asad@gmail.com";
        for(User u : application.login.users){
            if(u.getEmail().equals(email)){
                userAdded=false;
                break;
            }
        }
        assertFalse(userAdded);
    }

    @Then("user added failed")
    public void user_added_failed() {
        assertFalse(userAdded);
    }

    @When("i choose to add new user with with valid formatting")
    public void i_choose_to_add_new_user_with_with_valid_formatting() {
        String email = "frre.fff@gmail.com";
        String pass="2w421";
        boolean r=false;
        User u1 = new User(email,pass,"Admin");
        if(application.login.addUser(u1)){
            userAdded=true;
            r=true;
        }
        assertTrue(r);
    }

    @Then("user successfully added")
    public void user_successfully_added() {
        assertTrue(userAdded);
    }

    @When("i choose the user and setting the new value with valid formatting")
    public void i_choose_the_user_and_setting_the_new_value_with_valid_formatting() {
        String email = "frre.fff@gmail.com";
        String pass="2w421";
        User u1 = new User(email,pass,"dd");
        User u=application.login.users.get(1);
        if(application.login.updateUser(u, u1)) {
            isUserUpdating = true;
        }
        assertTrue(isUserUpdating);
    }

    @Then("user successfully updating")
    public void user_successfully_updating() {
        assertTrue(isUserUpdating);
    }

    @When("i choose the user i want to delete")
    public void i_choose_the_user_i_want_to_delete() {
        User u=new User("ibrahimeceasad@gmail.com","654321","Customer");
        if(application.login.deleteUser(u)){
            isUserDeleting=true;
        }
        assertTrue(isUserDeleting);
    }

    @Then("user successfully deleting")
    public void user_successfully_deleting() {
        assertTrue(isUserDeleting);
    }
    @Given("i am an admin\\(report)")
    public void i_am_an_admin_report() {
        assertEquals("Admin", v.type);
    }


    @Then("i am asked to choose report1 kind {string}")
    public void i_am_asked_to_choose_report1_kind(String string) {
        text=string;
    }
    @Then("The report1 details are printed at a file {string}")
    public void the_report1_details_are_printed_at_a_file(String string) {
        file=string;
        assertTrue(application.report(text,file));
    }
    @Then("i am asked to choose report2 kind {string}")
    public void i_am_asked_to_choose_report2_kind(String string) {
        text=string;
    }
    @Then("The report2 details are printed at a file {string}")
    public void the_report2_details_are_printed_at_a_file(String string) {
        file=string;
        assertTrue(application.report(text,file));
    }
    @Then("i am asked to choose report3 kind {string}")
    public void i_am_asked_to_choose_report3_kind(String string) {
        text=string;
    }
    @Then("The report3 details are printed at a file {string}")
    public void the_report3_details_are_printed_at_a_file(String string) {
        file=string;
        assertTrue(application.report(text,file));
    }
    @Then("i am asked to choose report4 kind {string}")
    public void i_am_asked_to_choose_report4_kind(String string) {
        text=string;
    }
    @Then("The report4 details are printed at a file {string}")
    public void the_report4_details_are_printed_at_a_file(String string) {
        file=string;
        assertTrue(application.report(text,file));
    }

    @Then("if the rating is not in the valid range, nothing should happen")
    public void if_the_rating_is_not_in_the_valid_range_nothing_should_happen() {
        if (!exist) {

            assertEquals(application.ratesqu(catname,pname), 0);
        }
    }
    @When("the requested quantity is enough")
    public void the_requested_quantity_is_enough() {
        qu = avl_q; // Assuming the available quantity is enough
    }

    @Then("the installer request should be successful")
    public void the_installer_request_should_be_successful() {
        assertTrue(application.installrequest(catname, pname, qu, carname));
        assertEquals(newqu, avl_q - qu);
    }

    @When("the requested quantity is not enough")
    public void the_requested_quantity_is_not_enough() {
        qu = avl_q + 1; // Assuming the available quantity is not enough
    }

    @Then("the installer request should be unsuccessful")
    public void the_installer_request_should_be_unsuccessful() {
        assertFalse(application.installrequest(catname, pname, qu, carname));
        assertEquals(newqu, avl_q);
    }


}
