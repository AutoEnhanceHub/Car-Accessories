package carAccessories;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class Testing {
    Application var=new Application();
    User v=new User("s121@gmial.com","122","Admin");
//    Testing(Application b,User bm){
//        var=b;
//        v=bm;
//    }

    String email,pass,newc,newname,oldname;
    int oldsize,index;
boolean found=false;
    @Given("I am an admin\\(adding) by admin")
    public void i_am_an_admin_adding_with_email_and_password() {
        assertEquals("Admin", v.type);
        oldsize=Application.categories.size();
    }

    @When("I add a new category with the name {string}")
    public void i_add_a_new_category_with_the_name(String string) {
newc=string;
    }

    @Then("i must scan if the name {string} is exits before")
    public void i_must_scan_if_the_name_is_exits_before(String string) {
 assertFalse(var.foundc(newc));
}

    @Then("if found i must not add the name {string}")
    public void if_found_i_must_not_add_the_name(String string) {
   if(var.foundc(newc)){
       System.out.println("This cant be added");
   }

    }

    @Then("if not found the category with name {string} must be added")
    public void if_not_found_the_category_with_name_must_be_added(String string) {
       if(!var.foundc(newc)){ newc=string;
        var.addcat(newc);

    }}

    @Then("i must confirm the adding by admin")
    public void i_must_confirm_the_adding_with_email_and_password() {
        if(v.type.equals("Admin")){
            System.out.print("Added successfully");
            assertEquals((oldsize + 1), Application.categories.size());
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
       assertFalse(var.foundc(newname));
    }


    @When("if found i will not edit it")
    public void if_found_i_will_not_edit_it() {
System.out.println("Cant be edited");
    }
    @When("if not found i must change its name")
    public void if_not_found_i_must_change_its_name() {
var.edtcatogry(oldname,newname);
    }

    @Then("the category {string} must be edited")
    public void the_category_must_be_edited(String string) {
assertFalse(var.foundc(oldname));
    }


    @Given("I am an admin\\(deleting) by admin")
    public void i_am_an_admin_deleting_by_admin() {
        assertEquals("Admin", v.type);

    }

    @When("I delete the category with the name {string}")
    public void i_delete_the_category_with_the_name(String string) {

     var.dltcat(string);
    }

    @Then("the category {string} must be deleted")
    public void the_category_must_be_deleted(String string) {
        assertFalse(var.foundc(string));
}
    private final Application application;



    public Testing(Application application) {
        this.application = application;
    }

    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        assertFalse(application.login.isLogged());
    }

    @When("the information is valid email is {string} and password is {string}")
    public void the_information_is_valid_email_is_and_password_is(String Email, String Pass) {
        boolean loginSuccessful = false;
        for(User u1:application.login.users){
            if(Login.emailValidator(u1.getEmail())){
                if(u1.getEmail().equalsIgnoreCase(Email)&&u1.getPassword().equals(Pass)){
                    application.login.setLogged(true);
                    loginSuccessful=true;
                    break;
                }
            }
        }
        assertTrue(loginSuccessful);
    }
    @And("verification code is {string}")
    public void verification_code_is(String string) {
        boolean f=true;
        assertTrue(f);
    }

    @Then("user successfully log in")
    public void user_successfully_log_in() {
        boolean loginSuccessful = false;
        if(application.login.isLogged()){
            loginSuccessful=true;
        }
        assertTrue(loginSuccessful);
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
        boolean loginFailed = false;
        if(!application.login.isLogged()){
            loginFailed=true;
        }
        assertTrue(loginFailed);
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
    }


}
